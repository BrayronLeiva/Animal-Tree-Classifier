package org.example;

public class Main {
    public static void main(String[] args) {
        TreeInterface tree = new Tree();
        tree.loadTree();
        //tree.inorder();
        //tree.play();
        Animal animal = new Animal("Perro", "ladra", true);
        Animal animal2 = new Animal("gato", "maulla", true);
        Animal animal3 = new Animal("pajaro", "vuela", true);

        List<Animal> animalList = new List<>();
        animalList.addFront(new NodeList<>(null,null, animal));
        animalList.addFront(new NodeList<>(null,null, animal2));
        animalList.addFront(new NodeList<>(null,null, animal3));

        animalList.printList();

    }
}