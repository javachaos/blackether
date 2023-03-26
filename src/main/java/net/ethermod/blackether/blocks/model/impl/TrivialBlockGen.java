package net.ethermod.blackether.blocks.model.impl;

import net.ethermod.blackether.blocks.model.core.BlockStateModelGenerator;
import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

public class TrivialBlockGen implements BlockStateModelGenerator {
    @Override
    public void genModels(BlockModelGenerators blockStateModelGenerator) {
        createTrivialEtherBlock(blockStateModelGenerator, Naming.BLOCK_OF_ETHER);
        createTrivialEtherBlock(blockStateModelGenerator, Naming.CHISELED_ETHER);
        createDarkGrassBlock(blockStateModelGenerator);
    }

    private void createTrivialEtherBlock(final BlockModelGenerators blockStateModelGenerator, String blockOfEther) {
        blockStateModelGenerator.createTrivialCube(
                BlockRegistry.getInstance().getBlock(blockOfEther));
    }

    private void createDarkGrassBlock(BlockModelGenerators blockStateModelGenerator) {
        ResourceLocation resourceLocation = new ResourceLocation(MOD_ID, Naming.DARK_GRASS_TEX);
        Block darkGrass = BlockRegistry.getInstance().getBlock(Naming.DARK_GRASS);

        TextureMapping textureMapping = (new TextureMapping()).put(TextureSlot.BOTTOM, resourceLocation)
                .copyForced(TextureSlot.BOTTOM, TextureSlot.PARTICLE)
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(darkGrass, "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(darkGrass, "_snow"));

        Variant variant = Variant.variant().with(VariantProperties.MODEL, ModelTemplates.CUBE_BOTTOM_TOP
                .createWithSuffix(darkGrass, "_snow", textureMapping, blockStateModelGenerator.modelOutput));

        blockStateModelGenerator.createGrassLikeBlock(darkGrass, ModelLocationUtils.getModelLocation(darkGrass), variant);
    }

}
