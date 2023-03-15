package net.ethermod.blackether;

import net.ethermod.blackether.entity.living.OnyxSnakeEntity;
import net.ethermod.blackether.registries.*;
import net.ethermod.blackether.utils.Naming;
import net.ethermod.blackether.utils.PropertyManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib.GeckoLib;

import java.util.ArrayDeque;
import java.util.Deque;

public class BlackEtherMod implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger(BlackEtherMod.class);
    public static final String MOD_ID = "ethermod";
    public static final PropertyManager PROPERTIES = new PropertyManager();

    @Override
    public void onInitialize() {
        LOGGER.debug("{} started initializing.", MOD_ID);
        GeckoLib.initialize();

        //Temp. deque to register all registerable registries
        Deque<Registerable> registries = new ArrayDeque<>();

        //Order of these matters.
        registries.add(EntityRegistry.getInstance());
        registries.add(SoundRegistry.getInstance());
        registries.add(BlockRegistry.getInstance());
        registries.add(ItemRegistry.getInstance());

        //Call register() method on each registerable
        registries.forEach(Registerable::register);

        //Done registering now free up this deque
        registries.clear();

        //Load Ores
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES, BlockRegistry.CUSTOM_ORE_PLACED_KEY);

        registerEntityAttributes();
        FuelRegistry.INSTANCE.add(ItemRegistry.getInstance().getItem(Naming.ETHER_ORE), 3000);
        LOGGER.debug("{} finished initializing.", MOD_ID);
    }


    private void registerEntityAttributes() {
        FabricDefaultAttributeRegistry.register(EntityRegistry.getInstance()
                .getEntityType(Naming.ONYX_SNAKE, OnyxSnakeEntity.class), createGenericEntityAttributes());
    }

    private AttributeSupplier.Builder createGenericEntityAttributes() {
        return LivingEntity.createLivingAttributes().add(Attributes.MOVEMENT_SPEED, 0.0280000000298023224D)
                .add(Attributes.FOLLOW_RANGE, 16.0D).add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.ATTACK_DAMAGE, 5)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1);
    }


}
