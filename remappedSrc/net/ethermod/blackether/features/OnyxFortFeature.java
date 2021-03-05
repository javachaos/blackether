package net.ethermod.blackether.features;

import com.mojang.serialization.Codec;
import net.ethermod.blackether.gen.OnyxFortGenerator;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Random;
import java.util.function.Function;

public class OnyxFortFeature extends StructureFeature<DefaultFeatureConfig> {
    public OnyxFortFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public StructureFeature.StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return Start::new;
    }

    public static class Start extends StructureStart<DefaultFeatureConfig> {
        public Start(StructureFeature<DefaultFeatureConfig> feature, int chunkX, int chunkZ, BlockBox box, int references,
                     long seed) {
            super(feature, chunkX, chunkZ, box, references, seed);
        }

        // Called when the world attempts to spawn in a new structure, and is the gap between your feature and generator.
        public void init(DynamicRegistryManager registryManager, ChunkGenerator chunkGenerator, StructureManager manager, int chunkX,
                         int chunkZ, Biome biome, DefaultFeatureConfig config) {
            int x = chunkX * 16;
            int z = chunkZ * 16;
            int y = chunkGenerator.getHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);
            BlockPos pos = new BlockPos(x, y, z);
            BlockRotation rotation = BlockRotation.random(this.random);
            OnyxFortGenerator.addPieces(manager, pos, rotation, this.children);
            this.setBoundingBoxFromChildren();
        }
    }
}

//    public OnyxFortFeature()
//    {
//        this(DefaultFeatureConfig::deserialize);
//    }
//    public OnyxFortFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> f) {
//        super(f);
//    }
//
//    @Override
//    protected int getSeedModifier() {
//        return 0;
//    }
//
//    @Override
//    public StructureStartFactory getStructureStartFactory() {
//        return OnyxFortFeature.FortStructureStart::new;
//    }
//
//    @Override
//    public String getName() {
//        return "onyxfort";
//    }
//
//    @Override
//    public int getRadius() {
//        return 8;
//    }
//
//    @Override
//    public boolean shouldStartAt(ChunkGenerator<?> chunkGenerator_1, Random random_1, int int_1, int int_2)
//    {
//        return true;
//    }
//
//    private static class FortStructureStart extends StructureStart {
//        public FortStructureStart(StructureFeature<?> structureFeature_1, int int_1, int int_2, Biome biome_1, MutableIntBoundingBox mutableIntBoundingBox_1, int int_3, long long_1) {
//            super(structureFeature_1, int_1, int_2, biome_1, mutableIntBoundingBox_1, int_3, long_1);
//        }
//        @Override
//        public void initialize(ChunkGenerator<?> chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome) {
//            DefaultFeatureConfig defaultFeatureConfig = chunkGenerator.getStructureConfig(biome, BlackEtherMod.onyxFortFeature);
//            int x = chunkX * 16;
//            int z = chunkZ * 16;
//            BlockPos startingPos = new BlockPos(x, 1, z);
//            BlockRotation rotation = BlockRotation.values()[this.random.nextInt(BlockRotation.values().length)];
//            OnyxFortGenerator.addPieces(structureManager, startingPos, rotation, this.children, this.random, defaultFeatureConfig);
//            this.setBoundingBoxFromChildren();
//        }
//
//    }
//}
