package org.example.Excepciones;

public class SinPostsCreados extends RuntimeException {
    public SinPostsCreados(String message) {
        super(message);
    }
}
