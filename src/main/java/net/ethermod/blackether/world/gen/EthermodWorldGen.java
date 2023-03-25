package net.ethermod.blackether.world.gen;

import net.ethermod.blackether.registries.Registerable;
import net.ethermod.blackether.world.EthermodConfiguredFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;

import java.util.concurrent.atomic.AtomicReference;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

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
        EthermodCarverGen.generateCarvers();
        EthermodOreGen.generateOres();
        EthermodTreeGen.generateTrees();
        EthermodEntitySpawns.spawnCreatures();
    }
}
