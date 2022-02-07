package net.ethermod.blackether.items;

import net.ethermod.blackether.BlackEtherMod;
import net.ethermod.blackether.enums.EnumHelper;
import net.ethermod.blackether.items.base.ShovelBase;
import net.minecraft.recipe.Ingredient;

public class OnyxShovel extends ShovelBase {
    public OnyxShovel() {
        super(EnumHelper.getToolMaterial(
                100,
                100,
                5,
                100,
                100,
                Ingredient.ofItems(RegisterItems.ONYX_ORE)));
    }
}
