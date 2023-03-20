package net.ethermod.blackether.blocks.gen.impl;

import net.ethermod.blackether.blocks.gen.core.EtherBlockLootGenerator;
import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.ethermod.blackether.utils.Naming;

public class DarkGrassLootGenerator extends EtherBlockLootGenerator {
    public DarkGrassLootGenerator(EthermodLootTableGenerator ltg) {
        super(ltg, Naming.DARK_GRASS);
    }
}
