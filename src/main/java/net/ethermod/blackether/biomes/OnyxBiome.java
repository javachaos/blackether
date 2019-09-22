package net.ethermod.blackether.biomes;

import net.ethermod.blackether.BlackEtherMod;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class OnyxBiome extends Biome {
    public OnyxBiome() {
        super(new Biome.Settings()
                .configureSurfaceBuilder(CustomSurfaceBuilder.ONYX, CustomSurfaceBuilder.ONYX_CONFIG)
                .precipitation(Precipitation.SNOW).category(Category.FOREST)
                .depth(0.024F)
                .scale(1.2F)
                .temperature(0.06F)
                .downfall(0.7F)
                .waterColor(5592405)
                .waterFogColor(11184810)
                .parent(null));
        this.addStructureFeature(BlackEtherMod.onyxFortFeature, FeatureConfig.DEFAULT);
        DefaultBiomeFeatures.addFossils(this);
        DefaultBiomeFeatures.addInfestedStone(this);
        DefaultBiomeFeatures.addEmeraldOre(this);
        DefaultBiomeFeatures.addDefaultStructures(this);
        DefaultBiomeFeatures.addDefaultLakes(this);
        DefaultBiomeFeatures.addDungeons(this);
        DefaultBiomeFeatures.addMineables(this);
        DefaultBiomeFeatures.addDefaultOres(this);
        this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.IRON_GOLEM, 12, 4, 4));
        this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.SPIDER, 100, 4, 4));
        this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.ZOMBIE, 95, 4, 4));
        this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));
        this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.WITCH, 5, 1, 1));
    }

}
