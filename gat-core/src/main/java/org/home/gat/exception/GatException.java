package org.home.gat.exception;

public class GatException extends RuntimeException {

    GatException(String string, Object... args) {
        super(String.format(string, args));
    }

    public GatException(Exception cause, String string, Object... args) {
        super(String.format(string, args), cause);
    }

}
