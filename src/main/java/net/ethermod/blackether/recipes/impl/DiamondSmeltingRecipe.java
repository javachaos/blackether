package net.ethermod.blackether.recipes.impl;

import net.ethermod.blackether.recipes.core.ItemRecipeBuilder;
import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.function.Consumer;

import static net.minecraft.data.recipes.RecipeProvider.oreBlasting;
import static net.minecraft.data.recipes.RecipeProvider.oreSmelting;

public class DiamondSmeltingRecipe implements ItemRecipeBuilder {
    @Override
    public void build(Consumer<FinishedRecipe> exporter) {
        Block blockOfEther = BlockRegistry.getInstance().getBlock(Naming.BLOCK_OF_ETHER);
        oreSmelting(exporter, List.of(blockOfEther),
                RecipeCategory.MISC, Items.DIAMOND,
                5f,//Experience
                900,  //Cook time
                Naming.ETHERMOD_ITEMGROUP);
        oreBlasting(exporter, List.of(blockOfEther),
                RecipeCategory.MISC, Items.DIAMOND,
                5f,//Experience
                300,  //Cook time
                Naming.ETHERMOD_ITEMGROUP);
    }
}
