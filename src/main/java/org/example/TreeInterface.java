package org.example;

public interface TreeInterface {
    public void loadTree();
    void insert(Animal data);
    void inorder();
    void preorder();
    void posorder();
    void poda();
    void cleanTree();
    boolean isEmpty();
    void play();
    void playRecursive(Node root);
}
