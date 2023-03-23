package net.ethermod.blackether.world;

import net.ethermod.blackether.entity.mobs.OnyxFrogEntity;
import net.ethermod.blackether.registries.EntityRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.jetbrains.annotations.Nullable;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;
import static net.minecraft.data.worldgen.biome.OverworldBiomes.calculateSkyColor;

public class EthermodBiomes {

    public static void bootstrap(BootstapContext<Biome> bootstapContext) {
        ResourceKey<Biome> biome = ResourceKey.create(Registries.BIOME, new ResourceLocation(MOD_ID, "onyx_biome"));
        HolderGetter<PlacedFeature> holderGetter = bootstapContext.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> holderGetter2 = bootstapContext.lookup(Registries.CONFIGURED_CARVER);
        bootstapContext.register(biome, create(holderGetter, holderGetter2, false, false));
    }

    private static Biome create(HolderGetter<PlacedFeature> holderGetter, HolderGetter<ConfiguredWorldCarver<?>> holderGetter2, boolean bl, boolean bl2) {
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(holderGetter, holderGetter2);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addSavannaGrass(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(builder);
        BiomeDefaultFeatures.addSavannaExtraGrass(builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(builder);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                EthermodPlacedFeatures.ONYXTREE_KEY);

        MobSpawnSettings.Builder builder2 = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(builder2);
        builder2.addSpawn(MobCategory.MONSTER,
                new MobSpawnSettings.SpawnerData(
                        EntityRegistry.getInstance().getEntityType(Naming.ONYX_FROG, OnyxFrogEntity.class),
                        1, 2, 6));
        BiomeDefaultFeatures.commonSpawns(builder2);

        return biome(false, 2.0F, 0.0F, builder2, builder, null);
    }

    private static Biome biome(boolean bl, float f, float g, int i, int j,
                               @Nullable Integer integer,
                               @Nullable Integer integer2,
                               MobSpawnSettings.Builder builder,
                               BiomeGenerationSettings.Builder builder2,
                               @Nullable Music music) {
        BiomeSpecialEffects.Builder builder3 = (new BiomeSpecialEffects.Builder())
                .waterColor(i).waterFogColor(j).fogColor(342234).skyColor(calculateSkyColor(f))
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music);
        if (integer != null) {
            builder3.grassColorOverride(integer);
        }

        if (integer2 != null) {
            builder3.foliageColorOverride(integer2);
        }

        return (new Biome.BiomeBuilder()).hasPrecipitation(bl)
                .temperature(f).downfall(g).specialEffects(builder3.build())
                .mobSpawnSettings(builder.build()).generationSettings(builder2.build()).build();
    }

    private static Biome biome(boolean bl, float f, float g,
                               MobSpawnSettings.Builder builder,
                               BiomeGenerationSettings.Builder builder2,
                               @Nullable Music music) {
        return biome(bl, f, g, 3422233, 323341, null, null, builder, builder2, music);
    }

}
