package net.ethermod.blackether.recipes.impl;

import net.ethermod.blackether.items.EtherIngot;
import net.ethermod.blackether.recipes.core.ItemRecipeBuilder;
import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.registries.ItemRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

import static net.minecraft.data.recipes.RecipeProvider.*;

public class OnyxDoorRecipe implements ItemRecipeBuilder {
    @Override
    public void build(Consumer<FinishedRecipe> exporter) {
        EtherIngot i = (EtherIngot) ItemRegistry.getInstance().getItem(Naming.ETHER_INGOT);
        doorBuilder(BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_DOOR),
                Ingredient.of(i))
                .unlockedBy(getHasName(i), has(i)).save(exporter);

    }
}
