package net.ethermod.blackether;

import net.ethermod.blackether.utils.PropertyManager;
import net.ethermod.blackether.utils.RegistryInitializer;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib.GeckoLib;

public class BlackEtherMod implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger(BlackEtherMod.class);
    public static final String MOD_ID = "ethermod";
    public static final PropertyManager PROPERTIES = new PropertyManager();

    @Override
    public void onInitialize() {
        LOGGER.debug("{} started initializing.", MOD_ID);
        GeckoLib.initialize();
        RegistryInitializer.registerAll();
        LOGGER.debug("{} finished initializing.", MOD_ID);

    }

}
