package org.example.Excepciones;

public class UsuarioNoAgregado extends RuntimeException {
    public UsuarioNoAgregado(String message) {
        super(message);
    }
}
