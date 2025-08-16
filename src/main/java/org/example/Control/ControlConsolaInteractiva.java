package org.example.Control;

import org.example.InputsManage.InputDeDatos;
import org.example.Modelos.User;
import org.example.Sesiones.Sesion;

import java.util.ArrayList;
import java.util.List;

public class ControlConsolaInteractiva {

    private final ControlLogicaEmpaquetado control;
    private final InputDeDatos input;


    public ControlConsolaInteractiva(ControlLogicaEmpaquetado control, InputDeDatos input) {
        this.control = control;
        this.input = input;
    }


    public void menuPrincipal(){
        boolean salir = false;
        while (!salir) {

            System.out.println("""
                    Bienvenido a la red social K97
                    ¿Que deseas hacer?
                    1.-Iniciar Sesion (interaccion / posts unicos / creacion de posts)
                    2.-Ver Posts (General)
                    3.-Ver Usuarios (General)
                    4.-Crear Usuarios
                    5.-Salir
                    """);
            int op = input.recibirInteger("Ingresa tu opcion:");
            switch (op){
                case 1->{
                    iniciarSesion();
                    if (control.haySesionActiva()){
                        menuSesion();
                    }
                }
                case 2->{
                    procesarListaSimple(control.mostrarTodosLosPostsSimple());
                }
                case 3->{
                    procesarListaSimple(control.mostrarTodosLosUsuarios());
                }
                case 4->{
                    crearUsuarios();
                }
                case 5->{
                    System.out.println("Hasta luego...");
                    salir = true;
                }
                default -> {
                    System.out.println("Opcion No disponible");
                }
            }

        }

    }

    private void menuSesion() {
        boolean logout = true;
        while (logout){
            System.out.println("Bienvenido "+control.nombreDeLaSesion());
            System.out.println("""
                    1.-Ver Feed General
                    2.-Ver Perfil de un usuario
                    3.- Salir sesion
                    """);
                int op = input.recibirInteger("Ingresa la opcion deseada:");
            switch (op) {

                case 1->{
                    verFeedGeneral();


                }


                 case 2->{

                     verPerfilDeUsuario();

                 }


                case 3 -> {
                    String n = control.nombreDeLaSesion();
                    if (control.logoutSesion()){
                        logout = false;
                        System.out.println("Has salido de la sesion del perfil de "+n);
                    }else{
                        System.out.println("Ha ocurrido un problema al salir de la sesion");
                    }
                }
                default -> {
                    System.out.println("Opcion no disponible");
                }

            }


        }


    }

    private void verFeedGeneral() {

        List<String> feed = control.feedGeneral();
        if (feed.isEmpty()) {
            System.out.println("No hay posts que mostrar");
            return;
        }

        for (String s : feed) {
            if (procesarOpcionesDeFeed(s)) break;
        }

    }
    
    
    private boolean procesarOpcionesDeFeed(String posts){


        System.out.println(posts);


        String op = input.recibirString("-----Enter/Seguir-L/DarLike-S/SeguiralPerfil-Q/Salir").toLowerCase();
        switch (op) {
        //ver como dar like si no se en que posts estoy
            case "L"->{

            }




            default -> {
                return false;
            }



        }

    return true;

        
        
    }
    
    
    

    private void verPerfilDeUsuario() {
        int idUsr = input.recibirInteger("Ingrese la id del usuario que quiere ver");

        verPfp(idUsr);


    }

    private void verPfp(int idUsr) {
    }

    private void crearUsuarios() {
        boolean salir = false;
        while (!salir){
            System.out.println("1.-Nuevo Usuario");
            System.out.println("2.- Salir");
            int op = input.recibirInteger("Ingrese la opcion:");
            switch (op) {
                case 1->{
                    String nombre = input.recibirString("Ingrese el nombre del usuario");
                    System.out.println(control.crearUsuario(nombre));
                }
                case 2->{
                    salir = true;
                }default -> {
                    System.out.println("No hay esa opcion");
                }
            }


        }

    }

    private void iniciarSesion() {
        List<String> usrs =  control.mostrarTodosLosUsuarios();
            if (usrs.isEmpty()) {
                System.out.println("No hay usuarios registrados");
                return;
            }
            usrs.forEach(System.out::println);
        int id = input.recibirInteger("Ingresa la id del usuario con el que quieres iniciar sesion");
        System.out.println(control.iniciarSesion(id));
    }
//
//    private void verPostGeneral(){
//        List<String> todos = control.mostrarTodosLosPostsSimple();
//
//        for (String todo : todos) {
//            System.out.println(todo);
//
//
//        }
//
//    }


    private void procesarListaSimple(List<String> lista){
        if (lista.isEmpty()){
            System.out.println("No hay recursos");
            return;
        }
        for (String s : lista) {
            System.out.println(s);
            System.out.println("----Enter para continuar----");
            input.pausaENInput();
        }
    }
//    private void verUsuariosGeneral(){
//
//
//
//        for (String mostrarTodosLosUsuario : control.mostrarTodosLosUsuarios()) {
//            System.out.println(mostrarTodosLosUsuario);
//            System.out.println("----Enter para continuar----");
//            input.pausaENInput();
//
//        }
//    }



}
