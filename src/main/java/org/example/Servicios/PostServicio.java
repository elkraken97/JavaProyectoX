package org.example.Servicios;

import org.example.Excepciones.ErrorAlGuardarDato;
import org.example.Excepciones.PostNoEncontrado;
import org.example.Excepciones.SinPostsCreados;
import org.example.Excepciones.UsuarioNoEncontrado;
import org.example.Modelos.Post;
import org.example.Repositorios.InterfacesDeRepo.RepositorioPostsInterface;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PostServicio {

    private final RepositorioPostsInterface repoPost;

    public PostServicio(RepositorioPostsInterface repoo){
       this.repoPost=repoo;
    }





    public Post crearPost(long usrId, String contenido){

        try {
            return repoPost.guardarPost(new Post(usrId,contenido));
        }catch (Exception e){
            throw new ErrorAlGuardarDato("Error creando post");
        }


    }

    public List<Post> todosLosPost(){
        List<Post> all = repoPost.todosLosPosts();

        if (all.isEmpty()) {
            throw new SinPostsCreados("No hay posts creados");

        }

        return all;
    }

    public List<Post> postPorUsuario(long idUsr){

        List<Post> usr = repoPost.buscarPostsPorUsuario(idUsr);
        if (usr.isEmpty()) {
            throw new SinPostsCreados("Este usuario no tiene posts");
        }
        return usr;


    }

    public Post postsPorID(int id){
        Post p = repoPost.buscarPostsPorID(id).orElse(null);
        if (p==null) {
            throw new PostNoEncontrado("No hay post con esa id");
        }

        return p;
    }




    public String procesarPost(Post postActual){


        //       Usuario usr = repoUsr.consultarUsuario(postActual.getIdCreador()).orElse(null);
//        String nickname = "";
//        int id = -1;
//        if (usr != null){
//            nickname = usr.getNombre();
//            id = usr.getId();
//        }else{
//            nickname = "No se encontro nombre de usuario";
//        }
//        System.out.println("Id:"+id);
//        System.out.println("Creador"+nickname);
//        System.out.println(postActual.getContenido());
//        System.out.println("Publicado el:"+postActual.getFechaPublicada());
//        System.out.println("Likes:"+postActual.getLikes());
//        // "--------------//Presiona Enter para el siguiente post//---------------"
           return "Id del post:" + postActual.getId() + "\n" +
                   "Contenido del post:\n" + postActual.getContenido() + "\n" +
                   "Publicado el " + postActual.getCreacion() + "\n" ;
           //ver como agregar likes aqui--!!!
    }


    public boolean eliminarPost(long postId){
      return repoPost.borrarPosts(postId);
    }



}
