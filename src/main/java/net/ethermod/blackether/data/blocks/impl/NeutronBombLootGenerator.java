package net.ethermod.blackether.data.blocks.impl;

import net.ethermod.blackether.data.EthermodLootTableGenerator;
import net.ethermod.blackether.data.blocks.core.EtherBlockLootGenerator;
import net.ethermod.blackether.utils.Naming;

public class NeutronBombLootGenerator extends EtherBlockLootGenerator {
    public NeutronBombLootGenerator(EthermodLootTableGenerator ltg) {
        super(ltg, Naming.NEUTRON_BOMB);
    }
}
