package org.example;

public class Main {
    public static void main(String[] args) {
        TreeInterface tree = new Tree();
        tree.loadTree();
        tree.inorder();
        tree.play();
    }
}