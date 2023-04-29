/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listas;

import handlers.EstructuraDeDatos;
import modelo.ModeloUsuario;

/**
 *
 * @author queza
 */
public class ListaDoble extends EstructuraDeDatos {

    private NodoDoble inicio;
    private NodoDoble fin;

    public ListaDoble() {
        this.inicio = null;
        this.fin = null;
    }

    @Override
    public void add(Object e) {
        NodoDoble nuevoNodo = new NodoDoble(e);
        if (this.inicio == null) {
            this.inicio = nuevoNodo;
        } else {
            NodoDoble temporal = this.inicio;
            while (temporal.getSiguiente() != null) {
                temporal = temporal.getSiguiente();
            }
            temporal.setSiguiente(nuevoNodo);
            nuevoNodo.setAnterior(temporal);
        }
    }

    @Override
    public int getSize() {
        int tamano = 0;
        NodoDoble actual = inicio;
        while (actual != null) {
            tamano++;
            actual = actual.getSiguiente();
        }
        return tamano;
    }

    @Override
    public Object get(int i) {
        NodoDoble nodo = inicio;
        for (int j = 0; j < i; j++) {
            nodo = nodo.getSiguiente();
        }
        return nodo.getValor();
    }

    @Override
    public Object find(Object e) {
        String[] files = null;
        NodoDoble temporal = inicio;
        int contador = 0;
        ModeloUsuario mod = null;
        while (temporal != null) {
            contador++;
            temporal = temporal.getSiguiente();
        }

        String[] userCat = (String[]) e;
        String user = userCat[0];
        String cat = userCat[1];

        temporal = inicio;
        files = new String[contador];

        contador = 0;
        for (int j = 0; j < files.length; j++) {
            mod = (ModeloUsuario) temporal.getValor();
            if (mod.getUser().equals(user) && mod.getCategorias().equals(cat)) {
                contador++;
                files[j] = mod.getFile();
            } else if (mod.getUser().equals(user) && cat == null) {
                contador++;
                files[j] = mod.getCategorias();
            }
            temporal = temporal.getSiguiente();
        }

        return files;
    }

    @Override
    public void delete(Object e) {
        String[] array = (String[]) e;
        String user, urlCat;
        user = array[0];
        urlCat = array[1];

        NodoDoble current = inicio;
        ModeloUsuario mod = null;
        while (current != null) {
            mod = (ModeloUsuario) current.getValor();
            if (mod.getUser().equals(user) && (mod.getFile().equals(urlCat) || mod.getCategorias().equals(urlCat))) {
                if (current == inicio && current == fin) {
                    inicio = fin = null;
                } else if (current == inicio) {
                    inicio = current.getSiguiente();
                    if (inicio != null) {
                        inicio.setAnterior(null);
                    }
                } else if (current == fin) {
                    fin = current.getAterior();
                    fin.setAnterior(null);
                } else {
                    current.getAterior().setSiguiente(current.getSiguiente());
                    if (current.getSiguiente() != null) {
                        current.getSiguiente().setAnterior(current.getAterior()); // Aca
                    }
                }
                return;
            }
            current = current.getSiguiente();
        }

    }

    public void mostrarTodo() {
        NodoDoble temporal = this.inicio;
        int i = 0;
        while (temporal != null) {
            i++;
            ModeloUsuario mod = (ModeloUsuario) temporal.getValor();
            System.out.println("----------------------------------------");
            System.out.println(i + ") El user: " + mod.getUser());
            System.out.println(i + ") El file: " + mod.getFile());
            System.out.println(i + ") La categoria: " + mod.getCategorias());
            temporal = temporal.getSiguiente();
        }
    }

    @Override
    public Object peek() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object getNext() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object pop() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
