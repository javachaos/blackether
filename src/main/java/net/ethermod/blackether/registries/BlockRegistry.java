package net.ethermod.blackether.registries;

import net.ethermod.blackether.blocks.BlockOfEther;
import net.ethermod.blackether.blocks.DarkGrassBlock;
import net.ethermod.blackether.blocks.EtherOreBlock;
import net.ethermod.blackether.blocks.NeutronBomb;
import net.ethermod.blackether.utils.Naming;
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

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

public class BlockRegistry extends BaseRegistry {

    private static BlockRegistry INSTANCE;

    private BlockRegistry() {
    }

    public static BlockRegistry getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BlockRegistry();
        }
        return INSTANCE;
    }

    @Override
    public Block getBlock(final String name) {
        return super.getBlock(name);
    }

    public static ResourceKey<PlacedFeature> CUSTOM_ORE_PLACED_KEY;

    private <B extends Block> B registerBlock(String name, B block) {
        return register(block, new ResourceLocation(MOD_ID, name));
    }

    private <B extends Block> B register(B block, ResourceLocation name) {
        Registry.register(BuiltInRegistries.BLOCK, name, block);
        BlockItem item = new BlockItem(block, (new Item.Properties()));

        item.registerBlocks(Item.BY_BLOCK, item);
        Registry.register(BuiltInRegistries.ITEM, name, item);
        return block;
    }

    @Override
    public void register() {
        Block ETHER_ORE_BLOCK = registerBlock(Naming.ETHER_ORE_BLOCK,
                new EtherOreBlock(FabricBlockSettings.of(Material.METAL,
                MaterialColor.COLOR_BLACK).randomTicks().lightLevel(x -> 9).strength(5.0f, 6.0f)));
        CUSTOM_ORE_PLACED_KEY = ResourceKey.create(
                Registries.PLACED_FEATURE, new ResourceLocation(MOD_ID, Naming.ETHER_ORE_BLOCK));
        Block BLOCK_OF_ETHER = registerBlock(Naming.BLOCK_OF_ETHER,
                new BlockOfEther(FabricBlockSettings.of(Material.METAL,
                        MaterialColor.COLOR_BLACK).strength(10.0F, 6.0F)));
        Block NEUTRON_BOMB = registerBlock(Naming.NEUTRON_BOMB,
                new NeutronBomb(FabricBlockSettings.of(Material.AIR).instabreak().sound(SoundType.GRASS)));
        Block DARK_GRASS = registerBlock(Naming.DARK_GRASS,
                new DarkGrassBlock(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK).strength(0.6f)));
        //TODO Extract out each of the magic values above into a Values class with nice variable names
        putBlock(Naming.ETHER_ORE_BLOCK, ETHER_ORE_BLOCK);
        putBlock(Naming.BLOCK_OF_ETHER, BLOCK_OF_ETHER);
        putBlock(Naming.NEUTRON_BOMB, NEUTRON_BOMB);
        putBlock(Naming.DARK_GRASS, DARK_GRASS);
    }
}