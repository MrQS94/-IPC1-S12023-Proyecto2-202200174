/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listas;

/**
 *
 * @author queza
 */
public class NodoDoble {

    private Object valor;
    private NodoDoble siguiente;
    private NodoDoble anterior;

    public NodoDoble(Object valor) {
        this.valor = valor;
        this.siguiente = null;
        this.anterior = null;
    }

    public NodoDoble getSiguiente() {
        return siguiente;
    }

    public NodoDoble getAterior() {
        return anterior;
    }

    public Object getValor() {
        return valor;
    }

    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }

    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

}
