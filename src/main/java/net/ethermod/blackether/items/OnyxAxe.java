package net.ethermod.blackether.items;

import net.ethermod.blackether.effects.ColoredDustParticleEffect;
import net.ethermod.blackether.enums.EnumHelper;
import net.ethermod.blackether.items.base.AxeBase;
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
                Ingredient.of(RegisterItems.ONYX_ORE)));
    }

    @Override
    public boolean mineBlock(@NotNull ItemStack itemStack_1, @NotNull Level world_1, @NotNull BlockState blockState_1, @NotNull BlockPos blockPos_1, @NotNull LivingEntity livingEntity_1) {
        spawnParticles(world_1, blockPos_1);
        spawnParticles(world_1, blockPos_1);
        spawnParticles(world_1, blockPos_1);
        spawnParticles(world_1, blockPos_1);
        spawnParticles(world_1, blockPos_1);
        spawnParticles(world_1, blockPos_1);
        spawnParticles(world_1, blockPos_1);
        spawnParticles(world_1, blockPos_1);
        spawnParticles(world_1, blockPos_1);//TODO Figure this one out using mixins, at this level we cannot access the postMine method.
        return super.mineBlock(itemStack_1, world_1, blockState_1, blockPos_1, livingEntity_1);
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
}
