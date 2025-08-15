package org.example.Control;

import org.example.InputsManage.InputDeDatos;
import org.example.Sesiones.Sesion;

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
                    1.-Iniciar Sesion (interaccion / posts unicos)
                    2.-Ver Posts (General)
                    3.-Ver Usuarios (General)
                    4.-Salir
                    """);
            int op = input.recibirInteger("Ingresa tu opcion:");
            switch (op){
                case 1->{

                    iniciarSesion();


                }
                case 4->{
                    System.out.println("Hasta luego...");
                    salir = true;
                }
                default -> {
                    System.out.println("Opcion No disponible");
                }
            }



        }


    }

    private void iniciarSesion() {
        control.mostrarTodosLosUsuarios().forEach(System.out::print);
        int id = input.recibirInteger("Ingresa la id del usuario con el que quieres iniciar sesion");
        System.out.println(control.iniciarSesion(id));
    }

    private void verPostGeneral(){
        for (String s : control.mostrarTodosLosPostsSimple()) {
            System.out.println(s);
            System.out.println("----Enter para continuar----");
           input.pausaENInput();
        }
    }



}
