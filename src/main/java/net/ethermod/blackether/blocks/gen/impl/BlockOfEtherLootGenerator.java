package net.ethermod.blackether.blocks.gen.impl;

import net.ethermod.blackether.blocks.gen.core.EtherItemBlockLootGenerator;
import net.ethermod.blackether.data.EthermodLootTableGenerator;
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