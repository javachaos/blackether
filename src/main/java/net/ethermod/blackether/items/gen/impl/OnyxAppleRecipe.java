package net.ethermod.blackether.items.gen.impl;

import net.ethermod.blackether.items.OnyxApple;
import net.ethermod.blackether.items.gen.core.ItemRecipeBuilder;
import net.ethermod.blackether.registries.ItemRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;
import static net.minecraft.data.recipes.RecipeProvider.*;

public class OnyxAppleRecipe implements ItemRecipeBuilder {

    @Override
    public void build(Consumer<FinishedRecipe> exporter) {
        OnyxApple apple = (OnyxApple) ItemRegistry.getInstance().getItem(Naming.ONYX_APPLE);
        Item onyxOre = ItemRegistry.getInstance().getItem(Naming.ONYX_ORE);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, apple)
                .pattern("XXX")
                .pattern("XSX")
                .pattern("XXX")
                .define('X', onyxOre)
                .define('S', Items.APPLE)
                .unlockedBy(getHasName(onyxOre), has(onyxOre))
                .unlockedBy(getHasName(apple), has(apple))
                .save(exporter, new ResourceLocation(MOD_ID, getSimpleRecipeName(apple)));
    }
}
