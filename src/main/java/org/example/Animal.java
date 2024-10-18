package org.example;

public class Animal {
    private String nombre;
    private String caracteristica;

    private boolean isAnimal;

    public Animal(){}

    public Animal(String nombre, String caracteristica, boolean animal) {
        isAnimal = animal;
        if (isAnimal) {
            this.nombre = nombre;
        } else {
            this.caracteristica = caracteristica;
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

    public String preguntar() {
        if(isAnimal){
            return "es "+ nombre;
        }else{
            return caracteristica;
        }
    }
}
