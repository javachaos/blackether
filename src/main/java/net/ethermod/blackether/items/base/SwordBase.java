package net.ethermod.blackether.items.base;

import net.ethermod.blackether.BlackEtherMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class SwordBase extends SwordItem {
    public SwordBase(ToolMaterial toolMaterial) {
        super(toolMaterial, -1, -2.2f, new Item.Settings().group(BlackEtherMod.BLACKETHERMOD_GROUP));
    }
}
