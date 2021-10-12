
package com.mycompany.u1e1prolog;

import java.util.Scanner;

/**
 *
 * @author chuch
 */
public class Main {

    public static void main(String[] args) {
        
        Operaciones ope=new Operaciones();
        Scanner leer= new Scanner(System.in);
        int a;
        System.out.println("Bienvenido");
        //Menu
        while(true){
            System.out.println("\nEliga una opción \n1) Infija a postfija \n2) Operación lógica \n3) Separar cadena \n0) Salir");
            a=leer.nextInt();
            switch (a){
                case 0:
                    System.out.println("Hasta luego");
                    System.exit(0);
                    break;
                case 1:
                    ope.opc1();
                    break;
                case 2:
                    ope.opc2();
                    break;
                case 3:
                    ope.opc3();
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        }
        
    }
    
}
