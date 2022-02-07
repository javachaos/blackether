package net.ethermod.blackether.utils;

import net.ethermod.blackether.BlackEtherMod;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;

/**
 * Constants.
 *
 * @author fred
 */
public final class Constants {

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
     * Number of Ticks per second in Minecraft.
     */
    public static final int TICKS_PER_SEC = 20;
  
    private static String MODID;
  
    /**
     * Application Properties file name.
     */
    public static final String PROPERTY_FILE_NAME = Constants.GAME_DIR
        + File.separator + getModID() + ".properties";
  
    /**
     * Private ctor.
     */
    private Constants() {
    }
  
    /**
     * Init the constants.
     *
     * @param modid the id for your mod
     */
    public static void init(final String modid) {
      Constants.MODID = modid;
    }
  
    /**
     * Returns the number of ticks per second.
     *
     * @param seconds the number of desired seconds to get ticks for
     * @return the number of ticks per second
     */
    public static final int getTicksPerSec(int seconds) {
      if (seconds >= 0) {
        return seconds * TICKS_PER_SEC;
      } else {
        throw new RuntimeException("Error, ticks per second conversion out of bounds.");
      }
    }
  
    /**
     * Returns the number of ticks per minute.
     *
     * @param minutes
     *      the number of desired minutes to get ticks for
     * @return the number of ticks per second
     *
     */
    public static final int getTicksPerMin(int minutes) {
      if (minutes >= 0) {
        return minutes * getTicksPerSec(60);
      } else {
        throw new RuntimeException("Error, ticks per second conversion out of bounds.");
      }
    }
  
    /**
     * Return the MODID for this current mod.
     * @return the universally identifiable mod identifier.
     */
    public static final String getModID() {
      return MODID;
    }
  }
