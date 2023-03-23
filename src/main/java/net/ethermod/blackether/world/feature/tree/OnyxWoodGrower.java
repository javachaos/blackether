package net.ethermod.blackether.world.feature.tree;

import net.ethermod.blackether.world.EthermodConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class OnyxWoodGrower extends AbstractMegaTreeGrower {

    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean bl) {
        return EthermodConfiguredFeatures.ONYXTREE;
    }

    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource randomSource) {
        return EthermodConfiguredFeatures.ONYXTREE;
    }
}
