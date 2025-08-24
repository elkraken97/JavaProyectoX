package org.example.Repositorios.RepostioriosSQl;

import org.example.Excepciones.DemasiadosCaracteres;
import org.example.Excepciones.ErrorAlGuardarDato;
import org.example.Modelos.Post;
import org.example.Repositorios.InterfacesDeRepo.RepositorioPostsInterface;

import javax.sql.DataSource;

import java.sql.*;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

public class RepostiorioPostsSQL implements RepositorioPostsInterface {

    private final DataSource dataSource;

    public RepostiorioPostsSQL(DataSource ds){
     this.dataSource=ds;
    }


    @Override
    public Post guardarPost(Post post){
     try(Connection connection = dataSource.getConnection()){
         String query = "insert into posts( usr_id, contenido, creacion) values (?,?,?);";
         PreparedStatement preparedStatement = connection.prepareCall(query);
         preparedStatement.setLong(1, post.getUsrId());
         preparedStatement.setString(2,post.getContenido());
         preparedStatement.setDate(3, Date.valueOf(post.getCreacion().toLocalDate()));
        int rows = preparedStatement.executeUpdate();

            if (rows == 1) {
                return post;
            }
     }catch (SQLException e) {
        throw new ErrorAlGuardarDato("Error al guardar post"+e.getSQLState());
    }
        return null;
    }

    @Override
    public Optional<Post> buscarPostsPorID(long id) throws DemasiadosCaracteres {
        try(Connection connection = dataSource.getConnection()) {
            String query = "Select * from posts where id = (?)";
            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            long usrId = resultSet.getLong("usr_id");
            String contenido = resultSet.getString("contenido");
            Post p = new Post(usrId,contenido);
            return Optional.of(p);

        } catch (SQLException e) {
            throw new ErrorAlGuardarDato("Error al buscar post"+e.getSQLState());
        } catch (DemasiadosCaracteres e) {
            throw new DemasiadosCaracteres("demasiados caracteres al buscar post"+e.getMessage());
        }

    }

    @Override
    public List<Post> buscarPostsPorUsuario(long usrId) {
        try(Connection connection = dataSource.getConnection()) {
            String query = "Select * from posts where usr_id = (?)";
            PreparedStatement preparedStatement = connection.prepareCall(query);
            preparedStatement.setLong(1,usrId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Post> posts = new ArrayList<>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String contenido = resultSet.getString("contenido");
                Post p = new Post(usrId,contenido);
                p.setId(id);
                posts.add(p);
            }
            return posts;

        }catch (SQLException e) {
            throw new ErrorAlGuardarDato("Error al buscar post"+e.getSQLState());
        } catch (DemasiadosCaracteres e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> todosLosPosts() {
        try(Connection connection = dataSource.getConnection()) {
            String query = "Select * from posts";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<Post> posts = new ArrayList<>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String contenido = resultSet.getString("contenido");
                Post p = new Post(resultSet.getLong("usr_id"),contenido);
                p.setId(id);
                posts.add(p);
            }
            return posts;

        } catch (SQLException | DemasiadosCaracteres e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean borrarPosts(long id) {

        try(Connection connection = dataSource.getConnection()) {
            String query = "delete from posts where id = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, id);
            int rows = preparedStatement.executeUpdate();
            return rows == 1;



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
}
