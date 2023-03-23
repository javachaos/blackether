package net.ethermod.blackether;

import net.ethermod.blackether.entity.mobs.OnyxFrogEntity;
import net.ethermod.blackether.entity.mobs.OnyxSnakeEntity;
import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.registries.EntityRegistry;
import net.ethermod.blackether.registries.ItemRegistry;
import net.ethermod.blackether.utils.Naming;
import net.ethermod.blackether.utils.PropertyManager;
import net.ethermod.blackether.utils.RegistryInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib.GeckoLib;

public class BlackEtherMod implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger(BlackEtherMod.class);
    public static final String MOD_ID = "ethermod";
    public static final PropertyManager PROPERTIES = new PropertyManager();


//TODO https://github.com/Witixin1512/GeckoLib-MultiLoader-Template/tree/gl4-vanillagradle#readme
// work towards shifting to this project template.

//TODO Finish onyx_biome to grey/black atmosphere
// add mob spawns
// add a new mob
// add a sound to the existing mob (snake)

    @Override
    public void onInitialize() {
        LOGGER.debug("{} started initializing.", MOD_ID);
        GeckoLib.initialize();
        RegistryInitializer.registerAll();
        registerEntityAttributes();
        LOGGER.debug("{} finished initializing.", MOD_ID);
    }

    private void registerEntityAttributes() {
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
        initBiomes();
        //Load Ores
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES, BlockRegistry.CUSTOM_ORE_PLACED_KEY);

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

        SpawnPlacements.register(snake, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules);

        FabricDefaultAttributeRegistry.register(snake, createGenericEntityAttributes());
        FabricDefaultAttributeRegistry.register(frog, createGenericEntityAttributes().add(Attributes.JUMP_STRENGTH, 0.5));
        FuelRegistry.INSTANCE.add(ItemRegistry.getInstance().getItem(Naming.ETHER_ORE), 3000);
    }

    private void initBiomes() {
        EntityType<OnyxSnakeEntity> snake = EntityRegistry
                .getInstance()
                .getEntityType(
                        Naming.ONYX_SNAKE,
                        OnyxSnakeEntity.class);
        //TODO consider adding mixin to get biome to spawn in overworld
        // or figure out how to do this using json.
        ResourceKey<Biome> biome = ResourceKey.create(Registries.BIOME,
                new ResourceLocation(MOD_ID, Naming.ONYX_BIOME));
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(biome),
                MobCategory.MONSTER,
                snake,
                50, 5, 10);
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
