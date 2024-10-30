package org.example;

public interface TreeInterface {
    public void loadTree();
    void insert(Animal data);
    void inorder();
    void preorder();
    void posorder();
    void cleanTree();
    void printTreeFormat();
    boolean isEmpty();
    void play();
    void playRecursive(NodeTree root, int nivel);

    Contenedor<Animal> convertTreeIntoList();
}
