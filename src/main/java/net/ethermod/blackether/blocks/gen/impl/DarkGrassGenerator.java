package net.ethermod.blackether.blocks.gen.impl;

import net.ethermod.blackether.blocks.gen.core.EtherBlockGenerator;
import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.ethermod.blackether.utils.Naming;

public class DarkGrassGenerator extends EtherBlockGenerator {
    public DarkGrassGenerator(EthermodLootTableGenerator ltg) {
        super(ltg, Naming.DARK_GRASS);
    }
}
