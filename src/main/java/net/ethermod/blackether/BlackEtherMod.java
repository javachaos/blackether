package net.ethermod.blackether;

import net.ethermod.blackether.biomes.OnyxBiome;
import net.ethermod.blackether.blocks.BlockOfEther;
import net.ethermod.blackether.blocks.EtherOreBlock;
import net.ethermod.blackether.commands.RandomTeleportCommand;
import net.ethermod.blackether.enums.CustomArmorMaterial;
import net.ethermod.blackether.features.OnyxFortFeature;
import net.ethermod.blackether.gen.OnyxFortGenerator;
import net.ethermod.blackether.items.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biomes.v1.FabricBiomes;
import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biomes.v1.OverworldClimate;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.fabricmc.fabric.impl.registry.FuelRegistryImpl;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BlackEtherMod implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger(BlackEtherMod.class);
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
	public static final StructurePieceType myStructurePieceType = Registry.register(Registry.STRUCTURE_PIECE, "onyx_fort_piece", OnyxFortGenerator.Piece::new);
	public static final StructureFeature<DefaultFeatureConfig> onyxFortFeature = Registry.register(Registry.FEATURE, "onyx_fort_feature", new OnyxFortFeature());
	public static final StructureFeature<?> myStructure = Registry.register(Registry.STRUCTURE_FEATURE, "onyx_fort_structure", onyxFortFeature);
    public static final Biome ONYX_BIOME = Registry.register(Registry.BIOME, new Identifier(MODID, "onyx_biome"), new OnyxBiome());
	public static final Item ONYX_HELMET = new ArmorItem(CustomArmorMaterial.ONYX, EquipmentSlot.HEAD, (new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item ONYX_CHESTPLATE = new ArmorItem(CustomArmorMaterial.ONYX, EquipmentSlot.CHEST, (new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item ONYX_LEGGINGS = new ArmorItem(CustomArmorMaterial.ONYX, EquipmentSlot.LEGS, (new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item ONYX_BOOTS = new ArmorItem(CustomArmorMaterial.ONYX, EquipmentSlot.FEET, (new Item.Settings().group(ItemGroup.COMBAT)));

	static {
		Feature.STRUCTURES.put("onyx feature", onyxFortFeature);
		//ITEMS
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

	private void registerCommands() {
		LOGGER.info("Registering commands for Black Ether Mod");
		CommandRegistry.INSTANCE.register(false, dispatcher -> RandomTeleportCommand.register(dispatcher));
	}

	@Override
	public void onInitialize() {
		registerCommands();
		OverworldBiomes.addContinentalBiome(BlackEtherMod.ONYX_BIOME, OverworldClimate.TEMPERATE, 2D);
		OverworldBiomes.addContinentalBiome(BlackEtherMod.ONYX_BIOME, OverworldClimate.COOL, 2D);
		FabricBiomes.addSpawnBiome(BlackEtherMod.ONYX_BIOME);
		OverworldBiomes.setRiverBiome(BlackEtherMod.ONYX_BIOME, null);
		OverworldBiomes.addHillsBiome(BlackEtherMod.ONYX_BIOME, Biomes.MOUNTAINS, 0.1);
		OverworldBiomes.addBiomeVariant(Biomes.PLAINS, BlackEtherMod.ONYX_BIOME, 0.33);
		Registry.BIOME.forEach(this::handleBiome);
		RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> handleBiome(biome));

	}

	/**
	 * Called during init for each biome.
	 * @param biome
	 */
	private void handleBiome(Biome biome) {
		if(biome.getCategory() != Biome.Category.RIVER
				&& biome.getCategory() != Biome.Category.OCEAN
				&& biome.getCategory() != Biome.Category.THEEND) {
			biome.addStructureFeature(onyxFortFeature, new DefaultFeatureConfig());
			biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES,
					Biome.configureFeature(
							onyxFortFeature,
							new DefaultFeatureConfig(),
							Decorator.CHANCE_PASSTHROUGH,
							new ChanceDecoratorConfig(1000)
					));
		}
		if(biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
			biome.addFeature(
					GenerationStep.Feature.UNDERGROUND_ORES,
					Biome.configureFeature(
							Feature.ORE,
							new OreFeatureConfig(
									OreFeatureConfig.Target.NATURAL_STONE,
									ETHER_ORE_BLOCK.getDefaultState(),
									5 //Ore vein size
							),
							Decorator.COUNT_RANGE,
							new RangeDecoratorConfig(
									8, //Number of veins per chunk
									0, //Bottom Offset
									0, //Min y level
									8 //Max y level
							)));
		}
	}
}
