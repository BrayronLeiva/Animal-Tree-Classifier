package org.example;

public class Node {

    private Animal data;
    private Node left;
    private Node rigt;
    private Node parent;

    public Node(Animal dato, Node left, Node right, Node parent){
        this.data = dato;
    }

    public Animal getData() {
        return data;
    }

    public void setData(Animal data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRigt() {
        return rigt;
    }

    public void setRigt(Node rigt) {
        this.rigt = rigt;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean hasChildren(){
        return left != null || rigt != null;
    }
}
