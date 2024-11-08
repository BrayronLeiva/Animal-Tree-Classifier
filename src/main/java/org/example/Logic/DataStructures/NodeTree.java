package org.example.Logic.DataStructures;

import org.example.Logic.Animal;

import java.io.Serializable;

public class NodeTree implements Serializable {

    private Animal data;
    private NodeTree left;
    private NodeTree right;
    private Boolean caracteristicaAdded;

    private static final long serialVersionUID = 1L;

    public NodeTree(Animal dato, NodeTree left, NodeTree right, Boolean caracteristicaAdded){

        this.data = dato;
        this.left = left;
        this.right = right;
        this.caracteristicaAdded = caracteristicaAdded;

    }

    public NodeTree(Animal dato, NodeTree left, NodeTree right){
        this.data = dato;
        this.left = left;
        this.right = right;
    }

    public Animal getData() {
        return data;
    }

    public void setData(Animal data) {
        this.data = data;
    }

    public NodeTree getLeft() {
        return left;
    }

    public void setLeft(NodeTree left) {
        this.left = left;
    }

    public NodeTree getRight() {
        return right;
    }

    public void setRight(NodeTree right) {
        this.right = right;
    }

    public boolean hasChildren(){
        return left != null || right != null;
    }

    public Boolean getCaracteristicaAdded() {return caracteristicaAdded;}

    public void setCaracteristicaAdded(Boolean caracteristicaAdded) {this.caracteristicaAdded = caracteristicaAdded;}
}
