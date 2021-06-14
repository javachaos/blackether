package net.ethermod.blackether.items.base;

import net.ethermod.blackether.BlackEtherMod;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterial;

public class AxeBase extends AxeItem {
    public AxeBase(ToolMaterial toolMaterial_1) {
        super(toolMaterial_1, -1.0f, 2.0f, new Item.Settings().group(BlackEtherMod.BLACKETHERMOD_GROUP));
    }
}
