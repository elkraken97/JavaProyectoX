package org.example.Repositorios;

import org.example.Modelos.Post;
import org.example.Repositorios.InterfacesDeRepo.RepositorioPostsInterface;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class RepostiorioPostsSQL implements RepositorioPostsInterface {

    private final Connection connection;

    public RepostiorioPostsSQL(Connection connection) throws SQLException {
        String usuario = "postgres";
        String conn = "jdbc:postgresql://localhost:5432/Aplicacion46";
        String passwd = "pwd";
        this.connection = DriverManager.getConnection(conn, usuario, passwd);
    }


    @Override
    public Post guardarPost(Post post) throws SQLException {
        String query = "Insert into users (nombre) values (?)";
        PreparedStatement preparedStatement = connection.prepareCall(query);

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
