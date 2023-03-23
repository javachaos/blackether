package net.ethermod.blackether.world.gen;

import net.ethermod.blackether.registries.BlockRegistry;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;

public class EthermodOreGen {
    public static void generateOres() {
        //Load Ores
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES, BlockRegistry.CUSTOM_ORE_PLACED_KEY);
    }
}
