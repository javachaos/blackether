package net.ethermod.blackether.recipes.impl;

import net.ethermod.blackether.blocks.BlockOfEther;
import net.ethermod.blackether.recipes.core.ItemRecipeBuilder;
import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.registries.ItemRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

import static net.minecraft.data.recipes.RecipeProvider.nineBlockStorageRecipesRecipesWithCustomUnpacking;
import static net.minecraft.data.recipes.RecipeProvider.nineBlockStorageRecipesWithCustomPacking;

public class BlockOfEtherRecipe implements ItemRecipeBuilder {
    public void build(Consumer<FinishedRecipe> exporter) {
        BlockOfEther blockOfEther = (BlockOfEther) BlockRegistry.getInstance().getBlock(Naming.BLOCK_OF_ETHER);
        Item etherOre = ItemRegistry.getInstance().getItem(Naming.ETHER_ORE);

        nineBlockStorageRecipesRecipesWithCustomUnpacking(exporter,
                RecipeCategory.MISC, etherOre,
                RecipeCategory.BUILDING_BLOCKS,
                blockOfEther, "onyx_ore_from_block_of_ether", Naming.ETHER_ORE);
        nineBlockStorageRecipesWithCustomPacking(exporter,
                RecipeCategory.MISC, etherOre,
                RecipeCategory.MISC, blockOfEther,
                "block_of_ether_from_onyx_ore", Naming.ETHER_ORE);
    }
}
