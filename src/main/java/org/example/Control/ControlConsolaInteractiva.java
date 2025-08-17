package org.example.Control;

import org.example.InputsManage.InputDeDatos;
import org.example.Modelos.Post;

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
                try{
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
                catch (Exception e){
                    System.out.println("Error:"+e.getMessage()+e.getLocalizedMessage());
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

        private void menuSesion() {

            boolean logout = true;
            String nombreDeLaSeson = control.nombreDeLaSesion();
            while (logout) {
                try{

                    System.out.println("Bienvenido " + nombreDeLaSeson);

                System.out.println("""
                       1.- Ver Feed General
                       2.- Ver Perfil de un usuario
                       3.- Salir sesion
                       4.- Crear Post
                       """);
                int op = input.recibirInteger("Ingresa la opcion deseada:");
                switch (op) {

                    case 1 -> {
                        verFeedGeneral();


                    }
                    case 2 -> {

                        verPerfilDeUsuario();

                    }
                    case 3 -> {


                        if (control.logoutSesion()) {
                            logout = false;
                            System.out.println("Has salido de la sesion del perfil de " + nombreDeLaSeson);

                        } else {
                            System.out.println("Ha ocurrido un problema al salir de la sesion");
                        }
                    }
                    case 4 -> {

                        crearPosts();

                    }
                    default -> {
                        System.out.println("Opcion no disponible");
                    }

                }

                }catch (Exception e){
                    System.out.println("Error:"+e.getMessage()+e.getLocalizedMessage());
                }
            }
        }



    private void crearPosts() {

        String contenido = input.recibirString("Ingresa el contenido de tu post (no puede superar los 200 caracteres)");
        System.out.println(control.crearPost(contenido));

    }

    private void verPerfilDeUsuario() {
        int idUsr = input.recibirInteger("Ingrese la id del usuario que quiere ver");
        System.out.println(control.mostrarInfoUsuario(idUsr));
        List<Post> posts = control.mostrarPostsDeUnUsuario(idUsr);
        for (Post post : posts) {
            System.out.println(control.procesarPost(post));

            if (procesarOpcionesDeFeed(post)) break;

        }

    }


    private void verFeedGeneral() {

        List<Post> feed = control.feedGeneral();
        if (feed.isEmpty()) {
            System.out.println("No hay posts que mostrar");
            return;
        }

        for (Post s : feed) {
            System.out.println(control.procesarPost(s));
            if (procesarOpcionesDeFeed(s)) break;
        }

    }


    private boolean procesarOpcionesDeFeed(Post posts){
        boolean perfilYaSeguido = control.usuarioYaSeguido(posts.getUsrId());
        boolean postsYaLikeado = control.publicacionLikeada(posts);
        String emp = "----Enter/Seguir----"+(!postsYaLikeado?"L/DarLike---":"L/QuitarLike")+(!perfilYaSeguido?"----S/SeguirPerfil----":"----S/DejarDeSeguir----");
        String op = input.recibirString(emp).toLowerCase();
        switch (op) {
            //ver como dar like si no se en que posts estoy
            case "l"->{

                if (postsYaLikeado){
                    System.out.println(control.quitarLike(posts));
                    return false;

                }
                System.out.println(control.darLike(posts));

                return false;
            }
            case "s"->{
                if (perfilYaSeguido){
                    System.out.println(control.dejarDeSeguir(posts.getUsrId()));
                    return false;
                }
                System.out.println(control.seguirAUsuario(posts.getUsrId()));
                return false;

            }
            case "q"->{
                return true;
            }

            default -> {
                return false;
            }



        }


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

