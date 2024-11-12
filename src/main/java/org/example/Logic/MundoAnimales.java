package org.example.Logic;

import org.example.Data.Datos;
import org.example.Logic.DataStructures.ListInterface;
import org.example.Logic.DataStructures.Tree;
import org.example.Logic.DataStructures.TreeInterface;
import org.example.Presentation.MenuGUI;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.text.Normalizer;
import java.util.Map;

public class MundoAnimales {

    private Map<String, ListInterface<String>> hashMap;
    private ListInterface<Animal> animalContenedor;
    private TreeInterface tree;
    private Datos manager;

    public MundoAnimales() {
        manager = new Datos();
        tree = new Tree();
        this.personalizarVista();

    }

    public void ejecutarJuego() {

        if (!manager.cargarDatos(tree)) {
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


        // Verificar si el nombre es válido
        if (nombreAnimal == null || nombreAnimal.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un nombre válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!nombreAnimal.matches("[a-zA-ZáéíóúÁÉÍÓÚÑñ ]+")) {  // Regex para solo letras y espacios
            JOptionPane.showMessageDialog(null, "El nombre solo debe contener letras y espacios.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            nombreAnimal = nombreAnimal.toLowerCase();
            nombreAnimal = this.quitarTildes(nombreAnimal);
            System.out.println("Animal digitado: " + nombreAnimal);
            if (hashMap.containsKey(nombreAnimal)) {
                JOptionPane.showMessageDialog(null, nombreAnimal + ": " + hashMap.get(nombreAnimal).getStreamListCaracteristicas());
                System.out.println(hashMap.get(nombreAnimal).getStreamList());
            } else {
                JOptionPane.showMessageDialog(null, "El animal digitado no esta registrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void personalizarVista(){
        // Configurar el estilo de los cuadros de diálogo
        UIManager.put("OptionPane.background", new Color(245, 245, 245)); // Gris claro
        UIManager.put("Panel.background", new Color(245, 245, 245)); // Gris claro
        UIManager.put("OptionPane.messageForeground", new Color(60, 60, 60)); // Gris oscuro
        UIManager.put("Button.background", new Color(102, 153, 173)); // Verde suave
        UIManager.put("Button.foreground", Color.WHITE); // Texto en blanco
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 14));
    }

    public String quitarTildes(String texto) {
        // Normalizar el texto para separar caracteres con tildes en base + tilde
        String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        // Eliminar únicamente las tildes de las vocales
        textoNormalizado = textoNormalizado.replaceAll("[\\p{M}&&[\\u0301]]", "");
        return textoNormalizado;
    }

    public void salir() {
        int save = JOptionPane.showConfirmDialog(null,
                "¿Quieres salvar el árbol?", "Pregunta", JOptionPane.YES_NO_OPTION);
        if (save == JOptionPane.YES_NO_OPTION) {
            manager.guardarDatos(tree);
        }
        JOptionPane.showMessageDialog(null, "¡Hasta luego!");
    }
}





