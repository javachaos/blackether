package net.ethermod.blackether.blocks.model.impl;

import net.ethermod.blackether.blocks.model.core.BlockStateModelGenerator;
import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.data.models.BlockModelGenerators;

public class TrivialBlockGen implements BlockStateModelGenerator {
    @Override
    public void genModels(BlockModelGenerators blockStateModelGenerator) {
        createTrivialEtherBlock(blockStateModelGenerator, Naming.BLOCK_OF_ETHER);
        createTrivialEtherBlock(blockStateModelGenerator, Naming.CHISELED_ETHER);

    }

    private void createTrivialEtherBlock(final BlockModelGenerators blockStateModelGenerator, String blockOfEther) {
        blockStateModelGenerator.createTrivialCube(
                BlockRegistry.getInstance().getBlock(blockOfEther));
    }

}
