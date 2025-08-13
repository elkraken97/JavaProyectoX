package org.example.Modelos;

import java.util.HashSet;
import java.util.Set;

public class User {

    private long id;
    private String nombre;
    private Set<Integer> seguidores;
    private Set<Integer> siguiendo;


    public User(String nombre) {
        this.nombre = nombre;
        id = -1;
        this.siguiendo = new HashSet<>();
        this.seguidores = new HashSet<>();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Integer> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(Set<Integer> seguidores) {
        this.seguidores = seguidores;
    }

    public Set<Integer> getSiguiendo() {
        return siguiendo;
    }

    public void setSiguiendo(Set<Integer> siguiendo) {
        this.siguiendo = siguiendo;
    }
}
