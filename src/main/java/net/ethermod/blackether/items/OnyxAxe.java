package net.ethermod.blackether.items;

import com.mojang.logging.LogUtils;
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
}
