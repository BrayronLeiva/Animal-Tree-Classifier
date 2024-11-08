package org.example.Logic.DataStructures;

import java.io.Serializable;

public interface ListInterface<T> {

    void addFront(T obj);

    void addEnd(T obj);

    NodeList<T> pop();

    NodeList<T> getDummy();

    void setDummy(NodeList<T> dummy);

    // Getter y setter para back
    NodeList<T> getBack();

    void setBack(NodeList<T> back);

    int getSize();
    void printList();
    String getStreamList();

    void reverse();

    void quickSort();

    String getStreamListCaracteristicas();

    boolean contains(T target);


}
