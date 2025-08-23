package org.example.Repositorios.InterfacesDeRepo;

import org.example.Modelos.User;

import java.util.List;
import java.util.Optional;

public interface RepositorioUsuarioInterface {
     User guardarUsuario(User usr);
     Optional<User> buscarUsuarioPorID(long id);
     Optional<User> buscarUsuarioPorNombre(String nombre);
     List<User> todosLosUsuarios();
     boolean borrarPorId(long id);
}