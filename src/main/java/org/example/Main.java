package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        TreeInterface tree = new Tree();
        tree.loadTree();
        tree.inorder();
        tree.play();
        List<Animal> animalList;
        animalList = tree.convertTreeIntoList();
        animalList.printList();

        boolean continuar = true;

        while (continuar) {
            // Mostrar el menú de opciones
            String opcion = JOptionPane.showInputDialog(null,
                    "Seleccione una opción:\n" +
                            "1. Ordenar lista\n" +
                            "2. Revertir lista\n" +
                            "3. Imprimir lista\n" +
                            "4. Buscar elemento\n" +
                            "5. Salir", "Menú de opciones", JOptionPane.QUESTION_MESSAGE);

            if (opcion != null) {
                switch (opcion) {
                    case "1":
                        ordenarLista();
                        break;
                    case "2":
                        revertirLista();
                        break;
                    case "3":
                        imprimirLista(animalList);
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

    private static void revertirLista() {

    }

    private static void imprimirLista(List<Animal> animalList) {
        System.out.println(animalList.getStreamList());
        JOptionPane.showMessageDialog(null, "Lista actual: " + animalList.getStreamList());
    }

    private static void buscarElemento() {

    }

}
