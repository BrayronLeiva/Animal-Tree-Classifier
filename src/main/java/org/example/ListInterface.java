package org.example;

public interface ListInterface<T> {

    void addFront(NodeList<T> nodeList);

    void addEnd(NodeList<T> nodeList);

    NodeList<T> getDummy();

    void setDummy(NodeList<T> dummy);

    // Getter y setter para back
    NodeList<T> getBack();

    void setBack(NodeList<T> back);

    void printList();
}
