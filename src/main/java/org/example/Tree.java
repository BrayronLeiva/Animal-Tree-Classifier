package org.example;

public class Tree implements TreeInterface {
    private Node base;

    public Tree() {
    }

    @Override
    public void insert(Integer data) {
        if(isEmpty()){
            base = new Node(data, null, null);
            return;
        }
        insertRecursive(base, data);
    }

    private void insertRecursive(Node base, Integer data) {
        if (data < base.getData()) {
            if (base.getLeft() == null) {
                base.setLeft(new Node(data, null, null));
            } else {
                insertRecursive(base.getLeft(), data);
            }
        } else {
            if (base.getRigt() == null) {
                base.setRigt(new Node(data, null, null));
            } else {
                insertRecursive(base.getRigt(), data);
            }
        }
    }

    @Override
    public void inorder() {
        if (base != null) {
            inOrderRecursive(base);
        }
        System.out.println("Recorrido inorder terminado");
    }

    private void inOrderRecursive(Node root) {
        if (root != null) {
            inOrderRecursive(root.getLeft());
            System.out.println(root.getData());
            inOrderRecursive(root.getRigt());
        }
    }

    @Override
    public void preorder() {
        if (base != null) {
            preOrderRecursive(base);
        }
        System.out.println("Recorrido preorder terminado");
    }

    private void preOrderRecursive(Node root) {
        if (root != null) {
            System.out.println(root.getData());
            preOrderRecursive(root.getLeft());
            preOrderRecursive(root.getRigt());
        }
    }

    @Override
    public void posorder() {
        if (base != null) {
            posOrderRecursive(base);
        }
        System.out.println("Recorrido posorder terminado");
    }

    private void posOrderRecursive(Node root) {
        if (root != null) {
            posOrderRecursive(root.getLeft());
            posOrderRecursive(root.getRigt());
            System.out.println(root.getData());
        }
    }

    @Override
    public void poda() {
        if (base != null) {
            podaRecursive(base);
        }
        System.out.println("Poda terminada");
    }

    private void podaRecursive(Node root) {
        if (root != null) {
            if (!root.getLeft().hasChildren()) {
                System.out.println("Se eliminó " + root.getLeft().getData());
                root.setLeft(null);
            } else {
                podaRecursive(root.getLeft());
            }
            if (!root.getRigt().hasChildren()) {
                System.out.println("Se eliminó " + root.getRigt().getData());
                root.setRigt(null);
            } else {
                podaRecursive(root.getRigt());
            }
        }
    }

    @Override
    public void minElement() {
        if (base != null) {
            minElementRecursive(base);
        }
    }

    private void minElementRecursive(Node root) {
        if (root != null) {
            if (root.getLeft() != null) {
                minElementRecursive(root.getLeft());
            } else {
                System.out.println("El mínimo elemento es " + root.getData());
            }
        }
    }

    @Override
    public void deleteMinElement() {
        if (base != null) {
            deleteMinElementRecursive(base);
        }
    }

    private void deleteMinElementRecursive(Node root) {
        if (root != null) {
            if (root.getLeft() == null) {
                System.out.println("Se eliminó el elemento " + root.getData());
                if (root.getRigt() == null) {
                    cleanTree();
                } else {
                    base = root.getRigt();
                }
                return;
            }

            if (root.getLeft().getLeft() == null) {
                System.out.println("Se eliminó el elemento " + root.getLeft().getData());
                if (root.getLeft().getRigt() == null) {
                    root.setLeft(null);
                } else {
                    root.setLeft(root.getLeft().getRigt());
                }
            } else {
                deleteMinElementRecursive(root.getLeft());
            }
        }
    }

    @Override
    public void getNivel(Integer data) {
        int nivel = 0;
        searchRecursive(data, base, nivel);
    }

    private void searchRecursive(Integer data, Node root, int nivel) {
        if (data == root.getData()) {
            System.out.println("El nivel del nodo con " + data + " es " + nivel);
            return;
        }
        if (data < root.getData()) {
            searchRecursive(data, root.getLeft(), nivel + 1);
        } else {
            searchRecursive(data, root.getRigt(), nivel + 1);
        }
    }

    @Override
    public void cleanTree() {
        base = null;
    }

    @Override
    public boolean isEmpty() {
        return base == null;
    }
}
