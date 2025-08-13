package org.example;

import org.example.Modelos.User;


public class Sesion {


    private User usuarioActual;

    public void iniciarSesion(User usr){
        this.usuarioActual = usr;
    }

    public void cerrarSesion(){
        this.usuarioActual = null;
    }

    public boolean haySesionActiva(){
        return this.usuarioActual!=null;
    }

    public User usuarioActual(){
        return this.usuarioActual;
    }



}
