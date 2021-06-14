package net.ethermod.blackether.items;

import net.ethermod.blackether.BlackEtherMod;
import net.ethermod.blackether.enums.EnumHelper;
import net.ethermod.blackether.items.base.HoeBase;
import net.minecraft.recipe.Ingredient;

public class OnyxHoe extends HoeBase {
    public OnyxHoe() {
        super(EnumHelper.getToolMaterial(
                100,
                10,
                10,
                10,
                100,
                Ingredient.ofItems(BlackEtherMod.ONYX_ORE)));
    }
}
