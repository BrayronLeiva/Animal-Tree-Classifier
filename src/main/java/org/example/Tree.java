package org.example;
import javax.swing.JOptionPane;
public class Tree implements TreeInterface {
    private Node base;

    public Tree() {
    }

    public void play(){
        if(isEmpty()){
            System.out.println("El arbol esta vacio ingrese un animal: ");
        }else{
            playRecursive(base);
        }
    }

    public void playRecursive(Node root){
        // Mostramos un cuadro de diálogo con botones de opción
        int respuesta = JOptionPane.showConfirmDialog(null,
                "El animal que está pensando : " + root.getData().preguntar() + "?",
                "Pregunta",
                JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            if(root.getData().isAnimal()){
                JOptionPane.showMessageDialog(null, "¡El animal ya existe!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("¡El animal ya existe!");
            } else{
                playRecursive(root.getRigt());
            }
        } else {
            System.out.println("¡Vamos a seguir buscando!");
            if(root.getLeft()!=null) {
                playRecursive(root.getLeft());
            }else {
                System.out.println("Estamos debajo de una caracteristica solamente ingresamos");
                if (!root.getData().isAnimal()) {
                    JOptionPane.showMessageDialog(null, "¡No hay informacion de este animal vamos a agregarla!", "Resultado", JOptionPane.INFORMATION_MESSAGE);

                    String nombreAnimal = JOptionPane.showInputDialog(null, "Digite el nombre del animal:",
                            "Nuevo animal", JOptionPane.QUESTION_MESSAGE);

                    // Si el usuario cancela o no introduce nada, nombreAnimal será null
                    if (nombreAnimal != null && !nombreAnimal.trim().isEmpty()) {
                        // Obtener la característica del nuevo animal
                        String caracteristicaAnimal = JOptionPane.showInputDialog(null, "Digite una característica distintiva del animal:",
                                "Nueva característica", JOptionPane.QUESTION_MESSAGE);

                        if (caracteristicaAnimal != null && !caracteristicaAnimal.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Nuevo animal: " + nombreAnimal + "\nCaracterística: "
                                    + caracteristicaAnimal, "Nuevo animal añadido", JOptionPane.INFORMATION_MESSAGE);
                            Animal animal = new Animal(nombreAnimal, caracteristicaAnimal, true);
                            root.setLeft(new Node(animal, null, null,root));
                            inorder();
                            play();
                        } else {
                            JOptionPane.showMessageDialog(null, "Debe ingresar una característica válida.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Debe ingresar un nombre válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    //fin de captura de datos


                } else {// si soy animal y voy a ingresar abajo mio tengo que crear el nodo de caracteristica para el arbol
                    System.out.println("Hay que hacer switch ya que vamos a ingresar denajo de un animal");
                    Animal carac_a = new Animal("1",root.getData().getCaracteristica(),false);
                    Node carac_n = new Node(carac_a,null,null,root.getParent());
                    root.getParent().setLeft(carac_n);
                    root.getParent().getLeft().setRigt(root);
                    root.setParent(carac_n);
                    JOptionPane.showMessageDialog(null, "¡No hay informacion de este animal vamos a agregarla!", "Resultado", JOptionPane.INFORMATION_MESSAGE);

                    String nombreAnimal = JOptionPane.showInputDialog(null, "Digite el nombre del animal:",
                            "Nuevo animal", JOptionPane.QUESTION_MESSAGE);

                    // Si el usuario cancela o no introduce nada, nombreAnimal será null
                    if (nombreAnimal != null && !nombreAnimal.trim().isEmpty()) {
                        // Obtener la característica del nuevo animal
                        String caracteristicaAnimal = JOptionPane.showInputDialog(null, "Digite una característica distintiva del animal:",
                                "Nueva característica", JOptionPane.QUESTION_MESSAGE);

                        if (caracteristicaAnimal != null && !caracteristicaAnimal.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Nuevo animal: " + nombreAnimal + "\nCaracterística: "
                                    + caracteristicaAnimal, "Nuevo animal añadido", JOptionPane.INFORMATION_MESSAGE);
                            Animal animal = new Animal(nombreAnimal, caracteristicaAnimal, true);
                            root.getParent().setLeft(new Node(animal, null, null,root));
                            inorder();
                            play();
                        } else {
                            JOptionPane.showMessageDialog(null, "Debe ingresar una característica válida.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Debe ingresar un nombre válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        }

    }

    @Override
    public void insert(Animal data) {
        if(isEmpty()){
            base = new Node(data, null, null,null);
            return;
        }
        insertRecursive(base, data);
    }

    public void loadTree(){
        Animal data = new Animal("ave","1",true);
        base =  new Node(data, null, null, null);
        Animal data1 = new Animal("","reptil",false);
        Node reptil = new Node(data1,null,null, base);
        base.setLeft(reptil);
        Animal data2 = new Animal("Lagarto","1",true);
        Node lagarto = new Node(data2,null,null, reptil);
        reptil.setRigt(lagarto);
        //regresamos a base
        Animal data3 = new Animal("1","cacarea", false);
        Node cacarea = new Node(data3,null,null, base);
        base.setRigt(cacarea);
        Animal data4 = new Animal("aguila","1", true);
        Node aguila = new Node(data4,null,null,cacarea);
        cacarea.setLeft(aguila);
        Animal data5 = new Animal("gallina","1",true);
        Node gallina = new Node(data5,null,null,cacarea);
        cacarea.setRigt(gallina);
    }

    private void insertRecursive(Node base, Animal data) {
        /*
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
        */
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
            System.out.println(root.getData().preguntar());
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
            System.out.println(root.getData().toString());
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
            System.out.println(root.getData().toString());
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
                System.out.println("Se eliminó " + root.getLeft().getData().toString());
                root.setLeft(null);
            } else {
                podaRecursive(root.getLeft());
            }
            if (!root.getRigt().hasChildren()) {
                System.out.println("Se eliminó " + root.getRigt().getData().toString());
                root.setRigt(null);
            } else {
                podaRecursive(root.getRigt());
            }
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
