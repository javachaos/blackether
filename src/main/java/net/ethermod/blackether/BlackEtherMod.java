package net.ethermod.blackether;

import net.ethermod.blackether.blocks.EtherOreBlock;
import net.ethermod.blackether.entity.NeutronBombEntityRenderer;
import net.ethermod.blackether.items.*;
import net.ethermod.blackether.utils.PropertyManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BlackEtherMod implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger(BlackEtherMod.class);
    public static final String MODID = "ethermod";
    public static final PropertyManager PROPERTIES = new PropertyManager();
    public static final Identifier NEUTRON_IONIZING = new Identifier(MODID, "neutron_ionizing");
    public static final SoundEvent NEUTRON_EVENT = SoundEvent.of(NEUTRON_IONIZING);
    public static final Block ETHER_ORE_BLOCK = new EtherOreBlock(FabricBlockSettings.of(Material.METAL,
            MapColor.BLACK).ticksRandomly().luminance(9).strength(5.0F, 6.0F));

    public static final RegistryKey<PlacedFeature> CUSTOM_ORE_PLACED_KEY = RegistryKey.of(
            RegistryKeys.PLACED_FEATURE, new Identifier(MODID, "ether_ore_block"));

    @Override
    public void onInitialize() {
        LOGGER.debug("{} started initializing.", MODID);
        RegisterItems.register();
        EntityRendererRegistry.register(RegisterItems.NEUTRON_BOMB_ENTITY, NeutronBombEntityRenderer::new);
        initProperties();
        loadOres();
        LOGGER.debug("{} finished initializing.", MODID);
    }

    private void loadOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, CUSTOM_ORE_PLACED_KEY);
    }


    private void initProperties() {
        //May use this code later on so we keep it.
//        spawnOnyx = PROPERTIES.getBooleanProperty("spawn.onyxforts", true);
//        onyxBiomeEnabled = PROPERTIES.getBooleanProperty("enable.onyx.biome", true);
//        onyxSpawnChance = PROPERTIES.getIntegerProperty("onyxfort.spawn.spacing", 128);
    }



}
