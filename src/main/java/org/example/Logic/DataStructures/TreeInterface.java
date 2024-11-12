package org.example.Logic.DataStructures;

import org.example.Logic.Animal;

import java.util.Map;

public interface TreeInterface {
    public boolean loadTree(String path);
    void inorder();
    void preorder();
    void posorder();
    void cleanTree();
    void printTreeFormat();
    boolean isEmpty();
    void play();
    void playRecursive(NodeTree root, int nivel);

    Contenedor<Animal> convertTreeIntoList();

    Map<String, ListInterface<String>> convertTreeIntoHashMap(ListInterface<Animal> contenedorAnimales);

    public void guardarArbol(String path);
}
