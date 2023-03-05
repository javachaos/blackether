package net.ethermod.blackether.items.base;

import net.ethermod.blackether.BlackEtherMod;
import net.ethermod.blackether.items.RegisterItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class SwordBase extends SwordItem {
    public SwordBase(ToolMaterial toolMaterial) {
        super(toolMaterial, -1, -2.2f, new FabricItemSettings().group(RegisterItems.BLACKETHERMOD_GROUP));
    }
}
