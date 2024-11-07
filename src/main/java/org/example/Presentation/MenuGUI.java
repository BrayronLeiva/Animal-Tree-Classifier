package org.example.Presentation;



import org.example.MundoAnimales;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI {
    private JFrame frame;
    private JPanel panel;
    private JButton opcion1;
    private JButton opcion2;
    private JButton opcion3;
    private JButton opcion4;
    private JButton opcion5;
    private MundoAnimales mundoAnimales;

    // Constructor que recibe una instancia de MundoAnimales
    public MenuGUI(MundoAnimales mundoAnimales) {
        this.mundoAnimales = mundoAnimales;

        // Crear el frame
        frame = new JFrame("Menú de opciones");
        frame.setSize(400, 300); // Aumentar el tamaño de la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Crear el panel principal con márgenes y un diseño de cuadrícula
        panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añadir márgenes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Añadir espaciado entre los botones
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;

        // Configurar y añadir cada botón
        opcion1 = crearBoton("1. Ordenar lista (Sort)", gbc);
        opcion1.addActionListener(e -> mundoAnimales.ordenarLista());

        opcion2 = crearBoton("2. Revertir lista (Reverse)", gbc);
        opcion2.addActionListener(e -> mundoAnimales.revertirLista());

        opcion3 = crearBoton("3. Imprimir lista (Display)", gbc);
        opcion3.addActionListener(e -> mundoAnimales.imprimirLista());

        opcion4 = crearBoton("4. Buscar elemento (Features)", gbc);
        opcion4.addActionListener(e -> mundoAnimales.buscarElemento());

        opcion5 = crearBoton("5. Salir", gbc);
        opcion5.addActionListener(e -> {
            mundoAnimales.salir();
            frame.dispose(); // Cerrar la ventana
        });

        // Añadir el panel al frame
        frame.add(panel);
    }

    // Método para crear un botón con estilo
    private JButton crearBoton(String texto, GridBagConstraints gbc) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(300, 30)); // Tamaño del botón
        boton.setBackground(new Color(102, 153, 173)); // Color de fondo del botón
        boton.setForeground(Color.WHITE); // Color del texto
        boton.setFont(new Font("Arial", Font.PLAIN, 14)); // Fuente y tamaño del texto
        boton.setFocusPainted(false); // Quitar el borde de enfoque
        panel.add(boton, gbc); // Añadir el botón al panel con el layout especificado
        return boton;
    }

    // Método para mostrar la ventana
    public void mostrarMenu() {
        frame.setVisible(true);
    }
}