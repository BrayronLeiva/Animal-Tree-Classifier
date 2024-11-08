package org.example.Logic.DataStructures;

import java.io.Serializable;

public class NodeList<T> {
    private NodeList<T> previous;  // Ahora es un nodo de tipo genérico <T>
    private NodeList<T> next;   // También de tipo genérico <T>
    private T data;             // El dato ahora es de tipo genérico


    public NodeList(NodeList<T> next, NodeList<T> previous, T data) {
        this.next = next;
        this.previous = previous;
        this.data = data;
    }

    public NodeList<T> getPrevious() {
        return previous;
    }

    public void setPrevious(NodeList<T> previous) {
        this.previous = previous;
    }

    public NodeList<T> getNext() {
        return next;
    }

    public void setNext(NodeList<T> next) {
        this.next = next;
    }

    // Getter y setter para data
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
