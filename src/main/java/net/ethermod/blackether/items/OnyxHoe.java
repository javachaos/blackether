package net.ethermod.blackether.items;

import net.ethermod.blackether.enums.EnumHelper;
import net.ethermod.blackether.items.base.HoeBase;
import net.ethermod.blackether.registries.ItemRegistry;
import net.minecraft.world.item.crafting.Ingredient;

public class OnyxHoe extends HoeBase {
    public OnyxHoe() {
        super(EnumHelper.getToolMaterial(
                100,
                10,
                10,
                10,
                100,
                Ingredient.of(ItemRegistry.getInstance().getItem("onyx_ore"))));
    }
}
