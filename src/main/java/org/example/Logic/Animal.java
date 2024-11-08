package org.example.Logic;

import org.example.Logic.DataStructures.Contenedor;
import org.example.Logic.DataStructures.ListInterface;
import org.example.Logic.DataStructures.NodeList;

import java.io.Serializable;

public class Animal implements Serializable {
    private String nombre;
    private String caracteristica;
    private boolean isAnimal;
    private int nivel;
    private ListInterface<String> listaCaracteristicas;

    private static final long serialVersionUID = 1L;

    public Animal(){}

    public Animal(String nombre, String caracteristica, boolean animal, int nivel) {
        this.isAnimal = animal;
        this.nombre = nombre;
        this.caracteristica = caracteristica;
        this.nivel = nivel;
        this.listaCaracteristicas = new Contenedor<String>();
        if(!listaCaracteristicas.contains(caracteristica)){
            listaCaracteristicas.addEnd(caracteristica);
        }

    }

    public boolean isAnimal() {
        return isAnimal;
    }

    public void setAnimal(boolean animal) {
        isAnimal = animal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String preguntar() {
        if(isAnimal){
            return "es "+ nombre;
        }else{
            return caracteristica;
        }
    }

    public void setearCaracteristicas(ListInterface<String> otrasCaracteristicas){
        NodeList<String> actual = otrasCaracteristicas.getDummy().getNext();
        for (int i = 0; i < otrasCaracteristicas.getSize() && actual!=otrasCaracteristicas.getBack(); i++) {
            if(!listaCaracteristicas.contains(actual.getData())) {
                listaCaracteristicas.addFront(actual.getData());
            }
            actual = actual.getNext();
        }
    }

    @Override
    public String toString() {
        return "\nNombre " + nombre + " Nivel " + nivel;
    }

    public ListInterface<String> getListaCaracteristicas() {
        return listaCaracteristicas;
    }

    public void setListaCaracteristicas(ListInterface<String> listaCaracteristicas) {
        this.listaCaracteristicas = listaCaracteristicas;
    }
}
