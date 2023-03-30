package net.ethermod.blackether.data.recipes.impl;

import net.ethermod.blackether.data.recipes.core.ItemRecipeBuilder;
import net.ethermod.blackether.registries.ItemRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.function.Consumer;

import static net.minecraft.data.recipes.RecipeProvider.oreBlasting;
import static net.minecraft.data.recipes.RecipeProvider.oreSmelting;

public class OnyxOreSmeltingRecipe implements ItemRecipeBuilder {
    @Override
    public void build(Consumer<FinishedRecipe> exporter) {
        Item onyxOre = ItemRegistry.getInstance().getItem(Naming.ONYX_ORE);
        oreSmelting(exporter, List.of(Items.DIAMOND),
                RecipeCategory.MISC, onyxOre,
                10f,//Experience
                900,  //Cook time
                Naming.ETHERMOD_ITEMGROUP);
        oreBlasting(exporter, List.of(Items.DIAMOND),
                RecipeCategory.MISC, onyxOre,
                10f,//Experience
                300,  //Cook time
                Naming.ETHERMOD_ITEMGROUP);
    }
}
