package net.ethermod.blackether.enums;

import net.ethermod.blackether.BlackEtherMod;
import net.ethermod.blackether.items.RegisterItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class CustomArmorMaterial implements ArmorMaterial {

    private static final int[] PROTECTION_VALUES = new int[]{17, 19, 21, 15};
    private static final int[] BASE_DURABILITY = {13, 15, 16, 11};
    private final float toughness = 0.0f;

    public CustomArmorMaterial() {
        super();
    }

    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * 5;
    }
    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION_VALUES[slot.getEntitySlotId()];
    }
    @Override
    public int getEnchantability() {
        return 100;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.BLOCK_ANVIL_USE;
    }
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(RegisterItems.ONYX_ORE);
    }
    @Override
    @Environment(EnvType.CLIENT)
    public String getName() {
        return "onyx";
    }
    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return 3.0f;
    }
}