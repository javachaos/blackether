package net.ethermod.blackether.items.gen.impl;

import net.ethermod.blackether.items.gen.core.ItemRecipeBuilder;
import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

import static net.minecraft.data.recipes.RecipeProvider.*;

public class OnyxSlabRecipe implements ItemRecipeBuilder {
    @Override
    public void build(Consumer<FinishedRecipe> exporter) {
        Block slab = BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_SLAB);
        Block plank = BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_PLANKS);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, slab, plank, 2);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, slab,
                Ingredient.of(plank))
                .unlockedBy("has_planks", has(plank))
                .save(exporter);

    }
}
