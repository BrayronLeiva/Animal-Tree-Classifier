package org.example.Logic.DataStructures;


import java.io.Serializable;

public class Stack<T extends Serializable> implements ListInterface<T> {
    private NodeList<T> dummy;  // Ahora es un nodo de tipo genérico <T>
    private NodeList<T> back;   // También de tipo genérico <T>

    private int size;

    public Stack(){
        back = new NodeList<>(null,null,null);
        dummy = new NodeList<>(null,null,null);
        dummy.setNext(back);
        back.setPrevious(dummy);
        this.size = 0;
    }
    @Override
    public void addFront(T obj){
        NodeList<T> nodeList = new NodeList<>(null,null, obj);
        NodeList<T> next_aux = dummy.getNext();
        dummy.setNext(nodeList);
        nodeList.setPrevious(dummy);
        nodeList.setNext(next_aux);
        next_aux.setPrevious(nodeList);
        this.size++;

    }

    @Override
    public NodeList<T> pop() {
        if(dummy.getNext()!=back){
            NodeList<T> target = dummy.getNext();
            this.deleteFront();
            return target;
        }else {
            System.out.println("La pila esta vacia");
            return null;
        }
    }
    public void deleteFront(){
        if(dummy.getNext()!=back){
            dummy.getNext().getNext().setPrevious(dummy);
            dummy.setNext(dummy.getNext().getNext());
            size--;
        }
    }
    // Getter y setter para dummy
    @Override
    public NodeList<T> getDummy() {
        return dummy;
    }
    @Override
    public void setDummy(NodeList<T> dummy) {
        this.dummy = dummy;
    }

    // Getter y setter para back
    @Override
    public NodeList<T> getBack() {
        return back;
    }
    @Override
    public void setBack(NodeList<T> back) {
        this.back = back;
    }

    @Override
    public void printList() {
        if(dummy.getNext()!=back) {
            printListRecursive(dummy.getNext());
        }

    }
    public void printListRecursive(NodeList<T> root) {
        if(root.getData()!=null) { //por si entra back
            System.out.println(root.getData().toString());
            printListRecursive(root.getNext());
        }

    }

    public String getStreamList(){

        if(dummy.getNext()!=back) {
            return getStreamListRecursive(dummy.getNext());
        }
        return "";
    }

    public String getStreamListRecursive(NodeList<T> root) {

        if(root.getData()!=null) { //por si entra back
            //System.out.println("Agregando: " + root.getData().toString());
            return root.getData().toString() + getStreamListRecursive(root.getNext());

        }
        return "";
    }

    @Override
    public boolean contains(T target){
        if(dummy.getNext()!=back) {
            return containsRecursive(dummy.getNext(), target);
        }else{
            return false;
        }
    }

    public boolean containsRecursive(NodeList<T> node, T target){
        if(node!=null && node != back) {//por si entra back
            if(node.getData().equals(target)){
                return true;
            }else{
                return containsRecursive(node.getNext(), target);
            }
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    @Override
    public void addEnd(T obj){
        System.out.println("Stack no agrega al final");
    }
    @Override
    public String getStreamListCaracteristicas(){

        System.out.println("Metodo no es de stack");
        return "";
    }

    @Override
    public void reverse() {
        System.out.println("Metodo no es de stack");
    }

    @Override
    public void quickSort() {
        System.out.println("Metodo no es de stack");
    }

    public void quickSortRecursive(Integer inicio, Integer fin){
        System.out.println("Metodo no es de stack");
    }

}