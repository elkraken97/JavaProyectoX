package org.example.Excepciones;

public class ErrorAlGuardarDato extends RuntimeException {
    public ErrorAlGuardarDato(String message) {
        super(message);
    }
}
