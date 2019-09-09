package net.ethermod.blackether.items;

import net.ethermod.blackether.BlackEtherMod;
import net.ethermod.blackether.enums.EnumHelper;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class OnyxAxe extends AxeBase {
    public OnyxAxe() {
        super(EnumHelper.getToolMaterial(
                500,
                30f,
                15f,
                100,
                100,
                 Ingredient.ofItems(BlackEtherMod.ONYX_ORE)));
    }
}
