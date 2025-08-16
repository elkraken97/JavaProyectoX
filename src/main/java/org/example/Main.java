package org.example;

import org.example.Control.ControlConsolaInteractiva;
import org.example.Control.ControlLogicaEmpaquetado;
import org.example.InputsManage.InputDeDatos;
import org.example.InputsManage.InputDeDatosConsola;
import org.example.Repositorios.InterfacesDeRepo.RepositorioPostsInterface;
import org.example.Repositorios.InterfacesDeRepo.RepositorioUsuarioInterface;
import org.example.Repositorios.RepositorioPostsEnMemoria;
import org.example.Repositorios.RepositorioUsuariosEnMemoria;
import org.example.Servicios.PostServicio;
import org.example.Servicios.UsuarioServicio;
import org.example.Sesiones.Sesion;

public class Main {
    public static void main(String[] args) {
        RepositorioUsuarioInterface repo = new RepositorioUsuariosEnMemoria();
        RepositorioPostsInterface repoPost = new RepositorioPostsEnMemoria();

        PostServicio postServicio = new PostServicio(repoPost);
        UsuarioServicio usuarioServicio = new UsuarioServicio(repo);

        Sesion sesion = new Sesion();

        InputDeDatos input = new InputDeDatosConsola();
    ControlLogicaEmpaquetado controlLogicaEmpaquetado = new ControlLogicaEmpaquetado(postServicio,usuarioServicio,sesion);
        ControlConsolaInteractiva controlConsolaInteractiva = new ControlConsolaInteractiva(controlLogicaEmpaquetado,input);

        controlConsolaInteractiva.menuPrincipal();


    }
}