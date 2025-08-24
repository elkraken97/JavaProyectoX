package org.example.Repositorios.RepostioriosSQl;

import org.example.Repositorios.InterfacesDeRepo.RepositorioLikesInterfaces;

public class RepositiorioLikesSQl implements RepositorioLikesInterfaces {


    @Override
    public boolean darLike(long postId, long userId) {





        return false;
    }

    @Override
    public boolean quitarLike(long postId, long userId) {
        return false;
    }

    @Override
    public int contarLikes(long postId) {
        return 0;
    }

    @Override
    public boolean usuarioDioLike(long postId, long userId) {
        return false;
    }

    @Override
    public boolean postCreado(long userId) {
        return false;
    }
}
