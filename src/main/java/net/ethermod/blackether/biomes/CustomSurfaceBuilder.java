package net.ethermod.blackether.biomes;

import net.ethermod.blackether.BlackEtherMod;
import net.minecraft.block.BlockState;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

import java.util.Random;
import java.util.function.Function;

public class CustomSurfaceBuilder extends SurfaceBuilder {

    public static final SurfaceBuilder<TernarySurfaceConfig> ONYX;
    public static final TernarySurfaceConfig ONYX_CONFIG;
    public static final BlockState BLOCK_OF_ETHER;
    public static final BlockState ETHER_ORE_BLOCK;

    public CustomSurfaceBuilder(Function function_1) {
        super(function_1);
    }

    @Override
    public void generate(Random var1, Chunk var2, Biome var3, int var4, int var5, int var6, double var7, BlockState var9, BlockState var10, int var11, long var12, SurfaceConfig var14) {

    }

    private static <C extends SurfaceConfig, F extends SurfaceBuilder<C>> F register(String s, F sb) {
        return (F) Registry.register(Registry.SURFACE_BUILDER, (String)s, sb);
    }


    static {
        ETHER_ORE_BLOCK = BlackEtherMod.ETHER_ORE_BLOCK.getDefaultState();
        BLOCK_OF_ETHER = BlackEtherMod.BLOCK_OF_ETHER.getDefaultState();
        ONYX_CONFIG = new TernarySurfaceConfig(BLOCK_OF_ETHER, ETHER_ORE_BLOCK, NETHERRACK);
        ONYX = register("onyx", new OnyxSurfaceBuilder(TernarySurfaceConfig::deserialize));
    }
}
