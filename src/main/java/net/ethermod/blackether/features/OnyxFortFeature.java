package net.ethermod.blackether.features;

import com.mojang.datafixers.Dynamic;
import net.ethermod.blackether.BlackEtherMod;
import net.ethermod.blackether.gen.OnyxFortGenerator;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.AbstractTempleFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.function.Function;

public class OnyxFortFeature extends AbstractTempleFeature<DefaultFeatureConfig> {

    public OnyxFortFeature()
    {
        this(DefaultFeatureConfig::deserialize);
    }
    public OnyxFortFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> f) {
        super(f);
    }

    @Override
    protected int getSeedModifier() {
        return 2311213;
    }

    @Override
    public StructureStartFactory getStructureStartFactory() {
        return FortStructureStart::new;
    }

    @Override
    public String getName() {
        return "Onyx Fort";
    }

    @Override
    public int getRadius() {
        return 3;
    }

    public static class FortStructureStart extends StructureStart {
        public FortStructureStart(StructureFeature<?> structureFeature_1, int int_1, int int_2, Biome biome_1, MutableIntBoundingBox mutableIntBoundingBox_1, int int_3, long long_1) {
            super(structureFeature_1, int_1, int_2, biome_1, mutableIntBoundingBox_1, int_3, long_1);
        }
        @Override
        public void initialize(ChunkGenerator<?> chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome) {
            DefaultFeatureConfig defaultFeatureConfig = chunkGenerator.getStructureConfig(biome, BlackEtherMod.onyxFortFeature);
            int x = chunkX * 16;
            int z = chunkZ * 16;
            BlockPos startingPos = new BlockPos(x, 0, z);
            BlockRotation rotation = BlockRotation.values()[this.random.nextInt(BlockRotation.values().length)];
            OnyxFortGenerator.addParts(structureManager, startingPos, rotation, this.children, this.random, defaultFeatureConfig);
            this.setBoundingBoxFromChildren();
        }

    }
}
