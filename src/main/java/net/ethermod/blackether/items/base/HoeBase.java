package net.ethermod.blackether.items.base;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;

public class HoeBase extends HoeItem {
    public HoeBase(Tier toolMaterial) {
        super(toolMaterial, -1, 1.5f, new FabricItemSettings());
    }
}
