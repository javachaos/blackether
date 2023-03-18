package net.ethermod.blackether.data;

import net.ethermod.blackether.blocks.EtherOreBlock;
import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.registries.ItemRegistry;
import net.ethermod.blackether.utils.Naming;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class EthermodLootTableGenerator extends FabricBlockLootTableProvider {

    public EthermodLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        EtherOreBlock etherBlock = (EtherOreBlock) BlockRegistry.getInstance().getBlock(Naming.ETHER_ORE_BLOCK);
        Item etherOre = ItemRegistry.getInstance().getItem(Naming.ETHER_ORE);
        add(etherBlock, block -> createSilkTouchDispatchTable(block, this.applyExplosionDecay(block,
                        LootItem.lootTableItem(etherOre)
                        .apply(SetItemCountFunction.setCount(
                          UniformGenerator.between(4.0F, 5.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        dropWhenSilkTouch(etherBlock);
    }
    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> resourceLocationBuilderBiConsumer) {
        // TODO add our items to the loottable.
    }
}
