package net.ethermod.blackether.blocks.gen.impl;

import net.ethermod.blackether.blocks.gen.core.EtherBlockLootGenerator;
import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.ethermod.blackether.utils.Naming;

public class StrippedOnyxWoodGen extends EtherBlockLootGenerator {
    public StrippedOnyxWoodGen(EthermodLootTableGenerator ltg) {
        super(ltg, Naming.ONYXWOOD_WOOD_STRIPPED);
    }
}
