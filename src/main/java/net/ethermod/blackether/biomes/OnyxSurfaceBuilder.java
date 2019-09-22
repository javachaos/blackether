package net.ethermod.blackether.biomes;

import com.mojang.datafixers.Dynamic;
import net.ethermod.blackether.BlackEtherMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

import java.util.Random;
import java.util.function.Function;

public class OnyxSurfaceBuilder extends SurfaceBuilder<TernarySurfaceConfig> {
    private static final BlockState CAVE_AIR;
    private static final BlockState ONYX_STONE;
    private static final BlockState GRAVEL;
    private static final BlockState GLOWSTONE;
    protected long seed;
    protected OctavePerlinNoiseSampler noise;

    public OnyxSurfaceBuilder(Function<Dynamic<?>, ? extends TernarySurfaceConfig> function_1) {
        super(function_1);
    }

    @Override
    public void generate(Random random_1, Chunk chunk_1, Biome biome_1, int int_1, int int_2, int int_3, double double_1, BlockState blockState_1, BlockState blockState_2, int int_4, long long_1, TernarySurfaceConfig ternarySurfaceConfig_1) {
        int int_5 = int_4 + 1;
        int int_6 = int_1 & 15;
        int int_7 = int_2 & 15;
        double double_2 = 0.03125D;
        boolean boolean_1 = this.noise.sample((double)int_1 * 0.03125D, (double)int_2 * 0.03125D, 0.0D) + random_1.nextDouble() * 0.2D > 0.0D;
        boolean boolean_2 = this.noise.sample((double)int_1 * 0.03125D, 109.0D, (double)int_2 * 0.03125D) + random_1.nextDouble() * 0.2D > 0.0D;
        int int_8 = (int)(double_1 / 3.0D + 3.0D + random_1.nextDouble() * 0.25D);
        BlockPos.Mutable blockPos$Mutable_1 = new BlockPos.Mutable();
        int int_9 = -1;
        BlockState blockState_3 = ONYX_STONE;
        BlockState blockState_4 = ONYX_STONE;

        for(int int_10 = 127; int_10 >= 0; --int_10) {
            blockPos$Mutable_1.set(int_6, int_10, int_7);
            BlockState blockState_5 = chunk_1.getBlockState(blockPos$Mutable_1);
            if (blockState_5.getBlock() != null && !blockState_5.isAir()) {
                if (blockState_5.getBlock() == blockState_1.getBlock()) {
                    if (int_9 == -1) {
                        if (int_8 <= 0) {
                            blockState_3 = CAVE_AIR;
                            blockState_4 = ONYX_STONE;
                        } else if (int_10 >= int_5 - 4 && int_10 <= int_5 + 1) {
                            blockState_3 = ONYX_STONE;
                            blockState_4 = ONYX_STONE;
                            if (boolean_2) {
                                blockState_3 = GRAVEL;
                                blockState_4 = ONYX_STONE;
                            }

                            if (boolean_1) {
                                blockState_3 = GLOWSTONE;
                                blockState_4 = GLOWSTONE;
                            }
                        }

                        if (int_10 < int_5 && (blockState_3 == null || blockState_3.isAir())) {
                            blockState_3 = blockState_2;
                        }

                        int_9 = int_8;
                        if (int_10 >= int_5 - 1) {
                            chunk_1.setBlockState(blockPos$Mutable_1, blockState_3, false);
                        } else {
                            chunk_1.setBlockState(blockPos$Mutable_1, blockState_4, false);
                        }
                    } else if (int_9 > 0) {
                        --int_9;
                        chunk_1.setBlockState(blockPos$Mutable_1, blockState_4, false);
                    }
                }
            } else {
                int_9 = -1;
            }
        }

    }

    public void initSeed(long long_1) {
        if (this.seed != long_1 || this.noise == null) {
            this.noise = new OctavePerlinNoiseSampler(new ChunkRandom(long_1), 4);
        }

        this.seed = long_1;
    }

    static {
        CAVE_AIR = Blocks.CAVE_AIR.getDefaultState();
        ONYX_STONE = Blocks.OBSIDIAN.getDefaultState();
        GRAVEL = Blocks.GRAVEL.getDefaultState();
        GLOWSTONE = Blocks.GLOWSTONE.getDefaultState();
    }
}
