package org.example.Logic;

import org.example.DataStructures.ListInterface;

import java.io.Serializable;

public class Animal implements Serializable {
    private String nombre;
    private String caracteristica;
    private boolean isAnimal;
    private int nivel;
    private ListInterface<String> listaCaracteristicas;

    private static final long serialVersionUID = 1L;

    public Animal(){}

    public Animal(String nombre, String caracteristica, boolean animal, int nivel, ListInterface<String> listaCaracteristicas) {
        isAnimal = animal;
        this.nombre = nombre;
        this.caracteristica = caracteristica;
        this.nivel = nivel;
        this.listaCaracteristicas = listaCaracteristicas;
        if(!listaCaracteristicas.contains(caracteristica)){
            listaCaracteristicas.addEnd(caracteristica);
        }

    }

    public Animal(String nombre, String caracteristica, boolean animal, int nivel) {
        isAnimal = animal;
        this.nombre = nombre;
        this.caracteristica = caracteristica;
        this.nivel = nivel;

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
