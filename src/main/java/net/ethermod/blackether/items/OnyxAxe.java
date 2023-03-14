package net.ethermod.blackether.items;

import net.ethermod.blackether.effects.ColoredDustParticleEffect;
import net.ethermod.blackether.enums.EnumHelper;
import net.ethermod.blackether.items.base.AxeBase;
import net.ethermod.blackether.registries.ItemRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;


public class OnyxAxe extends AxeBase {
    public OnyxAxe() {
        super(EnumHelper.getToolMaterial(
                1562,
                15f,
                15f,
                100,
                100,
                Ingredient.of(ItemRegistry.getInstance().getItem(Naming.ONYX_ORE))));
    }

    @Override
    public boolean mineBlock(@NotNull ItemStack itemStack, @NotNull Level world,
                             @NotNull BlockState blockState, @NotNull BlockPos blockPos,
                             @NotNull LivingEntity livingEntity) {
        spawnParticles(world, blockPos);
        spawnParticles(world, blockPos);
        spawnParticles(world, blockPos);
        spawnParticles(world, blockPos);
        spawnParticles(world, blockPos);
        spawnParticles(world, blockPos);
        spawnParticles(world, blockPos);
        spawnParticles(world, blockPos);
        spawnParticles(world, blockPos);
        return super.mineBlock(itemStack, world, blockState, blockPos, livingEntity);
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
                world.addParticle(ColoredDustParticleEffect.BLACK, pos.getX() + x, pos.getY() + y, pos.getZ() + z, 0.0D, 0.0D, 0.0D);
            }
        }

    }
}
