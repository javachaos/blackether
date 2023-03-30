package net.ethermod.blackether.registries;

import net.ethermod.blackether.data.world.EthermodCarverGen;
import net.ethermod.blackether.data.world.EthermodEntitySpawns;
import net.ethermod.blackether.data.world.EthermodOreGen;
import net.ethermod.blackether.data.world.EthermodTreeGen;

import java.util.concurrent.atomic.AtomicReference;

public class WorldGenRegistry extends Registerable {

    private static final AtomicReference<WorldGenRegistry> INSTANCE = new AtomicReference<>();

    private WorldGenRegistry() {
    }

    public static WorldGenRegistry getInstance() {
        if (INSTANCE.get() == null) {
            INSTANCE.set(new WorldGenRegistry());
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
