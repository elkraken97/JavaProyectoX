package org.example.Servicios;

import org.example.Excepciones.PostNoEncontrado;
import org.example.Modelos.Post;
import org.example.Repositorios.InterfacesDeRepo.RepositorioPostsInterface;

import java.util.Collections;
import java.util.List;

public class PostServicio {
    private final RepositorioPostsInterface repoPost;

    public PostServicio(RepositorioPostsInterface repoo){
       this.repoPost=repoo;
    }

    public void quitarLike(long idUsuario,long idPost) {

        repoPost.buscarPostsPorID(idPost).ifPresent(p->{
            if (p.unlike(idUsuario)) {
                System.out.println("Like quitado");
            }else{
                System.out.println("No habias dado like a esta publicacon");
            }
        });

    }



    public void darLike(long idUsuario,long idPost){

        repoPost.buscarPostsPorID(idPost).ifPresent(p -> {
            if (p.likear(idUsuario)) {
                System.out.println("publicacion likeada");
            }else{
                System.out.println("Ya tienes un like en esta publicacion");
            }
        });


    }



    public void crearPost(long usrId, String contenido){

        try {
            Post nuevo =  new Post(usrId,contenido);
            Post p = repoPost.guardarPost(nuevo);
            if (p.getId()!=-1){
                System.out.println("Post guardado Exitosamente");
            }else{
                System.out.println("Algo ocurrio "+p.getId());
            }

        }catch (Exception e){
            System.out.println("Ocurrio un error al guardar el post:\n"+ e.getMessage());
        }


    }


    public List<Post> todosLosPost(){
        List<Post> all = repoPost.todosLosPosts();

        if (all.isEmpty()) {
            System.out.println("No hay posts creados");
            return Collections.emptyList();
        }

        return all;
    }

    public List<Post> postPorUsuario(int idUsr){

        List<Post> usr = repoPost.buscarPostsPorUsuario(idUsr);
        if (usr.isEmpty()) {
            System.out.println("Este usuario no tiene post creados");
            return Collections.emptyList();
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
                   "Publicado el " + postActual.getCreacion() + "\n" +
                   "Likes:" + postActual.getLikes();
    }


    public void eliminarPost(long postId){
        if (repoPost.borrarPosts(postId)) {
            System.out.println("Post borrado exitosamnete");
        }else{
            System.out.println("la id de ese post es invalida");
        }
    }



}
