package org.example;

public class NodeTree {

    private Animal data;
    private NodeTree left;
    private NodeTree rigt;
    private Boolean caracteristicaAdded;
    //private NodeTree parent;

    public NodeTree(Animal dato, NodeTree left, NodeTree right, Boolean caracteristicaAdded){

        this.data = dato;
        this.left = left;
        this.rigt = right;
        this.caracteristicaAdded = caracteristicaAdded;

    }

    public NodeTree(Animal dato, NodeTree left, NodeTree right){
        this.data = dato;
        this.left = left;
        this.rigt = right;
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

    public NodeTree getRigt() {
        return rigt;
    }

    public void setRigt(NodeTree rigt) {
        this.rigt = rigt;
    }


    //public NodeTree getParent() {
        //return parent;
    //}

    //public void setParent(NodeTree parent) {
        //this.parent = parent;
    //}

    public boolean hasChildren(){
        return left != null || rigt != null;
    }

    public Boolean getCaracteristicaAdded() {return caracteristicaAdded;}

    public void setCaracteristicaAdded(Boolean caracteristicaAdded) {this.caracteristicaAdded = caracteristicaAdded;}
}
