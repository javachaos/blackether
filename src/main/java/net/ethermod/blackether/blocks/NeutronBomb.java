package net.ethermod.blackether.blocks;

import net.ethermod.blackether.BlackEtherMod;
import net.ethermod.blackether.effects.ColoredDustParticleEffect;
import net.ethermod.blackether.entity.NeutronBombEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class NeutronBomb extends Block {

    public static final BooleanProperty UNSTABLE;

    public NeutronBomb(Settings settings) {
        super(settings);
        this.setDefaultState(getDefaultState().with(UNSTABLE, false));
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState blockState, World world, BlockPos blockPos, Random random) {
            spawnParticles(world, blockPos, ColoredDustParticleEffect.NEON_GREEN);
            spawnParticles(world, blockPos, ColoredDustParticleEffect.GREEN);
            spawnParticles(world, blockPos, ColoredDustParticleEffect.BLACK);
    }

    private static void spawnParticles(World world, BlockPos pos, ParticleEffect color) {
        double spread = 0.5625D;
        Random r = world.random;
        Direction[] dir = Direction.values();

        for (Direction d : dir) {
            BlockPos blockPos2 = pos.offset(d);
            if (!world.getBlockState(blockPos2).isOpaque()) {
                Direction.Axis dAxis = d.getAxis();
                double x = dAxis == Direction.Axis.X ? 0.5D + spread * d.getOffsetX() : (double) r.nextFloat();
                double y = dAxis == Direction.Axis.Y ? 0.5D + spread * d.getOffsetY() : (double) r.nextFloat();
                double z = dAxis == Direction.Axis.Z ? 0.5D + spread * d.getOffsetZ() : (double) r.nextFloat();
                world.addParticle(color, pos.getX() + x, pos.getY() + y, pos.getZ() + z, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!oldState.isOf(state.getBlock())) {
            if (world.isReceivingRedstonePower(pos)) {
                primeNeutronBomb(world, pos);
                world.removeBlock(pos, false);
            }
        }
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (world.isReceivingRedstonePower(pos)) {
            primeNeutronBomb(world, pos);
            world.removeBlock(pos, false);
        }

    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient() && !player.isCreative() && Boolean.TRUE.equals(state.get(UNSTABLE))) {
            primeNeutronBomb(world, pos);
        }

        super.onBreak(world, pos, state, player);
    }

    @Override
    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
        if (!world.isClient) {
            explosion.affectWorld(true);
            NeutronBombEntity tntEntity = new NeutronBombEntity(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, explosion.getCausingEntity());
            int i = tntEntity.getFuse();
            tntEntity.setFuse((short)(world.random.nextInt(i / 4) + i / 8));
            world.spawnEntity(tntEntity);
        }
    }

    public static void primeNeutronBomb(World world, BlockPos pos) {
        primeNeutronBomb(world, pos, null);
    }

    private static void primeNeutronBomb(World world, BlockPos pos, @Nullable LivingEntity igniter) {
        if (!world.isClient) {
            NeutronBombEntity neutron = new NeutronBombEntity(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, igniter);
            world.spawnEntity(neutron);
            world.playSound(null, neutron.getX(), neutron.getY(), neutron.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.playSound(null, neutron.getX(), neutron.getY(), neutron.getZ(), BlackEtherMod.NEUTRON_EVENT, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.emitGameEvent(igniter, GameEvent.PRIME_FUSE, pos);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!itemStack.isOf(Items.FLINT_AND_STEEL) && !itemStack.isOf(Items.FIRE_CHARGE)) {
            return super.onUse(state, world, pos, player, hand, hit);
        } else {
            primeNeutronBomb(world, pos, player);
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
            Item item = itemStack.getItem();
            if (!player.isCreative()) {
                if (itemStack.isOf(Items.FLINT_AND_STEEL)) {
                    itemStack.damage(1, (LivingEntity)player, ((playerx) -> player.sendToolBreakStatus(hand)));
                } else {
                    itemStack.decrement(1);
                }
            }

            player.incrementStat(Stats.USED.getOrCreateStat(item));
            return ActionResult.success(world.isClient);
        }
    }

    @Override
    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        if (!world.isClient) {
            BlockPos blockPos = hit.getBlockPos();
            Entity entity = projectile.getOwner();
            if (projectile.isOnFire() && projectile.canModifyAt(world, blockPos)) {
                primeNeutronBomb(world, blockPos, entity instanceof LivingEntity ? (LivingEntity)entity : null);
                world.removeBlock(blockPos, false);
            }
        }

    }

    @Override
    public boolean shouldDropItemsOnExplosion(Explosion explosion) {
        return false;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(UNSTABLE);
    }

    static {
        UNSTABLE = Properties.UNSTABLE;
    }
}
