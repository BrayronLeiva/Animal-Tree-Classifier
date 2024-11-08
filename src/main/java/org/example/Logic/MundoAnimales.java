package org.example.Logic;

import org.example.Logic.DataStructures.ListInterface;
import org.example.Logic.DataStructures.Tree;
import org.example.Logic.DataStructures.TreeInterface;
import org.example.Presentation.MenuGUI;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MundoAnimales {

    private Map<String, ListInterface<String>> hashMap;
    private ListInterface<Animal> animalContenedor;
    private TreeInterface tree;

    public MundoAnimales() {
        // Configurar el estilo de los cuadros de diálogo
        UIManager.put("OptionPane.background", new Color(245, 245, 245)); // Gris claro
        UIManager.put("Panel.background", new Color(245, 245, 245)); // Gris claro
        UIManager.put("OptionPane.messageForeground", new Color(60, 60, 60)); // Gris oscuro
        UIManager.put("Button.background", new Color(102, 153, 173)); // Verde suave
        UIManager.put("Button.foreground", Color.WHITE); // Texto en blanco
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 14));


    }

    public void ejecutarJuego() {
        tree = new Tree();
        if (!tree.loadTree()) {
            return;
        }
        tree.inorder();
        tree.play();
        animalContenedor = tree.convertTreeIntoList();
        hashMap = tree.convertTreeIntoHashMap(animalContenedor);

        // Crear y mostrar el menú gráfico, pasando esta instancia de MundoAnimales
        MenuGUI menu = new MenuGUI(this);
        menu.mostrarMenu();
    }

    public void ordenarLista() {
        JOptionPane.showMessageDialog(null, "Lista Ordenada", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        animalContenedor.quickSort();
    }

    public void revertirLista() {
        JOptionPane.showMessageDialog(null, "Lista Revertida", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        animalContenedor.reverse();
    }

    public void imprimirLista() {
        System.out.println(animalContenedor.getStreamList());
        JOptionPane.showMessageDialog(null, "Lista actual: " + animalContenedor.getStreamList());
    }

    public void buscarElemento() {
        // Pedir el nombre del animal
        String nombreAnimal = JOptionPane.showInputDialog(null, "Digite el nombre del animal:",
                "Nuevo animal", JOptionPane.QUESTION_MESSAGE);
        nombreAnimal = nombreAnimal.toLowerCase();
        System.out.println("Animal digitado: " + nombreAnimal);

        // Verificar si el nombre es válido
        if (nombreAnimal == null || nombreAnimal.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un nombre válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!nombreAnimal.matches("[a-zA-ZáéíóúÁÉÍÓÚÑñ ]+")) {  // Regex para solo letras y espacios
            JOptionPane.showMessageDialog(null, "El nombre solo debe contener letras y espacios.", "Error", JOptionPane.ERROR_MESSAGE);
        } else
            if (hashMap.containsKey(nombreAnimal)) {
            JOptionPane.showMessageDialog(null, nombreAnimal + ": " + hashMap.get(nombreAnimal).getStreamListCaracteristicas());
            System.out.println(hashMap.get(nombreAnimal).getStreamList());
        }
            else {
            JOptionPane.showMessageDialog(null, "El animal digitado no esta registrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void salir() {
        int save = JOptionPane.showConfirmDialog(null,
                "¿Quieres salvar el árbol?", "Pregunta", JOptionPane.YES_NO_OPTION);
        if (save == JOptionPane.YES_NO_OPTION) {
            tree.guardarArbol();
        }
        JOptionPane.showMessageDialog(null, "¡Hasta luego!");
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