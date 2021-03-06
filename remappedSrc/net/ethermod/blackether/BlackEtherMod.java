package net.ethermod.blackether;

import net.ethermod.blackether.blocks.BlockOfEther;
import net.ethermod.blackether.blocks.DarkGrassBlock;
import net.ethermod.blackether.blocks.EtherOreBlock;
import net.ethermod.blackether.enums.CustomArmorMaterial;
import net.ethermod.blackether.features.OnyxFortFeature;
import net.ethermod.blackether.gen.OnyxFortGenerator;
import net.ethermod.blackether.items.*;
import net.ethermod.blackether.utils.PropertyManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.fabricmc.fabric.impl.content.registry.FuelRegistryImpl;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BlackEtherMod implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger(BlackEtherMod.class);
    public static final PropertyManager PROPERTIES = new PropertyManager();
    public static final String MODID = "ethermod";
    public static final Block ETHER_ORE_BLOCK = new EtherOreBlock(FabricBlockSettings.of(Material.METAL,
            MapColor.BRIGHT_RED).ticksRandomly().lightLevel(9).strength(5.0F, 6.0F));
    public static final DarkGrassBlock DARK_GRASS = new DarkGrassBlock(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK).strength(0.6f));
    public static final StructurePieceType ONYXFORT_PIECE = OnyxFortGenerator.Piece::new;
    private static final StructureFeature<DefaultFeatureConfig> ONYXFORT_STRUCTURE = new OnyxFortFeature(DefaultFeatureConfig.CODEC);
    private static final StructureFeature<DefaultFeatureConfig> ONYXFORT_STRUCTURE_UNRARE = new OnyxFortFeature(DefaultFeatureConfig.CODEC);
    private static final ConfiguredStructureFeature<?, ?> ONYXFORT_CONFIGURED = ONYXFORT_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);
    private static final ConfiguredStructureFeature<?, ?> ONYXFORT_CONFIGURED_UNRARE = ONYXFORT_STRUCTURE_UNRARE.configure(DefaultFeatureConfig.DEFAULT);
    public static final RegistryKey<Biome> ONYX_BIOME_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MODID, "onyx_biome"));
    public static final Item ONYX_ORE = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    private static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> ONYXBIOME_SURFACE_BUILDER = SurfaceBuilder.DEFAULT
            .withConfig(new TernarySurfaceConfig(
                    DARK_GRASS.getDefaultState(), Blocks.BASALT.getDefaultState(),
                    ETHER_ORE_BLOCK.getDefaultState()
            ));

    public static final ItemGroup BLACKETHERMOD_GROUP = FabricItemGroupBuilder.create(
            new Identifier(MODID, "ethermod_group"))
            .icon(() -> new ItemStack(RegisterItems.ONYX_APPLE))
            .build();

    private static final Biome ONYXBIOME = createOnyxBiome();
    private static ConfiguredFeature<?, ?> ORE_ETHER_OVERWORLD = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    ETHER_ORE_BLOCK.getDefaultState(),
                    15)) // vein size
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
                    0,
                    0,
                    64)))
            .spreadHorizontally()
            .repeat(10); // number of veins per chunk

    private static ConfiguredFeature<?, ?> ORE_ETHER_ONYXBIOME = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    ETHER_ORE_BLOCK.getDefaultState(),
                    25)) // vein size
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
                    0,
                    0,
                    64)))
            .spreadHorizontally()
            .repeat(40); // number of veins per chunk

    private boolean spawnOnyx;
    private boolean onyxBiomeEnabled;
    private int onyxSpawnChance;

    @Override
    public void onInitialize() {
        initProperties();
        RegisterItems.register();
        setupBiomes();
    }

    private void setupBiomes() {
        //Load overworld ether ore feature
        RegistryKey<ConfiguredFeature<?, ?>> oreEtherOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier(MODID, "ore_ether_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreEtherOverworld.getValue(), ORE_ETHER_OVERWORLD);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreEtherOverworld);

        //Load onyxbiome extra ore feature
        RegistryKey<ConfiguredFeature<?, ?>> oreEtherOnyxBiome = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier(MODID, "ore_ether_onyxbiome"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreEtherOnyxBiome.getValue(), ORE_ETHER_ONYXBIOME);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ONYX_BIOME_KEY), GenerationStep.Feature.UNDERGROUND_ORES, oreEtherOnyxBiome);

        //Register surface builder
        Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier(MODID, "onyx_biome"), ONYXBIOME_SURFACE_BUILDER);
        Registry.register(BuiltinRegistries.BIOME, ONYX_BIOME_KEY.getValue(), ONYXBIOME);

        //Add darkgrass block
        Registry.register(Registry.BLOCK, new Identifier(MODID, "dark_grass"), DARK_GRASS);
        Registry.register(Registry.ITEM, new Identifier(MODID, "dark_grass"), new BlockItem(DARK_GRASS, new FabricItemSettings().group(ItemGroup.MATERIALS)));

        //Setup biome spawn rates (lucky rare)
        OverworldBiomes.addContinentalBiome(ONYX_BIOME_KEY, OverworldClimate.TEMPERATE, 0.0777D);
        OverworldBiomes.addContinentalBiome(ONYX_BIOME_KEY, OverworldClimate.COOL, 0.002D);

        //Register onyx fort structures and setup spawn rates, quite rare
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(MODID, "onyx_fort_piece"), ONYXFORT_PIECE);
        FabricStructureBuilder.create(new Identifier(MODID, "onyx_fort_structure"), ONYXFORT_STRUCTURE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(128, 32, 314159)
                .adjustsSurface()
                .register();
        RegistryKey<ConfiguredStructureFeature<?, ?>> onyxBiomeFortAll = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY,
                new Identifier(MODID, "onyx_fort_structure"));

        //Register onyx fort structures and setup spawn rates, not so rare variant
        FabricStructureBuilder.create(new Identifier(MODID, "onyx_fort_structure_unrare"), ONYXFORT_STRUCTURE_UNRARE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(16, 8, 314159)
                .adjustsSurface()
                .register();
        RegistryKey<ConfiguredStructureFeature<?, ?>> onyxBiomeFort = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY,
                new Identifier(MODID, "onyx_fort_structure_unrare"));

        //Add the structure to all biomes.
        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, onyxBiomeFortAll.getValue(), ONYXFORT_CONFIGURED);
        BiomeModifications.addStructure(BiomeSelectors.all(), onyxBiomeFortAll);

        //Add the onyx fort structure to the Onyx biome with better odds
        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, onyxBiomeFort.getValue(), ONYXFORT_CONFIGURED_UNRARE);
        BiomeModifications.addStructure(BiomeSelectors.includeByKey(ONYX_BIOME_KEY), onyxBiomeFort);
    }

    private static Biome createOnyxBiome() {
        // We specify what entities spawn and what features generate in the biome.
        // Aside from some structures, trees, rocks, plants and
        //   custom entities, these are mostly the same for each biome.
        // Vanilla configured features for biomes are defined in DefaultBiomeFeatures.

        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        spawnSettings.creatureSpawnProbability(0.8f);
        DefaultBiomeFeatures.addFarmAnimals(spawnSettings);
        DefaultBiomeFeatures.addMonsters(spawnSettings, 95, 5, 100);
        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        generationSettings.surfaceBuilder(ONYXBIOME_SURFACE_BUILDER);
        DefaultBiomeFeatures.addDefaultUndergroundStructures(generationSettings);
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addDefaultLakes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);

        return (new Biome.Builder())
                .precipitation(Biome.Precipitation.NONE)
                .category(Biome.Category.SAVANNA)
                .depth(0.0024f)
                .scale(0.000001f)
                .temperature(1.6F)
                .downfall(0.6F)
                .effects((new BiomeEffects.Builder())
                        .waterColor(0x3f76e4)
                        .waterFogColor(0x050533)
                        .fogColor(0xc0d8ff)
                        .skyColor(0x77adff)
                        .build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }

    private void initProperties() {
        spawnOnyx = PROPERTIES.getBooleanProperty("spawn.onyxforts", true);
        onyxBiomeEnabled = PROPERTIES.getBooleanProperty("enable.onyx.biome", true);
        onyxSpawnChance = PROPERTIES.getIntegerProperty("onyxfort.spawn.chance", 1000);
    }

}
