package net.ethermod.blackether.utils;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static net.ethermod.blackether.BlackEtherMod.MOD_ID;

/**
 * PropertyManager class used to get property values from the Property file
 * defined in fileName.
 *
 * @author fred
 */
public final class PropertyManager {

    private static final Logger LOGGER = LogUtils.getLogger();
    //LogManager.getLogger(PropertyManager.class);
    private final Properties props = new Properties();
    /**
     * Application Properties file name.
     */
    public static final String PROPERTY_FILE_NAME = Constants.GAME_DIR
            + File.separator + MOD_ID + ".properties";
    private boolean isLoaded = false;

    public PropertyManager() {
        loadProperties();
    }

    private void loadProperties() {
        try {
            if (new File(PROPERTY_FILE_NAME).createNewFile()) {
                PropertyManager.LOGGER.debug("Created new empty properties file.");
            }
        } catch (Exception e) {
            PropertyManager.LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        try (FileInputStream fin = new FileInputStream(PROPERTY_FILE_NAME)) {
            if (!new File(PROPERTY_FILE_NAME).createNewFile()) {
                props.load(fin);
                PropertyManager.LOGGER.debug("Properties loaded into memory.");
                String properties = props.stringPropertyNames().toString();
                LOGGER.debug(properties);
            }
        } catch (final IOException e) {
            PropertyManager.LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        isLoaded = true;
    }

    private void writeProperties() {
        try (FileOutputStream fos = new FileOutputStream(PROPERTY_FILE_NAME)) {
            props.store(fos, null);
            PropertyManager.LOGGER.debug("Properties written to disk.");
        } catch (final IOException e) {
            PropertyManager.LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Add a property to this property manager.
     *
     * @param key   the key to store the property under
     * @param value the value of the property to be added
     * @param <T>   the type of the value to store
     */
    public <T extends Number> void addProperty(
            final String key, final T value) {
        if (!isLoaded) {
            loadProperties();
        }
        props.put(key, value);
        writeProperties();
    }

    /**
     * Add a string property to this property manager.
     *
     * @param key   the key to store the property under
     * @param value the value of the property to be added
     */
    public void addProperty(
            final String key, final String value) {
        if (!isLoaded) {
            loadProperties();
        }
        props.put(key, value);
        writeProperties();
    }

    /**
     * Return a string property from the underlying PROPS instance.
     *
     * @param key the key of the value to get
     * @return the value assigned the key key
     */
    public String getStringProperty(final String key) {
        if (!isLoaded) {
            loadProperties();
        }
        if ((key == null) || (key.length() <= 0)) {
            throw new IllegalArgumentException("Key was null or zero length.");
        }
        return props.getProperty(key);
    }

    /**
     * Return the property as a string.
     *
     * @param key          the key of the property.
     * @param defaultValue the default value for the property.
     * @return the property if it exists or if it
     * does not exist returns the defaultValue.
     */
    public String getStringProperty(final String key,
                                    final String defaultValue) {
        String temp;
        if (!isLoaded) {
            loadProperties();
        }
        if ((key == null) || (key.length() <= 0)) {
            return defaultValue;
        }
        temp = props.getProperty(key);
        if (temp == null || (temp.length() <= 0)) {
            return defaultValue;
        }

        return temp;
    }

    /**
     * GetFloatProperty.
     *
     * @param key the key to the property.
     * @return the property value as a float.
     * if the value is not recognizable as a
     * float an exception is thrown.
     */
    public float getFloatProperty(final String key) {
        return Float.parseFloat(getStringProperty(key));
    }

    /**
     * Return the property as a Float.
     *
     * @param key          the key of the property.
     * @param defaultValue the default value for the property.
     * @return the property if it exists as a Float or if it
     * does not exist returns the defaultValue.
     */
    public float getFloatProperty(
            final String key, final float defaultValue) {
        return Float.parseFloat(getStringProperty(key, defaultValue + ""));
    }

    /**
     * GetIntegerProperty.
     *
     * @param key the key to the property.
     * @return the property value as an Integer.
     * if the value is not recognizable as an
     * Integer an exception is thrown.
     */
    public int getIntegerProperty(final String key) {
        return Integer.parseInt(getStringProperty(key));
    }

    /**
     * Return the property as an Integer.
     *
     * @param key          the key of the property.
     * @param defaultValue the default value for the property.
     * @return the property if it exists as an Integer or if it
     * does not exist returns the defaultValue.
     */
    public int getIntegerProperty(
            final String key, final int defaultValue) {
        return Integer.parseInt(getStringProperty(
                key, defaultValue + ""));
    }

    /**
     * Get a property from the property as a long value.
     *
     * @param key the key of the property to get
     * @return the value of the property
     */
    public long getLongProperty(final String key) {
        return Long.parseLong(getStringProperty(key));
    }

    /**
     * Get a property from the property as a long value.
     * If the property cannot be returned from the underlying
     * PROPS instance then return defaultValue.
     *
     * @param key          the key of the property to get
     * @param defaultValue the defaultValue to be returned if
     *                     the value for key cannot be returned
     * @return the value for key or defaultValue
     * if the value for key cannot be returned
     */
    public long getLongProperty(
            final String key, final long defaultValue) {
        return Long.parseLong(getStringProperty(
                key, defaultValue + ""));
    }

    /**
     * Get a property from the property as a boolean value.
     * If the property cannot be returned from the underlying
     * PROPS instance then return defaultValue.
     *
     * @param key          the key of the property to get
     * @param defaultValue the defaultValue to be returned if
     *                     the value for key cannot be returned
     * @return the value for key or defaultValue
     * if the value for key cannot be returned
     */
    public boolean getBooleanProperty(final String key,
                                      final boolean defaultValue) {
        return Boolean.parseBoolean(getStringProperty(key, defaultValue + ""));
    }
}
