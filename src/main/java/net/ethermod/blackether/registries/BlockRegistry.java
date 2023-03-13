package net.ethermod.blackether.registries;

import net.ethermod.blackether.blocks.BlockOfEther;
import net.ethermod.blackether.blocks.DarkGrassBlock;
import net.ethermod.blackether.blocks.EtherOreBlock;
import net.ethermod.blackether.blocks.NeutronBomb;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import static net.ethermod.blackether.BlackEtherMod.MODID;

public class BlockRegistry {

    public static final EtherOreBlock ETHER_ORE_BLOCK = registerBlock("ether_ore_block", new EtherOreBlock(FabricBlockSettings.of(Material.METAL,
            MaterialColor.COLOR_BLACK).randomTicks().lightLevel(x -> 9).strength(5.0f, 6.0f)));

    public static final ResourceKey<PlacedFeature> CUSTOM_ORE_PLACED_KEY = ResourceKey.create(
            Registries.PLACED_FEATURE, new ResourceLocation(MODID, "ether_ore_block"));

    public static final BlockOfEther BLOCK_OF_ETHER = registerBlock("block_of_ether",
            new BlockOfEther(FabricBlockSettings.of(Material.METAL, MaterialColor.COLOR_BLACK).strength(10.0F, 6.0F)));
    public static final NeutronBomb NEUTRON_BOMB = registerBlock("neutron_bomb",
            new NeutronBomb(FabricBlockSettings.of(Material.AIR).instabreak().sound(SoundType.GRASS)));
    public static final DarkGrassBlock DARK_GRASS = registerBlock("dark_grass",
            new DarkGrassBlock(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK).strength(0.6f)));

    public static <B extends Block> B registerBlock(String name, B block) {
        return register(block, new ResourceLocation(MODID, name));
    }

    private static <B extends Block> B register(B block, ResourceLocation name) {
        Registry.register(BuiltInRegistries.BLOCK, name, block);
        BlockItem item = new BlockItem(block, (new Item.Properties()));

        item.registerBlocks(Item.BY_BLOCK, item);
        Registry.register(BuiltInRegistries.ITEM, name, item);
        return block;
    }
}