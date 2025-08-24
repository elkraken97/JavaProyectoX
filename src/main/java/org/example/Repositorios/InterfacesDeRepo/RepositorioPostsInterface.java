package org.example.Repositorios.InterfacesDeRepo;

import org.example.Excepciones.DemasiadosCaracteres;
import org.example.Modelos.Post;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

 public interface RepositorioPostsInterface {

     Post guardarPost(Post post);

     Optional<Post> buscarPostsPorID(long id) throws DemasiadosCaracteres;

     List<Post> buscarPostsPorUsuario(long usrId);

     List<Post> todosLosPosts();

     boolean borrarPosts(long id);


}
