package net.ethermod.blackether.data.blocks.impl;

import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.ethermod.blackether.data.blocks.core.EtherItemBlockLootGenerator;
import net.ethermod.blackether.utils.Naming;

public class EtherOreBlockLootGenerator extends EtherItemBlockLootGenerator {
    public EtherOreBlockLootGenerator(EthermodLootTableGenerator ltg) {
        super(ltg,
                Naming.ETHER_ORE_BLOCK,
                Naming.ETHER_ORE,
                4,
                5);
    }
}
