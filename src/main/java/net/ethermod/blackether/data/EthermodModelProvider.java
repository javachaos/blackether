package net.ethermod.blackether.data;

import net.ethermod.blackether.blocks.EtherOreBlock;
import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.utils.Naming;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class EthermodModelProvider extends FabricModelProvider {
    public EthermodModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(final BlockModelGenerators blockStateModelGenerator) {
        createEtherOreBlock(blockStateModelGenerator);
        //TODO consider extracting this to an interface
        // instead of using just methods.
        createTrivialEtherBlock(blockStateModelGenerator, Naming.BLOCK_OF_ETHER);
        createTrivialEtherBlock(blockStateModelGenerator, Naming.CHISELED_ETHER);
    }

    private void createTrivialEtherBlock(final BlockModelGenerators blockStateModelGenerator, String blockOfEther) {
        blockStateModelGenerator.createTrivialCube(
                BlockRegistry.getInstance().getBlock(blockOfEther));
    }

    private void createEtherOreBlock(final BlockModelGenerators blockStateModelGenerator) {
        EtherOreBlock etherOreBlock = (EtherOreBlock) BlockRegistry.getInstance().getBlock(Naming.ETHER_ORE_BLOCK);
        ResourceLocation resourceLocation = TexturedModel.CUBE.create(etherOreBlock, blockStateModelGenerator.modelOutput);
        ResourceLocation resourceLocation2 = blockStateModelGenerator.createSuffixedVariant(etherOreBlock,
                "_lit", ModelTemplates.CUBE_ALL, TextureMapping::cube);
        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(etherOreBlock)
                .with(BlockModelGenerators.createBooleanModelDispatch(
                        BlockStateProperties.LIT, resourceLocation2, resourceLocation)));
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
    }
}
