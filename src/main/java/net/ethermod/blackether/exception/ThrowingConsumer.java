package net.ethermod.blackether.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;

@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> {
    void accept(T t) throws E;
    static <T, E extends Exception> Consumer<T> handlingConsumerWrapper(
            ThrowingConsumer<T, E> throwingConsumer, Class<E> exceptionClass) {
        return i -> {
            try {
                throwingConsumer.accept(i);
            } catch (Exception ex) {
                try {
                    E exCast = exceptionClass.cast(ex);
                    Logger logger = LogManager.getLogger(exceptionClass);
                    logger.error("Exception occured : {}", exCast.getMessage());
                } catch (ClassCastException ccEx) {
                    throw new EthermodException(ex);
                }
            }
        };
    }
}
