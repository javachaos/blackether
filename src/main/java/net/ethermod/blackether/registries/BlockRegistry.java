package net.ethermod.blackether.registries;

import net.ethermod.blackether.blocks.BlockOfEther;
import net.ethermod.blackether.blocks.DarkGrassBlock;
import net.ethermod.blackether.blocks.EtherOreBlock;
import net.ethermod.blackether.blocks.NeutronBomb;
import net.ethermod.blackether.utils.Naming;
import net.ethermod.blackether.world.feature.tree.OnyxWoodGrower;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.concurrent.atomic.AtomicReference;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

public class BlockRegistry extends BaseRegistry {

    private static final AtomicReference<BlockRegistry> INSTANCE = new AtomicReference<>();

    private BlockRegistry() {
    }

    public static BlockRegistry getInstance() {
        if (INSTANCE.get() == null) {
            INSTANCE.set(new BlockRegistry());
        }
        return INSTANCE.get();
    }

    @Override
    public Block getBlock(final String name) {
        return super.getBlock(name);
    }

    public static final ResourceKey<PlacedFeature> CUSTOM_ORE_PLACED_KEY = ResourceKey.create(
            Registries.PLACED_FEATURE, new ResourceLocation(MOD_ID, Naming.ETHER_ORE_BLOCK));

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
        Block etherOreBlock = registerBlock(Naming.ETHER_ORE_BLOCK,
                new EtherOreBlock(FabricBlockSettings.of(Material.METAL,
                        MaterialColor.COLOR_BLACK).randomTicks().lightLevel(x -> 9).strength(5.0f, 6.0f)));
        Block blockOfEther = registerBlock(Naming.BLOCK_OF_ETHER,
                new BlockOfEther(FabricBlockSettings.of(Material.METAL,
                        MaterialColor.COLOR_BLACK).strength(10.0F, 6.0F)));
        Block chiseledEther = registerBlock(Naming.CHISELED_ETHER,
                new BlockOfEther(FabricBlockSettings.of(Material.METAL,
                        MaterialColor.COLOR_BLACK).strength(2.0F, 4.0F)));
        Block neutronBomb = registerBlock(Naming.NEUTRON_BOMB,
                new NeutronBomb(FabricBlockSettings.of(Material.AIR).instabreak().sound(SoundType.GRASS)));
        Block darkGrassBlock = registerBlock(Naming.DARK_GRASS,
                new DarkGrassBlock(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK).strength(0.6f)));
        registerLogs();

        putBlock(Naming.ETHER_ORE_BLOCK, etherOreBlock);
        putBlock(Naming.BLOCK_OF_ETHER, blockOfEther);
        putBlock(Naming.NEUTRON_BOMB, neutronBomb);
        putBlock(Naming.DARK_GRASS, darkGrassBlock);
        putBlock(Naming.CHISELED_ETHER, chiseledEther);
    }

    private void registerLogs() {
        Block onyxWoodLog = registerBlock(Naming.ONYXWOOD_LOG,
                new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
        Block onyxWood = registerBlock(Naming.ONYXWOOD_WOOD,
                new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD)));
        Block onyxWoodLogStripped = registerBlock(Naming.ONYXWOOD_LOG_STRIPPED,
                new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG)));
        Block onyxWoodStripped = registerBlock(Naming.ONYXWOOD_WOOD_STRIPPED,
                new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD)));
        Block onyxWoodPlanks = registerBlock(Naming.ONYXWOOD_PLANKS,
                new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
        Block onyxWoodLeaves = registerBlock(Naming.ONYXWOOD_LEAVES,
                new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES)));
        Block onyxWoodSapling = registerBlock(Naming.ONYXWOOD_SAPLING,
                new SaplingBlock(new OnyxWoodGrower(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));
        Block pottedOnyxWoodSapling = registerBlock(Naming.POTTED_ONYXWOOD_SAPLING,
                new FlowerPotBlock(onyxWoodSapling, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING)));
        Block petrifiedOnyxWoodSlab = registerBlock(Naming.PETRIFIED_ONYXWOOD_SLAB,
                new SlabBlock(FabricBlockSettings.copyOf(Blocks.PETRIFIED_OAK_SLAB)));

        putBlock(Naming.ONYXWOOD_LOG, onyxWoodLog);
        putBlock(Naming.ONYXWOOD_WOOD, onyxWood);
        putBlock(Naming.ONYXWOOD_LOG_STRIPPED, onyxWoodLogStripped);
        putBlock(Naming.ONYXWOOD_WOOD_STRIPPED, onyxWoodStripped);
        putBlock(Naming.ONYXWOOD_PLANKS, onyxWoodPlanks);
        putBlock(Naming.ONYXWOOD_LEAVES, onyxWoodLeaves);
        putBlock(Naming.ONYXWOOD_SAPLING, onyxWoodSapling);
        putBlock(Naming.POTTED_ONYXWOOD_SAPLING, pottedOnyxWoodSapling);
        putBlock(Naming.PETRIFIED_ONYXWOOD_SLAB, petrifiedOnyxWoodSlab);
    }

    public void generateTranslation(FabricLanguageProvider.TranslationBuilder translationBuilder) {
        translationBuilder.add(getBlock(Naming.ETHER_ORE_BLOCK), "Ether Ore");
        translationBuilder.add(getBlock(Naming.NEUTRON_BOMB), "Neutron Bomb");
        translationBuilder.add(getBlock(Naming.DARK_GRASS), "Dark Grass");
        translationBuilder.add(getBlock(Naming.CHISELED_ETHER), "Chiseled Ether");
        translationBuilder.add(getBlock(Naming.BLOCK_OF_ETHER), "Block Of Ether");
        translationBuilder.add(getBlock(Naming.ONYXWOOD_LOG), "Onyx Log");
        translationBuilder.add(getBlock(Naming.ONYXWOOD_WOOD), "Onyx Wood");
        translationBuilder.add(getBlock(Naming.ONYXWOOD_LOG_STRIPPED), "Stripped Onyx Log");
        translationBuilder.add(getBlock(Naming.ONYXWOOD_WOOD_STRIPPED), "Stripped Onyx Wood");
        translationBuilder.add(getBlock(Naming.ONYXWOOD_PLANKS), "Onyx Planks");
        translationBuilder.add(getBlock(Naming.ONYXWOOD_LEAVES), "Onyx Leaves");
        translationBuilder.add(getBlock(Naming.ONYXWOOD_SAPLING), "Onyx Sapling");
        translationBuilder.add(getBlock(Naming.POTTED_ONYXWOOD_SAPLING), "Potted Onyx Sapling");
        translationBuilder.add(getBlock(Naming.PETRIFIED_ONYXWOOD_SLAB), "Onyx Slab");
    }
}