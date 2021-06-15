package net.ethermod.blackether.blocks;

import net.ethermod.blackether.effects.ColoredDustParticleEffect;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.GrassBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Random;

public class DarkGrassBlock extends GrassBlock implements Fertilizable {
    public DarkGrassBlock(Settings settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState blockState, World world, BlockPos blockPos, Random random) {
        spawnParticles(world, blockPos);
    }

    private static void spawnParticles(World world, BlockPos pos) {
        double spread = 0.5625D;
        Random r = world.random;
        Direction[] dir = Direction.values();
        int dirSize = dir.length;

        for (Direction d : dir) {
            BlockPos blockPos_2 = pos.offset(d);
            if (!world.getBlockState(blockPos_2).isOpaque()) {
                Direction.Axis dAxis = d.getAxis();
                double x = dAxis == Direction.Axis.X ? 0.5D + spread * (double) d.getOffsetX() : (double) r.nextFloat();
                double y = dAxis == Direction.Axis.Y ? 0.5D + spread * (double) d.getOffsetY() : (double) r.nextFloat();
                double z = dAxis == Direction.Axis.Z ? 0.5D + spread * (double) d.getOffsetZ() : (double) r.nextFloat();
                world.addParticle(ColoredDustParticleEffect.GREEN, (double) pos.getX() + x, (double) pos.getY() + y, (double) pos.getZ() + z, 0.0D, 0.0D, 0.0D);
            }
        }

    }
}
