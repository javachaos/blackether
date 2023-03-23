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


//TODO https://github.com/Witixin1512/GeckoLib-MultiLoader-Template/tree/gl4-vanillagradle#readme
// work towards shifting to this project template.

//TODO Finish onyx_biome to grey/black atmosphere
// add mob spawns
// add a new mob
// add a sound to the existing mob (snake)

    @Override
    public void onInitialize() {
        LOGGER.debug("{} started initializing.", MOD_ID);
        GeckoLib.initialize();
        RegistryInitializer.registerAll();
        LOGGER.debug("{} finished initializing.", MOD_ID);
    }


}
