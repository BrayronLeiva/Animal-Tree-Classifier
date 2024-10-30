package org.example;

import java.util.Map;

public interface TreeInterface {
    public void loadTree();
    void inorder();
    void preorder();
    void posorder();
    void cleanTree();
    void printTreeFormat();
    boolean isEmpty();
    void play();
    void playRecursive(NodeTree root, int nivel);

    Contenedor<Animal> convertTreeIntoList();

    Map<String, Contenedor<String>> convertTreeIntoHashMap();
}
