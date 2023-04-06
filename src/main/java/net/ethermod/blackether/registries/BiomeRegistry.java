package net.ethermod.blackether.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

public class BiomeRegistry {

    private BiomeRegistry() {}

    public static final ResourceKey<Biome> ONYX_BIOME = ResourceKey.create(Registries.BIOME, new ResourceLocation(MOD_ID, "onyx_biome"));
}
