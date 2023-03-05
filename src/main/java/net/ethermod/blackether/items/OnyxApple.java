package net.ethermod.blackether.items;

import net.ethermod.blackether.BlackEtherMod;
import net.ethermod.blackether.utils.Constants;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class OnyxApple extends Item {

    public OnyxApple() {
        super(new FabricItemSettings().maxCount(16).food(
                new FoodComponent.Builder()
                        .alwaysEdible()
                        .hunger(10)
                        .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, Constants.getTicksPerMin(5)), 1.0f)
                        .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, Constants.getTicksPerSec(45)), 0.5f)
                        .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, Constants.getTicksPerSec(45)), 0.5f)
                        .statusEffect(new StatusEffectInstance(StatusEffects.BAD_OMEN, Constants.getTicksPerSec(45)), 1.0f)
                        .statusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, Constants.getTicksPerSec(45)), 1.0f)
                        .statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, Constants.getTicksPerSec(45)), 1.0f)
                        .saturationModifier(100).build()));
    }

    @Environment(EnvType.CLIENT)
    public boolean hasEnchantmentGlint(ItemStack itemStack_1) {
        return true;
    }
}
