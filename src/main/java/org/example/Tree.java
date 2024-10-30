package org.example;
import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;

public class Tree implements TreeInterface {
    private NodeTree base;

    private Contenedor<NodeTree> listStructure;

    public Tree() {
        listStructure = new Contenedor<NodeTree>();
    }

    public void play(){
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
        }

        // Pedir la característica distintiva del animal
        caracteristicaAnimal[0] = JOptionPane.showInputDialog(null, "Digite una característica distintiva del animal:",
                "Nueva característica", JOptionPane.QUESTION_MESSAGE);

        // Verificar si la característica es válida
        if (caracteristicaAnimal[0] == null || caracteristicaAnimal[0].trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una característica válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true; // Retornamos true si los datos fueron capturados correctamente
    }


    public void playRecursive(NodeTree root, int nivel){
        String[] nombreAnimal = new String[1];
        String[] caracteristicaAnimal = new String[1];
        // Mostramos un cuadro de diálogo con botones de opción
        int respuesta = makeQuestion("El animal que esta pensando : ", root.getData().preguntar());

        if (respuesta == JOptionPane.YES_OPTION) {
            if(root.getData().isAnimal()){
                showMessage("¡El animal ya existe!");
                System.out.println("¡El animal ya existe!");
                quieresJugarOtraVez();

            } else{
                playRecursive(root.getRigt(), nivel+1); // si es si y no se trata de un animal
            }

        } else { //SI LA RESPUESTA ES NO
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
                        NodeTree animalNodo = new NodeTree(animal, null, null, root, false);
                        root.setLeft(animalNodo); // Ingresar a la izquierda del nodo actual
                        inorder();
                        play();
                    }
                    //fin de captura de datos

                }//FIN DEL PRIMER CASO

