package net.ethermod.blackether.recipes.impl;

import net.ethermod.blackether.recipes.core.ItemRecipeBuilder;
import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;

import java.util.function.Consumer;

import static net.minecraft.data.recipes.RecipeProvider.chiseled;

public class ChiseledEtherRecipe implements ItemRecipeBuilder {
    @Override
    public void build(Consumer<FinishedRecipe> exporter) {
        chiseled(exporter,
                RecipeCategory.BUILDING_BLOCKS,
                BlockRegistry.getInstance().getBlock(Naming.CHISELED_ETHER),
                BlockRegistry.getInstance().getBlock(Naming.BLOCK_OF_ETHER));
    }
}
