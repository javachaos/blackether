package net.ethermod.blackether.items;

import net.ethermod.blackether.BlackEtherMod;
import net.ethermod.blackether.effects.ColoredDustParticleEffect;
import net.ethermod.blackether.enums.EnumHelper;
import net.ethermod.blackether.items.base.AxeBase;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Random;

public class OnyxAxe extends AxeBase {
    public OnyxAxe() {
        super(EnumHelper.getToolMaterial(
                500,
                30f,
                15f,
                100,
                100,
                 Ingredient.ofItems(BlackEtherMod.ONYX_ORE)));
    }


    @Override
    public boolean postMine(ItemStack itemStack_1, World world_1, BlockState blockState_1, BlockPos blockPos_1, LivingEntity livingEntity_1) {
        spawnParticles(world_1, blockPos_1);
        spawnParticles(world_1, blockPos_1);
        spawnParticles(world_1, blockPos_1);
        spawnParticles(world_1, blockPos_1);
        spawnParticles(world_1, blockPos_1);
        spawnParticles(world_1, blockPos_1);
        spawnParticles(world_1, blockPos_1);
        spawnParticles(world_1, blockPos_1);
        spawnParticles(world_1, blockPos_1);//TODO Figure this one out using mixins, at this level we cannot access the postMine method.
        return super.postMine(itemStack_1, world_1, blockState_1, blockPos_1, livingEntity_1);
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
}
