package org.example;

import org.w3c.dom.Node;

public class Contenedor<T> implements ListInterface<T> {

    private NodeList<T> dummy;  // Ahora es un nodo de tipo genérico <T>
    private NodeList<T> back;   // También de tipo genérico <T>

    private int size;

    Contenedor(){
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
    public void addEnd(T obj){
        NodeList<T> nodeList = new NodeList<>(null,null, obj);
        NodeList<T> previous_aux = back.getPrevious();
        back.setPrevious(nodeList);
        nodeList.setNext(back);
        nodeList.setPrevious(previous_aux);
        previous_aux.setNext(nodeList);
        this.size++;
        //System.out.println("Index de " + nodeList.getData().toString() + " " + this.getIndexOfNode(nodeList));

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
    public String getStreamListCaracteristicas(){

        if(dummy.getNext()!=back) {
            return getStreamListRecursiveCaracteristicas(dummy.getNext());
        }
        return "";
    }

    public String getStreamListRecursiveCaracteristicas(NodeList<T> root) {

        if(root.getData()!=null) { //por si entra back
            //System.out.println("Agregando: " + root.getData().toString());
            if(root.getNext().getData()!=null) {
                return root.getData().toString() + " , " + getStreamListRecursive(root.getNext());
            }else{
                return root.getData().toString();
            }

        }
        return "";
    }

    @Override
    public void reverse() {

        Contenedor<T> reverseContenedor = new Contenedor<T>();

        if(dummy.getNext()!=back) {
            reverseRecursive(dummy.getNext(), reverseContenedor);
        }

        this.setDummy(reverseContenedor.getDummy());
        this.setBack(reverseContenedor.getBack());

    }


    public void reverseRecursive(NodeList<T> root, Contenedor<T> reverseContenedor ){
        if(root!=null && root != back){//por si entra back
            System.out.println("Revirtiendo: " + root.getData().toString());
            reverseContenedor.addFront(root.getData());
            reverseRecursive(root.getNext(), reverseContenedor);
        }
    }

    public NodeList<T> getNode(Integer target){
        if(dummy.getNext()!=back){
            return getNodeRecursive(dummy.getNext(), target, 0);
        }
        return null;
    }

    public NodeList<T> getNodeRecursive(NodeList<T> node, Integer target, Integer index){
        if(node!=back){
            if(target==index){
                return node;
            }else {
                return getNodeRecursive(node.getNext(), target, index + 1);
            }
        }
        return null;
    }

    public Integer getIndexOfNode(NodeList<T> node){
        if(dummy.getNext()!=back){
            return getIndexOfNodeRecursive(node, dummy.getNext(), 0);
        }
        return -1;
    }

    public Integer getIndexOfNodeRecursive(NodeList<T> node, NodeList<T> actual, Integer index){
        if(node!=back){
            if(actual.getData().equals(node.getData())){
                return index;
            }else {
                return getIndexOfNodeRecursive(node, actual.getNext(), index + 1);
            }
        }
        return null;
    }

    @Override
    public void quickSort() {
        Integer fin = this.getIndexOfNode(back.getPrevious());
        Integer inicio = this.getIndexOfNode(dummy.getNext());
        if(dummy.getNext()!=back){ //existen elementos
            quickSortRecursive(inicio,fin);
        }
    }

    public void quickSortRecursive(Integer inicio, Integer fin){
        if(inicio<fin) {
            Integer puntoQuibre = ubicarPivote(getNode(inicio), getNode(fin));
            quickSortRecursive(inicio, puntoQuibre-1);
            quickSortRecursive(puntoQuibre+1, fin);
        }
    }

    private Integer ubicarPivote(NodeList<T> rootDummy, NodeList<T> rootBack){
        //7 3 5 8 4 9 0 1
        NodeList<T> backAux = rootBack;
        NodeList<T> dummyAux = rootDummy;
        NodeList<T> switchAux;
        NodeList<T> previousBack;
        NodeList<T> nextBack;
        NodeList<T> previousDummy;
        NodeList<T> nextDummy;

        while (dummyAux!=backAux){

            // Haciendo cast a Animal
            Animal dummyData = (Animal) dummyAux.getData();
            Animal backData = (Animal) backAux.getData();

            while (dummyData.getNivel()<=backData.getNivel() && dummyAux!=backAux){
                backAux = backAux.getPrevious();
                backData = (Animal) backAux.getData();
            }
            if(dummyAux!=backAux) {
                //cuando cierra este cicle hay que hacer el intercambio
                previousBack = backAux.getPrevious(); //15
                nextBack = backAux.getNext(); //20
                previousDummy = dummyAux.getPrevious(); //null
                nextDummy = dummyAux.getNext(); //9

                if (backAux.getPrevious() != dummyAux) { //nunca el dummyaux esta a left de backaux
                    backAux.setNext(nextDummy);
                    backAux.setPrevious(previousDummy);
                    if (previousDummy != null) {
                        previousDummy.setNext(backAux);
                    }
                    if (nextDummy != null) {
                        nextDummy.setPrevious(backAux);
                    }

                    dummyAux.setPrevious(previousBack);
                    dummyAux.setNext(nextBack);
                    if (previousBack != null) {
                        previousBack.setNext(dummyAux);
                    }
                    if (nextBack != null) {
                        nextBack.setPrevious(dummyAux);
                    }

                } else {// si los 2 estan a la par

                    backAux.setPrevious(previousDummy);
                    if (previousDummy != null) {
                        previousDummy.setNext(backAux);
                    }

                    dummyAux.setNext(nextBack);
                    if (nextBack != null) {
                        nextBack.setPrevious(dummyAux);
                    }

                    //ahora conecta a los directo
                    dummyAux.setPrevious(backAux);
                    backAux.setNext(dummyAux);
                }
                switchAux = dummyAux;
                dummyAux = backAux;
                backAux = switchAux;

                dummyData = (Animal) dummyAux.getData();
                backData = (Animal) backAux.getData();
            }

            while (backData.getNivel()>=dummyData.getNivel() && dummyAux!=backAux){
                dummyAux = dummyAux.getNext();
                dummyData = (Animal) dummyAux.getData();
            }
            if(dummyAux!=backAux) {
                previousBack = backAux.getPrevious();
                nextBack = backAux.getNext();
                previousDummy = dummyAux.getPrevious();
                nextDummy = dummyAux.getNext();

                if (backAux.getPrevious() != dummyAux) { //nunca el dummyaux esta a left de backaux
                    backAux.setNext(nextDummy);
                    backAux.setPrevious(previousDummy);
                    if (previousDummy != null) {
                        previousDummy.setNext(backAux);
                    }
                    if (nextDummy != null) {
                        nextDummy.setPrevious(backAux);
                    }

                    dummyAux.setPrevious(previousBack);
                    dummyAux.setNext(nextBack);
                    if (previousBack != null) {
                        previousBack.setNext(dummyAux);
                    }
                    if (nextBack != null) {
                        nextBack.setPrevious(dummyAux);
                    }
                } else {// si los 2 estan a la par

                    backAux.setPrevious(previousDummy);
                    if (previousDummy != null) {
                        previousDummy.setNext(backAux);
                    }

                    dummyAux.setNext(nextBack);
                    if (nextBack != null) {
                        nextBack.setPrevious(dummyAux);
                    }

                    //ahora conecta a los directo
                    dummyAux.setPrevious(backAux);
                    backAux.setNext(dummyAux);
                }

                switchAux = dummyAux;
                dummyAux = backAux;
                backAux = switchAux;
            }
        }

        System.out.println("Acomodo de pivote listo");
        return this.getIndexOfNode(dummyAux);
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
}
