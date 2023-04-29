/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listas;

import handlers.EstructuraDeDatos;

/**
 *
 * @author queza
 */
public class ListaSimple extends EstructuraDeDatos {

    private NodoSimple inicio;

    public ListaSimple() {
        this.inicio = null;
    }

    @Override
    public void add(Object e) {
        NodoSimple nuevoNodo = new NodoSimple(e);

        if (inicio == null) {
            inicio = nuevoNodo;
        } else {
            NodoSimple temporal = inicio;
            while (temporal.getSiguiente() != null) {
                temporal = temporal.getSiguiente();
            }
            temporal.setSiguiente(nuevoNodo);
        }
    }

    @Override
    public int getSize() {
        NodoSimple temporal = inicio;
        int i = 0;
        while (temporal != null) {
            i++;
            temporal = temporal.getSiguiente();
        }
        return i;
    }

    @Override
    public Object get(int i) {
        NodoSimple nodo = inicio;
        for (int j = 0; j < i; j++) {
            nodo = nodo.getSiguiente();
        }
        return nodo.getValor();
    }

    @Override
    public Object find(Object e) {
        return null;
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
        return null;
    }

    @Override
    public void delete(Object e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void mostrarTodo() {
        NodoSimple temporal = inicio;
        int i = 0;

        while (temporal != null) {
            System.out.println(i++ + ") " + temporal.getValor());
            temporal = temporal.getSiguiente();
        }
    }

}
