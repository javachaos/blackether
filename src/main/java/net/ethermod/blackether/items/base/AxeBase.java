package net.ethermod.blackether.items.base;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;

public class AxeBase extends AxeItem {
    public AxeBase(Tier toolMaterial_1) {
        super(toolMaterial_1, -1.0f, 2.0f, new FabricItemSettings());
    }
}
