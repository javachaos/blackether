package net.ethermod.blackether.data.blocks.core;

import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.function.BiConsumer;

public abstract class EtherLootTableGenerator implements LootGenerator {

    protected LootTable.Builder builder;
    protected final FabricBlockLootTableProvider lootTableGenerator;

    public EtherLootTableGenerator(final EthermodLootTableGenerator ltg) {
        if (ltg == null) {
            throw new IllegalArgumentException("Loot table generator cannot be null.");
        }
        this.lootTableGenerator = ltg;
    }

    protected void setBuilder(LootTable.Builder apply) {
        this.builder = apply;
    }

    @Override
    public abstract void generate();

    @Override
    public abstract void accept(BiConsumer<ResourceLocation, LootTable.Builder> resourceLocationBuilderBiConsumer);
}
