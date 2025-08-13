package org.example.Servicios;

import org.example.Excepciones.UsuarioNoAgregado;
import org.example.Excepciones.UsuarioNoEncontrado;
import org.example.Excepciones.UsuarioNoSeguido;
import org.example.Modelos.User;
import org.example.Repositorios.RepositorioUsuarioInterface;

import java.util.Collections;
import java.util.List;

public class UsuarioServicio {

     private final RepositorioUsuarioInterface repoUsuario;

    public UsuarioServicio(RepositorioUsuarioInterface repoUsuario) {
        this.repoUsuario = repoUsuario;
    }

    public User  agregarUsuario(String nombre){
        User usrN = new User(nombre);
       User n = repoUsuario.guardarUsuario(usrN);
        if (n.getId()!=-1){
          return  n;
        }
        else{

            throw new UsuarioNoAgregado("El usuario con la id "+n.getId()+ "No se ha podio agregar");
        }

    }

    public boolean seguirAUsuario(long usuarioActual,long idUsuarioASeguir){

        User aSeguir = repoUsuario.buscarUsuarioPorID(idUsuarioASeguir).orElseThrow(UsuarioNoEncontrado::new);

        User seguidor = repoUsuario.buscarUsuarioPorID(usuarioActual).orElseThrow(UsuarioNoEncontrado::new);

        if (aSeguir.nuevoSeguidor(seguidor.getId())&&seguidor.seguirAUsuario(aSeguir.getId())) {
            return  true;
        }else{
            throw new UsuarioNoSeguido("Ha ocurrido un problema al querer seguir al usuario");
        }


    }

    public boolean dejarDeSeguirAUsuario(long usuarioActual, long idUsuarioADejarDeSeguir){

        User actual = repoUsuario.buscarUsuarioPorID(usuarioActual).orElseThrow(UsuarioNoEncontrado::new);

        User dejarDeSeguir = repoUsuario.buscarUsuarioPorID(idUsuarioADejarDeSeguir).orElseThrow(UsuarioNoEncontrado::new);

        if (actual.dejarDeSeguir(dejarDeSeguir.getId())  && dejarDeSeguir.dejoDeseguir(actual.getId())) {
            return true;
        }
        throw new UsuarioNoSeguido("ha ocurrido un problema al dejar de seguir al usuario");
    }




    public List<User> todosLosUsuarios(){

        List<User> usr = repoUsuario.todosLosUsuarios();


        if (usr.isEmpty()) {

            return Collections.emptyList();
        }
        return usr;

    }

    public User usuarioPorId(long id){
         return repoUsuario.buscarUsuarioPorID(id).orElseThrow(UsuarioNoEncontrado::new);
    }

    public User usuarioPorNombre(String name){
        return repoUsuario.buscarUsuarioPorNombre(name).orElseThrow(UsuarioNoEncontrado::new);
    }




    public void borrarUsuario(long idUsuarioABorrar){

        if (!repoUsuario.borrarPorId(idUsuarioABorrar)) {
            throw new UsuarioNoEncontrado();
        }


    }


}
