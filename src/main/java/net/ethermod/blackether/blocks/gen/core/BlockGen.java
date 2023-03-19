package net.ethermod.blackether.blocks.gen.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.function.BiConsumer;

public interface BlockGen {
    void generate();

    void accept(final BiConsumer<ResourceLocation,
            LootTable.Builder> resourceLocationBuilderBiConsumer);
}
