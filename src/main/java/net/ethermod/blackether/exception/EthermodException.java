package net.ethermod.blackether.exception;

public class EthermodException extends RuntimeException {
    public EthermodException(String s) {
        super(s);
    }

    public EthermodException(Exception e) {
        super(e);
    }
}
