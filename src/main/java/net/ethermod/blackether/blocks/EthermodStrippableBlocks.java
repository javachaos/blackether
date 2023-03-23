package net.ethermod.blackether.blocks;

import net.ethermod.blackether.registries.BlockRegistry;
import net.ethermod.blackether.utils.Naming;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;

public class EthermodStrippableBlocks {
    public static void registerStrippables() {
        StrippableBlockRegistry.register(
                BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_WOOD),
                BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_WOOD_STRIPPED));
        StrippableBlockRegistry.register(
                BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_LOG),
                BlockRegistry.getInstance().getBlock(Naming.ONYXWOOD_LOG_STRIPPED));
    }
}
