package org.example.DataStructures;

import java.io.Serializable;

public interface ListInterface<T extends Serializable> extends Serializable {

    void addFront(T obj);

    void addEnd(T obj);

    NodeList<T> getDummy();

    void setDummy(NodeList<T> dummy);

    // Getter y setter para back
    NodeList<T> getBack();

    void setBack(NodeList<T> back);

    void printList();
    String getStreamList();

    void reverse();

    void quickSort();

    String getStreamListCaracteristicas();

    boolean contains(T target);


}
