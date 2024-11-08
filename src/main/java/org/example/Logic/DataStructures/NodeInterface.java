package org.example.Logic.DataStructures;

public interface NodeInterface<T> {
    T getData();
    void setData(T data);
    NodeInterface<T> getLeft();
    void setLeft(NodeInterface<T> left);
    NodeInterface<T> getRight();
    void setRight(NodeInterface<T> right);
    NodeInterface<T> getParent();
    void setParent(NodeInterface<T> parent);
    boolean hasChildren();
    Boolean getCaracteristicaAdded();
    void setCaracteristicaAdded(Boolean caracteristicaAdded);
}
