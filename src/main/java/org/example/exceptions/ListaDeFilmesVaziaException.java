package org.example.exceptions;

public class ListaDeFilmesVaziaException extends RuntimeException{
    public ListaDeFilmesVaziaException() {
    }

    public ListaDeFilmesVaziaException(String message) {
        super(message);
    }

    public ListaDeFilmesVaziaException(String message, Throwable cause) {
        super(message, cause);
    }

    public ListaDeFilmesVaziaException(Throwable cause) {
        super(cause);
    }

    public ListaDeFilmesVaziaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
