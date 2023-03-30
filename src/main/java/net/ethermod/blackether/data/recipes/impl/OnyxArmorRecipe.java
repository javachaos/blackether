package net.ethermod.blackether.data.recipes.impl;

import net.ethermod.blackether.data.recipes.core.ItemRecipeBuilder;
import net.ethermod.blackether.registries.ItemRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;
import static net.minecraft.data.recipes.RecipeProvider.*;

public class OnyxArmorRecipe implements ItemRecipeBuilder {

    @Override
    public void build(Consumer<FinishedRecipe> exporter) {
        Item boots = ItemRegistry.getInstance().getItem(Naming.ONYX_BOOTS);
        Item leggings = ItemRegistry.getInstance().getItem(Naming.ONYX_LEGGINGS);
        Item chectplate = ItemRegistry.getInstance().getItem(Naming.ONYX_CHESTPLATE);
        Item helmet = ItemRegistry.getInstance().getItem(Naming.ONYX_HELMET);
        Item onyxOre = ItemRegistry.getInstance().getItem(Naming.ONYX_ORE);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, boots)
                .pattern("   ")
                .pattern("X X")
                .pattern("X X")
                .define('X', onyxOre)
                .unlockedBy(getHasName(onyxOre), has(onyxOre))
                .save(exporter, new ResourceLocation(MOD_ID, getSimpleRecipeName(boots)));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, leggings)
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .define('X', onyxOre)
                .unlockedBy(getHasName(onyxOre), has(onyxOre))
                .save(exporter, new ResourceLocation(MOD_ID, getSimpleRecipeName(leggings)));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chectplate)
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', onyxOre)
                .unlockedBy(getHasName(onyxOre), has(onyxOre))
                .save(exporter, new ResourceLocation(MOD_ID, getSimpleRecipeName(chectplate)));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet)
                .pattern("XXX")
                .pattern("X X")
                .pattern("   ")
                .define('X', onyxOre)
                .unlockedBy(getHasName(onyxOre), has(onyxOre))
                .save(exporter, new ResourceLocation(MOD_ID, getSimpleRecipeName(helmet)));
    }
}
