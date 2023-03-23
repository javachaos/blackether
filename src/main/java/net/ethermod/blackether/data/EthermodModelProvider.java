package net.ethermod.blackether.data;

import net.ethermod.blackether.blocks.EtherOreBlock;
import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.utils.Naming;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

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
        generateTrees(blockStateModelGenerator);

    }

    private void generateTrees(BlockModelGenerators blockStateModelGenerator) {
        Block onyxWoodLog = BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_LOG);
        Block onyxWood = BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_WOOD);
        Block onyxWoodLogStripped = BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_LOG_STRIPPED);
        Block onyxWoodStripped = BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_WOOD_STRIPPED);
        Block onyxWoodPlanks = BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_PLANKS);
        Block onyxWoodLeaves = BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_LEAVES);
        Block onyxWoodSapling = BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_SAPLING);
        Block pottedOnyxWoodSapling = BlockRegistry.getInstance().getBlock(Naming.POTTED_ONYXWOOD_SAPLING);
        Block petrifiedOnyxWoodSlab = BlockRegistry.getInstance().getBlock(Naming.PETRIFIED_ONYXWOOD_SLAB);

        blockStateModelGenerator.woodProvider(onyxWoodLogStripped)
                .logWithHorizontal(onyxWoodLogStripped).wood(onyxWoodStripped);
        blockStateModelGenerator.woodProvider(onyxWoodLog)
                .logWithHorizontal(onyxWoodLog).wood(onyxWood);
        blockStateModelGenerator.createTrivialBlock(onyxWoodLeaves, TexturedModel.LEAVES);
        blockStateModelGenerator.createPlant(onyxWoodSapling, pottedOnyxWoodSapling,
                BlockModelGenerators.TintState.NOT_TINTED);
        BlockFamily b = BlockFamilies.familyBuilder(onyxWoodPlanks).getFamily();
        //TODO Implement the block below (adding items and textures as needed
//                .button(Blocks.OAK_BUTTON).fence(Blocks.OAK_FENCE)
//                .fenceGate(Blocks.OAK_FENCE_GATE).pressurePlate(Blocks.OAK_PRESSURE_PLATE)
//                .sign(Blocks.OAK_SIGN, Blocks.OAK_WALL_SIGN).slab(Blocks.OAK_SLAB)
//                .stairs(Blocks.OAK_STAIRS).door(Blocks.OAK_DOOR).trapdoor(Blocks.OAK_TRAPDOOR)
//                .recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
        blockStateModelGenerator.family(b.getBaseBlock()).generateFor(b);

        ResourceLocation resourceLocation = new ResourceLocation(MOD_ID, Naming.ONYXWOOD_PLANKS);
        TexturedModel texturedModel = TexturedModel.CUBE.get(onyxWoodPlanks);
        ResourceLocation resourceLocation2 = ModelTemplates.SLAB_BOTTOM.create(petrifiedOnyxWoodSlab, texturedModel.getMapping(),
                blockStateModelGenerator.modelOutput);
        ResourceLocation resourceLocation3 = ModelTemplates.SLAB_TOP.create(petrifiedOnyxWoodSlab, texturedModel.getMapping(),
                blockStateModelGenerator.modelOutput);
        blockStateModelGenerator.blockStateOutput.accept(
                BlockModelGenerators.createSlab(petrifiedOnyxWoodSlab, resourceLocation2, resourceLocation3, resourceLocation));
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
