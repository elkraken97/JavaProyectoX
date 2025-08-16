package org.example.Control;


import org.example.Modelos.Post;
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



    public List<String> feedGeneral(){


      return servicioPosts.todosLosPost().stream().map(servicioPosts::procesarPost).toList();


    }



    public boolean haySesionActiva(){
        return sesion.haySesionActiva();
    }
    public String nombreDeLaSesion(){
        return  sesion.usuarioActual().getNombre();
    }

    public String iniciarSesion(int id){

        if (sesion.haySesionActiva()) {
            return "Ya hay una sesion activa como"+sesion.usuarioActual().getNombre();
        }
        User usr = servicioUsuarios.usuarioPorId(id);
        sesion.iniciarSesion(usr);
        return "Has iniciado Sesion como " + usr.getNombre();
    }

   public List<String> mostrarTodosLosUsuarios(){

       return servicioUsuarios.todosLosUsuarios().stream().map(servicioUsuarios::procesarUsuario).toList();

   }

    public boolean logoutSesion(){
        sesion.cerrarSesion();
        return sesion.haySesionActiva();
    }


   public List<String> mostrarTodosLosPostsSimple(){

      return servicioPosts.todosLosPost().stream().map(servicioPosts::procesarPost).toList();


   }


    public String crearUsuario(String nombre){

       User usr =  servicioUsuarios.agregarUsuario(nombre);

        return servicioUsuarios.procesarUsuario(usr);

    }




}
