package net.ethermod.blackether.data.blocks.impl;

import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.ethermod.blackether.data.blocks.core.EtherItemBlockLootGenerator;
import net.ethermod.blackether.utils.Naming;

public class BlockOfEtherLootGenerator extends EtherItemBlockLootGenerator {
    public BlockOfEtherLootGenerator(EthermodLootTableGenerator ltg) {
        super(ltg,
                Naming.BLOCK_OF_ETHER,
                Naming.ETHER_ORE,
                9,
                9);
    }
}