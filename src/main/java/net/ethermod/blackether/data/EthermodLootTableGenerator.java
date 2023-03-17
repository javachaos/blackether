package net.ethermod.blackether.data;

import net.ethermod.blackether.blocks.EtherOreBlock;
import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.registries.ItemRegistry;
import net.ethermod.blackether.utils.Naming;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.function.BiConsumer;

public class EthermodLootTableGenerator extends FabricBlockLootTableProvider {

    public EthermodLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        createOreDrop(
                BlockRegistry.getInstance().getBlock(Naming.ETHER_ORE_BLOCK),
                ItemRegistry.getInstance().getItem(Naming.ETHER_ORE));

    }

    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> resourceLocationBuilderBiConsumer) {

    }
}
