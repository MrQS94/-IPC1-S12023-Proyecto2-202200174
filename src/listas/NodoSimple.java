/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listas;

/**
 *
 * @author queza
 */
public class NodoSimple {

    private Object valor;
    private NodoSimple siguiente;

    public NodoSimple(Object valor) {
        this.valor = valor;
        this.siguiente = null;
    }

    public NodoSimple getSiguiente() {
        return siguiente;
    }

    public Object getValor() {
        return valor;
    }

    public void setSiguiente(NodoSimple siguiente) {
        this.siguiente = siguiente;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
}
