package org.example.Servicios;

import org.example.Excepciones.ErrorAlCrearLikesDeUsuario;
import org.example.Repositorios.InterfacesDeRepo.RepositorioLikesInterfaces;
import org.example.Repositorios.RepositorioLikesEnMemoria;

public class LikesServicio {


    private final RepositorioLikesInterfaces repositorioLikesInterfaces;


    public LikesServicio(RepositorioLikesInterfaces repositorioLikesInterfaces) {
        this.repositorioLikesInterfaces = repositorioLikesInterfaces;
    }

    public boolean darLike(long postId, long userId) {
        return repositorioLikesInterfaces.darLike(postId, userId);
    }
    public boolean quitarLike(long postId, long userId) {
        return repositorioLikesInterfaces.quitarLike(postId, userId);
    }

    public int contarLikes(long postId) {
        return repositorioLikesInterfaces.contarLikes(postId);
    }
    public boolean usuarioDioLike(long postId, long userId) {
        return repositorioLikesInterfaces.usuarioDioLike(postId, userId);
    }

    public boolean crearPost(long userId) {
        if(repositorioLikesInterfaces.postCreado(userId)){
            return  true;
        }else{
            throw new ErrorAlCrearLikesDeUsuario("Error al momento de crearLosServicosLikes del usuario");
        }

    }



}
