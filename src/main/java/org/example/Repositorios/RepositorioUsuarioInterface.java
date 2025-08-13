package org.example.Repositorios;

import org.example.Modelos.User;

import java.util.List;
import java.util.Optional;

public interface RepositorioUsuarioInterface {


    public User guardarUsuario(User usr);

    public Optional<User> buscarUsuarioPorID(long id);

    public Optional<User> buscarUsuarioPorNombre(String nombre);

    public List<User> todosLosUsuarios();

    public boolean borrarPorId(long id);

}