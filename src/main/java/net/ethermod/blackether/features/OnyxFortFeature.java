package net.ethermod.blackether.features;

import com.mojang.serialization.Codec;
import net.ethermod.blackether.gen.OnyxFortGenerator;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class OnyxFortFeature extends StructureFeature<DefaultFeatureConfig> {
    public OnyxFortFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public StructureFeature.StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return Start::new;
    }

    public static class Start extends StructureStart<DefaultFeatureConfig> {

        public Start(StructureFeature<DefaultFeatureConfig> feature, ChunkPos chunkPos, int references, long seed) {
            super(feature, chunkPos, references, seed);
        }

        @Override
        public void init(DynamicRegistryManager registryManager, ChunkGenerator chunkGenerator, StructureManager manager, ChunkPos pos, Biome biome, DefaultFeatureConfig config, HeightLimitView world) {
            int x = pos.x * 16;
            int z = pos.z * 16;
            int y = chunkGenerator.getHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG, world);
            BlockPos posi = new BlockPos(x, y, z);
            BlockRotation rotation = BlockRotation.random(this.random);
            OnyxFortGenerator.addPieces(manager, posi, rotation, this);
            this.setBoundingBoxFromChildren();
        }
    }
}
