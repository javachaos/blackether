package net.ethermod.blackether.blocks.model.core;

import net.minecraft.data.models.BlockModelGenerators;

public interface BlockStateModelGenerator {
    void genModels(final BlockModelGenerators blockStateModelGenerator);
}
