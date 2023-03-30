package net.ethermod.blackether.data.world;

import net.ethermod.blackether.BlackEtherMod;
import net.ethermod.blackether.effects.ColoredDustParticleEffect;
import net.ethermod.blackether.entity.mobs.OnyxFrogEntity;
import net.ethermod.blackether.entity.mobs.OnyxSnakeEntity;
import net.ethermod.blackether.registries.EntityRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;
import static net.minecraft.data.worldgen.biome.OverworldBiomes.calculateSkyColor;

public class EthermodBiomes {

    public static void bootstrap(BootstapContext<Biome> bootstapContext) {
        if (BlackEtherMod.PROPERTIES.getBooleanProperty("enable.onyx.biome", true)) {
            ResourceKey<Biome> biome = ResourceKey.create(Registries.BIOME, new ResourceLocation(MOD_ID, "onyx_biome"));
            HolderGetter<PlacedFeature> holderGetter = bootstapContext.lookup(Registries.PLACED_FEATURE);
            HolderGetter<ConfiguredWorldCarver<?>> holderGetter2 = bootstapContext.lookup(Registries.CONFIGURED_CARVER);
            bootstapContext.register(biome,
                    create(holderGetter, holderGetter2));

        }
    }

    private static Biome create(HolderGetter<PlacedFeature> holderGetter, HolderGetter<ConfiguredWorldCarver<?>> holderGetter2) {
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(holderGetter, holderGetter2);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(builder);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                EthermodPlacedFeatures.ONYXTREE_KEY);
        BiomeDefaultFeatures.addSavannaGrass(builder);

        MobSpawnSettings.Builder builder2 = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(builder2);
        builder2.addSpawn(MobCategory.MONSTER,
                new MobSpawnSettings.SpawnerData(
                        EntityRegistry.getInstance().getEntityType(Naming.ONYX_FROG, OnyxFrogEntity.class),
                        1, 2, 6));
        builder2.addSpawn(MobCategory.MONSTER,
                new MobSpawnSettings.SpawnerData(
                        EntityRegistry.getInstance().getEntityType(Naming.ONYX_SNAKE, OnyxSnakeEntity.class),
                        1, 2, 6));
        BiomeDefaultFeatures.commonSpawns(builder2);

        return biome(builder2, builder);
    }

    private static Biome biome(MobSpawnSettings.Builder builder,
                               BiomeGenerationSettings.Builder builder2) {
        BiomeSpecialEffects.Builder builder3 = (new BiomeSpecialEffects.Builder())
                .grassColorOverride(0x3B3B3B)
                .ambientParticle(new AmbientParticleSettings(
                        new ColoredDustParticleEffect(0f, 0f, 0f, 0.2f),
                        0.12f))
                .waterColor(0)
                .waterFogColor(7039851)
                .fogColor(7829115)
                .skyColor(calculateSkyColor(6184036f))
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(null);
        return (new Biome.BiomeBuilder()).hasPrecipitation(true)
                .temperature((float) 2.0).downfall((float) 0.0).specialEffects(builder3.build())
                .mobSpawnSettings(builder.build()).generationSettings(builder2.build()).build();
    }

}
