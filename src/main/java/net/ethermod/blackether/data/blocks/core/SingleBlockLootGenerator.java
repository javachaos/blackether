package net.ethermod.blackether.data.blocks.core;

import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.function.BiConsumer;

public abstract class SingleBlockLootGenerator implements LootGenerator {

    protected LootTable.Builder builder;
    protected final String blockName;
    protected final Block block;

    protected final FabricBlockLootTableProvider lootTableGenerator;

    public SingleBlockLootGenerator(final EthermodLootTableGenerator ltg,
                                    final String blockName,
                                    final Block b) {
        this.block = b;
        this.blockName = blockName;
        this.lootTableGenerator = ltg;
    }

    protected void setBuilder(LootTable.Builder apply) {
        this.builder = apply;
    }

    public abstract void generate();

    public abstract void accept(final BiConsumer<ResourceLocation,
            LootTable.Builder> resourceLocationBuilderBiConsumer);
}
