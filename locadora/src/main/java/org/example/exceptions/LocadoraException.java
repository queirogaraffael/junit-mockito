package org.example.exceptions;

public class LocadoraException extends RuntimeException{
    public LocadoraException() {
    }

    public LocadoraException(String message) {
        super(message);
    }

    public LocadoraException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocadoraException(Throwable cause) {
        super(cause);
    }

    public LocadoraException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
