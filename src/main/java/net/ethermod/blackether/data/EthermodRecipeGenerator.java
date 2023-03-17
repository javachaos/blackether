package net.ethermod.blackether.data;

import net.ethermod.blackether.items.gen.OnyxOreSmeltingRecipe;
import net.ethermod.blackether.items.gen.PickaxeRecipe;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.List;
import java.util.function.Consumer;

public class EthermodRecipeGenerator extends FabricRecipeProvider {
    public EthermodRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> exporter) {
        //To add a new recipe create a class which implements ItemRecipeBuilder
        // in items.gen and add it to this list.
        List.of(new PickaxeRecipe(),
                new OnyxOreSmeltingRecipe())
                .forEach(x -> x.build(exporter));
    }
}
