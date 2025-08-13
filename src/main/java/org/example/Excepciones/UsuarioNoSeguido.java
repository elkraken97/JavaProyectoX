package org.example.Excepciones;

public class UsuarioNoSeguido extends RuntimeException {
    public UsuarioNoSeguido(String message) {
        super(message);
    }
}
