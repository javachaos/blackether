package net.ethermod.blackether.recipes.impl;

import net.ethermod.blackether.blocks.NeutronBomb;
import net.ethermod.blackether.recipes.core.ItemRecipeBuilder;
import net.ethermod.blackether.registries.BlockRegistry;
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

public class NeutronBombRecipe implements ItemRecipeBuilder {

    @Override
    public void build(Consumer<FinishedRecipe> exporter) {
        NeutronBomb bomb = (NeutronBomb) BlockRegistry.getInstance().getBlock(Naming.NEUTRON_BOMB);
        Item neutron = ItemRegistry.getInstance().getItem(Naming.NEUTRONIUM);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, bomb)
                .pattern("XXX")
                .pattern("XSX")
                .pattern("XXX")
                .define('X', Items.TNT)
                .define('S', neutron)
                .unlockedBy(getHasName(neutron), has(neutron))
                .unlockedBy(getHasName(Items.TNT), has(Items.TNT))
                .save(exporter, new ResourceLocation(MOD_ID, getSimpleRecipeName(bomb)));
    }
}
