package net.ethermod.blackether.enums;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class EnumHelper {

    public static Tier getToolMaterial(int durability, float miningSpeed, float attackDmg,
                                       int miningLevel, int enchantability, Ingredient repairIngredient) {
        return new Tier() {
            @Override
            public int getUses() {
                return durability;
            }

            @Override
            public float getSpeed() {
                return miningSpeed;
            }

            @Override
            public float getAttackDamageBonus() {
                return attackDmg;
            }

            @Override
            public int getLevel() {
                return miningLevel;
            }

            @Override
            public int getEnchantmentValue() {
                return enchantability;
            }

            @Override
            public @NotNull Ingredient getRepairIngredient() {
                return repairIngredient;
            }
        };
    }
}
