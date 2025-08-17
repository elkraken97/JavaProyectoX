package org.example.Modelos;

import java.util.HashSet;
import java.util.Set;

public class User {

    private long id;
    private String nombre;
    private Set<Long> seguidores;
    private Set<Long> siguiendo;


    public User(String nombre) {
        this.nombre = nombre;
        id = -1;
        this.siguiendo = new HashSet<>();
        this.seguidores = new HashSet<>();
    }

    public boolean yaSeguid(long id){return seguidores.contains(id);}

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

    public Set<Long> getSeguidores() {
        return seguidores;
    }

    public boolean seguirAUsuario(Long usrId){
        return siguiendo.add(usrId);
    }

    public boolean dejarDeSeguir(Long usrId){
        return siguiendo.remove(usrId);
    }

    public boolean nuevoSeguidor(Long usrId){
        return seguidores.add(usrId);
    }

    public boolean dejoDeseguir(Long usrID){
        return  seguidores.remove(usrID);
    }

    public void setSeguidores(Set<Long> seguidores) {
        this.seguidores = seguidores;
    }

    public Set<Long> getSiguiendo() {
        return siguiendo;
    }

    public void setSiguiendo(Set<Long> siguiendo) {
        this.siguiendo = siguiendo;
    }
}
