package net.ethermod.blackether;

import net.ethermod.blackether.blocks.EtherOreBlock;
import net.ethermod.blackether.entity.NeutronBombEntityRenderer;
import net.ethermod.blackether.features.OnyxFortFeature;
import net.ethermod.blackether.gen.OnyxFortGenerator;
import net.ethermod.blackether.items.*;
import net.ethermod.blackether.utils.PropertyManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
//import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
//import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.block.*;
import net.minecraft.sound.SoundEvent;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
//import net.minecraft.world.biome.BiomeEffects;
//import net.minecraft.world.biome.GenerationSettings;
//import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.*;
//import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
//import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BlackEtherMod implements ModInitializer {


    public static final Logger LOGGER = LogManager.getLogger(BlackEtherMod.class);
    public static final String MODID = "ethermod";
    public static final PropertyManager PROPERTIES = new PropertyManager(MODID);
    public static final Identifier NEUTRON_IONIZING = new Identifier(MODID, "neutron_ionizing");
    public static SoundEvent NEUTRON_EVENT = new SoundEvent(NEUTRON_IONIZING);
    public static final Block ETHER_ORE_BLOCK = new EtherOreBlock(FabricBlockSettings.of(Material.METAL,
            MapColor.BLACK).ticksRandomly().luminance(9).strength(5.0F, 6.0F));
    //public static final StructurePieceType ONYXFORT_PIECE = OnyxFortGenerator.Piece::new;
//   // private static final StructureFeature<DefaultFeatureConfig> ONYXFORT_STRUCTURE = new OnyxFortFeature(DefaultFeatureConfig.CODEC);
//    private static final StructureFeature<DefaultFeatureConfig> ONYXFORT_STRUCTURE_UNRARE = new OnyxFortFeature(DefaultFeatureConfig.CODEC);
//    private static final ConfiguredStructureFeature<?, ?> ONYXFORT_CONFIGURED = ONYXFORT_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);
//    private static final ConfiguredStructureFeature<?, ?> ONYXFORT_CONFIGURED_UNRARE = ONYXFORT_STRUCTURE_UNRARE.configure(DefaultFeatureConfig.DEFAULT);
    //public static final RegistryKey<Biome> ONYX_BIOME_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MODID, "onyx_biome"));
