package net.ethermod.blackether.world.gen;

import net.ethermod.blackether.utils.Naming;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

public class EthermodCarvers {

    public static final ResourceKey<ConfiguredWorldCarver<?>>
            DARK_GRASS_CONFIGURED_CARVER = registerCarver(Naming.DARK_GRASS_CARVER);


    public static void bootstrap(BootstapContext<ConfiguredWorldCarver<?>> context) {
        HolderGetter<Block> holderGetter = context.lookup(Registries.BLOCK);

        context.register(DARK_GRASS_CONFIGURED_CARVER,
                WorldCarver.CAVE.configured(
                        new CaveCarverConfiguration(0.215F,
                                UniformHeight.of(VerticalAnchor.aboveBottom(18),
                                        VerticalAnchor.absolute(180)),
                                UniformFloat.of(1.9F, 10.19F),
                                VerticalAnchor.aboveBottom(18),
                                CarverDebugSettings.of(false, Blocks.CRIMSON_BUTTON.defaultBlockState()),
                                holderGetter.getOrThrow(BlockTags.OVERWORLD_CARVER_REPLACEABLES),
                                UniformFloat.of(1.1F, 14.4F),
                                UniformFloat.of(0.0F, 12.3F),
                                UniformFloat.of(0.0F, 0.8887F))));

    }

    public static ResourceKey<ConfiguredWorldCarver<?>> registerCarver(String name) {
        return ResourceKey.create(Registries.CONFIGURED_CARVER, new ResourceLocation(MOD_ID, name));
    }


}
