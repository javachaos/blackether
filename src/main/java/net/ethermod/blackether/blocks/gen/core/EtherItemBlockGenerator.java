package net.ethermod.blackether.blocks.gen.core;

import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.registries.ItemRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

public class EtherItemBlockGenerator extends BlockGenerator {

    public EtherItemBlockGenerator(final EthermodLootTableGenerator ltg,
                                   String blockName, String itemName, int dropMin, int dropMax) {
        super(ltg, blockName, BlockRegistry.getInstance().getBlock(blockName),
                ItemRegistry.getInstance().getItem(itemName));
        setBuilder(lootTableGenerator.createOreDrop(block, item)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(dropMin, dropMax)))
                .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)));
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
