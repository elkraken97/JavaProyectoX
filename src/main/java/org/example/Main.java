package org.example;

import org.example.Control.ControlConsolaInteractiva;
import org.example.Control.ControlLogicaEmpaquetado;
import org.example.InputsManage.InputDeDatos;
import org.example.InputsManage.InputDeDatosConsola;
import org.example.Repositorios.InterfacesDeRepo.RepositorioLikesInterfaces;
import org.example.Repositorios.InterfacesDeRepo.RepositorioPostsInterface;
import org.example.Repositorios.InterfacesDeRepo.RepositorioUsuarioInterface;
import org.example.Repositorios.RepoEnMemoria.RepositorioLikesEnMemoria;
import org.example.Repositorios.RepoEnMemoria.RepositorioPostsEnMemoria;
import org.example.Repositorios.RepoEnMemoria.RepositorioUsuariosEnMemoria;
import org.example.Servicios.LikesServicio;
import org.example.Servicios.PostServicio;
import org.example.Servicios.UsuarioServicio;
import org.example.Sesiones.Sesion;

public class Main {
    public static void main(String[] args) {
        RepositorioUsuarioInterface repo = new RepositorioUsuariosEnMemoria();
        RepositorioPostsInterface repoPost = new RepositorioPostsEnMemoria();
        RepositorioLikesInterfaces likesInterfaces = new RepositorioLikesEnMemoria();
        PostServicio postServicio = new PostServicio(repoPost);
        UsuarioServicio usuarioServicio = new UsuarioServicio(repo);
        LikesServicio servicioLikes = new LikesServicio(likesInterfaces);
        Sesion sesion = new Sesion();

        InputDeDatos input = new InputDeDatosConsola();
        ControlLogicaEmpaquetado controlLogicaEmpaquetado = new ControlLogicaEmpaquetado(postServicio,usuarioServicio,sesion,servicioLikes);
        ControlConsolaInteractiva controlConsolaInteractiva = new ControlConsolaInteractiva(controlLogicaEmpaquetado,input);

        controlConsolaInteractiva.menuPrincipal();


    }
}