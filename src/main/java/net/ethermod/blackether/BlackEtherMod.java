package net.ethermod.blackether;

import net.ethermod.blackether.entity.misc.NeutronBombEntityRenderer;
import net.ethermod.blackether.registries.*;
import net.ethermod.blackether.utils.PropertyManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib.GeckoLib;

public class BlackEtherMod implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger(BlackEtherMod.class);
    public static final String MODID = "ethermod";
    public static final PropertyManager PROPERTIES = new PropertyManager();

    @Override
    public void onInitialize() {
        LOGGER.debug("{} started initializing.", MODID);
        EntityRendererRegistry.register(EntityRegistry.NEUTRON_BOMB_ENTITY, NeutronBombEntityRenderer::new);
        initProperties();
        GeckoLib.initialize();
        new SoundRegistry();
        new BlockRegistry();
        new BlockEntityRegistry();
        new EntityRegistry();
        new ItemRegistry();
        loadOres();
        registerEntityAttributes();
        FuelRegistry.INSTANCE.add(ItemRegistry.ETHER_ORE, 3000);
        LOGGER.debug("{} finished initializing.", MODID);
    }

    private void loadOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES, BlockRegistry.CUSTOM_ORE_PLACED_KEY);
    }


    private void initProperties() {
//        spawnOnyx = PROPERTIES.getBooleanProperty("spawn.onyxforts", true);
//        onyxBiomeEnabled = PROPERTIES.getBooleanProperty("enable.onyx.biome", true);
//        onyxSpawnChance = PROPERTIES.getIntegerProperty("onyxfort.spawn.spacing", 128);
    }


    private void registerEntityAttributes() {
        FabricDefaultAttributeRegistry.register(EntityRegistry.ONYX_SNAKE, createGenericEntityAttributes());
    }

    private static AttributeSupplier.Builder createGenericEntityAttributes() {
        return PathfinderMob.createLivingAttributes().add(Attributes.MOVEMENT_SPEED, 0.80000000298023224D)
                .add(Attributes.FOLLOW_RANGE, 16.0D).add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.ATTACK_DAMAGE, 5)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1);
    }


}
