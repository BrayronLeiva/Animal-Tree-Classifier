package org.example.Data;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.example.DataStructures.Contenedor;
import org.example.DataStructures.NodeList;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContenedorAdapter<T extends Serializable> extends TypeAdapter<Contenedor<T>> {

    @Override
    public void write(JsonWriter out, Contenedor<T> contenedor) throws IOException {
        out.beginArray();
        NodeList<T> currentNode = contenedor.getDummy().getNext();  // Comienza después del dummy

        while (currentNode != null && currentNode.getData() != null) {
            out.value(currentNode.getData().toString());
            currentNode = currentNode.getNext();
        }

        out.endArray();
    }

    @Override
    public Contenedor<T> read(JsonReader in) throws IOException {
        Contenedor<T> contenedor = new Contenedor<>();
        List<T> elements = new ArrayList<>();

        in.beginArray();
        while (in.hasNext()) {
            // Aquí debes realizar el casting a `T` si los datos en el JSON son del tipo adecuado
            T item = (T) in.nextString();
            elements.add(item);
        }
        in.endArray();

        // Agregar todos los elementos a la lista doblemente enlazada en `Contenedor`
        for (T element : elements) {
            contenedor.addEnd(element);
        }
        return contenedor;
    }
}
