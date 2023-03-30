package net.ethermod.blackether.data.blocks.core;

import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.function.BiConsumer;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

public class EtherBlockLootGenerator extends SingleBlockLootGenerator {

    public EtherBlockLootGenerator(final EthermodLootTableGenerator ltg,
                                   String blockName) {
        super(ltg, blockName, BlockRegistry.getInstance().getBlock(blockName));
        setBuilder(LootTable.lootTable().withPool(
                ltg.applyExplosionCondition(block,
                        LootPool.lootPool().setRolls(
                                ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(block)))));
    }

    @Override
    public void generate() {
        lootTableGenerator.add(block, builder);
        lootTableGenerator.dropWhenSilkTouch(block);
    }

    @Override
    public void accept(final BiConsumer<ResourceLocation, LootTable.Builder> resourceLocationBuilderBiConsumer) {
        resourceLocationBuilderBiConsumer.accept(new ResourceLocation(MOD_ID,
                Naming.BLOCKS_FOLDER + blockName), builder);
    }
}

