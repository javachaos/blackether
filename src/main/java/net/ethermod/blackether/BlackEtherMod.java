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
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class BlackEtherMod implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger(BlackEtherMod.class);
    public static final String MODID = "ethermod";
    public static final PropertyManager PROPERTIES = new PropertyManager();
    public static final Identifier NEUTRON_IONIZING = new Identifier(MODID, "neutron_ionizing");
    public static final SoundEvent NEUTRON_EVENT = new SoundEvent(NEUTRON_IONIZING);
    public static final Block ETHER_ORE_BLOCK = new EtherOreBlock(FabricBlockSettings.of(Material.METAL,
            MapColor.BLACK).ticksRandomly().luminance(9).strength(5.0F, 6.0F));

    private static final ConfiguredFeature<?, ?> ORE_ETHER_OVERWORLD = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreConfiguredFeatures.BASE_STONE_OVERWORLD,
                    ETHER_ORE_BLOCK.getDefaultState(),
                    PROPERTIES.getIntegerProperty("blackether.ore.vein_size", 15))); // vein size

    public static final PlacedFeature ORE_ETHER_OVERWORLD_FEATURE = ORE_ETHER_OVERWORLD.withPlacement(
            CountPlacementModifier.of(PROPERTIES.getIntegerProperty("blackether.ore.veins_per_chunk", 7)), // number of veins per chunk
            SquarePlacementModifier.of(), // spreading horizontally
            HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))); // height

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
        registerOreByName("ore_ether_overworld", ORE_ETHER_OVERWORLD, ORE_ETHER_OVERWORLD_FEATURE);
    }

    private void registerOreByName(final String oreName, final ConfiguredFeature<?, ?> feature, final PlacedFeature placedFeature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                new Identifier(MODID, oreName), feature);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(MODID, oreName),
                placedFeature);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                        new Identifier(MODID, oreName)));
    }

    private void initProperties() {
        //May use this code later on so we keep it.
//        spawnOnyx = PROPERTIES.getBooleanProperty("spawn.onyxforts", true);
//        onyxBiomeEnabled = PROPERTIES.getBooleanProperty("enable.onyx.biome", true);
//        onyxSpawnChance = PROPERTIES.getIntegerProperty("onyxfort.spawn.spacing", 128);
    }



}
