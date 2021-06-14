package net.ethermod.blackether.gen;

import net.ethermod.blackether.BlackEtherMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.*;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.List;
import java.util.Random;

public class OnyxFortGenerator {
    //    private static final Identifier id = new Identifier(BlackEtherMod.MODID,"forts/onyx_fort");
//
//    public static void addPieces(StructureManager structureManager, BlockPos blockPos, BlockRotation rotation, List<StructurePiece> list_1, Random random, DefaultFeatureConfig defaultFeatureConfig)
//    {
//        list_1.add(new OnyxFortGenerator.Piece(structureManager, id, blockPos, rotation));
//    }
    private static final Identifier onyxfort_id = new Identifier(BlackEtherMod.MODID, "forts/onyx_fort");

    public static void addPieces(StructureManager manager, BlockPos pos, BlockRotation rotation, List<StructurePiece> pieces) {
        pieces.add(new Piece(manager, onyxfort_id, pos, rotation));
    }

    public static class Piece extends SimpleStructurePiece {
        private final BlockRotation rotation;
        private final Identifier identifier;

        public Piece(StructureManager structureManager_1, CompoundTag compoundTag_1) {
            super(BlackEtherMod.ONYXFORT_PIECE, compoundTag_1);

            this.identifier = new Identifier(compoundTag_1.getString("onyxfort"));
            this.rotation = BlockRotation.valueOf(compoundTag_1.getString("rotation"));

            this.setStructureData(structureManager_1);
        }

        public Piece(StructureManager structureManager, Identifier identifier, BlockPos pos, BlockRotation rotation) {
            super(BlackEtherMod.ONYXFORT_PIECE, 0);
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
            StructurePlacementData structurePlacementData_1 = (new StructurePlacementData())
                    .setRotation(this.rotation)
                    .setPosition(pos)
                    .addProcessor(BlockIgnoreStructureProcessor.IGNORE_AIR_AND_STRUCTURE_BLOCKS);
            this.setStructureData(structure_1, this.pos, structurePlacementData_1);
        }

        @Override
        protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess serverWorldAccess, Random random,
                                      BlockBox boundingBox) {
        }

        @Override
        public boolean generate(
                StructureWorldAccess structureWorldAccess,
                StructureAccessor structureAccessor,
                ChunkGenerator chunkGenerator,
                Random random,
                BlockBox boundingBox,
                ChunkPos chunkPos,
                BlockPos blockPos) {
            int yHeight = structureWorldAccess.getTopY(Heightmap.Type.WORLD_SURFACE_WG, this.pos.getX() + 5, this.pos.getZ() + 5);
            this.pos = this.pos.add(0, yHeight - 1, 0);
            return super.generate(structureWorldAccess, structureAccessor, chunkGenerator, random, boundingBox, chunkPos, blockPos);
        }
    }
}
