package net.ethermod.blackether.blocks.gen.impl;

import net.ethermod.blackether.blocks.gen.core.EtherBlockLootGenerator;
import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.ethermod.blackether.utils.Naming;

public class NeutronBombLootGenerator extends EtherBlockLootGenerator {
    public NeutronBombLootGenerator(EthermodLootTableGenerator ltg) {
        super(ltg, Naming.NEUTRON_BOMB);
    }
}
