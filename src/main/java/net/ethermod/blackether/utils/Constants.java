package net.ethermod.blackether.utils;

import net.ethermod.blackether.BlackEtherMod;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;

/**
 * Constants.
 * @author fred
 *
 */
public final class Constants {

    /**
     * Private ctor.
     */
    private Constants() {
    }

    /**
     * Init the constants.
     */
    public static void init() {
        new Constants();
    }

    /**
     * Encoding.
     */
    public static final String ENCODING = "UTF-8";

    /**
     * Application directory location.
     */
    public static final String GAME_DIR = FabricLoader.getInstance().getGameDirectory()
                    + File.separator;

    /**
     * Application Properties file name.
     */
    public static final String PROPERTY_FILE_NAME = Constants.GAME_DIR
            + File.separator + BlackEtherMod.MODID + ".properties";

}
