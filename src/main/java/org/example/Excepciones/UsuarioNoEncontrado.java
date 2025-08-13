package org.example.Excepciones;

public class UsuarioNoEncontrado extends RuntimeException {
    public UsuarioNoEncontrado() {
        super("usuario no encontrado");
    }
}
