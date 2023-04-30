/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import listas.ListaDoble;
import listas.ListaSimple;
import vista.MenuPrincipal;

/**
 *
 * @author queza
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ListaSimple listUsuario = new ListaSimple();
        ListaDoble listDoble = new ListaDoble();
        MenuPrincipal menuP = new MenuPrincipal(listUsuario, listDoble);
        menuP.show();
    }

}
