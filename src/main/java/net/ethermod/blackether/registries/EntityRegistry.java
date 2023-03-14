package net.ethermod.blackether.registries;

import net.ethermod.blackether.entity.living.OnyxSnakeEntity;
import net.ethermod.blackether.entity.misc.NeutronBombEntity;
import net.ethermod.blackether.utils.Naming;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;

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

    public static EntityType<NeutronBombEntity> NEUTRON_BOMB_ENTITY;
    public static EntityType<OnyxSnakeEntity> ONYX_SNAKE;

    public <T extends Mob> EntityType<T> registerMob(String name, EntityType.EntityFactory<T> entity,
                                                     float width, float height) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE,
                new ResourceLocation(MOD_ID, name), FabricEntityTypeBuilder.create(MobCategory.CREATURE, entity)
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
        NEUTRON_BOMB_ENTITY = registerEntity(
                Naming.NEUTRON_BOMB, NeutronBombEntity::new, 0.75f, 0.75f);
        ONYX_SNAKE = registerMob(Naming.ONYX_SNAKE,
                OnyxSnakeEntity::new, 1.5f, 1.5f);
    }
}