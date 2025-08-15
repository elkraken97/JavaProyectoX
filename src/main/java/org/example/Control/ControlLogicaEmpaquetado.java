package org.example.Control;


import org.example.Modelos.User;
import org.example.Servicios.PostServicio;
import org.example.Servicios.UsuarioServicio;
import org.example.Sesiones.Sesion;

import java.util.List;

public class ControlLogicaEmpaquetado {

    private final PostServicio servicioPosts;
    private final UsuarioServicio servicioUsuarios;
    private final Sesion sesion;


    public ControlLogicaEmpaquetado(PostServicio servicioPosts, UsuarioServicio servicioUsuarios, Sesion sesion) {
        this.servicioPosts = servicioPosts;
        this.servicioUsuarios = servicioUsuarios;
        this.sesion = sesion;
    }


    public String iniciarSesion(int id){
        User usr = servicioUsuarios.usuarioPorId(id);
        sesion.iniciarSesion(usr);
        return "Has iniciado Sesion como " + usr.getNombre();
    }

   public List<String> mostrarTodosLosUsuarios(){

       return servicioUsuarios.todosLosUsuarios().stream().map(servicioUsuarios::procesarUsuario).toList();

   }


   public List<String> mostrarTodosLosPostsSimple(){

      return servicioPosts.todosLosPost().stream().map(servicioPosts::procesarPost).toList();


   }




}
