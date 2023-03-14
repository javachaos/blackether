package net.ethermod.blackether.registries;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;

public abstract class BaseRegistry extends Registerable {
    private final HashMap<String, SoundEvent> soundEvents = new HashMap<>();
    private final HashMap<String, Block> blocks = new HashMap<>();
    private final HashMap<String, Item> items = new HashMap<>();

    protected void putItem(final String name, final Item item) {
        items.put(name, item);
    }

    protected Item getItem(final String name) {
        return items.get(name);
    }

    protected Block getBlock(final String name) {
        return blocks.get(name);
    }

    protected void putBlock(final String name, final Block block) {
        blocks.put(name, block);
    }

    protected SoundEvent getSoundEvent(final String name) {
        return soundEvents.get(name);
    }

    protected void putSoundEvent(final String name, final SoundEvent event) {
        soundEvents.put(name, event);
    }
}
