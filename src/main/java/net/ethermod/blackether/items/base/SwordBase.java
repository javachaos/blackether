package net.ethermod.blackether.items.base;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class SwordBase extends SwordItem {
    public SwordBase(Tier toolMaterial) {
        super(toolMaterial, -1, -2.2f, new FabricItemSettings());
    }
}
