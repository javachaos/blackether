package net.ethermod.blackether.world;

import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.ArrayList;
import java.util.List;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

public class EthermodPlacedFeatures {

    private EthermodPlacedFeatures() {}

    public static final ResourceKey<PlacedFeature> ETHERORE = registerKey(Naming.ETHER_ORE_BLOCK);
    public static final ResourceKey<PlacedFeature> DARK_GRASS = registerKey(Naming.DARK_GRASS);
    public static final ResourceKey<PlacedFeature> ONYXTREE_KEY = registerKey(Naming.ONYXWOOD_TREE_PLACED);

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, ONYXTREE_KEY, configuredFeatureRegistryEntryLookup
                        .getOrThrow(EthermodConfiguredFeatures.ONYXTREE),
                VegetationPlacements.treePlacement(
                        PlacementUtils.countExtra(11, 0.000001f, 12),
                        BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_SAPLING))
        );

        register(context, DARK_GRASS, configuredFeatureRegistryEntryLookup
                        .getOrThrow(EthermodConfiguredFeatures.DARK_GRASS),
                NoiseThresholdCountPlacement.of(0, 1, 1),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());

        register(context, ETHERORE, configuredFeatureRegistryEntryLookup
                        .getOrThrow(EthermodConfiguredFeatures.ETHERORE),
                modifiersWithCount(6,                         //Vein size
                        HeightRangePlacement.triangle(
                                VerticalAnchor.aboveBottom(-80),  //min Y
                                VerticalAnchor.aboveBottom(80))));//max Y
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
            BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
            Holder<ConfiguredFeature<?, ?>> configuration,
            PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }

    public static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, InSquarePlacement.spread(), heightModifier, BiomeFilter.biome());
    }

    public static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacement.of(count), heightModifier);
    }

    public static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilter.onAverageOnceEvery(chance), heightModifier);
    }

}
