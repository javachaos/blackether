package net.ethermod.blackether.items.base;

import net.ethermod.blackether.BlackEtherMod;
import net.ethermod.blackether.items.RegisterItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterial;

public class HoeBase extends HoeItem {
    public HoeBase(ToolMaterial toolMaterial) {
        super(toolMaterial, -1, 1.5f, new FabricItemSettings());
    }
}
