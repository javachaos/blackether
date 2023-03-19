package net.ethermod.blackether.items.gen.impl;

import net.ethermod.blackether.items.gen.core.ItemRecipeBuilder;
import net.ethermod.blackether.registries.ItemRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

import static net.minecraft.data.recipes.RecipeProvider.stonecutterResultFromBase;

public class OnyxDustRecipe implements ItemRecipeBuilder {
    @Override
    public void build(Consumer<FinishedRecipe> exporter) {
        Item onyxOre = ItemRegistry.getInstance().getItem(Naming.ONYX_ORE);
        Item onyxDust = ItemRegistry.getInstance().getItem(Naming.ONYX_DUST);
        stonecutterResultFromBase(exporter, RecipeCategory.MISC, onyxDust, onyxOre, 9);
    }
}
