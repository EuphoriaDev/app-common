package ru.euphoria.commons.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

/**
 * @since 1.1
 */
public class Params extends TreeMap<String, String> {

    /**
     * Maps the specified key to the specified value.
     *
     * @param key   the param name
     * @param value the number param value
     */
    public Params put(String key, int value) {
        put(key, String.valueOf(value));
        return this;
    }

    /**
     * Maps the specified key to the specified value.
     *
     * @param key   the param name
     * @param value the number param value
     */
    public Params put(String key, long value) {
        put(key, String.valueOf(value));
        return this;
    }

    /**
     * Maps the specified key to the specified value.
     *
     * @param key   the param name
     * @param value the boolean param value
     */
    public Params put(String key, boolean value) {
        put(key, value ? "1" : "0");
        return this;
    }

    /**
     * Maps the specified key to the specified value.
     *
     * @param key   the param name
     * @param value the param value
     */
    public Params put(String key, Object value) {
        put(key, String.valueOf(value));
        return this;
    }

    /**
     * Concatenates this params and the specified url
     *
     * @param url the string to join
     */
    public String join(String url) {
        if (url.charAt(url.length() - 1) != '?') {
            return url.concat("?").concat(toString());
        } else {
            return url.concat(toString());
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();

        for (Map.Entry<String, String> entry : entrySet()) {
            buffer.append(entry.getKey());
            buffer.append("=");

            try {
                buffer.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            buffer.append("&");
        }

        return buffer.toString();
    }
}