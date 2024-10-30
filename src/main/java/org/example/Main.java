package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        TreeInterface tree = new Tree();
        tree.loadTree();
        //tree.inorder();
        //tree.play();
        Contenedor<Animal> animalContenedor;
        //animalContenedor = tree.convertTreeIntoList();
        animalContenedor = new Contenedor<Animal>();
        Animal a = new Animal("12","12",true, 12);
        Animal a1 = new Animal("9","9",true, 9);
        Animal a2 = new Animal("18","18",true, 18);
        Animal a3 = new Animal("8","8",true, 8);
        Animal a4 = new Animal("24","24",true, 24);
        Animal a5 = new Animal("15","15",true, 15);
        Animal a6 = new Animal("10","10",true, 10);
        Animal a7 = new Animal("20","20",true, 20);
        animalContenedor.addEnd(a);
        animalContenedor.addEnd(a1);
        animalContenedor.addEnd(a2);
        animalContenedor.addEnd(a3);
        animalContenedor.addEnd(a4);
        animalContenedor.addEnd(a5);
        animalContenedor.addEnd(a6);
        animalContenedor.addEnd(a7);
        animalContenedor.printList();
       // animalContenedor.quickSort();


        //animalContenedor.printList();


        boolean continuar = false;

        while (continuar) {
            // Mostrar el menú de opciones
            String opcion = JOptionPane.showInputDialog(null,
                    "Seleccione una opción:\n" +
                            "1. Ordenar lista (Sort)\n" +
                            "2. Revertir lista (Reverse)\n" +
                            "3. Imprimir lista (Display)\n" +
                            "4. Buscar elemento\n" +
                            "5. Salir", "Menú de opciones", JOptionPane.QUESTION_MESSAGE);

            if (opcion != null) {
                switch (opcion) {
                    case "1":
                        ordenarLista();
                        break;
                    case "2":
                        revertirLista(animalContenedor);
                        break;
                    case "3":
                        imprimirLista(animalContenedor);
                        break;
                    case "4":
                        buscarElemento();
                        break;
                    case "5":
                        continuar = false; // Salir del ciclo
                        JOptionPane.showMessageDialog(null, "¡Hasta luego!");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida. Intente de nuevo.");
                        break;
                }
            } else {
                continuar = false; // Salir si cancelan el cuadro de diálogo
            }
        }

    }
    private static void ordenarLista() {

    }

    private static void revertirLista(Contenedor<Animal> animalContenedor) {
        animalContenedor.reverse();
    }

    private static void imprimirLista(Contenedor<Animal> animalContenedor) {
        System.out.println(animalContenedor.getStreamList());
        JOptionPane.showMessageDialog(null, "Lista actual: " + animalContenedor.getStreamList());
    }

    private static void buscarElemento() {

    }

}
