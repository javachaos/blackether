package net.ethermod.blackether.items.gen;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public interface ItemRecipeBuilder {
    void build(Consumer<FinishedRecipe> exporter);
}
