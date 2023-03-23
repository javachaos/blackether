package net.ethermod.blackether.blocks.gen.impl;

import net.ethermod.blackether.blocks.gen.core.BlockLootGenerator;
import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

public class OnyxWoodSlabGen extends BlockLootGenerator {

    public OnyxWoodSlabGen(EthermodLootTableGenerator ltg) {
        super(ltg, Naming.PETRIFIED_ONYXWOOD_SLAB,
                BlockRegistry.getInstance().getBlock(Naming.PETRIFIED_ONYXWOOD_SLAB), null);
        setBuilder(ltg.createSlabItemTable(block)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 1))));
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
