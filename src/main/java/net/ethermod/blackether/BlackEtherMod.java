package net.ethermod.blackether;

import net.ethermod.blackether.entity.living.OnyxSnakeEntity;
import net.ethermod.blackether.registries.*;
import net.ethermod.blackether.utils.Naming;
import net.ethermod.blackether.utils.PropertyManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib.GeckoLib;

import java.util.ArrayDeque;
import java.util.Deque;

public class BlackEtherMod implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger(BlackEtherMod.class);
    public static final String MOD_ID = "ethermod";
    public static final PropertyManager PROPERTIES = new PropertyManager();

    @Override
    public void onInitialize() {
                LOGGER.debug("{} started initializing.", MOD_ID);
        GeckoLib.initialize();

        //Temp. deque to register all registerable registries
        Deque<Registerable> registries = new ArrayDeque<>();

        //Order of these matters.
        registries.add(EntityRegistry.getInstance());
        registries.add(SoundRegistry.getInstance());
        registries.add(BlockRegistry.getInstance());
        registries.add(ItemRegistry.getInstance());

        //Call register() method on each registerable
        registries.forEach(Registerable::register);

        //Done registering now free up this deque
        registries.clear();

        registerEntityAttributes();
        LOGGER.debug("{} finished initializing.", MOD_ID);
    }

    private void registerEntityAttributes() {
        EntityType<OnyxSnakeEntity> snake = EntityRegistry
                .getInstance()
                .getEntityType(
                        Naming.ONYX_SNAKE,
                        OnyxSnakeEntity.class);
        //Load Ores
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES, BlockRegistry.CUSTOM_ORE_PLACED_KEY);

        //Add onyx snake spawn to the deep dark.
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(Biomes.DEEP_DARK), MobCategory.MONSTER,
                snake,
                50, 5, 10);
        //Add onyx snake spawn to forest biome
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(Biomes.FOREST), MobCategory.MONSTER,
                snake,
                50, 5, 10);

        SpawnPlacements.register(snake, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules);

        FabricDefaultAttributeRegistry.register(snake, createGenericEntityAttributes());
        FuelRegistry.INSTANCE.add(ItemRegistry.getInstance().getItem(Naming.ETHER_ORE), 3000);
    }

    private AttributeSupplier.Builder createGenericEntityAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.0280000000298023224D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 5)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1);
    }


}
