package net.ethermod.blackether.data.recipes.impl;

import net.ethermod.blackether.data.recipes.core.ItemRecipeBuilder;
import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

import static net.minecraft.data.recipes.RecipeProvider.has;

public class StickRecipe implements ItemRecipeBuilder {
    @Override
    public void build(Consumer<FinishedRecipe> exporter) {
        Block planks = BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_PLANKS);
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, Items.STICK, 4)
                .pattern("#")
                .pattern("#")
                .define('#', planks)
                .group("sticks")
                .unlockedBy("has_planks", has(planks)).save(exporter);
    }
}
