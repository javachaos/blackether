package net.ethermod.blackether.blocks.model.impl;

import net.ethermod.blackether.blocks.model.core.BlockStateModelGenerator;
import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.world.level.block.Block;

public class TreeBlockStateGen implements BlockStateModelGenerator {
    @Override
    public void genModels(BlockModelGenerators blockStateModelGenerator) {
        Block onyxWoodLog = BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_LOG);
        Block onyxWood = BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_WOOD);
        Block onyxWoodLogStripped = BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_LOG_STRIPPED);
        Block onyxWoodStripped = BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_WOOD_STRIPPED);
        Block onyxWoodPlanks = BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_PLANKS);
        Block onyxWoodLeaves = BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_LEAVES);
        Block onyxWoodSapling = BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_SAPLING);
        Block pottedOnyxWoodSapling = BlockRegistry.getInstance().getBlock(Naming.POTTED_ONYXWOOD_SAPLING);
        Block onyxWoodSlab = BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_SLAB);

        blockStateModelGenerator.woodProvider(onyxWoodLog)
                .logWithHorizontal(onyxWoodLog).wood(onyxWood);
        blockStateModelGenerator.woodProvider(onyxWoodLogStripped)
                .logWithHorizontal(onyxWoodLogStripped).wood(onyxWoodStripped);
        blockStateModelGenerator.createTrivialBlock(onyxWoodLeaves, TexturedModel.LEAVES);
        blockStateModelGenerator.createPlant(onyxWoodSapling, pottedOnyxWoodSapling,
                BlockModelGenerators.TintState.NOT_TINTED);

        BlockFamily b = BlockFamilies.familyBuilder(onyxWoodPlanks)
                .button(BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_BUTTON))
                .fence(BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_FENCE))
                .fenceGate(BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_GATE))
                .pressurePlate(BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_PRESSURE_PLATE))
                .slab(onyxWoodSlab)
                .stairs(BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_STAIRS))
                .door(BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_DOOR))
                .trapdoor(BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_TRAPDOOR))
                .recipeGroupPrefix("onyx_wooden")
                .recipeUnlockedBy("has_planks")
                .getFamily();
        blockStateModelGenerator.family(onyxWoodPlanks)
                .generateFor(b);
    }
}
