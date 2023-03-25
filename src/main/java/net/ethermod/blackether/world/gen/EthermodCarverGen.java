package net.ethermod.blackether.world.gen;

import net.ethermod.blackether.registries.BiomeRegistry;
import net.ethermod.blackether.world.EthermodCarvers;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;

public class EthermodCarverGen {
    public static void generateCarvers() {
        BiomeModifications.addCarver(BiomeSelectors.includeByKey(BiomeRegistry.ONYX_BIOME),
                GenerationStep.Carving.AIR,
                EthermodCarvers.DARK_GRASS_CONFIGURED_CARVER);
    }
}
