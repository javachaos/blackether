package net.ethermod.blackether.utils;

import net.ethermod.blackether.exception.EthermodException;
import net.fabricmc.loader.api.FabricLoader;
import java.io.File;
import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

/**
 * Constants.
 *
 * @author fred
 */
public final class Constants {

    /**
     * Application directory location.
     */
    public static final String GAME_DIR = FabricLoader.getInstance().getGameDir()
        + File.separator;
  
    /**
     * Number of Ticks per second in Minecraft.
     */
    public static final int TICKS_PER_SEC = 20;

  
    /**
     * Application Properties file name.
     */
    public static final String PROPERTY_FILE_NAME = Constants.GAME_DIR
        + File.separator + MOD_ID + ".properties";
  
    /**
     * Private ctor.
     */
    private Constants() {
    }

    /**
     * Returns the number of ticks per second.
     *
     * @param seconds the number of desired seconds to get ticks for
     * @return the number of ticks per second
     */
    public static int inSeconds(int seconds) {
      if (seconds >= 0) {
        return seconds * TICKS_PER_SEC;
      } else {
        throw new EthermodException("Error, ticks per second conversion out of bounds.");
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
    public static int inMinutes(int minutes) {
      if (minutes >= 0) {
        return minutes * inSeconds(60);
      } else {
        throw new EthermodException("Error, ticks per second conversion out of bounds.");
      }
    }

  }
