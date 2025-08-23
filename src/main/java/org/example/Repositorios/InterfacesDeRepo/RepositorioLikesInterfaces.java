package org.example.Repositorios.InterfacesDeRepo;

import java.util.List;

public interface RepositorioLikesInterfaces {
    boolean darLike(long postId, long userId);
    boolean quitarLike(long postId, long userId);
    int contarLikes(long postId);

    boolean usuarioDioLike(long postId, long userId);
    boolean postCreado(long userId);
}
