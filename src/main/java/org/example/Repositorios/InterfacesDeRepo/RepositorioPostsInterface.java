package org.example.Repositorios.InterfacesDeRepo;

import org.example.Modelos.Post;

import java.util.List;
import java.util.Optional;

public interface RepositorioPostsInterface {

    public Post guardarPost(Post post);

    public Optional<Post> buscarPostsPorID(long id);

    public List<Post> buscarPostsPorUsuario(long usrId);

    public List<Post> todosLosPosts();

    public boolean borrarPosts(long id);


}
