package net.ethermod.blackether.blocks;

import net.ethermod.blackether.effects.ColoredDustParticleEffect;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public final class DarkGrassBlock extends SpreadingSnowyDirtBlock implements BonemealableBlock {
    public DarkGrassBlock(Properties settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void animateTick(@NotNull BlockState blockState, @NotNull Level world,
                            @NotNull BlockPos blockPos, @NotNull RandomSource random) {
        spawnParticles(world, blockPos);
    }

    private static void spawnParticles(Level world, BlockPos pos) {
        double spread = 0.5625D;
        RandomSource r = world.random;
        Direction[] dir = Direction.values();

        for (Direction d : dir) {
            BlockPos blockPos = pos.relative(d);
            if (!world.getBlockState(blockPos).canOcclude()) {
                Direction.Axis dAxis = d.getAxis();
                double x = dAxis == Direction.Axis.X ? 0.5D + spread * d.getStepX() : (double) r.nextFloat();
                double y = dAxis == Direction.Axis.Y ? 0.5D + spread * d.getStepY() : (double) r.nextFloat();
                double z = dAxis == Direction.Axis.Z ? 0.5D + spread * d.getStepZ() : (double) r.nextFloat();
                world.addParticle(ColoredDustParticleEffect.GREEN,  pos.getX() + x,
                        pos.getY() + y, pos.getZ() + z, 0.0D, 0.0D, 0.0D);
            }
        }

    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader levelReader,
                                         @NotNull BlockPos blockPos,
                                         @NotNull BlockState blockState, boolean bl) {
        return false;
    }

    @Override
    public boolean isBonemealSuccess(@NotNull Level level, @NotNull RandomSource randomSource,
                                     @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return false;
    }

    @Override
    public void performBonemeal(@NotNull ServerLevel serverLevel, @NotNull RandomSource randomSource,
                                @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        throw new UnsupportedOperationException("Bonemeal unsupported!");
    }
}
