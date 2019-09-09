package net.ethermod.blackether.items.base;

import net.minecraft.item.*;
import net.minecraft.util.ActionResult;

public class AxeBase extends AxeItem {
    public AxeBase(ToolMaterial toolMaterial_1) {
        super(toolMaterial_1, -1.0f, 2.0f, new Item.Settings().group(ItemGroup.TOOLS));
    }
}
