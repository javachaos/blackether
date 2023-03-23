package net.ethermod.blackether.blocks.gen.impl;

import net.ethermod.blackether.blocks.gen.core.EtherBlockLootGenerator;
import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.ethermod.blackether.utils.Naming;

public class ChiseledEtherBlockLootGenerator extends EtherBlockLootGenerator {
    public ChiseledEtherBlockLootGenerator(EthermodLootTableGenerator ltg) {
        super(ltg, Naming.CHISELED_ETHER);
    }
}
