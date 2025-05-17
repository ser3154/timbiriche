package com.mycompany.timbirichenetwork;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Protocolo {
    private static final Gson GSON = new GsonBuilder().create();

    /** Serializa un EventoRed a JSON (una línea). */
    public static String encode(EventoRed ev) {
        return GSON.toJson(ev);
    }

    /** Deserializa JSON a un EventoRed. */
    public static EventoRed decode(String json) {
        return GSON.fromJson(json, EventoRed.class);
    }
}
