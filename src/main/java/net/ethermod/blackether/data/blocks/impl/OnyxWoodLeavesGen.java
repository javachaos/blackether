package net.ethermod.blackether.data.blocks.impl;

import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.ethermod.blackether.data.blocks.core.EtherItemBlockLootGenerator;
import net.ethermod.blackether.utils.Naming;

public class OnyxWoodLeavesGen extends EtherItemBlockLootGenerator {
    public OnyxWoodLeavesGen(EthermodLootTableGenerator ltg) {
        super(ltg, Naming.ONYXWOOD_LEAVES, Naming.ONYX_APPLE, 0, 1);
    }
}
