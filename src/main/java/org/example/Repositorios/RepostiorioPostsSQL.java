package org.example.Repositorios;

import org.example.Excepciones.ErrorAlGuardarDato;
import org.example.Modelos.Post;
import org.example.Repositorios.InterfacesDeRepo.RepositorioPostsInterface;

import javax.sql.DataSource;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class RepostiorioPostsSQL implements RepositorioPostsInterface {

    private final DataSource dataSource;

    public RepostiorioPostsSQL(DataSource ds){
     this.dataSource=ds;
    }


    @Override
    public Post guardarPost(Post post) throws SQLException {
     try(Connection connection = dataSource.getConnection()){
         String query = "Insert into users (nombre) values (?)";
         PreparedStatement preparedStatement = connection.prepareCall(query);
         preparedStatement.setString(1,post.getContenido());
        int rows = preparedStatement.executeUpdate();

            if (rows == 1) {
                return post;
            }
     } catch (ErrorAlGuardarDato e) {
         throw new ErrorAlGuardarDato("Error al guardar el post en la base de datos: " + e.getMessage());
     }
        return null;
    }

    @Override
    public Optional<Post> buscarPostsPorID(long id) {
        return Optional.empty();
    }

    @Override
    public List<Post> buscarPostsPorUsuario(long usrId) {
        return List.of();
    }

    @Override
    public List<Post> todosLosPosts() {
        return List.of();
    }

    @Override
    public boolean borrarPosts(long id) {
        return false;
    }
}
