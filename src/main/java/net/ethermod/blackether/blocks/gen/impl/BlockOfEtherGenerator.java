package net.ethermod.blackether.blocks.gen.impl;

import net.ethermod.blackether.blocks.gen.core.EtherItemBlockGenerator;
import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.ethermod.blackether.utils.Naming;

public class BlockOfEtherGenerator extends EtherItemBlockGenerator {
    public BlockOfEtherGenerator(EthermodLootTableGenerator ltg) {
        super(ltg,
                Naming.BLOCK_OF_ETHER,
                Naming.ETHER_ORE,
                9,
                9);
    }
}