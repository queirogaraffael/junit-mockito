package org.example.exceptions;

public class UsuarioNegativadoSPC extends RuntimeException{
    public UsuarioNegativadoSPC() {
    }

    public UsuarioNegativadoSPC(String message) {
        super(message);
    }

    public UsuarioNegativadoSPC(String message, Throwable cause) {
        super(message, cause);
    }

    public UsuarioNegativadoSPC(Throwable cause) {
        super(cause);
    }

    public UsuarioNegativadoSPC(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
