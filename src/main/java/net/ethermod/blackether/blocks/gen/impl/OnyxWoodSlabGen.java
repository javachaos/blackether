package net.ethermod.blackether.blocks.gen.impl;

import net.ethermod.blackether.blocks.gen.core.SingleBlockLootGenerator;
import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.function.BiConsumer;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

public class OnyxWoodSlabGen extends SingleBlockLootGenerator {

    public OnyxWoodSlabGen(EthermodLootTableGenerator ltg) {
        super(ltg, Naming.ONYXWOOD_SLAB,
                BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_SLAB));
        setBuilder(ltg.createSlabItemTable(block));
    }

    @Override
    public void generate() {
        lootTableGenerator.add(block, builder);
    }

    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> resourceLocationBuilderBiConsumer) {
        resourceLocationBuilderBiConsumer.accept(new ResourceLocation(MOD_ID,
                Naming.BLOCKS_FOLDER + blockName), builder);
    }
}
