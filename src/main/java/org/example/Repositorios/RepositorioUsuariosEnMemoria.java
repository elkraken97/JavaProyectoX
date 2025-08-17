package org.example.Repositorios;

import org.example.Modelos.User;
import org.example.Repositorios.InterfacesDeRepo.RepositorioUsuarioInterface;

import java.util.*;

public class RepositorioUsuariosEnMemoria implements RepositorioUsuarioInterface {
    private static long count = 1;
    private final Map<Long,User> usuarios = new HashMap<>();


    public RepositorioUsuariosEnMemoria() {
        // Usuarios iniciales de prueba
        guardarUsuario(new User("Alice"));
        guardarUsuario(new User("Bob"));
        guardarUsuario(new User("Charlie"));
    }


    @Override
    public User guardarUsuario(User usr) {
        if(usr.getId()==-1){
            usr.setId(count++);
        }
        usuarios.put(usr.getId(),usr);
        return usr;
    }

    @Override
    public Optional<User> buscarUsuarioPorID(long id) {
        return Optional.ofNullable(usuarios.get(id));
    }

    @Override
    public Optional<User> buscarUsuarioPorNombre(String nombre) {

        return usuarios.values().stream().filter(user -> user.getNombre().equalsIgnoreCase(nombre.trim())).findFirst();
    }

    @Override
    public List<User> todosLosUsuarios() {
        return new ArrayList<>(usuarios.values());
    }

    @Override
    public boolean borrarPorId(long id) {
        return usuarios.remove(id)!=null;
    }
}
