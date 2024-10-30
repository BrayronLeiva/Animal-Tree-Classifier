package org.example;

public class Animal {
    private String nombre;
    private String caracteristica;
    private boolean isAnimal;

    private int nivel;

    public Animal(){}

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
}
