package net.ethermod.blackether.data;

import net.ethermod.blackether.blocks.gen.core.BlockGen;
import net.ethermod.blackether.blocks.gen.impl.BlockOfEtherGenerator;
import net.ethermod.blackether.blocks.gen.impl.DarkGrassGenerator;
import net.ethermod.blackether.blocks.gen.impl.EtherOreBlockGenerator;
import net.ethermod.blackether.blocks.gen.impl.NeutronBombGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.List;
import java.util.function.BiConsumer;

public class EthermodLootTableGenerator extends FabricBlockLootTableProvider {

    private final List<BlockGen> blocks;

    public EthermodLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
        blocks = List.of(
                new NeutronBombGenerator(this),
                new EtherOreBlockGenerator(this),
                new DarkGrassGenerator(this),
                new BlockOfEtherGenerator(this));
    }

    @Override
    public void generate() {
        blocks.forEach(BlockGen::generate);
    }

    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> r) {
        blocks.forEach(x -> x.accept(r));
    }
}
