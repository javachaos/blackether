package net.ethermod.blackether.data;

import net.ethermod.blackether.blocks.gen.core.LootGenerator;
import net.ethermod.blackether.blocks.gen.impl.*;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.List;
import java.util.function.BiConsumer;

public class EthermodLootTableGenerator extends FabricBlockLootTableProvider {

    private final List<LootGenerator> blocks;

    public EthermodLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
        blocks = List.of(
                new NeutronBombLootGenerator(this),
                new EtherOreBlockLootGenerator(this),
                new DarkGrassLootGenerator(this),
                new BlockOfEtherLootGenerator(this),
                new OnyxFortLootTableGenerator(this));
    }

    @Override
    public void generate() {
        blocks.forEach(LootGenerator::generate);
    }

    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> r) {
        blocks.forEach(x -> x.accept(r));
    }
}
