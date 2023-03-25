package net.ethermod.blackether.world.gen;

import net.ethermod.blackether.world.EthermodCarvers;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

public class EthermodCarverGen {
    public static void generateCarvers() {
        ResourceKey<Biome> biome = ResourceKey.create(Registries.BIOME, new ResourceLocation(MOD_ID, "onyx_biome"));
        BiomeModifications.addCarver(BiomeSelectors.includeByKey(biome),
                GenerationStep.Carving.AIR,
                EthermodCarvers.DARK_GRASS_CONFIGURED_CARVER);
    }
}
