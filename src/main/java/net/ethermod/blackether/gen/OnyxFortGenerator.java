package net.ethermod.blackether.gen;

import net.ethermod.blackether.BlackEtherMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.*;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;

import java.util.List;
import java.util.Random;

public class OnyxFortGenerator {
    private static final Identifier onyxfort_id = new Identifier(BlackEtherMod.MODID, "forts/onyx_fort");

    public static void addPieces(StructureManager manager, BlockPos pos, BlockRotation rotation, List<StructurePiece> pieces) {
        pieces.add(new Piece(manager, onyxfort_id, pos, rotation));
    }

    public static class Piece extends SimpleStructurePiece {
        private final BlockRotation rotation;
        private final Identifier identifier;

        public Piece(StructureManager structureManager, CompoundTag compTag) {
            super(BlackEtherMod.ONYXFORT_PIECE, compTag);

            this.identifier = new Identifier(compTag.getString("onyxfort"));
            this.rotation = BlockRotation.valueOf(compTag.getString("rotation"));

            this.setStructureData(structureManager);
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
            Structure struct = structureManager.getStructureOrBlank(this.identifier);
            StructurePlacementData structPosData = (new StructurePlacementData())
                    .setRotation(this.rotation)
                    .setMirror(BlockMirror.NONE)
                    .setPosition(pos)
                    .addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
            this.setStructureData(struct, this.pos, structPosData);
        }

        @Override
        protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess serverWorldAccess, Random random,
                                      BlockBox boundingBox) {
        }
//        @Override
//        public boolean generate(
//                StructureWorldAccess structureWorldAccess,
//                StructureAccessor structureAccessor,
//                ChunkGenerator chunkGenerator,
//                Random random,
//                BlockBox boundingBox,
//                ChunkPos chunkPos,
//                BlockPos blockPos) {
//            int yHeight = structureWorldAccess.getTopY(Heightmap.Type.WORLD_SURFACE_WG, this.pos.getX() + 5, this.pos.getZ() + 5);
//            this.pos = this.pos.add(0, yHeight - 1, 0);
//            return super.generate(structureWorldAccess, structureAccessor,chunkGenerator, random, boundingBox, chunkPos, blockPos);
//        }
    }
}
