/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.laboratorio.modelo;

import com.laboratorio.vista.ArbolVista;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 *
 * @author ktajt
 */
public class Arbol {

    private Nodo raiz;
    int alt;

    public Arbol() {
        raiz = null;
    }

    public boolean agregar(int dato) {
        Nodo nuevo = new Nodo(dato, null, null);
        insertar(nuevo, raiz);
        return true;
    }

    //Metodo para insertar un dato en el arbol...no acepta repetidos :)
    public void insertar(Nodo nuevo, Nodo pivote) {
        if (this.raiz == null) {
            raiz = nuevo;
        } else {
            if (nuevo.getDato() <= pivote.getDato()) {
                if (pivote.getIzq() == null) {
                    pivote.setIzq(nuevo);
                } else {
                    insertar(nuevo, pivote.getIzq());
                }
            } else {
                if (pivote.getDer() == null) {
                    pivote.setDer(nuevo);
                } else {
                    insertar(nuevo, pivote.getDer());
                }
            }
        }

    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    //Recorrido preorden, recibe el nodo a empezar (raiz) y una linkedlist para ir guardando el recorrido
    public LinkedList preOrden() {
        LinkedList rec = new LinkedList();
        preorden(raiz, rec);
        return rec;
    }

    public void preorden(Nodo aux, LinkedList recorrido) {
        if (aux != null) {
            recorrido.add(aux.getDato());
            preorden(aux.getIzq(), recorrido);
            preorden(aux.getDer(), recorrido);
        }
    }

    //Recorrido inorden, recibe el nodo a empezar (raiz) y una linkedlist para ir guardando el recorrido
    public LinkedList inOrden() {
        LinkedList rec = new LinkedList();
        inorden(raiz, rec);
        return rec;
    }

    public void inorden(Nodo aux, LinkedList recorrido) {
        if (aux != null) {
            inorden(aux.getIzq(), recorrido);
            recorrido.add(aux.getDato());
            inorden(aux.getDer(), recorrido);
        }
    }

    //Recorrido postorden, recibe el nodo a empezar (raiz) y una linkedlist para ir guardando el recorrido
    public LinkedList postOrden() {
        LinkedList rec = new LinkedList();
        postorden(raiz, rec);
        return rec;
    }

    public void postorden(Nodo aux, LinkedList recorrido) {
        if (aux != null) {
            postorden(aux.getIzq(), recorrido);
            postorden(aux.getDer(), recorrido);
            recorrido.add(aux.getDato());
        }
    }

    //Metodo para verificar si hay un nodo en el arbol
    public boolean existe(int dato) {
        Nodo aux = raiz;
        while (aux != null) {
            if (dato == aux.getDato()) {
                return true;
            } else if (dato > aux.getDato()) {
                aux = aux.getDer();
            } else {
                aux = aux.getIzq();
            }
        }
        return false;
    }

    private void altura(Nodo aux, int nivel) {
        if (aux != null) {
            altura(aux.getIzq(), nivel + 1);
            alt = nivel;
            altura(aux.getDer(), nivel + 1);
        }
    }

    //Devuleve la altura del arbol
    public int getAltura() {
        altura(raiz, 1);
        return alt;
    }

    public boolean eliminar(int dato) {
        if (raiz == null) {
            return false;  // Árbol vacío, no hay nada que eliminar
        } else {
            if (existe(dato)) {
                raiz = eliminarNodo(raiz, dato);
                return true;
            } else {
                return false;  // El nodo con el dato especificado no existe en el árbol
            }
        }
    }

    private Nodo eliminarNodo(Nodo actual, int dato) {
        if (actual == null) {
            return null;
        }

        if (dato < actual.getDato()) {
            actual.setIzq(eliminarNodo(actual.getIzq(), dato));
        } else if (dato > actual.getDato()) {
            actual.setDer(eliminarNodo(actual.getDer(), dato));
        } else {
            // Caso 1: Nodo con un solo hijo o sin hijos
            if (actual.getIzq() == null) {
                return actual.getDer();
            } else if (actual.getDer() == null) {
                return actual.getIzq();
            }

            // Caso 2: Nodo con dos hijos
            actual.setDato(encontrarMinimo(actual.getDer()).getDato());
            actual.setDer(eliminarNodo(actual.getDer(), actual.getDato()));
        }

        return actual;
    }

    private Nodo encontrarMinimo(Nodo nodo) {
        while (nodo.getIzq() != null) {
            nodo = nodo.getIzq();
        }
        return nodo;
    }

    public JPanel getDibujo() {
        return new ArbolVista(this);
    }
}
