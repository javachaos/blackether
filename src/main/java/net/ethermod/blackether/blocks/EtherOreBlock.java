package net.ethermod.blackether.blocks;

import net.ethermod.blackether.effects.ColoredDustParticleEffect;
import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.utils.Naming;
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
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class EtherOreBlock extends Block {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public EtherOreBlock(Properties settings) {
        super(settings);
        registerDefaultState(getStateDefinition().any().setValue(LIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateManager) {
        stateManager.add(LIT);
    }

    @Override
    public @NotNull InteractionResult use(BlockState state, Level world,
                                          BlockPos pos, Player player,
                                          InteractionHand hand, BlockHitResult blockHitResult) {
        world.setBlockAndUpdate(pos,
                BlockRegistry.getInstance()
                        .getBlock(Naming.ETHER_ORE_BLOCK)
                        .defaultBlockState().setValue(LIT, true));
        light(state, world, pos);
        return super.use(state, world, pos, player, hand, blockHitResult);
    }

    private static void light(BlockState state, Level world, BlockPos pos) {
        spawnParticles(world, pos);
        if (Boolean.FALSE.equals(state.getValue(LIT))) {
            world.setBlock(pos, state.setValue(LIT, true), 3);
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void animateTick(BlockState blockState, @NotNull Level world,
                            @NotNull BlockPos blockPos, @NotNull RandomSource random) {
        if (Boolean.TRUE.equals(blockState.getValue(LIT))) {
            spawnParticles(world, blockPos);
        }
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
                world.addParticle(ColoredDustParticleEffect.BLACK, pos.getX() + x,
                        pos.getY() + y, pos.getZ() + z, 0.0D, 0.0D, 0.0D);
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
