package net.ethermod.blackether;

import net.ethermod.blackether.blocks.BlockOfEther;
import net.ethermod.blackether.blocks.EtherOreBlock;
import net.ethermod.blackether.enums.CustomArmorMaterial;
import net.ethermod.blackether.features.OnyxFortFeature;
import net.ethermod.blackether.gen.OnyxFortGenerator;
import net.ethermod.blackether.items.*;
import net.ethermod.blackether.utils.PropertyManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.*;
//import net.fabricmc.fabric.api.biomes.v1.FabricBiomes;
//import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes;
//import net.fabricmc.fabric.api.biomes.v1.OverworldClimate;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.fabricmc.fabric.impl.content.registry.FuelRegistryImpl;
//import net.fabricmc.fabric.impl.registry.FuelRegistryImpl;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
//import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
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
	public static final Block ETHER_ORE_BLOCK = new EtherOreBlock(FabricBlockSettings.of(Material.METAL, MaterialColor.LAVA).ticksRandomly().lightLevel(9).strength(5.0F, 6.0F).build());
	public static final Item ETHER_ORE = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
	public static final Item ONYX_ORE = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
	public static final Item ONYX_DUST = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
	public static final Item ONYX_PICKAXE = new OnyxPickaxe();
	public static final Item ONYX_SHOVEL = new OnyxShovel();
	public static final Item ONYX_AXE = new OnyxAxe();
	public static final Item ONYX_HOE = new OnyxHoe();
	public static final Item ONYX_SWORD = new OnyxSword();
	public static final Block BLOCK_OF_ETHER = new BlockOfEther(FabricBlockSettings.of(Material.METAL, MaterialColor.BLACK).strength(10.0F, 6.0F).build());

	public static final StructurePieceType ONYXFORT_PIECE = OnyxFortGenerator.Piece::new;
	private static final StructureFeature<DefaultFeatureConfig> ONYXFORT_STRUCTURE = new OnyxFortFeature(DefaultFeatureConfig.CODEC);
	private static final ConfiguredStructureFeature<?, ?> ONYXFORT_CONFIGURED = ONYXFORT_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);

	//public static final StructurePieceType myStructurePieceType = Registry.register(Registry.STRUCTURE_PIECE, "onyx_fort_piece", OnyxFortGenerator.Piece::new);
	//public static final StructureFeature<DefaultFeatureConfig> onyxFortFeature = Registry.register(Registry.FEATURE, "onyx_fort_feature", new OnyxFortFeature());
	//public static final StructureFeature<?> myStructure = Registry.register(Registry.STRUCTURE_FEATURE, "onyx_fort_structure", onyxFortFeature);
    //public static final Biome ONYX_BIOME = Registry.register(Registry.BIOME_SOURCE, new Identifier(MODID, "onyx_biome"), new OnyxBiome());

	public static final RegistryKey<Biome> ONYX_BIOME_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MODID, "onyx_biome"));

	public static final Item ONYX_HELMET = new ArmorItem(CustomArmorMaterial.ONYX, EquipmentSlot.HEAD, (new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item ONYX_CHESTPLATE = new ArmorItem(CustomArmorMaterial.ONYX, EquipmentSlot.CHEST, (new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item ONYX_LEGGINGS = new ArmorItem(CustomArmorMaterial.ONYX, EquipmentSlot.LEGS, (new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item ONYX_BOOTS = new ArmorItem(CustomArmorMaterial.ONYX, EquipmentSlot.FEET, (new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item ONYX_APPLE = new OnyxApple();
	public static final Identifier SEND_TOAST_TO_CLIENT_PACKET_ID = new Identifier(MODID, "showtext");

	static {
		LOGGER.info("Registering items and blocks for Black Ether Mod");
		//Feature.STRUCTURES.put("onyx feature", onyxFortFeature);
		//ITEMS
		Registry.register(Registry.ITEM, new Identifier(MODID, "onyx_apple"), ONYX_APPLE);
		Registry.register(Registry.ITEM, new Identifier(MODID, "onyx_pickaxe"), ONYX_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier(MODID, "onyx_axe"), ONYX_AXE);
		Registry.register(Registry.ITEM, new Identifier(MODID, "onyx_hoe"), ONYX_HOE);
		Registry.register(Registry.ITEM, new Identifier(MODID, "onyx_sword"), ONYX_SWORD);
		Registry.register(Registry.ITEM, new Identifier(MODID, "onyx_shovel"), ONYX_SHOVEL);
		Registry.register(Registry.ITEM, new Identifier(MODID, "ether_ore"), ETHER_ORE);
		Registry.register(Registry.ITEM, new Identifier(MODID, "onyx_ore"), ONYX_ORE);
		Registry.register(Registry.ITEM, new Identifier(MODID, "onyx_dust"), ONYX_DUST);
		Registry.register(Registry.ITEM, new Identifier(MODID, "ether_ore_block"), new BlockItem(ETHER_ORE_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier(MODID, "block_of_ether"), new BlockItem(BLOCK_OF_ETHER, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM,new Identifier(MODID,"onyx_helmet"), ONYX_HELMET);
		Registry.register(Registry.ITEM,new Identifier(MODID,"onyx_chestplate"), ONYX_CHESTPLATE);
		Registry.register(Registry.ITEM,new Identifier(MODID,"onyx_leggings"), ONYX_LEGGINGS);
		Registry.register(Registry.ITEM,new Identifier(MODID,"onyx_boots"), ONYX_BOOTS);
		//BLOCKS
		Registry.register(Registry.BLOCK, new Identifier(MODID, "ether_ore_block"), ETHER_ORE_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(MODID, "block_of_ether"), BLOCK_OF_ETHER);
		FuelRegistryImpl.INSTANCE.add(ETHER_ORE, 3000);
	}

	private boolean spawnOnyx;
	private boolean onyxBiomeEnabled;
	private int onyxSpawnChance;

	@Override
	public void onInitialize() {
		initProperties();
		setupBiomes();
	}

	private void setupBiomes() {
		Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier(MODID, "onyx_biome"), ONYXBIOME_SURFACE_BUILDER);
		Registry.register(BuiltinRegistries.BIOME, ONYX_BIOME_KEY.getValue(), ONYXBIOME);
		OverworldBiomes.addContinentalBiome(ONYX_BIOME_KEY, OverworldClimate.TEMPERATE, 2D);
		OverworldBiomes.addContinentalBiome(ONYX_BIOME_KEY, OverworldClimate.COOL, 2D);

		Registry.register(Registry.STRUCTURE_PIECE, new Identifier(MODID, "onyx_fort_piece"), ONYXFORT_PIECE);
		FabricStructureBuilder.create(new Identifier(MODID, "onyx_fort_structure"), ONYXFORT_STRUCTURE)
				.step(GenerationStep.Feature.SURFACE_STRUCTURES)
				.defaultConfig(32, 8, 12345)
				.adjustsSurface()
				.register();

		RegistryKey<ConfiguredStructureFeature<?, ?>> myConfigured = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_WORLDGEN,
				new Identifier(MODID, "onyx_fort_structure"));
		BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, myConfigured.getValue(), ONYXFORT_CONFIGURED);
	}

	private static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> ONYXBIOME_SURFACE_BUILDER = SurfaceBuilder.DEFAULT
			.withConfig(new TernarySurfaceConfig(
					Blocks.GLOWSTONE.getDefaultState(),
					Blocks.OBSIDIAN.getDefaultState(),
					Blocks.BLACK_STAINED_GLASS.getDefaultState()));

	private static final Biome ONYXBIOME = createOnyxBiome();

	private static Biome createOnyxBiome() {
		// We specify what entities spawn and what features generate in the biome.
		// Aside from some structures, trees, rocks, plants and
		//   custom entities, these are mostly the same for each biome.
		// Vanilla configured features for biomes are defined in DefaultBiomeFeatures.

		SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
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
		BiomeModifications.addSpawn(
				BiomeSelectors.includeByKey(ONYX_BIOME_KEY),
				SpawnGroup.CREATURE,
				EntityType.SHEEP,
				1 /* weight */,
				15 /* minGroupSize */,
				25 /* maxGroupSize */
		);

		return (new Biome.Builder())
				.precipitation(Biome.Precipitation.RAIN)
				.category(Biome.Category.FOREST)
				.depth(0.024f)
				.scale(1.2f)
				.temperature(0.06F)
				.downfall(0.06F)
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
//TODO Rework
//	/**
//	 * Called during init for each biome.
//	 * @param biome the current biome
//	 */
//	private void handleBiome(Biome biome) {
//		if(spawnOnyx && biome.getCategory() != Biome.Category.RIVER
//				&& biome.getCategory() != Biome.Category.OCEAN
//				&& biome.getCategory() != Biome.Category.THEEND) {
//			biome.addStructureFeature(onyxFortFeature, new DefaultFeatureConfig());
//			biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES,
//					Biome.configureFeature(
//							onyxFortFeature,
//							new DefaultFeatureConfig(),
//							Decorator.CHANCE_PASSTHROUGH,
//							new ChanceDecoratorConfig(onyxSpawnChance)
//					));
//		}
//		if(onyxBiomeEnabled && biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
//			biome.addFeature(
//					GenerationStep.Feature.UNDERGROUND_ORES,
//					Biome.configureFeature(
//							Feature.ORE,
//							new OreFeatureConfig(
//									OreFeatureConfig.Target.NATURAL_STONE,
//									ETHER_ORE_BLOCK.getDefaultState(),
//									5 //Ore vein size
//							),
//							Decorator.COUNT_RANGE,
//							new RangeDecoratorConfig(
//									8, //Number of veins per chunk
//									0, //Bottom Offset
//									0, //Min y level
//									8 //Max y level
//							)));
//		}
//	}
}
