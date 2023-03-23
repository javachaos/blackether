package net.ethermod.blackether.world.gen;

import net.ethermod.blackether.entity.mobs.OnyxFrogEntity;
import net.ethermod.blackether.entity.mobs.OnyxSnakeEntity;
import net.ethermod.blackether.registries.EntityRegistry;
import net.ethermod.blackether.utils.Naming;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;

public class EthermodEntitySpawns {
    public static void spawnCreatures() {
        EntityType<OnyxSnakeEntity> snake = EntityRegistry
                .getInstance()
                .getEntityType(
                        Naming.ONYX_SNAKE,
                        OnyxSnakeEntity.class);
        EntityType<OnyxFrogEntity> frog = EntityRegistry
                .getInstance()
                .getEntityType(
                        Naming.ONYX_FROG,
                        OnyxFrogEntity.class);

        //Add onyx snake spawn to the deep dark.
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(Biomes.DEEP_DARK), MobCategory.MONSTER,
                snake,
                50, 5, 10);
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(), MobCategory.MONSTER,
                snake,
                50, 5, 10);
        //Add onyx snake spawn to forest biome
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(Biomes.FOREST), MobCategory.MONSTER,
                snake,
                50, 5, 10);
        //Add onyx frog spawn to forest biome
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(Biomes.FOREST), MobCategory.MONSTER,
                frog,
                50, 5, 10);

        SpawnPlacements.register(snake, SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules);
        FabricDefaultAttributeRegistry.register(snake, createGenericEntityAttributes());
        FabricDefaultAttributeRegistry.register(frog, createGenericEntityAttributes().add(Attributes.JUMP_STRENGTH, 0.5));
    }

    private static AttributeSupplier.Builder createGenericEntityAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.0280000000298023224D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 5)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1);
    }
}
