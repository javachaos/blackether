package net.ethermod.blackether.blocks.gen.impl;

import net.ethermod.blackether.blocks.gen.core.EtherItemBlockLootGenerator;
import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.ethermod.blackether.utils.Naming;

public class OnyxWoodLeavesGen extends EtherItemBlockLootGenerator {
    public OnyxWoodLeavesGen(EthermodLootTableGenerator ltg) {
        super(ltg, Naming.ONYXWOOD_LEAVES, Naming.ONYX_APPLE, 0, 1);
    }
}
