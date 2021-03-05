package net.ethermod.blackether.enums;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class EnumHelper {

    public static final ToolMaterial getToolMaterial(int durability, float miningSpeed, float attackDmg, int miningLevel,int enchantability,Ingredient repairIngredient) {
        return new ToolMaterial() {
            @Override
            public int getDurability() {
                return durability;
            }

            @Override
            public float getMiningSpeedMultiplier() { return miningSpeed; }

            @Override
            public float getAttackDamage() {
                return attackDmg;
            }

            @Override
            public int getMiningLevel() {
                return miningLevel;
            }

            @Override
            public int getEnchantability() {
                return enchantability;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return repairIngredient;
            }
        };
    }
}
