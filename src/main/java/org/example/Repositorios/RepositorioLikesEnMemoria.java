package org.example.Repositorios;

import org.example.Repositorios.InterfacesDeRepo.RepositorioLikesInterfaces;

import java.util.*;

//Ahora editar los servicios para que usen este repositorio
//y cambiar la logica del ike hasta capas superiores de la logica (cambiar casi toda la logica del programa) o ojala y no sea eso

public class RepositorioLikesEnMemoria implements RepositorioLikesInterfaces {


    private final Map<Long,Set<Long>> likesPorPost;

    public RepositorioLikesEnMemoria() {
        this.likesPorPost = new HashMap<>();
    }


    public boolean postCreado(long postId){
     return likesPorPost.computeIfAbsent(postId, k -> new HashSet<>()).isEmpty();
    }

    @Override
    public boolean darLike(long postId, long userId) {

        return likesPorPost.computeIfAbsent(postId, k -> new HashSet<>()).add(userId);


    }

    @Override
    public boolean quitarLike(long postId, long userId) {

        Set<Long> likes = likesPorPost.get(postId);
        if (likes != null)
            return likes.remove(userId);

        return false;
    }

    @Override
    public int contarLikes(long postId) {
        return  likesPorPost.get(postId).size();
    }

    @Override
    public List<Long> obtenerUsuariosQueDieronLike(long postId) {
        if (likesPorPost.containsKey(postId)) {
            return likesPorPost.get(postId).stream().toList();
        }
        return Collections.emptyList();
    }

    @Override
    public boolean usuarioDioLike(long postId, long userId) {
        Set<Long> likes = likesPorPost.get(postId);
        return likes != null && likes.contains(userId);


    }
}
