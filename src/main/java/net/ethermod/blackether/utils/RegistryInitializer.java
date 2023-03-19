package net.ethermod.blackether.utils;

import net.ethermod.blackether.registries.*;

import java.util.ArrayDeque;
import java.util.Deque;

public class RegistryInitializer {
    public static void registerAll() {
        //Temp. deque to register all registerable registries
        Deque<Registerable> registries = new ArrayDeque<>();

        //Order of these matters.
        registries.add(EntityRegistry.getInstance());
        registries.add(SoundRegistry.getInstance());
        registries.add(BlockRegistry.getInstance());
        registries.add(ItemRegistry.getInstance());

        //Call register() method on each registerable
        registries.forEach(Registerable::register);

        //Done registering now free up this deque
        registries.clear();
    }
}
