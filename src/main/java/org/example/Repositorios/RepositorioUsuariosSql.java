package org.example.Repositorios;


import org.example.Modelos.User;

import org.example.Repositorios.InterfacesDeRepo.RepositorioUsuarioInterface;

import javax.sql.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RepositorioUsuariosSql implements RepositorioUsuarioInterface {

    private final DataSource ds;

    public RepositorioUsuariosSql(DataSource ds) {

        this.ds = ds;
    }


    @Override
    public User guardarUsuario(User usr) {
        String sql = "insert into users(nombre) values (?)";
        try(Connection cn = ds.getConnection()){
            PreparedStatement preparedStatement =  cn.prepareStatement(sql);
        preparedStatement.setString(1, usr.getNombre());
        preparedStatement.execute();
        return usr;


        }
        catch (SQLException e){
            System.out.println("Error al insertar usuario en la conexion a db"+e.getMessage());
        }
        return usr;
    }

    @Override
    public Optional<User> buscarUsuarioPorID(long id) {
        String sql = "select * from users where id = (?)";

        try(Connection cn = ds.getConnection()){
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {


                if (resultSet.next()) {
                    User user = new User(resultSet.getString("nombre"));
                    user.setId(resultSet.getLong("id"));
                    return Optional.of(user);
                }


            }
        }catch (Exception e){
            System.out.println("Error al buscar usuario en la conexion a db"+e.getMessage());
        }


        return Optional.empty();
    }

    @Override
    public Optional<User> buscarUsuarioPorNombre(String nombre) {
        String sql = "select * from users where nombre = (?)  LIMIT 1";

        try(Connection cn = ds.getConnection()){
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
            preparedStatement.setString(1, nombre);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {


                if (resultSet.next()) {
                    User user = new User(resultSet.getString("nombre"));
                    user.setId(resultSet.getLong("id"));
                    return Optional.of(user);
                }


            }
        }catch (Exception e){
            System.out.println("Error al buscar usuario en la conexion a db"+e.getMessage());
        }


        return Optional.empty();
    }

    @Override
    public List<User> todosLosUsuarios() {
        String sql = "select * from users";
        try(Connection cn = ds.getConnection()){
            Statement statement = cn.createStatement();
           ResultSet resultSet =  statement.executeQuery(sql);
           List<User> users = new ArrayList<>();
            while(resultSet.next()){
                User user = new User(resultSet.getString("nombre"));
                user.setId(resultSet.getLong("id"));
                users.add(user);

            }
            return users;

        } catch (SQLException e) {
            System.out.println("Error al buscar usuarios en la conexion a db"+e.getMessage());
        }


        return Collections.emptyList();
    }

    @Override
    public boolean borrarPorId(long id) {

        String sql = "delete from users where id = (?)";
        try(Connection cn = ds.getConnection()){
            PreparedStatement preparedStatement = cn.prepareStatement(sql);
                preparedStatement.setLong(1, id);
                int rows =  preparedStatement.executeUpdate();
                return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error al borrar usuario en la conexion a db"+e.getMessage());
        }
            return false;


    }
}
