package org.example;

import org.example.DataStructures.ListInterface;
import org.example.DataStructures.Tree;
import org.example.DataStructures.TreeInterface;
import org.example.Models.Animal;

import javax.swing.*;
import java.util.Map;

public class MundoAnimales {

    public MundoAnimales() {}
    private Map<String, ListInterface<String>> hashMap;
    public void ejecutarJuego() {
        TreeInterface tree = new Tree();
        if(!tree.loadTree()){
            return;
        }
        tree.inorder();
        tree.play();
        ListInterface<Animal> animalContenedor;
        animalContenedor = tree.convertTreeIntoList();
        hashMap = tree.convertTreeIntoHashMap();

        boolean continuar = true;
        while (continuar) {
            // Mostrar el menú de opciones
            String opcion = JOptionPane.showInputDialog(null,
                    "Seleccione una opción:\n" +
                            "1. Ordenar lista (Sort)\n" +
                            "2. Revertir lista (Reverse)\n" +
                            "3. Imprimir lista (Display)\n" +
                            "4. Buscar elemento (Features)\n" +
                            "5. Salir", "Menú de opciones", JOptionPane.QUESTION_MESSAGE);

            if (opcion != null) {
                switch (opcion) {
                    case "1":
                        ordenarLista(animalContenedor);
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
                        salir(tree);
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

    private void salir(TreeInterface tree){
        int save = JOptionPane.showConfirmDialog(null,
                "Quieres salvar el arbol" + "?",
                "Pregunta",
                JOptionPane.YES_NO_OPTION);;
        if(save == JOptionPane.YES_NO_OPTION){
            tree.guardarArbol();
        }
        JOptionPane.showMessageDialog(null, "¡Hasta luego!");
    }
    private void ordenarLista(ListInterface<Animal> animalContenedor) {
        animalContenedor.quickSort();
    }

    private void revertirLista(ListInterface<Animal> animalContenedor) {
        animalContenedor.reverse();
    }

    private void imprimirLista(ListInterface<Animal> animalContenedor) {
        System.out.println(animalContenedor.getStreamList());
        JOptionPane.showMessageDialog(null, "Lista actual: " + animalContenedor.getStreamList());
    }

    private void buscarElemento() {
        // Pedir el nombre del animal
        String nombreAnimal = JOptionPane.showInputDialog(null, "Digite el nombre del animal:",
                "Nuevo animal", JOptionPane.QUESTION_MESSAGE);

        // Verificar si el nombre es válido
        if (nombreAnimal == null || nombreAnimal.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un nombre válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if(hashMap.containsKey(nombreAnimal)) {
            JOptionPane.showMessageDialog(null, nombreAnimal + ": " + hashMap.get(nombreAnimal).getStreamListCaracteristicas());
            System.out.println(hashMap.get(nombreAnimal).getStreamList());
        }else{
            JOptionPane.showMessageDialog(null, "El animal digitado no esta registrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}




/*
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
        //animalContenedor.printList();
        animalContenedor.quickSort();


        animalContenedor.printList();
*/