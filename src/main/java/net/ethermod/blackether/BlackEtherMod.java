package net.ethermod.blackether;

import net.ethermod.blackether.blocks.EtherOreBlock;
import net.ethermod.blackether.entity.misc.NeutronBombEntityRenderer;
import net.ethermod.blackether.items.*;
import net.ethermod.blackether.utils.PropertyManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib.GeckoLib;

public class BlackEtherMod implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger(BlackEtherMod.class);
    public static final String MODID = "ethermod";
    public static final PropertyManager PROPERTIES = new PropertyManager();
    public static final ResourceLocation NEUTRON_IONIZING = new ResourceLocation(MODID, "neutron_ionizing");
    public static final SoundEvent NEUTRON_EVENT = SoundEvent.createVariableRangeEvent(NEUTRON_IONIZING);
    public static final Block ETHER_ORE_BLOCK = new EtherOreBlock(FabricBlockSettings.of(Material.METAL,
            MaterialColor.COLOR_BLACK).randomTicks().lightLevel(x -> 9).strength(5.0f,6.0f));

    public static final ResourceKey<PlacedFeature> CUSTOM_ORE_PLACED_KEY = ResourceKey.create(
            Registries.PLACED_FEATURE, new ResourceLocation(MODID, "ether_ore_block"));


    @Override
    public void onInitialize() {
        LOGGER.debug("{} started initializing.", MODID);
        RegisterItems.register();
        EntityRendererRegistry.register(RegisterItems.NEUTRON_BOMB_ENTITY, NeutronBombEntityRenderer::new);
        initProperties();
        loadOres();
        GeckoLib.initialize();
        LOGGER.debug("{} finished initializing.", MODID);
    }

    private void loadOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, CUSTOM_ORE_PLACED_KEY);
    }



    private void initProperties() {
//        spawnOnyx = PROPERTIES.getBooleanProperty("spawn.onyxforts", true);
//        onyxBiomeEnabled = PROPERTIES.getBooleanProperty("enable.onyx.biome", true);
//        onyxSpawnChance = PROPERTIES.getIntegerProperty("onyxfort.spawn.spacing", 128);
    }



}
