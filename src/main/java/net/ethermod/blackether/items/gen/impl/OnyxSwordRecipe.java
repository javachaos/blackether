package net.ethermod.blackether.items.gen.impl;

import net.ethermod.blackether.items.OnyxSword;
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

public class OnyxSwordRecipe implements ItemRecipeBuilder {
    public void build(Consumer<FinishedRecipe> exporter) {
        OnyxSword sword = (OnyxSword) ItemRegistry.getInstance().getItem(Naming.ONYX_SWORD);
        Item onyxOre = ItemRegistry.getInstance().getItem(Naming.ONYX_ORE);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword)
                .pattern(" X ")
                .pattern(" X ")
                .pattern(" S ")
                .define('X', onyxOre)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(onyxOre), has(onyxOre))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(exporter, new ResourceLocation(MOD_ID, getSimpleRecipeName(sword)));
    }
}
