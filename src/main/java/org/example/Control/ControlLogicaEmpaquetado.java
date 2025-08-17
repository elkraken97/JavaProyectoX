package org.example.Control;



import org.example.Modelos.Post;
import org.example.Modelos.User;
import org.example.Servicios.PostServicio;
import org.example.Servicios.UsuarioServicio;
import org.example.Sesiones.Sesion;


import java.util.Collections;
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



    public List<Post> feedGeneral(){


      return servicioPosts.todosLosPost();


    }

    public boolean usuarioYaSeguido(long idUsr){


        return servicioUsuarios.usuarioYaSeguido(sesion.usuarioActual().getId(),idUsr);
    }

    public String seguirAUsuario(long idUser){
        User usr = servicioUsuarios.usuarioPorId(idUser);
        if (servicioUsuarios.seguirAUsuario(sesion.usuarioActual().getId(),usr.getId())) {
            return "Has seguido a "+usr.getNombre();
        }
        return "Ha ocurrido un error al seguir a "+usr.getNombre();

    }


    public String dejarDeSeguir(long idUser){
        User usr = servicioUsuarios.usuarioPorId(idUser);

        if (servicioUsuarios.dejarDeSeguirAUsuario(sesion.usuarioActual().getId(),idUser)){
            return "Has dejado de seguir a "+usr.getNombre();
        }
        return "Ha ocurrido un problema al dejar de seguir a "+usr.getNombre();

    }

    public String procesarPost(Post ps){
        return servicioPosts.procesarPost(ps);
    }

    public String darLike(Post post){
        if (servicioPosts.darLike(sesion.usuarioActual().getId(),post.getId())) {
            return "Has likeado esta publicacion";
        }
        return "Ha ocurrido un problema al dar like";
    }

    public String quitarLike(Post post){
        if (servicioPosts.quitarLike(sesion.usuarioActual().getId(),post.getId())){
            return "Has quitado el like de esta publicacion";
        }
        return "Ha ocurrido un problema al dar like a esta publicacion ";
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
   public List<Post> mostrarPostsDeUnUsuario(long id){

        return  servicioPosts.postPorUsuario(id);

   }

   public String crearPost(String contenido){
       if (servicioPosts.crearPost(sesion.usuarioActual().getId(),contenido)) {
           return "Post creado";
       }
       return "Ocurrio un problema al procesar el post";
   }


   public String mostrarInfoUsuario(long id){
        return  servicioUsuarios.procesarUsuario(servicioUsuarios.usuarioPorId(id));
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


    public boolean publicacionLikeada(Post posts) {

        return posts.publicacionLikeada(sesion.usuarioActual().getId());


    }
}
