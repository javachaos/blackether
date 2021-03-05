package net.ethermod.blackether.blocks;

import net.ethermod.blackether.BlackEtherMod;
import net.ethermod.blackether.effects.ColoredDustParticleEffect;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Random;

public class EtherOreBlock extends Block {
    public static final BooleanProperty LIT = BooleanProperty.of("lit");

    public EtherOreBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(LIT, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(LIT);
    }

    @Override
    public void onSteppedOn(World world, BlockPos blockPos, Entity entity) {
        light(world.getBlockState(blockPos), world, blockPos);
        super.onSteppedOn(world, blockPos, entity);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
        world.setBlockState(pos, BlackEtherMod.ETHER_ORE_BLOCK.getDefaultState().with(LIT, true));
        light(state, world, pos);
        return super.onUse(state, world, pos, player, hand, blockHitResult);
    }

    private static void light(BlockState state, World world, BlockPos pos) {
        spawnParticles(world, pos);
        if (!state.get(LIT)) {
            world.setBlockState(pos, state.with(LIT, true), 3);
        }
    }

    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState blockState, World world, BlockPos blockPos, Random random) {
        if (blockState.get(LIT)) {
            spawnParticles(world, blockPos);
        }
    }

    private static void spawnParticles(World world, BlockPos pos) {
        double spread = 0.5625D;
        Random r = world.random;
        Direction[] dir = Direction.values();
        int dirSize = dir.length;

        for(int i = 0; i < dirSize; ++i) {
            Direction d = dir[i];
            BlockPos blockPos_2 = pos.offset(d);
            if (!world.getBlockState(blockPos_2).isOpaque()) {
                Direction.Axis dAxis = d.getAxis();
                double x = dAxis == Direction.Axis.X ? 0.5D + spread * (double)d.getOffsetX() : (double)r.nextFloat();
                double y = dAxis == Direction.Axis.Y ? 0.5D + spread * (double)d.getOffsetY() : (double)r.nextFloat();
                double z = dAxis == Direction.Axis.Z ? 0.5D + spread * (double)d.getOffsetZ() : (double)r.nextFloat();
                world.addParticle(ColoredDustParticleEffect.BLACK, (double)pos.getX() + x, (double)pos.getY() + y, (double)pos.getZ() + z, 0.0D, 0.0D, 0.0D);
            }
        }

    }

    @Override
    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        light(state, world, pos);
        super.onBlockBreakStart(state, world, pos, player);
    }
}
