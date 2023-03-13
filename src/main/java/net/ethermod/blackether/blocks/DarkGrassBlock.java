package net.ethermod.blackether.blocks;

import net.ethermod.blackether.effects.ColoredDustParticleEffect;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class DarkGrassBlock extends GrassBlock implements BonemealableBlock {
    public DarkGrassBlock(Properties settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)
    public void animateTick(@NotNull BlockState blockState, @NotNull Level world, @NotNull BlockPos blockPos, @NotNull RandomSource random) {
        spawnParticles(world, blockPos);
    }

    private static void spawnParticles(Level world, BlockPos pos) {
        double spread = 0.5625D;
        RandomSource r = world.random;
        Direction[] dir = Direction.values();

        for (Direction d : dir) {
            BlockPos blockPos_2 = pos.relative(d);
            if (!world.getBlockState(blockPos_2).canOcclude()) {
                Direction.Axis dAxis = d.getAxis();
                double x = dAxis == Direction.Axis.X ? 0.5D + spread * (double) d.getStepX() : (double) r.nextFloat();
                double y = dAxis == Direction.Axis.Y ? 0.5D + spread * (double) d.getStepY() : (double) r.nextFloat();
                double z = dAxis == Direction.Axis.Z ? 0.5D + spread * (double) d.getStepZ() : (double) r.nextFloat();
                world.addParticle(ColoredDustParticleEffect.GREEN, (double) pos.getX() + x, (double) pos.getY() + y, (double) pos.getZ() + z, 0.0D, 0.0D, 0.0D);
            }
        }

    }
}
