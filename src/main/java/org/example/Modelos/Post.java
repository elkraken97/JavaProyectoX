package org.example.Modelos;

import org.example.Excepciones.DemasiadosCaracteres;

import java.time.LocalDateTime;


public class Post {

    private long id;
    private final long usrId;
    private final String contenido;
    private final LocalDateTime creacion;



    public Post(long usrId, String contenido) throws DemasiadosCaracteres {

        if (contenido.length()>200){
            throw new DemasiadosCaracteres("El contenido del post debe tener menos de 200 caracteres");
        } this.id = -1 ;
        this.usrId = usrId;
        this.contenido = contenido;
        this.creacion = LocalDateTime.now();


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

    public String getContenido() {
        return contenido;
    }


    public LocalDateTime getCreacion() {
        return creacion;
    }



}
