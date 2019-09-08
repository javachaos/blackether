package net.ethermod.blackether.blocks;

import net.ethermod.blackether.BlackEtherMod;
import net.ethermod.blackether.effects.ColoredDustParticleEffect;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
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
        this.setDefaultState((BlockState)this.getDefaultState().with(LIT, false));
    }

    @Environment(EnvType.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    public int getLuminance(BlockState blockState_1) {
        return (Boolean)blockState_1.get(LIT) ? super.getLuminance(blockState_1) : 0;
    }

    @Override
    public void onSteppedOn(World world_1, BlockPos blockPos_1, Entity entity_1) {
        light(world_1.getBlockState(blockPos_1), world_1, blockPos_1);
        super.onSteppedOn(world_1, blockPos_1, entity_1);
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory) {
        stateFactory.add(LIT);
    }

    @Override
    public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
        world.setBlockState(pos, BlackEtherMod.ETHER_ORE_BLOCK.getDefaultState().with(LIT, true));
        light(state, world, pos);
        return super.activate(state, world, pos, player, hand, blockHitResult);
    }

    private static void light(BlockState state, World world, BlockPos pos) {
        spawnParticles(world, pos);
        if (!(Boolean)state.get(LIT)) {
            world.setBlockState(pos, (BlockState)state.with(LIT, true), 3);
        }
    }

    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
        if ((Boolean)blockState_1.get(LIT)) {
            spawnParticles(world_1, blockPos_1);
        }
    }

    @Override
    public void onScheduledTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
        if ((Boolean)blockState_1.get(LIT)) {
            world_1.setBlockState(blockPos_1, (BlockState)blockState_1.with(LIT, false), 3);
        }

    }

    @Override
    public void onStacksDropped(BlockState blockState_1, World world_1, BlockPos blockPos_1, ItemStack itemStack_1) {
        super.onStacksDropped(blockState_1, world_1, blockPos_1, itemStack_1);
        if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, itemStack_1) == 0) {
            int int_1 = 1 + world_1.random.nextInt(5);
            this.dropExperience(world_1, blockPos_1, int_1);
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
            if (!world.getBlockState(blockPos_2).isFullOpaque(world, blockPos_2)) {
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
