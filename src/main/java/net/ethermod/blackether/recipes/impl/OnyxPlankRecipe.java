package net.ethermod.blackether.recipes.impl;

import net.ethermod.blackether.recipes.core.ItemRecipeBuilder;
import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

import static net.minecraft.data.recipes.RecipeProvider.getHasName;
import static net.minecraft.data.recipes.RecipeProvider.has;

public class OnyxPlankRecipe implements ItemRecipeBuilder {
    @Override
    public void build(Consumer<FinishedRecipe> exporter) {
        Block planks = BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_PLANKS);
        Block log = BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_LOG);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, planks, 4)
                .requires(log).group("planks")
                .unlockedBy(getHasName(log), has(log)).save(exporter);
    }
}
