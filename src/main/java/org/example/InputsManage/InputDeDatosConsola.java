package org.example.InputsManage;

import java.util.Scanner;

public class InputDeDatosConsola implements InputDeDatos{

    private final Scanner sc;

    public InputDeDatosConsola() {
        this.sc = new Scanner(System.in);
    }

    @Override
    public String recibirString(String msj) {

        while (true){
            try{
                System.out.println(msj);
                return sc.nextLine().trim();
            }catch (Exception e){
                System.out.println("Has ingresado erroneamente el dato "+e.getMessage());
            }
        }


    }

    @Override
    public long recibirLong(String msj) {
        while (true){
            try{
                System.out.println(msj);
                return sc.nextLong();
            }catch (Exception e){
                System.out.println("Has ingresado erroneamente el long");
            }
        }
    }

    @Override
    public int recibirInteger(String msj) {
        while (true){
            try{
                System.out.println(msj);
                return Integer.parseInt(sc.nextLine().trim());
            }catch (Exception e){
                System.out.println("Has ingresado erroneamente el long");
            }
        }
    }

    @Override
    public void pausaENInput() {
        sc.nextLine();
    }


}
