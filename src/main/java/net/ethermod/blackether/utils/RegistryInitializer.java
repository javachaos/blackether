package net.ethermod.blackether.utils;

import net.ethermod.blackether.blocks.EthermodStrippableBlocks;
import net.ethermod.blackether.registries.*;
import net.ethermod.blackether.world.EthermodConfiguredFeatures;
import net.ethermod.blackether.world.dimension.EthermodDimensions;
import net.ethermod.blackether.world.gen.EthermodWorldGen;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;

import java.util.ArrayDeque;
import java.util.Deque;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

public class RegistryInitializer {
    public static void registerAll() {
        //Temp. deque to register all registerable registries
        Deque<Registerable> registries = new ArrayDeque<>();
        //Order of these matters.
        registries.add(EntityRegistry.getInstance());
        registries.add(SoundRegistry.getInstance());
        registries.add(BlockRegistry.getInstance());
        registries.add(ItemRegistry.getInstance());
        registries.add(EthermodWorldGen.getInstance());
        registries.add(EthermodDimensions.getInstance());
        //Call register() method on each registerable
        registries.forEach(Registerable::register);

        EthermodStrippableBlocks.registerStrippables();
        FuelRegistry.INSTANCE.add(ItemRegistry.getInstance().getItem(Naming.ETHER_ORE), 3000);
        //Done registering now free up this deque
        //TODO revise this line
        //registries.clear();
    }
}
