package net.ethermod.blackether.items.base;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;

public class ShovelBase extends ShovelItem {
    public ShovelBase(Tier toolMaterial) {
        super(toolMaterial, -1.0f, 2.0f, new FabricItemSettings());
    }
}
