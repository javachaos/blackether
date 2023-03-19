package net.ethermod.blackether.blocks.gen.core;

import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.function.BiConsumer;

public abstract class BlockGenerator implements BlockGen {

    protected LootTable.Builder builder;

    protected final String blockName;
    protected final Block block;
    protected final Item item;
    protected final FabricBlockLootTableProvider lootTableGenerator;

    public BlockGenerator(final EthermodLootTableGenerator ltg, final String blockName,
                          final Block b, final Item i) {
        this.block = b;
        this.blockName = blockName;
        this.item = i;
        this.lootTableGenerator = ltg;
    }

    protected void setBuilder(LootTable.Builder apply) {
        this.builder = apply;
    }

    public abstract void generate();

    public abstract void accept(final BiConsumer<ResourceLocation,
            LootTable.Builder> resourceLocationBuilderBiConsumer);

}