                else {
                    //caso en que vengo de no, y estoy parado en un animal, por lo que significa que vengo de un camino en donde habia
                    //una caracteristica que el si tenia algo y tambien en el no tenia algo ya que ese algo es donde estoy justo ahora
                    //por lo que voy a sacar mi caracteristica para hacer un nodo y ponerme yo del lado del si para luego pedir los datos
                    //del nuevo animal y colocarlo del lado del no, justo como estoy yo antes de hacer todo esto
                    if (root.getParent().getData().getCaracteristica() != root.getData().getCaracteristica()) {
                        if (!root.getCaracteristicaAdded()){

                            System.out.println("Hay que hacer switch ya que vamos a ingresar debajo de un animal");

                            Animal carac_animal = new Animal("1", root.getData().getCaracteristica(), false, nivel);//va ocupar mi posicion
                            NodeTree carac_nodo = new NodeTree(carac_animal, null, null, root.getParent());

                            root.getParent().setLeft(carac_nodo); //padre de ballena en este momento que era la caract que no cumple ballena //la cambiamos por mi caracteristica
                            carac_nodo.setRigt(root); //como es mi caracteristica me voy a si
                            root.setParent(carac_nodo); //mi padre ahora va ser esa caracteristica mia
                            //decir que la caracteristica de root = por ejemplo ballena esta agregada
                            root.setCaracteristicaAdded(true);
                            root.getData().setNivel(nivel+1);//voy a estar 1 mas abajo de donde estoy actualmente

                            showMessage("¡No hay informacion de este animal vamos a agregarla! #2");

                            if (capturarDatosAnimal(nombreAnimal, caracteristicaAnimal)) {
                                // Mostrar la información capturada
                                showNewAnimalAdd(nombreAnimal[0],caracteristicaAnimal[0]);
                                //el mae que va la izquierda por que es no
                                Animal animal = new Animal(nombreAnimal[0], caracteristicaAnimal[0], true, nivel+1);
                                NodeTree nodeTreeAnimal = new NodeTree(animal, null, null, carac_nodo, false);
                                carac_nodo.setLeft(nodeTreeAnimal);//pongo de no al animal nuevo que no le he agregado la caracteristica
                                inorder();
                                play();
                            }

                    }
                        else {
                            //caso caracteristica agregada pero queda en la parte superior por lo que no se puede agregar doble caracteristica
                            //caso en que ponga a la izquierda de mi padre (yo como animal) a la caracteristica del nuevo, por lo que del lado del si
                            //de esa caracteristica estaria el nuevo animal y del lado del no estaria yo o mi nodo
                            showMessage("¡No hay informacion de este animal vamos a agregarla #4!");
                            if (capturarDatosAnimal(nombreAnimal, caracteristicaAnimal)) {
                                // Mostrar la información capturada
                                showNewAnimalAdd(nombreAnimal[0],caracteristicaAnimal[0]);
                                Animal animal = new Animal(nombreAnimal[0], caracteristicaAnimal[0], true, nivel+1);//creo nuevo animal
                                Animal animalCaracteristica = new Animal("",caracteristicaAnimal[0],false,nivel);//creo carac del nuevo animal

                                NodeTree parent = root.getParent(); //mi padre
                                NodeTree animalNodeCaracteTree = new NodeTree(animalCaracteristica,null,null,parent);//nodo carac del nuevo

                                parent.setLeft(animalNodeCaracteTree); //left de mi padre ahora es la nueva carac del nuevo animal
                                NodeTree animalObj = new NodeTree(animal,null,null, animalNodeCaracteTree, true);// nodo del nuevo animal
                                animalNodeCaracteTree.setRigt(animalObj);//en el si de la carac esta el nuevo animal
                                animalNodeCaracteTree.setLeft(root); //como no yo - ya que mi carac esta mas arriba y no se puede poner doble
                                root.getData().setNivel(nivel+1); //mi nivel baja

                                root.setParent(animalNodeCaracteTree);
                                //carac_nodo.setLeft(new Node(animal, null, null, carac_nodo));
                                inorder();
                                play();
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

                            NodeTree parent = root.getParent();
                            NodeTree animalNodeCaracteTree = new NodeTree(animalCaracteristica,null,null,parent);
                            parent.setRigt(animalNodeCaracteTree);
                            NodeTree animalObj = new NodeTree(animal,null,null, animalNodeCaracteTree, true);
                            animalNodeCaracteTree.setRigt(animalObj);
                            animalNodeCaracteTree.setLeft(root);
                            root.getData().setNivel(nivel+1);
                            root.setParent(animalNodeCaracteTree);
                            inorder();
                            play();
                        }

                    }
                }
            }
        }

    }


    public void loadTree(){
        Animal data = new Animal("1","ave",false, 1);
        base =  new NodeTree(data, null, null, null);

        Animal data1 = new Animal("","reptil",false,2);
        NodeTree reptil = new NodeTree(data1,null,null, base);
        base.setLeft(reptil);
        Animal data2 = new Animal("Lagarto","reptil",true, 3);
        NodeTree lagarto = new NodeTree(data2,null,null, reptil, true);
        reptil.setRigt(lagarto);
        //regresamos a base
        Animal data3 = new Animal("1","cacarea", false,2);
        NodeTree cacarea = new NodeTree(data3,null,null, base);
        base.setRigt(cacarea);
        Animal data4 = new Animal("aguila","caza", true,3);
        NodeTree aguila = new NodeTree(data4,null,null,cacarea, true);
        cacarea.setLeft(aguila);
        Animal data5 = new Animal("gallina","cacarea",true,3);
        NodeTree gallina = new NodeTree(data5,null,null,cacarea, true);
        cacarea.setRigt(gallina);
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
            inOrderRecursive(root.getRigt());
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
            preOrderRecursive(root.getRigt());
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
            posOrderRecursive(root.getRigt());
            System.out.println(root.getData().preguntar());
        }
    }

    @Override
    public Contenedor<Animal> convertTreeIntoList(){
        Contenedor<Animal> animalContenedor = new Contenedor<>();
        if (base != null) {
            convertTreeIntoListRecursive(base, animalContenedor);
        }
        System.out.println("Lista almacenada correctamente");
        return animalContenedor;
    }

    public void convertTreeIntoListRecursive(NodeTree root, Contenedor<Animal> animalContenedor){
        if (root != null) {
            convertTreeIntoListRecursive(root.getLeft(), animalContenedor);
            convertTreeIntoListRecursive(root.getRigt(), animalContenedor);
            if(root.getData().isAnimal()) {
                System.out.println("Agregadando de arbol a contenedor: " + root.getData().preguntar());
                listStructure.addEnd(root);
                animalContenedor.addEnd(root.getData());
            }
        }
    }

    @Override
    public Map<String, Contenedor<String>> convertTreeIntoHashMap(){
        Map<String, Contenedor<String>> hashMap = new HashMap<>();
        if (base != null) {
            convertTreeIntoHashMapRecursive(listStructure.getDummy().getNext(), hashMap);
        }
        System.out.println("HashMap almacenado correctamente");
        return hashMap;
    }

    public void convertTreeIntoHashMapRecursive(NodeList<NodeTree> node, Map<String, Contenedor<String>> animalContenedor){
        if (node != listStructure.getBack()) {
            NodeTree actual = node.getData();
            //convertTreeIntoHashMapRecursive(root.getLeft(), animalContenedor);
            animalContenedor.put(actual.getData().getNombre(), fillAnimalCharacteristics(actual));
            convertTreeIntoHashMapRecursive(node.getNext(), animalContenedor);
            //convertTreeIntoHashMapRecursive(root.getRigt(), animalContenedor);
        }
    }

    public Contenedor<String> fillAnimalCharacteristics(NodeTree node){
        Contenedor<String> listaCaracteristicas = new Contenedor<String>();
        return fillAnimalCharacteristicsRecursive(node, node.getParent(), listaCaracteristicas);

    }

    public Contenedor<String> fillAnimalCharacteristicsRecursive(NodeTree actual, NodeTree parent, Contenedor<String> listaCaracteristicas){
        if(parent!=null) {
            if (parent.getRigt() == actual) {
                listaCaracteristicas.addEnd(parent.getData().getCaracteristica());
                fillAnimalCharacteristicsRecursive(parent, parent.getParent(), listaCaracteristicas);
            } else {
                fillAnimalCharacteristicsRecursive(parent, parent.getParent(), listaCaracteristicas);
            }
        }
        return listaCaracteristicas;

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
            return printTreeFormatRecursive(root.getLeft()) + "   " + root.getData().preguntar() + "   " + printTreeFormatRecursive(root.getRigt());
        }
        return "NULL";
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
