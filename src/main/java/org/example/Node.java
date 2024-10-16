package org.example;

public class Node {

    private Integer data;
    private Node left;
    private Node rigt;

    public Node(Integer dato, Node left, Node right){
        this.data = dato;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
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

    public boolean hasChildren(){
        return left != null || rigt != null;
    }
}
