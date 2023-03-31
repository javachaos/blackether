package net.ethermod.blackether.blocks;

import net.ethermod.blackether.effects.ColoredDustParticleEffect;
import net.ethermod.blackether.entity.misc.NeutronBombEntity;
import net.ethermod.blackether.registries.SoundRegistry;
import net.ethermod.blackether.utils.Constants;
import net.ethermod.blackether.utils.Naming;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class NeutronBomb extends Block {

    public static final BooleanProperty UNSTABLE;

    public NeutronBomb(Properties settings) {
        super(settings);
        this.registerDefaultState(defaultBlockState().setValue(UNSTABLE, false));
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void animateTick(@NotNull BlockState blockState, @NotNull Level world,
                            @NotNull BlockPos blockPos, @NotNull RandomSource random) {
        spawnParticles(world, blockPos, ColoredDustParticleEffect.NEON_GREEN);
        spawnParticles(world, blockPos, ColoredDustParticleEffect.GREEN);
        spawnParticles(world, blockPos, ColoredDustParticleEffect.BLACK);
    }

    private static void spawnParticles(Level world, BlockPos pos, ParticleOptions color) {
        double spread = 0.5625D;
        RandomSource r = world.random;
        Direction[] dir = Direction.values();

        for (Direction d : dir) {
            BlockPos blockPos2 = pos.relative(d);
            if (!world.getBlockState(blockPos2).canOcclude()) {
                Direction.Axis dAxis = d.getAxis();
                double x = dAxis == Direction.Axis.X ? 0.5D + spread * d.getStepX() : (double) r.nextFloat();
                double y = dAxis == Direction.Axis.Y ? 0.5D + spread * d.getStepY() : (double) r.nextFloat();
                double z = dAxis == Direction.Axis.Z ? 0.5D + spread * d.getStepZ() : (double) r.nextFloat();
                world.addParticle(color, pos.getX() + x,
                        pos.getY() + y, pos.getZ() + z, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public void onPlace(BlockState state, @NotNull Level world,
                        @NotNull BlockPos pos, BlockState oldState, boolean notify) {
        if (!oldState.is(state.getBlock()) && (world.hasNeighborSignal(pos))) {
                primeNeutronBomb(world, pos);
                world.removeBlock(pos, false);

        }
    }

    @Override
    public void neighborChanged(@NotNull BlockState state, Level world,
                                @NotNull BlockPos pos, @NotNull Block block,
                                @NotNull BlockPos fromPos, boolean notify) {
        if (world.hasNeighborSignal(pos)) {
            primeNeutronBomb(world, pos);
            world.removeBlock(pos, false);
        }

    }

    @Override
    public void playerWillDestroy(Level world, @NotNull BlockPos pos,
                                  @NotNull BlockState state, @NotNull Player player) {
        if (!world.isClientSide() && !player.isCreative() && Boolean.TRUE.equals(state.getValue(UNSTABLE))) {
            primeNeutronBomb(world, pos);
        }

        super.playerWillDestroy(world, pos, state, player);
    }

    @Override
    public void wasExploded(Level world, @NotNull BlockPos pos, @NotNull Explosion explosion) {
        if (!world.isClientSide) {
            explosion.finalizeExplosion(true);
            NeutronBombEntity tntEntity = new NeutronBombEntity(world, pos.getX() + 0.5D, pos.getY(),
                    pos.getZ() + 0.5D, explosion.getIndirectSourceEntity());
            int i = tntEntity.getFuse();
            tntEntity.setFuse((short) (world.random.nextInt(i / 4) + i / 8));
            world.addFreshEntity(tntEntity);
        }
    }

    public static void primeNeutronBomb(Level world, BlockPos pos) {
        primeNeutronBomb(world, pos, null);
    }

    private static void primeNeutronBomb(Level world, BlockPos pos, @Nullable LivingEntity igniter) {
        if (!world.isClientSide) {
            NeutronBombEntity neutron = new NeutronBombEntity(world,
                    pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, igniter);
            world.addFreshEntity(neutron);
            world.playSound(null, neutron.getX(), neutron.getY(), neutron.getZ(),
                    SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            world.playSound(null, neutron.getX(), neutron.getY(), neutron.getZ(),
                    SoundRegistry.getInstance().getSoundEvent(Naming.NEUTRON_IONIZING),
                    SoundSource.BLOCKS, 1.0F, 1.0F);
            world.gameEvent(igniter, GameEvent.PRIME_FUSE, pos);
        }
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level world,
                                          @NotNull BlockPos pos, Player player, @NotNull InteractionHand hand,
                                          @NotNull BlockHitResult hit) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!itemStack.is(Items.FLINT_AND_STEEL) && !itemStack.is(Items.FIRE_CHARGE)) {
            return super.use(state, world, pos, player, hand, hit);
        } else {
            primeNeutronBomb(world, pos, player);
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL | Block.UPDATE_IMMEDIATE);
            Item item = itemStack.getItem();
            if (!player.isCreative()) {
                if (itemStack.is(Items.FLINT_AND_STEEL)) {
                    itemStack.hurtAndBreak(1, (LivingEntity) player,
                            (playerx -> player.broadcastBreakEvent(hand)));
                } else {
                    itemStack.shrink(1);
                }
            }

            player.awardStat(Stats.ITEM_USED.get(item));
            return InteractionResult.sidedSuccess(world.isClientSide);
        }
    }

    @Override
    public void onProjectileHit(Level world, @NotNull BlockState state, @NotNull BlockHitResult hit,
                                @NotNull Projectile projectile) {
        if (!world.isClientSide) {
            BlockPos blockPos = hit.getBlockPos();
            Entity entity = projectile.getOwner();
            if (projectile.isOnFire() && projectile.mayInteract(world, blockPos)) {
                primeNeutronBomb(world, blockPos, entity instanceof LivingEntity n ? n : null);
                world.removeBlock(blockPos, false);
            }
        }

    }

    @Override
    public boolean dropFromExplosion(@NotNull Explosion explosion) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UNSTABLE);
    }

    static {
        UNSTABLE = BlockStateProperties.UNSTABLE;
    }
}
