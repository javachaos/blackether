package net.ethermod.blackether.data.recipes.core;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public interface ItemRecipeBuilder {
    void build(Consumer<FinishedRecipe> exporter);
}
