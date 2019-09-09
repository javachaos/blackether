package net.ethermod.blackether;

import net.ethermod.blackether.blocks.BlockOfEther;
import net.ethermod.blackether.blocks.EtherOreBlock;
import net.ethermod.blackether.items.OnyxAxe;
import net.ethermod.blackether.items.OnyxPickaxe;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.fabric.impl.registry.FuelRegistryImpl;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class BlackEtherMod implements ModInitializer {

	public static final Block ETHER_ORE_BLOCK = new EtherOreBlock(FabricBlockSettings.of(Material.METAL, MaterialColor.LAVA).ticksRandomly().lightLevel(9).strength(5.0F, 6.0F).build());
	public static final Item ETHER_ORE = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
	public static final Item ONYX_ORE = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
	public static final Item ONYX_PICKAXE = new OnyxPickaxe();
	public static final Item ONYX_AXE = new OnyxAxe();
	public static final Block BLOCK_OF_ETHER = new BlockOfEther(FabricBlockSettings.of(Material.METAL, MaterialColor.BLACK).strength(10.0F, 6.0F).build());

	@Override
	public void onInitialize() {
		Registry.BIOME.forEach(this::handleBiome);
		RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> handleBiome(biome));

		//ITEMS
		Registry.register(Registry.ITEM, new Identifier("ethermod", "onyx_pickaxe"), ONYX_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier("ethermod", "onyx_axe"), ONYX_AXE);
		Registry.register(Registry.ITEM, new Identifier("ethermod", "ether_ore"), ETHER_ORE);
		Registry.register(Registry.ITEM, new Identifier("ethermod", "onyx_ore"), ONYX_ORE);
		Registry.register(Registry.ITEM, new Identifier("ethermod", "ether_ore_block"), new BlockItem(ETHER_ORE_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier("ethermod", "block_of_ether"), new BlockItem(BLOCK_OF_ETHER, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

		//BLOCKS
		Registry.register(Registry.BLOCK, new Identifier("ethermod", "ether_ore_block"), ETHER_ORE_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier("ethermod", "block_of_ether"), BLOCK_OF_ETHER);
		FuelRegistryImpl.INSTANCE.add(ETHER_ORE, 3000);
	}

	/**
	 * Called during init for each biome.
	 * @param biome
	 */
	private void handleBiome(Biome biome) {
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
