package net.ethermod.blackether.biomes;

import net.ethermod.blackether.BlackEtherMod;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MountainsBiome;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class OnyxBiome extends Biome {
    public OnyxBiome() {
        super(new Biome.Settings()
                .configureSurfaceBuilder(SurfaceBuilder.NETHER, SurfaceBuilder.NETHER_CONFIG)
                .precipitation(Precipitation.SNOW).category(Category.EXTREME_HILLS)
                .depth(0.24F)
                .scale(0.2F)
                .temperature(0.6F)
                .downfall(0.7F)
                .waterColor(4159204)
                .waterFogColor(329011)
                .parent((String)null));
        this.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL));
        this.addStructureFeature(Feature.STRONGHOLD, FeatureConfig.DEFAULT);
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
