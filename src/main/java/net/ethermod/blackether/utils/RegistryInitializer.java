package net.ethermod.blackether.utils;

import net.ethermod.blackether.registries.*;
import net.ethermod.blackether.world.dimension.EthermodDimensions;
import net.fabricmc.fabric.api.registry.FuelRegistry;

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
        registries.add(WorldGenRegistry.getInstance());
        registries.add(EthermodDimensions.getInstance());
        //Call register() method on each registerable
        registries.forEach(Registerable::register);

        FuelRegistry.INSTANCE.add(ItemRegistry.getInstance().getItem(Naming.ETHER_ORE), 3000);
        registries.clear();
    }
}
