package org.example.Repositorios.InterfacesDeRepo;

import org.example.Modelos.Post;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

 public interface RepositorioPostsInterface {

     Post guardarPost(Post post) throws SQLException;

     Optional<Post> buscarPostsPorID(long id);

     List<Post> buscarPostsPorUsuario(long usrId);

     List<Post> todosLosPosts();

     boolean borrarPosts(long id);


}
