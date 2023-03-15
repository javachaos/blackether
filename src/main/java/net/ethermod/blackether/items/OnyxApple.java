package net.ethermod.blackether.items;

import net.ethermod.blackether.utils.Constants;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class OnyxApple extends Item {

    public OnyxApple() {
        super(new FabricItemSettings().stacksTo(16).food(
                new FoodProperties.Builder()
                        .alwaysEat()
                        .nutrition(10)
                        .effect(new MobEffectInstance(MobEffects.HEALTH_BOOST, Constants.inMinutes(5)), 1.0f)
                        .effect(new MobEffectInstance(MobEffects.DIG_SPEED, Constants.inSeconds(45)), 0.5f)
                        .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, Constants.inSeconds(45)), 0.5f)
                        .effect(new MobEffectInstance(MobEffects.BAD_OMEN, Constants.inSeconds(45)), 1.0f)
                        .effect(new MobEffectInstance(MobEffects.INVISIBILITY, Constants.inSeconds(45)), 1.0f)
                        .effect(new MobEffectInstance(MobEffects.GLOWING, Constants.inSeconds(45)), 1.0f)
                        .saturationMod(100).build()));
    }

}
