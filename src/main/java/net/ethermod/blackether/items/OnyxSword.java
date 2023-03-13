package net.ethermod.blackether.items;

import net.ethermod.blackether.enums.EnumHelper;
import net.ethermod.blackether.items.base.SwordBase;
import net.minecraft.world.item.crafting.Ingredient;

public class OnyxSword extends SwordBase {
    public OnyxSword() {
        super(EnumHelper.getToolMaterial(
                100,
                10,
                20,
                1,
                100,
                Ingredient.of(RegisterItems.ONYX_ORE)));
    }
}
