package net.ethermod.blackether.items;

import net.ethermod.blackether.enums.EnumHelper;
import net.ethermod.blackether.items.base.ShovelBase;
import net.ethermod.blackether.registries.ItemRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.world.item.crafting.Ingredient;

public class OnyxShovel extends ShovelBase {
    public OnyxShovel() {
        super(EnumHelper.getToolMaterial(
                100,
                100,
                5,
                100,
                100,
                Ingredient.of(ItemRegistry.getInstance().getItem(Naming.ONYX_ORE))));
    }
}
