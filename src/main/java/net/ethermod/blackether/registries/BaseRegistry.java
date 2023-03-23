package net.ethermod.blackether.registries;

import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public abstract class BaseRegistry extends Registerable {
    public static final Logger LOGGER = LogManager.getLogger(BaseRegistry.class);
    private final HashMap<String, SoundEvent> soundEvents = new HashMap<>();
    private final HashMap<String, Block> blocks = new HashMap<>();
    private final HashMap<String, Item> items = new HashMap<>();
    private final HashMap<String, EntityType<? extends Entity>> entities = new HashMap<>();
    private final HashMap<String, ResourceKey<ConfiguredFeature<?, ?>>> resourceKeys = new HashMap<>();

    protected void putResourceKey(String k, ResourceKey<ConfiguredFeature<?, ?>> v) {
        resourceKeys.put(k, v);
    }

    protected ResourceKey<ConfiguredFeature<?, ?>> getResourceKey(String k) {
        return resourceKeys.get(k);
    }

    protected HashMap<String, Block> getBlocks() {
        return blocks;
    }

    protected HashMap<String, Item> getItems() {
        return items;
    }

    protected HashMap<String, EntityType<? extends Entity>> getEntities() {
        return entities;
    }


    protected void putEntity(final String name, final EntityType<? extends Entity> entityType) {
        entities.put(name, entityType);
    }

    @SuppressWarnings("unchecked")
    protected <T extends Entity> EntityType<T> getEntity(final String name, Class<T> clazz) {
        EntityType<? extends Entity> entityType = entities.get(name);
        if (entityType != null && entityType.getBaseClass().isAssignableFrom(clazz)) {
            return (EntityType<T>) entityType;
        } else {
            return null;
        }
    }

    protected void putItem(final String name, final Item item) {
        LOGGER.debug("Adding item {} to item map.", name);
        items.put(name, item);
    }

    protected Item getItem(final String name) {
        return items.get(name);
    }

    protected Block getBlock(final String name) {
        return blocks.get(name);
    }

    protected void putBlock(final String name, final Block block) {
        LOGGER.debug("Adding block {} to block map.", name);
        blocks.put(name, block);
    }

    protected SoundEvent getSoundEvent(final String name) {
        return soundEvents.get(name);
    }

    protected void putSoundEvent(final String name, final SoundEvent event) {
        LOGGER.debug("Adding sound event {} to sound event map.", name);
        soundEvents.put(name, event);
    }
}
