package org.example.Logic.DataStructures;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.example.Logic.Animal;
import org.w3c.dom.Node;

import javax.swing.JOptionPane;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Tree implements TreeInterface {
    private NodeTree base;

    public Tree() {}
    public void play(){
        ListInterface<String> lista = new Contenedor<String>();
        if(isEmpty()){
            System.out.println("El arbol esta vacio ingrese un animal: ");
        }else{
            playRecursive(base, 1);
        }
    }

    public int makeQuestion(String pregunta, String dato){
        return JOptionPane.showConfirmDialog(null,
                pregunta + dato + "?",
                "Pregunta",
                JOptionPane.YES_NO_OPTION);
    }

    public int makeQuestion(String pregunta){
        return JOptionPane.showConfirmDialog(null,
                pregunta + "?",
                "Pregunta",
                JOptionPane.YES_NO_OPTION);
    }

    public void quieresJugarOtraVez(){
        if(makeQuestion("Quieres jugar de nuevo")==JOptionPane.YES_OPTION){
            play();
        }
    }

    public void showMessage(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showNewAnimalAdd(String animal, String caracteristica){
        JOptionPane.showMessageDialog(null, "Nuevo animal: " + animal + "\nCaracterística: " + caracteristica,
                "Nuevo animal añadido", JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean capturarDatosAnimal(String[] nombreAnimal, String[] caracteristicaAnimal) {
        // Pedir el nombre del animal
        nombreAnimal[0] = JOptionPane.showInputDialog(null, "Digite el nombre del animal:",
                "Nuevo animal", JOptionPane.QUESTION_MESSAGE);

        // Verificar si el nombre es válido
        if (nombreAnimal[0] == null || nombreAnimal[0].trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un nombre válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false; // Retornamos false si los datos no son válidos
        } else if (!nombreAnimal[0].matches("[a-zA-ZáéíóúÁÉÍÓÚÑñ ]+")) {  // Regex para solo letras y espacios
            JOptionPane.showMessageDialog(null, "El nombre solo debe contener letras y espacios.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Pedir la característica distintiva del animal
        caracteristicaAnimal[0] = JOptionPane.showInputDialog(null, "Digite una característica distintiva del animal:",
                "Nueva característica", JOptionPane.QUESTION_MESSAGE);

        // Verificar si la característica es válida
        if (caracteristicaAnimal[0] == null || caracteristicaAnimal[0].trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una característica válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }else if (!caracteristicaAnimal[0].matches("[a-zA-ZáéíóúÁÉÍÓÚÑñ ]+")) {  // Regex para solo letras y espacios
            JOptionPane.showMessageDialog(null, "La caracteristica del animal solo debe contener letras y espacios.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true; // Retornamos true si los datos fueron capturados correctamente
    }

    public void playRecursive(NodeTree root, int nivel){
        String[] nombreAnimal = new String[1];
        String[] caracteristicaAnimal = new String[1];
        // Mostramos un cuadro de diálogo con botones de opción
        int respuesta = makeQuestion("El animal que esta pensando : ", root.getData().preguntar());

        //SI LA RESPUESTA ES SI
        if (respuesta == JOptionPane.YES_OPTION) {
            if(root.getData().isAnimal()){
                showMessage("¡El animal ya existe!");
                System.out.println("¡El animal ya existe!");
                quieresJugarOtraVez();

            } else{
                playRecursive(root.getRight(), nivel+1); // si es si y no se trata de un animal
            }

        }

        //SI LA RESPUESTA ES NO
        if(respuesta == JOptionPane.NO_OPTION) {
            System.out.println("¡Vamos a seguir buscando!");

            if(root.getLeft()!=null) {

                playRecursive(root.getLeft(), nivel+1);

            }else {
                //caso de que estoy en una caracteristica y el lado izquierdo esta vacio, por lo cual si hay una caracteristica es que del lado
                //del si hay algo un animal, y si del aldo del no esta vacio entonces aprovecho y me coloco yo sin agregar ninguna caracteristica ni nada
                //solamente todo ese lugar
                if (!root.getData().isAnimal()) {

                    System.out.println("Estamos debajo de una caracteristica solamente ingresamos");
                    showMessage("¡No hay informacion de este animal vamos a agregarla #1!");
                    if (capturarDatosAnimal(nombreAnimal, caracteristicaAnimal)) {
                        // Mostrar la información capturada
                        showNewAnimalAdd(nombreAnimal[0],caracteristicaAnimal[0]);
                        // Crear un nuevo animal y nodo
                        Animal animal = new Animal(nombreAnimal[0], caracteristicaAnimal[0], true, nivel+1);

                        NodeTree animalNodo = new NodeTree(animal, null, null, false);
                        root.setLeft(animalNodo); // Ingresar a la izquierda del nodo actual
                        inorder();
                        play();
                    }else{
                        quieresJugarOtraVez();
                    }
                    //fin de captura de datos

                }//FIN DEL PRIMER CASO

                else {
                    //caso en que vengo de no, y estoy parado en un animal, por lo que significa que vengo de un camino en donde habia
                    //una caracteristica que el si tenia algo y tambien en el no tenia algo ya que ese algo es donde estoy justo ahora
                    //por lo que voy a sacar mi caracteristica para hacer un nodo y ponerme yo del lado del si para luego pedir los datos
                    //del nuevo animal y colocarlo del lado del no, justo como estoy yo antes de hacer todo esto
                    if (!Objects.equals(this.getPivote(root).getData().getCaracteristica(), root.getData().getCaracteristica())) {
                        if (!root.getCaracteristicaAdded()){

                            showMessage("¡No hay informacion de este animal vamos a agregarla! #2");

                            if (capturarDatosAnimal(nombreAnimal, caracteristicaAnimal)) {

                                Animal carac_animal = new Animal("1", root.getData().getCaracteristica(), false, nivel);//va ocupar mi posicion
                                NodeTree carac_nodo = new NodeTree(carac_animal, null, null);

                                this.getPivote(root).setLeft(carac_nodo); //padre de ballena en este momento que era la caract que no cumple ballena //la cambiamos por mi caracteristica
                                carac_nodo.setRight(root); //como es mi caracteristica me voy a si
                                //root.setParent(carac_nodo); //mi padre ahora va ser esa caracteristica mia
                                //decir que la caracteristica de root = por ejemplo ballena esta agregada
                                root.setCaracteristicaAdded(true);
                                root.getData().setNivel(nivel+1);//voy a estar 1 mas abajo de donde estoy actualmente

                                // Mostrar la información capturada
                                showNewAnimalAdd(nombreAnimal[0],caracteristicaAnimal[0]);
                                //el mae que va la izquierda por que es no
                                Animal animal = new Animal(nombreAnimal[0], caracteristicaAnimal[0], true, nivel+1);
                                NodeTree nodeTreeAnimal = new NodeTree(animal, null, null, false);
                                carac_nodo.setLeft(nodeTreeAnimal);//pongo de no al animal nuevo que no le he agregado la caracteristica
                                inorder();
                                play();
                            }else{
                                quieresJugarOtraVez();
                            }

                    }
                        else {
                            //caso caracteristica agregada pero queda en la parte superior por lo que no se puede agregar doble caracteristica
                            //caso en que pongo a la izquierda de mi padre (yo como animal) a la caracteristica del nuevo, por lo que del lado del si
                            //de esa caracteristica estaria el nuevo animal y del lado del no estaria yo o mi nodo
                            showMessage("¡No hay informacion de este animal vamos a agregarla #4!");
                            if (capturarDatosAnimal(nombreAnimal, caracteristicaAnimal)) {
                                // Mostrar la información capturada
                                showNewAnimalAdd(nombreAnimal[0],caracteristicaAnimal[0]);
                                Animal animal = new Animal(nombreAnimal[0], caracteristicaAnimal[0], true, nivel+1);//creo nuevo animal
                                Animal animalCaracteristica = new Animal("",caracteristicaAnimal[0],false,nivel);//creo carac del nuevo animal

                                //NodeTree parent = root.getParent(); //mi padre
                                NodeTree animalNodeCaracteTree = new NodeTree(animalCaracteristica,null,null);//nodo carac del nuevo

                                this.getPivote(root).setLeft(animalNodeCaracteTree); //left de mi padre ahora es la nueva carac del nuevo animal
                                NodeTree animalObj = new NodeTree(animal,null,null, true);// nodo del nuevo animal
                                animalNodeCaracteTree.setRight(animalObj);//en el si de la carac esta el nuevo animal
                                animalNodeCaracteTree.setLeft(root); //como no yo - ya que mi carac esta mas arriba y no se puede poner doble
                                root.getData().setNivel(nivel+1); //mi nivel baja

                                //root.setParent(animalNodeCaracteTree);
                                //carac_nodo.setLeft(new Node(animal, null, null, carac_nodo));
                                inorder();
                                play();
                            }else{
                                quieresJugarOtraVez();
                            }

                        }
                   }
                    else {
                        //caso de que mi caracteristica ya esta agregada y es mi padre - esta justo arriba
                        //por lo que a diferencia de cuando mi caracteristica esta agregado y esta mucha mas arriba de mi - osea no es mi padre
                        //lo que hago es que ponga que el right de mi padre va a ser la caracteristica del nuevo animal, (en el caso anterior era ala left en lugar de right)
                        //luego siguiente con el algoritmo actual: al nuevo animal lo ponio a la derecha o el lado del si de ese nodo caracteristica y yo
                        //me coloco del lado del nodo simplemente (ese paso si es igual que en el caso anterior o 4 jaja)

                        showMessage("¡No hay informacion de este animal vamos a agregarla #3!");

                        if (capturarDatosAnimal(nombreAnimal, caracteristicaAnimal)) {
                            // Mostrar la información capturada
                            showNewAnimalAdd(nombreAnimal[0],caracteristicaAnimal[0]);
                            Animal animal = new Animal(nombreAnimal[0], caracteristicaAnimal[0], true, nivel+1);
                            Animal animalCaracteristica = new Animal("",caracteristicaAnimal[0],false, nivel);

                            //NodeTree parent = root.getParent();
                            NodeTree animalNodeCaracteTree = new NodeTree(animalCaracteristica,null,null);
                            this.getPivote(root).setRight(animalNodeCaracteTree);
                            NodeTree animalObj = new NodeTree(animal,null,null, true);
                            animalNodeCaracteTree.setRight(animalObj);
                            animalNodeCaracteTree.setLeft(root);
                            root.getData().setNivel(nivel+1);
                            //root.setParent(animalNodeCaracteTree);
                            inorder();
                            play();
                        }else{
                            quieresJugarOtraVez();
                        }

                    }
                }
            }
        }

    }


    @Override
    public void inorder() {
        if (base != null) {
            inOrderRecursive(base);
        }
        System.out.println("Recorrido inorder terminado");
    }

    private void inOrderRecursive(NodeTree root) {
        if (root != null) {
            inOrderRecursive(root.getLeft());
            System.out.println(root.getData().preguntar());
            inOrderRecursive(root.getRight());
        }
    }

    @Override
    public void preorder() {
        if (base != null) {
            preOrderRecursive(base);
        }
        System.out.println("Recorrido preorder terminado");
    }

    private void preOrderRecursive(NodeTree root) {
        if (root != null) {
            System.out.println(root.getData().preguntar());
            preOrderRecursive(root.getLeft());
            preOrderRecursive(root.getRight());
        }
    }

    @Override
    public void posorder() {
        if (base != null) {
            posOrderRecursive(base);
        }
        System.out.println("Recorrido posorder terminado");
    }

    private void posOrderRecursive(NodeTree root) {
        if (root != null) {
            posOrderRecursive(root.getLeft());
            posOrderRecursive(root.getRight());
            System.out.println(root.getData().preguntar());
        }
    }

    @Override
    public Contenedor<Animal> convertTreeIntoList(){
        Contenedor<Animal> animalContenedor = new Contenedor<>();
        ListInterface<String> stackCaracteristicas = new Stack<>();
        stackCaracteristicas.getStreamListCaracteristicas();
        if (base != null) {
            convertTreeIntoListRecursive(base, animalContenedor, stackCaracteristicas);
        }
        System.out.println("Lista almacenada correctamente");
        return animalContenedor;
    }

    public void convertTreeIntoListRecursive(NodeTree root, ListInterface<Animal> animalContenedor, ListInterface<String> stackCaracteristicas){
        if (root != null) {
            //Left
            convertTreeIntoListRecursive(root.getLeft(), animalContenedor,stackCaracteristicas);
            //Right
            if(root.getRight()!=null){  stackCaracteristicas.addFront(root.getData().getCaracteristica());}
            convertTreeIntoListRecursive(root.getRight(), animalContenedor,stackCaracteristicas);

            if(root.getData().isAnimal()) {
                System.out.println("Agregadando de arbol a contenedor: " + root.getData().preguntar());
                System.out.println("Con las caracteristicas");
                Animal aux = root.getData();
                Animal nuevo = new Animal(aux.getNombre(),aux.getCaracteristica(),aux.isAnimal(),aux.getNivel());
                nuevo.setearCaracteristicas(stackCaracteristicas);
                animalContenedor.addEnd(nuevo);
            } else if (stackCaracteristicas.contains(root.getData().getCaracteristica())) {
                stackCaracteristicas.pop();
            }
        }
    }

    @Override
    public Map<String, ListInterface<String>> convertTreeIntoHashMap(ListInterface<Animal> contenedorAnimales){
        Map<String, ListInterface<String>> hashMap = new HashMap<>();
        if (base != null) {
            NodeList<Animal> aux = contenedorAnimales.getDummy().getNext();
            while (aux!=contenedorAnimales.getBack()) {
                String key = aux.getData().getNombre().toLowerCase();
                key = this.quitarTildes(key);
                System.out.println("Key: " + key);
                hashMap.put(key, aux.getData().getListaCaracteristicas());
                aux = aux.getNext();
            }
        }
        System.out.println("HashMap almacenado correctamente");
        return hashMap;
    }

    public NodeTree getPivote(NodeTree node){
        if(base!=null){
            return getPivoteRecursive(base, node);
        }
        return null;
    }

    public NodeTree searchPivote(NodeTree node, NodeTree target){
        if(node.hasLeft()){
            if(node.getLeft().getData().equals(target.getData()))  {
                return node;
            }

        }
        if(node.hasRight()){
            if(node.getRight().getData().equals(target.getData())){
                return node;
            }
        }

        return null;
    }

    public NodeTree getPivoteRecursive(NodeTree actual, NodeTree target){
        if(actual!=null){
            NodeTree rleft;
            NodeTree rright;
            NodeTree resul = searchPivote(actual, target);
            if (resul == null) {
                rleft = getPivoteRecursive(actual.getLeft(), target);
                rright = getPivoteRecursive(actual.getRight(), target);
            } else {
                return resul;
            }
            if(rleft==null && rright==null) {
                return null;
            }else{
                if(rleft!=null){ //vemos si es el left
                    return rleft;
                }else{ //es el derecho
                    return rright;
                }
            }//else de que si encontro algo
        }   return null;


    }


    @Override
    public void printTreeFormat(){
        if (base != null) {
            System.out.println(printTreeFormatRecursive(base));
        }
        System.out.println("Recorrido tree format terminado");
    }

    private String printTreeFormatRecursive(NodeTree root){
        if (root != null) {
            return printTreeFormatRecursive(root.getLeft()) + "   " + root.getData().preguntar() + "   " + printTreeFormatRecursive(root.getRight());
        }
        return "NULL";
    }

    @Override
    public boolean loadTree(String path){
        GsonBuilder gsonBuilder = new GsonBuilder();

        // Registrar el adaptador para ListInterface<String> usando ContenedorAdapter
        //gsonBuilder.registerTypeAdapter(new TypeToken<ListInterface<String>>(){}.getType(), new ContenedorAdapter<String>());

        Gson gson = gsonBuilder.create(); // Crea el objeto Gson con el registrador

        try (FileReader reader = new FileReader(path)) {
            // Convertir el JSON en el nodo raíz del árbol
            base = gson.fromJson(reader, NodeTree.class);
            this.showMessage("El arbol se a cargado desde " + path);
            return true;
        } catch (IOException | JsonSyntaxException e) {
            showMessage("No se encontró el archivo");
            e.printStackTrace();
            return false;
        }
    }

    // Método para guardar el árbol como JSON usando Gson

    @Override
    public void guardarArbol(String path) {
        // Crear un GsonBuilder y registrar el adaptador para ListInterface
        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();

        // Registrar el adaptador para ListInterface<String> usando ContenedorAdapter
        //gsonBuilder.registerTypeAdapter(new TypeToken<ListInterface<String>>(){}.getType(), new ContenedorAdapter<String>());
        // Configura el GsonBuilder para serializar solo los campos con @Expose
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();

        // Crear el objeto Gson con el adaptador registrado
        Gson gson = gsonBuilder.create();

        try (FileWriter writer = new FileWriter(path)) {
            // Serializamos el nodo base (raíz) y sus nodos hijos automáticamente
            gson.toJson(base, writer);
            System.out.println("El árbol se ha guardado en " + path);
            this.showMessage("El árbol se ha guardado en " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String quitarTildes(String texto) {
        // Normalizar el texto para separar caracteres con tildes en base + tilde
        String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        // Eliminar únicamente las tildes de las vocales
        textoNormalizado = textoNormalizado.replaceAll("[\\p{M}&&[\\u0301]]", "");
        return textoNormalizado;
    }
    @Override
    public void cleanTree() {
        base = null;
    }

    @Override
    public boolean isEmpty() {
        return base == null;
    }
}
