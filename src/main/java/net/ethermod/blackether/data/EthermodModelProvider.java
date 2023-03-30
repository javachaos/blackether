package net.ethermod.blackether.data;

import net.ethermod.blackether.data.model.impl.EtherOreBlockStateGen;
import net.ethermod.blackether.data.model.impl.TreeBlockStateGen;
import net.ethermod.blackether.data.model.impl.TrivialBlockGen;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;

import java.util.List;

public class EthermodModelProvider extends FabricModelProvider {
    public EthermodModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(final BlockModelGenerators blockStateModelGenerator) {
        List.of(
                        new TreeBlockStateGen(),
                        new EtherOreBlockStateGen(),
                        new TrivialBlockGen())
                .forEach(x -> x.genModels(blockStateModelGenerator));
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
    }
}
