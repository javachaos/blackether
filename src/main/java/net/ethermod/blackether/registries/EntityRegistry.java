package net.ethermod.blackether.registries;

import net.ethermod.blackether.entity.misc.NeutronBombEntity;
import net.ethermod.blackether.entity.mobs.OnyxFrogEntity;
import net.ethermod.blackether.entity.mobs.OnyxSnakeEntity;
import net.ethermod.blackether.utils.Naming;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.concurrent.atomic.AtomicReference;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

public class EntityRegistry extends BaseRegistry {

    private static final AtomicReference<EntityRegistry> INSTANCE = new AtomicReference<>();

    private EntityRegistry() {
    }

    public static EntityRegistry getInstance() {
        if (INSTANCE.get() == null) {
            INSTANCE.set(new EntityRegistry());
        }
        return INSTANCE.get();
    }

    public <T extends Entity> EntityType<T> getEntityType(final String name, Class<T> clazz) {
        return super.getEntity(name, clazz);
    }

    public <T extends Entity> void putEntityType(String name, EntityType<T> type) {
        super.putEntity(name, type);
    }

    public <T extends Mob> EntityType<T> registerMob(String name, EntityType.EntityFactory<T> entity,
                                                     float width, float height) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE,
                new ResourceLocation(MOD_ID, name), FabricEntityTypeBuilder.create(MobCategory.MONSTER, entity)
                        .dimensions(EntityDimensions.scalable(width, height)).build());
    }

    public <T extends Entity> EntityType<T> registerEntity(String name, EntityType.EntityFactory<T> entity,
                                                           float width, float height) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE,
                new ResourceLocation(MOD_ID, name), FabricEntityTypeBuilder.create(MobCategory.MISC, entity)
                        .dimensions(EntityDimensions.scalable(width, height)).build());
    }

    @Override
    public void register() {
        EntityType<NeutronBombEntity> neutronBombEntityEntityType = registerEntity(
                Naming.NEUTRON_BOMB, NeutronBombEntity::new, 0.75f, 0.75f);
        EntityType<OnyxSnakeEntity> onyxSnake = registerMob(Naming.ONYX_SNAKE,
                OnyxSnakeEntity::new, 1.5f, 1.5f);
        EntityType<OnyxFrogEntity> onyxFrog = registerMob(Naming.ONYX_FROG,
                OnyxFrogEntity::new, 1.5f, 1.5f);

        putEntityType(Naming.NEUTRON_BOMB, neutronBombEntityEntityType);
        putEntityType(Naming.ONYX_SNAKE, onyxSnake);
        putEntityType(Naming.ONYX_FROG, onyxFrog);
    }

    public void generateTranslation(FabricLanguageProvider.TranslationBuilder translationBuilder) {
        translationBuilder.add(getEntity(Naming.NEUTRON_BOMB, NeutronBombEntity.class), "Neutron Bomb");
        translationBuilder.add(getEntity(Naming.ONYX_SNAKE, OnyxSnakeEntity.class), "Onyx Snake");
        translationBuilder.add(getEntity(Naming.ONYX_FROG, OnyxFrogEntity.class), "Onyx Frog");
    }

    public void putResourceKey(String key, ResourceKey<ConfiguredFeature<?, ?>> value) {
        super.putResourceKey(key, value);
    }

    public ResourceKey<ConfiguredFeature<?, ?>> getResourceKey(String key) {
        return super.getResourceKey(key);
    }
}