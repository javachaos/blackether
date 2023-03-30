package net.ethermod.blackether.data.blocks.impl;

import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.ethermod.blackether.data.blocks.core.EtherBlockLootGenerator;
import net.ethermod.blackether.utils.Naming;

public class OnyxWoodGenerator extends EtherBlockLootGenerator {
    public OnyxWoodGenerator(EthermodLootTableGenerator ltg) {
        super(ltg, Naming.ONYXWOOD_WOOD);
    }
}
