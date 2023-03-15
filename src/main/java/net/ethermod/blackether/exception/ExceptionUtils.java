package net.ethermod.blackether.exception;


import net.ethermod.blackether.exception.EthermodException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Helper class to help with exception code.
 *
 * @author fred
 */
public final class ExceptionUtils {

    /**
     * Private Ctor.
     */
    private ExceptionUtils() {
    }

    /**
     * Log the error and set the Application
     * state to ERROR state.
     *
     * @param c the class for the logger.
     * @param e the exception thrown.
     */
    public static void fatalError(final Class<?> c, final Exception e) {
        e.printStackTrace();
        logError(c, e);
    }

    /**
     * Log the error and set the Application
     * state to ERROR state.
     *
     * @param c the class for the logger.
     * @param e the exception thrown.
     */
    public static void logError(final Class<?> c, final Exception e) {
        Logger logger = LogManager.getLogger(c);
        logger.error(e.getMessage());
        throw new EthermodException(e);
    }


}