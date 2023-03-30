package net.ethermod.blackether.data.blocks.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.function.BiConsumer;

public interface LootGenerator {
    void generate();

    void accept(final BiConsumer<ResourceLocation,
            LootTable.Builder> resourceLocationBuilderBiConsumer);
}
