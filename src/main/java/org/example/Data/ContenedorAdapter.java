package org.example.Data;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.example.DataStructures.Contenedor;
import org.example.DataStructures.ListInterface;
import org.example.DataStructures.NodeList;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Type;

public class ContenedorAdapter<T extends Serializable> implements JsonSerializer<ListInterface<T>>, JsonDeserializer<ListInterface<T>> {

    @Override
    public JsonElement serialize(ListInterface<T> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonArray jsonArray = new JsonArray();
        NodeList<T> current = src.getDummy().getNext();

        // Serializamos todos los elementos en la lista hasta `back`
        while (current != null && current != src.getBack()) {
            jsonArray.add(context. serialize(current.getData()));
            current = current.getNext();
        }

        return jsonArray;
    }

    @Override
    public ListInterface<T> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ListInterface<T> contenedor = new Contenedor<>();

        JsonArray jsonArray = json.getAsJsonArray();
        for (JsonElement element : jsonArray) {
            T item = context.deserialize(element, ((Class<T>) ((ParameterizedType) typeOfT).getActualTypeArguments()[0]));
            contenedor.addEnd(item);
        }

        return contenedor;
    }
}