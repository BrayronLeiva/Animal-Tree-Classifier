package org.example;

public class List<T> implements ListInterface<T> {

    private NodeList<T> dummy;  // Ahora es un nodo de tipo genérico <T>
    private NodeList<T> back;   // También de tipo genérico <T>

    List(){
        back = new NodeList<>(null,null,null);
        dummy = new NodeList<>(null,null,null);
        dummy.setNext(back);
        back.setPrevious(dummy);
    }
    @Override
    public void addFront(NodeList<T> nodeList){
        NodeList<T> previous_aux = dummy.getNext();
        dummy.setNext(nodeList);
        nodeList.setPrevious(dummy);
        nodeList.setNext(previous_aux);
        previous_aux.setPrevious(nodeList);

    }
    @Override
    public void addEnd(NodeList<T> nodeList){

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
}
