package net.ethermod.blackether.blocks;

import net.ethermod.blackether.effects.ColoredDustParticleEffect;
import net.ethermod.blackether.registries.BlockRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class EtherOreBlock extends Block {
    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    public EtherOreBlock(Properties settings) {
        super(settings);
        registerDefaultState(getStateDefinition().any().setValue(LIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateManager) {
        stateManager.add(LIT);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level world, @NotNull BlockPos pos, @NotNull Player player,
                                          @NotNull InteractionHand hand, @NotNull BlockHitResult blockHitResult) {
        world.setBlockAndUpdate(pos, BlockRegistry.getInstance().getBlock("ether_ore_block").defaultBlockState().setValue(LIT, true));
        light(state, world, pos);
        return super.use(state, world, pos, player, hand, blockHitResult);
    }

    private static void light(BlockState state, Level world, BlockPos pos) {
        spawnParticles(world, pos);
        if (!state.getValue(LIT)) {
            world.setBlock(pos, state.setValue(LIT, true), 3);
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void animateTick(BlockState blockState, @NotNull Level world, @NotNull BlockPos blockPos, @NotNull RandomSource random) {
        if (blockState.getValue(LIT)) {
            spawnParticles(world, blockPos);
        }
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
                world.addParticle(ColoredDustParticleEffect.BLACK, (double) pos.getX() + x, (double) pos.getY() + y, (double) pos.getZ() + z, 0.0D, 0.0D, 0.0D);
            }
        }

    }

    @Override
    public void attack(@NotNull BlockState state, @NotNull Level world,
                       @NotNull BlockPos pos, @NotNull Player player) {
        light(state, world, pos);
        super.attack(state, world, pos, player);
    }
}
