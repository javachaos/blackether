package net.ethermod.blackether.data.world;

import net.ethermod.blackether.registries.BiomeRegistry;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;

public class EthermodOreGen {

    public static void generateOres() {
        //Load Ores
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeRegistry.ONYX_BIOME)
                        .and(BiomeSelectors.foundInOverworld()),
                GenerationStep.Decoration.UNDERGROUND_ORES, EthermodPlacedFeatures.ETHERORE);
    }
}
