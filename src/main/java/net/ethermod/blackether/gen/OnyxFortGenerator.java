package net.ethermod.blackether.gen;

import net.ethermod.blackether.BlackEtherMod;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.*;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.Random;

public class OnyxFortGenerator {

    private static final Identifier onyxfort_id = new Identifier(BlackEtherMod.MODID, "forts/onyx_fort");

    public static void addPieces(StructureManager manager, BlockPos pos, BlockRotation rotation,
                                 StructurePiecesHolder pieces) {
        pieces.addPiece(new Piece(manager, onyxfort_id, pos, rotation));
    }

    public static class Piece extends SimpleStructurePiece {

        public Piece(ServerWorld world, NbtCompound compTag) {
            super(BlackEtherMod.ONYXFORT_PIECE,compTag, world,
                    (id) -> createPlacementData(BlockRotation.valueOf(compTag.getString("Rot"))));
        }

        public Piece(StructureManager manager, Identifier template, BlockPos pos, BlockRotation rotation) {
            super(BlackEtherMod.ONYXFORT_PIECE, 0, manager, template, template.toString(),
                    createPlacementData(rotation), pos);
        }

        @Override
        protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess serverWorldAccess, Random random,
                                      BlockBox boundingBox) {
        }

        @Override
        protected void writeNbt(ServerWorld world, NbtCompound nbt) {
            super.writeNbt(world, nbt);
            nbt.putString("Rot", this.placementData.getRotation().name());
        }

        @Override
        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos pos) {
            boundingBox.encompass(this.structure.calculateBoundingBox(this.placementData, this.pos));
            return super.generate(world, structureAccessor, chunkGenerator, random, boundingBox, chunkPos, pos);
        }

    }

    private static StructurePlacementData createPlacementData(BlockRotation rotation) {
        return (new StructurePlacementData()).setRotation(rotation).setMirror(BlockMirror.NONE)
                .addProcessor(BlockIgnoreStructureProcessor.IGNORE_AIR_AND_STRUCTURE_BLOCKS);
    }
}
