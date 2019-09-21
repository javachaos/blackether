package net.ethermod.blackether.utils;

import com.mojang.brigadier.arguments.IntegerArgumentType;
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

    public static final int TICKS_PER_SEC = 20;

    /**
     * Returns the number of ticks per second.
     * @param seconds the number of desired seconds to get ticks for
     * @return
     *      the number of ticks per second
     */
    public static final int getTicksPerSec(int seconds) {
        if (seconds >= 0 && seconds <= Integer.MAX_VALUE) {
            return seconds * TICKS_PER_SEC;
        } else {
            throw new RuntimeException("Error, ticks per second conversion out of bounds.");
        }
    }

    public static final int getTicksPerMin(int minutes) {
        if (minutes >= 0 && minutes <= Integer.MAX_VALUE) {
            return minutes * getTicksPerSec(60);
        } else {
            throw new RuntimeException("Error, ticks per second conversion out of bounds.");
        }
    }
}
