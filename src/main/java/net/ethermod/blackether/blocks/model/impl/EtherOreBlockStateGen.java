package net.ethermod.blackether.blocks.model.impl;

import net.ethermod.blackether.blocks.EtherOreBlock;
import net.ethermod.blackether.blocks.model.core.BlockStateModelGenerator;
import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class EtherOreBlockStateGen implements BlockStateModelGenerator {
    @Override
    public void genModels(BlockModelGenerators blockStateModelGenerator) {
        EtherOreBlock etherOreBlock = (EtherOreBlock) BlockRegistry.getInstance().getBlock(Naming.ETHER_ORE_BLOCK);
        ResourceLocation resourceLocation = TexturedModel.CUBE.create(etherOreBlock, blockStateModelGenerator.modelOutput);
        ResourceLocation resourceLocation2 = blockStateModelGenerator.createSuffixedVariant(etherOreBlock,
                "_lit", ModelTemplates.CUBE_ALL, TextureMapping::cube);
        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(etherOreBlock)
                .with(BlockModelGenerators.createBooleanModelDispatch(
                        BlockStateProperties.LIT, resourceLocation2, resourceLocation)));
    }
}
