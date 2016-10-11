package ru.euphoria.commons.vk;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import ru.euphoria.commons.json.JsonArray;
import ru.euphoria.commons.json.JsonObject;

/**
 * Converter json object to VK object.
 *
 * @since 1.1
 */
public class VKParser {

    /**
     * Returns root JSONArray from server response
     *
     * @param source standard VK server response
     */
    public static JsonArray responseArray(JsonObject source) {
        if (source.has("response")) {
            Object json = source.opt("response");
            if (json instanceof JsonObject) {
                // response is object: {response: {...}}
                source = (JsonObject) json;
            } else if (json instanceof JsonArray) {
                // response is array: {response: [...]}
                return ((JsonArray) json);
            }
        }

        // items in response: {response: {...
        // items: [...]
        // }}
        return source.optJsonArray("items");
    }

    @SuppressWarnings("unchecked")
    public static <E> ArrayList<E> parse(Class<E> aClass, JsonArray array) {
        if (array.length() == 0) {
            return null;
        }

        ArrayList<E> models = new ArrayList<>(array.length());
        try {
            Constructor<?> constructor = aClass.getConstructor(JsonObject.class);
            for (int i = 0; i < array.length(); i++) {
                models.add((E) constructor.newInstance(array.optJsonObject(i)));
            }
            return models;
        } catch (Exception e) {
            throw new IllegalArgumentException("Can't get constructor for json object");
        }
    }
}
