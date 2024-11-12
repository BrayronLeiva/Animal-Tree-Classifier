package org.example.Data;

import org.example.Logic.DataStructures.Tree;
import org.example.Logic.DataStructures.TreeInterface;

public class Datos {
    public Datos() {
    }
    public boolean cargarDatos(TreeInterface tree){
        return tree.loadTree("src/main/resources/tree.json");
    }
    public void guardarDatos(TreeInterface tree){
        tree.guardarArbol("src/main/resources/tree.json");
    }
}
