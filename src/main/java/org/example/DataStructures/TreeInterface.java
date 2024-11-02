package org.example.DataStructures;

import org.example.Models.Animal;

import java.util.Map;

public interface TreeInterface {
    public boolean loadTree();
    void inorder();
    void preorder();
    void posorder();
    void cleanTree();
    void printTreeFormat();
    boolean isEmpty();
    void play();
    void playRecursive(NodeTree root, NodeTree parent, int nivel, ListInterface<String> lista);

    Contenedor<Animal> convertTreeIntoList();

    Map<String, ListInterface<String>> convertTreeIntoHashMap();

    public void guardarArbol();
}
