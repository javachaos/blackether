package net.ethermod.blackether.items.base;

import net.ethermod.blackether.BlackEtherMod;
import net.ethermod.blackether.items.RegisterItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterial;

public class AxeBase extends AxeItem {
    public AxeBase(ToolMaterial toolMaterial_1) {
        super(toolMaterial_1, -1.0f, 2.0f, new FabricItemSettings().group(RegisterItems.BLACKETHERMOD_GROUP));
    }
}
