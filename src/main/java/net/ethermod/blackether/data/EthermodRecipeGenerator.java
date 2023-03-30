package net.ethermod.blackether.data;

import net.ethermod.blackether.data.recipes.impl.*;
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
        List.of(
                        new OnyxPickaxeRecipe(),
                        new OnyxOreSmeltingRecipe(),
                        new OnyxAppleRecipe(),
                        new OnyxHoeRecipe(),
                        new OnyxShovelRecipe(),
                        new OnyxSwordRecipe(),
                        new OnyxAxeRecipe(),
                        new NeutroniumSmeltingRecipe(),
                        new BlockOfEtherRecipe(),
                        new DiamondSmeltingRecipe(),
                        new NeutronBombRecipe(),
                        new OnyxDustRecipe(),
                        new OnyxArmorRecipe(),
                        new StickRecipe(),
                        new OnyxSlabRecipe(),
                        new OnyxPlankRecipe(),
                        new ChiseledEtherRecipe(),
                        new EtherIngotSmeltingRecipe(),
                        new OnyxDoorRecipe())
                .forEach(x -> x.build(exporter));
    }
}
