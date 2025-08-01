package org.example.exceptions;

public class FilmeSemEstoqueException extends RuntimeException {

    public FilmeSemEstoqueException() {
        super("Filme sem estoque disponivel.");
    }

    public FilmeSemEstoqueException(String message) {
        super(message);
    }

    public FilmeSemEstoqueException(String message, Throwable cause) {
        super(message, cause);
    }

    public FilmeSemEstoqueException(Throwable cause) {
        super(cause);
    }
}
