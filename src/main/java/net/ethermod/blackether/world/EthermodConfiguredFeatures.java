package net.ethermod.blackether.world;

import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

public class EthermodConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ETHERORE = registerKey(Naming.ETHER_ORE_BLOCK);
    public static final ResourceKey<ConfiguredFeature<?, ?>> ONYXTREE = registerKey(Naming.ONYXWOOD_TREE);


    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        Block etherOreBlock = BlockRegistry.getInstance().getBlock(Naming.ETHER_ORE_BLOCK);
        List<OreConfiguration.TargetBlockState> etherOre =
                List.of(OreConfiguration.target(stoneReplaceables, etherOreBlock.defaultBlockState()),
                        OreConfiguration.target(deepslateReplaceables, etherOreBlock.defaultBlockState()));
        register(context, ONYXTREE,
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_LOG)),
                        new StraightTrunkPlacer(4, 6, 3),
                        BlockStateProvider.simple(BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_LEAVES)
                                .defaultBlockState()),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                        new TwoLayersFeatureSize(1, 0, 2)).build());
        register(context, ETHERORE, Feature.ORE, new OreConfiguration(etherOre, 12));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>>
    void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                  ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
