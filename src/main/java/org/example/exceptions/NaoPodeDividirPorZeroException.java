package org.example.exceptions;

public class NaoPodeDividirPorZeroException extends RuntimeException {

    public NaoPodeDividirPorZeroException() {
    }

    public NaoPodeDividirPorZeroException(String message) {
        super(message);
    }

    public NaoPodeDividirPorZeroException(String message, Throwable cause) {
        super(message, cause);
    }

    public NaoPodeDividirPorZeroException(Throwable cause) {
        super(cause);
    }

    public NaoPodeDividirPorZeroException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
