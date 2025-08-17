package org.example.Repositorios.InterfacesDeRepo;

import org.example.Modelos.Post;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RepostiorioPostsSQL implements RepositorioPostsInterface{

    private final Connection conn;

    public RepostiorioPostsSQL() throws SQLException {
        this.conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/");
    }


    @Override
    public Post guardarPost(Post post) {
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
