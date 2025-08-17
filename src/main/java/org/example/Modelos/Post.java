package org.example.Modelos;

import org.example.Excepciones.DemasiadosCaracteres;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Post {

    private long id;
    private long usrId;
    private String contenido;
    private LocalDateTime creacion;
    private Set<Long> likes;


    public Post(long usrId, String contenido) throws DemasiadosCaracteres {

        if (contenido.length()>200){
            throw new DemasiadosCaracteres("El contenido del post debe tener menos de 200 caracteres");
        } this.id = -1 ;
        this.usrId = usrId;
        this.contenido = contenido;
        this.creacion = LocalDateTime.now();
        this.likes = new HashSet<>();

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUsrId() {
        return usrId;
    }

    public void setUsrId(long usrId) {
        this.usrId = usrId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getCreacion() {
        return creacion;
    }

    public void setCreacion(LocalDateTime creacion) {
        this.creacion = creacion;
    }

    public Set<Long> getLikes() {
        return likes;
    }

    public boolean likear(long id) {
        return likes.add(id);
    }

    public boolean unlike(long id){
        return likes.remove(id);
    }

    public boolean publicacionLikeada(long id){return likes.contains(id);}


}
