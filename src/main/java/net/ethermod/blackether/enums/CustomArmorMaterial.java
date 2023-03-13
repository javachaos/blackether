package net.ethermod.blackether.enums;

import net.ethermod.blackether.registries.ItemRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class CustomArmorMaterial implements ArmorMaterial {

    private static final int[] PROTECTION_VALUES = new int[]{17, 19, 21, 15};
    private static final int[] BASE_DURABILITY = {13, 15, 16, 11};

    public CustomArmorMaterial() {
        super();
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getIndex()] * 5;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot slot) {
        return PROTECTION_VALUES[slot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return 100;
    }

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return SoundEvents.ANVIL_USE;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return Ingredient.of(ItemRegistry.ONYX_ORE);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public @NotNull String getName() {
        return "onyx";
    }

    @Override
    public float getToughness() {
        return 0.0f;
    }

    @Override
    public float getKnockbackResistance() {
        return 3.0f;
    }
}