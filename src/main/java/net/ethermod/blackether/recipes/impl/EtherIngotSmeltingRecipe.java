package net.ethermod.blackether.recipes.impl;

import net.ethermod.blackether.recipes.core.ItemRecipeBuilder;
import net.ethermod.blackether.registries.ItemRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.function.Consumer;

import static net.minecraft.data.recipes.RecipeProvider.oreBlasting;
import static net.minecraft.data.recipes.RecipeProvider.oreSmelting;

public class EtherIngotSmeltingRecipe implements ItemRecipeBuilder {
    @Override
    public void build(Consumer<FinishedRecipe> exporter) {
        Item dust = ItemRegistry.getInstance().getItem(Naming.ONYX_DUST);
        oreSmelting(exporter, List.of(dust),
                RecipeCategory.MISC, ItemRegistry.getInstance().getItem(Naming.ETHER_INGOT),
                15f,//Experience
                100,  //Cook time
                Naming.ETHERMOD_ITEMGROUP);
        oreBlasting(exporter, List.of(dust),
                RecipeCategory.MISC, ItemRegistry.getInstance().getItem(Naming.ETHER_INGOT),
                15f,//Experience
                50,  //Cook time
                Naming.ETHERMOD_ITEMGROUP);
    }
}
