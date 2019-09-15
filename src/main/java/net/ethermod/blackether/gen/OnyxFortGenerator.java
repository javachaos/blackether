package net.ethermod.blackether.gen;

import net.ethermod.blackether.BlackEtherMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.*;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.structure.processor.BlockRotStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.List;
import java.util.Random;

public class OnyxFortGenerator
{
    private static final Identifier id = new Identifier(BlackEtherMod.MODID,"forts/onyx_fort");

    public static void addPieces(StructureManager structureManager, BlockPos blockPos, BlockRotation rotation, List<StructurePiece> list_1, Random random, DefaultFeatureConfig defaultFeatureConfig)
    {
        list_1.add(new OnyxFortGenerator.Piece(structureManager, id, blockPos, rotation));
    }

    public static class Piece extends SimpleStructurePiece
    {
        private final BlockRotation rotation;
        private final Identifier identifier;

        public Piece(StructureManager structureManager_1, CompoundTag compoundTag_1) {
            super(BlackEtherMod.myStructurePieceType, compoundTag_1);

            this.identifier = new Identifier(compoundTag_1.getString("onyxfort"));
            this.rotation = BlockRotation.valueOf(compoundTag_1.getString("rotation"));

            this.setStructureData(structureManager_1);
        }

        public Piece(StructureManager structureManager, Identifier identifier, BlockPos pos, BlockRotation rotation)
        {
            super(BlackEtherMod.myStructurePieceType, 0);
            this.rotation = rotation;
            this.identifier = identifier;
            this.pos = pos;
            this.setStructureData(structureManager);
        }

        @Override
        protected void toNbt(CompoundTag compoundTag_1) {
            super.toNbt(compoundTag_1);
            compoundTag_1.putString("onyxfort", this.identifier.toString());
            compoundTag_1.putString("rotation", this.rotation.name());
        }

        public void setStructureData(StructureManager structureManager) {
            Structure structure_1 = structureManager.getStructureOrBlank(this.identifier);
            StructurePlacementData structurePlacementData_1 = (new StructurePlacementData()).setRotation(this.rotation).setMirrored(BlockMirror.NONE).setPosition(pos).addProcessor(BlockIgnoreStructureProcessor.IGNORE_AIR_AND_STRUCTURE_BLOCKS);
            this.setStructureData(structure_1, this.pos, structurePlacementData_1);
        }

        @Override
        protected void handleMetadata(String s, BlockPos blockPos, IWorld iWorld, Random random, MutableIntBoundingBox mutableIntBoundingBox)
        {
        }

        @Override
        public boolean generate(IWorld iWorld_1, Random random_1, MutableIntBoundingBox mutableIntBoundingBox_1, ChunkPos chunkPos_1)
        {
            int yHeight = iWorld_1.getTop(Heightmap.Type.WORLD_SURFACE_WG, this.pos.getX() + 5, this.pos.getZ() + 5);
            this.pos = this.pos.add(0, yHeight - 1, 0);
            return super.generate(iWorld_1, random_1, mutableIntBoundingBox_1, chunkPos_1);
        }
    }
}
