package net.ethermod.blackether.blocks.gen.impl;

import net.ethermod.blackether.blocks.gen.core.EtherItemBlockGenerator;
import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.ethermod.blackether.utils.Naming;

public class EtherOreBlockGenerator extends EtherItemBlockGenerator {
    public EtherOreBlockGenerator(EthermodLootTableGenerator ltg) {
        super(ltg,
                Naming.ETHER_ORE_BLOCK,
                Naming.ETHER_ORE,
                4,
                5);
    }
}
