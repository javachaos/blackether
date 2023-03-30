package net.ethermod.blackether.items;

import net.ethermod.blackether.enums.EnumHelper;
import net.ethermod.blackether.items.base.AxeBase;
import net.ethermod.blackether.registries.ItemRegistry;
import net.ethermod.blackether.utils.Naming;
import net.minecraft.world.item.crafting.Ingredient;


public class OnyxAxe extends AxeBase {
    public OnyxAxe() {
        super(EnumHelper.getToolMaterial(
                1562,
                15f,
                15f,
                100,
                100,
                Ingredient.of(ItemRegistry.getInstance().getItem(Naming.ONYX_ORE))));
    }
}
