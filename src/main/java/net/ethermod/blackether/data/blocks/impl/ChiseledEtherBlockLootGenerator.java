package net.ethermod.blackether.data.blocks.impl;

import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.ethermod.blackether.data.blocks.core.EtherBlockLootGenerator;
import net.ethermod.blackether.utils.Naming;

public class ChiseledEtherBlockLootGenerator extends EtherBlockLootGenerator {
    public ChiseledEtherBlockLootGenerator(EthermodLootTableGenerator ltg) {
        super(ltg, Naming.CHISELED_ETHER);
    }
}
