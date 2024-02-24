/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.laboratorio.controlador;

import com.laboratorio.modelo.Arbol;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 *
 * @author ktajt
 */
public class ArbolControlador {

    Arbol arbol = new Arbol();

    public ArbolControlador() {
    }

    public boolean insertar(Integer dato) {
        return (this.arbol.agregar(dato));
    }

    //metodo para mostrar los recorridos del arbol
    public String preOrden() {
        LinkedList it = this.arbol.preOrden();
        return (recorrido(it, "Recorrido PreOrden"));
    }

    public String inOrden() {
        LinkedList it = this.arbol.inOrden();
        return (recorrido(it, "Recorrido InOrden"));
    }

    public String postOrden() {
        LinkedList it = this.arbol.postOrden();
        return (recorrido(it, "Recorrido PosOrden"));
    }

    //metodo para poder mostrar los tipos d recorrido
    private String recorrido(LinkedList it, String msg) {
        int i = 0;
        String r = msg + "\n";
        while (i < it.size()) {
            r += "\t" + it.get(i).toString() + "";
            i++;
        }
        return (r);
    }

    //Metodo para buscar dato en el nodo
    public String buscar(Integer dato) {
        boolean siEsta = this.arbol.existe(dato);
        String r = "El dato:" + dato.toString() + "\n";
        r += siEsta ? "Si se encuentra en el arbol" : "No se encuentra en el arbol";
        return (r);
    }

    public void eliminar(int a) {
        try {
            this.arbol.eliminar(a);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public JPanel getDibujo() {
        return this.arbol.getDibujo();
    }
}
