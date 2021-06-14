package net.ethermod.blackether.items.base;

import net.ethermod.blackether.BlackEtherMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;

public class ShovelBase extends ShovelItem {
    public ShovelBase(ToolMaterial toolMaterial) {
        super(toolMaterial, -1.0f, 2.0f, new Item.Settings().group(BlackEtherMod.BLACKETHERMOD_GROUP));
    }
}
