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

public class BasicToolsRecipe implements ItemRecipeBuilder {
    @Override
    public void build(Consumer<FinishedRecipe> exporter) {
        Block planks = BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_PLANKS);

        ShapedRecipeBuilder
                .shaped(RecipeCategory.TOOLS, Items.WOODEN_AXE , 1)
                .define('#', planks)
                .define('/', Items.STICK)
                .pattern("## ")
                .pattern("#/ ")
                .pattern(" / ")
                .group("tools")
                .unlockedBy("has_planks", has(planks)).save(exporter);
        ShapedRecipeBuilder
                .shaped(RecipeCategory.TOOLS, Items.WOODEN_HOE, 1)
                .define('#', planks)
                .define('/', Items.STICK)
                .pattern("## ")
                .pattern(" / ")
                .pattern(" / ")
                .group("tools")
                .unlockedBy("has_planks", has(planks)).save(exporter);
        ShapedRecipeBuilder
                .shaped(RecipeCategory.TOOLS, Items.WOODEN_PICKAXE, 1)
                .define('#', planks)
                .define('/', Items.STICK)
                .pattern("###")
                .pattern(" / ")
                .pattern(" / ")
                .group("tools")
                .unlockedBy("has_planks", has(planks)).save(exporter);
        ShapedRecipeBuilder
                .shaped(RecipeCategory.TOOLS, Items.WOODEN_SHOVEL, 1)
                .define('#', planks)
                .define('/', Items.STICK)
                .pattern(" # ")
                .pattern(" / ")
                .pattern(" / ")
                .group("tools")
                .unlockedBy("has_planks", has(planks)).save(exporter);
        ShapedRecipeBuilder
                .shaped(RecipeCategory.TOOLS, Items.WOODEN_SWORD, 1)
                .define('#', planks)
                .define('/', Items.STICK)
                .pattern(" # ")
                .pattern(" # ")
                .pattern(" / ")
                .group("tools")
                .unlockedBy("has_planks", has(planks)).save(exporter);

    }
}
