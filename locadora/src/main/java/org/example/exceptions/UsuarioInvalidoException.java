package org.example.exceptions;

public class UsuarioInvalidoException extends RuntimeException {

    public UsuarioInvalidoException() {
        super("Usuário inválido.");
    }

    public UsuarioInvalidoException(String message) {
        super(message);
    }

    public UsuarioInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsuarioInvalidoException(Throwable cause) {
        super(cause);
    }
}

