package net.ethermod.blackether.data.blocks.impl;

import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.ethermod.blackether.data.blocks.core.EtherBlockLootGenerator;
import net.ethermod.blackether.utils.Naming;

public class DarkGrassLootGenerator extends EtherBlockLootGenerator {
    public DarkGrassLootGenerator(EthermodLootTableGenerator ltg) {
        super(ltg, Naming.DARK_GRASS);
    }
}
