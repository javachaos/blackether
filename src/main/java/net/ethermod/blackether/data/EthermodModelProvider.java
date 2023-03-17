package net.ethermod.blackether.data;

import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.utils.Naming;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;

public class EthermodModelProvider extends FabricModelProvider {
    public EthermodModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createTrivialCube(
                BlockRegistry.getInstance().getBlock(Naming.ETHER_ORE_BLOCK));
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
    }
}
