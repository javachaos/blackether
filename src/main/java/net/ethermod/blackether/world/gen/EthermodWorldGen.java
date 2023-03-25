package net.ethermod.blackether.world.gen;

import net.ethermod.blackether.registries.Registerable;

import java.util.concurrent.atomic.AtomicReference;

public class EthermodWorldGen extends Registerable {

    private static final AtomicReference<EthermodWorldGen> INSTANCE = new AtomicReference<>();

    private EthermodWorldGen() {
    }

    public static EthermodWorldGen getInstance() {
        if (INSTANCE.get() == null) {
            INSTANCE.set(new EthermodWorldGen());
        }
        return INSTANCE.get();
    }

    @Override
    public void register() {
        EthermodOreGen.generateOres();
        EthermodCarverGen.generateCarvers();
        EthermodTreeGen.generateTrees();
        EthermodEntitySpawns.spawnCreatures();
    }
}