//    private static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> OBSIDIAN_SURFACE_BUILDER = SurfaceBuilder.DEFAULT
//            .withConfig(new TernarySurfaceConfig(
//                    Blocks.OBSIDIAN.getDefaultState(),
//                    Blocks.DIRT.getDefaultState(),
//                    Blocks.GRAVEL.getDefaultState()));
//    private static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> ONYXBIOME_SURFACE_BUILDER = SurfaceBuilder.DEFAULT
//            .withConfig(new TernarySurfaceConfig(
//                    RegisterItems.DARK_GRASS.getDefaultState(), Blocks.BASALT.getDefaultState(),
//                    ETHER_ORE_BLOCK.getDefaultState()
//            ));

    private static final Biome ONYXBIOME = createOnyxBiome();
    private static ConfiguredFeature<?, ?> ORE_ETHER_OVERWORLD = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreConfiguredFeatures.BASE_STONE_OVERWORLD,
                    ETHER_ORE_BLOCK.getDefaultState(),
                    15)); // vein size

    public static PlacedFeature ORE_ETHER_OVERWORLD_FEATURE = ORE_ETHER_OVERWORLD.withPlacement(
            CountPlacementModifier.of(10), // number of veins per chunk
            SquarePlacementModifier.of(), // spreading horizontally
            HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))); // height

    private static ConfiguredFeature<?, ?> ORE_ETHER_ONYXBIOME = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreConfiguredFeatures.BASE_STONE_OVERWORLD,
                    ETHER_ORE_BLOCK.getDefaultState(),
                    25)); // vein size

    public static PlacedFeature ORE_ETHER_ONYXBIOME_FEATURE = ORE_ETHER_ONYXBIOME.withPlacement(
            CountPlacementModifier.of(40), // number of veins per chunk
            SquarePlacementModifier.of(), // spreading horizontally
            HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))); // height

    private boolean spawnOnyx;
    private boolean onyxBiomeEnabled;
    private int onyxSpawnChance;

    @Override
    public void onInitialize() {
        RegisterItems.register();
        EntityRendererRegistry.INSTANCE.register(RegisterItems.NEUTRON_BOMB_ENTITY, NeutronBombEntityRenderer::new);
        initProperties();
        loadOres();
        setupBiomes();
    }

    private void loadOres() {
        registerOreByName("ore_ether_overworld", ORE_ETHER_OVERWORLD, ORE_ETHER_OVERWORLD_FEATURE);
        registerOreByName("ore_ether_onyxbiome", ORE_ETHER_ONYXBIOME, ORE_ETHER_ONYXBIOME_FEATURE);
    }

    private void registerOreByName(String oreName, ConfiguredFeature<?, ?> feature, PlacedFeature placedFeature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                new Identifier(MODID, oreName), feature);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(MODID, oreName),
                placedFeature);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                        new Identifier(MODID, oreName)));
    }

    private void setupBiomes() {
//        if (onyxBiomeEnabled) {
//            //Load onyxbiome extra ore feature
//            RegistryKey<ConfiguredFeature<?, ?>> oreEtherOnyxBiome = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
//                    new Identifier(MODID, "ore_ether_onyxbiome"));
//            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreEtherOnyxBiome.getValue(), ORE_ETHER_ONYXBIOME);
//            BiomeModifications.addFeature(BiomeSelectors.includeByKey(ONYX_BIOME_KEY), GenerationStep.Feature.UNDERGROUND_ORES, oreEtherOnyxBiome);
//
//            //Register surface builder
//            Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier(MODID, "onyx_biome"), ONYXBIOME_SURFACE_BUILDER);
//            Registry.register(BuiltinRegistries.BIOME, ONYX_BIOME_KEY.getValue(), ONYXBIOME);
//
//            //Setup biome spawn rates (lucky rare)
//            OverworldBiomes.addContinentalBiome(ONYX_BIOME_KEY, OverworldClimate.TEMPERATE, 0.00777D);
//            OverworldBiomes.addContinentalBiome(ONYX_BIOME_KEY, OverworldClimate.COOL, 0.00002D);
//        }
//
//        if (spawnOnyx) {
//            registerOnyxForts();
//        }
    }

    /**
     * Register onyx fort spawns.
     */
    private void registerOnyxForts() {
//        //Register onyx fort structures and setup spawn rates, quite rare
//        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(MODID, "onyx_fort_piece"), ONYXFORT_PIECE);
//        FabricStructureBuilder.create(new Identifier(MODID, "onyx_fort_structure"), ONYXFORT_STRUCTURE)
//                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
//                .defaultConfig( onyxSpawnChance, 32, 314159)
//                .adjustsSurface()
//                .register();
//        RegistryKey<ConfiguredStructureFeature<?, ?>> onyxBiomeFortAll = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY,
//                new Identifier(MODID, "onyx_fort_structure"));
//
//        //Register onyx fort structures and setup spawn rates, not so rare variant
//        FabricStructureBuilder.create(new Identifier(MODID, "onyx_fort_structure_unrare"), ONYXFORT_STRUCTURE_UNRARE)
//                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
//                .defaultConfig(16, 8, 314159)
//                .adjustsSurface()
//                .register();
//        RegistryKey<ConfiguredStructureFeature<?, ?>> onyxBiomeFort = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY,
//                new Identifier(MODID, "onyx_fort_structure_unrare"));
//
//        //Add the structure to all biomes.
//        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, onyxBiomeFortAll.getValue(), ONYXFORT_CONFIGURED);
//        BiomeModifications.addStructure(BiomeSelectors.all(), onyxBiomeFortAll);
//
//        //Add the onyx fort structure to the Onyx biome with better odds
//        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, onyxBiomeFort.getValue(), ONYXFORT_CONFIGURED_UNRARE);
//        BiomeModifications.addStructure(BiomeSelectors.includeByKey(ONYX_BIOME_KEY), onyxBiomeFort);
    }

    private static Biome createOnyxBiome() {
        // We specify what entities spawn and what features generate in the biome.
        // Aside from some structures, trees, rocks, plants and
        //   custom entities, these are mostly the same for each biome.
        // Vanilla configured features for biomes are defined in DefaultBiomeFeatures.
//
//        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
//        spawnSettings.creatureSpawnProbability(0.8f);
//        DefaultBiomeFeatures.addFarmAnimals(spawnSettings);
//        DefaultBiomeFeatures.addMonsters(spawnSettings, 95, 5, 100);
//        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
//        generationSettings.surfaceBuilder(ONYXBIOME_SURFACE_BUILDER);
//        DefaultBiomeFeatures.addDefaultUndergroundStructures(generationSettings);
//        DefaultBiomeFeatures.addLandCarvers(generationSettings);
//        DefaultBiomeFeatures.addDefaultLakes(generationSettings);
//        DefaultBiomeFeatures.addDungeons(generationSettings);
//        DefaultBiomeFeatures.addMineables(generationSettings);
//        DefaultBiomeFeatures.addDefaultOres(generationSettings);
//        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
//        DefaultBiomeFeatures.addSprings(generationSettings);
//        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
//
//        return (new Biome.Builder())
//                .precipitation(Biome.Precipitation.NONE)
//                .category(Biome.Category.SAVANNA)
//                .depth(0.0024f)
//                .scale(0.000001f)
//                .temperature(1.6F)
//                .downfall(0.6F)
//                .effects((new BiomeEffects.Builder())
//                        .waterColor(0x3f76e4)
//                        .waterFogColor(0x050533)
//                        .fogColor(0xc0d8ff)
//                        .skyColor(0x77adff)
//                        .build())
//                .spawnSettings(spawnSettings.build())
//                .generationSettings(generationSettings.build())
//                .build();
        return null;
    }

    private void initProperties() {
        spawnOnyx = PROPERTIES.getBooleanProperty("spawn.onyxforts", true);
        onyxBiomeEnabled = PROPERTIES.getBooleanProperty("enable.onyx.biome", true);
        onyxSpawnChance = PROPERTIES.getIntegerProperty("onyxfort.spawn.spacing", 128);
    }



}
