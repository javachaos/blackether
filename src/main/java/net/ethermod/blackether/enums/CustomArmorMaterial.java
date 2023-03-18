package net.ethermod.blackether.enums;

import net.ethermod.blackether.registries.ItemRegistry;
import net.ethermod.blackether.utils.Naming;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
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
    public int getDurabilityForType(ArmorItem.Type type) {
        return BASE_DURABILITY[type.getSlot().getIndex()] * 5;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return PROTECTION_VALUES[type.getSlot().getIndex()];
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
        return Ingredient.of(ItemRegistry.getInstance().getItem(Naming.ONYX_ORE));
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