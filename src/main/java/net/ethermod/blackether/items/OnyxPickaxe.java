package net.ethermod.blackether.items;

import net.ethermod.blackether.enums.EnumHelper;
import net.ethermod.blackether.items.base.PickaxeBase;
import net.ethermod.blackether.registries.ItemRegistry;
import net.minecraft.world.item.crafting.Ingredient;

public class OnyxPickaxe extends PickaxeBase {
    public OnyxPickaxe() {
        super(EnumHelper.getToolMaterial(
                500,
                30f,
                10f,
                100,
                100,
                Ingredient.of(ItemRegistry.getInstance().getItem("onyx_ore"))));
    }
}
