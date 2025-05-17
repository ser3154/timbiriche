/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.blackboard.modelo.Jugador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author joseq
 */
public class ProfileStorage {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path BASE_DIR = Paths.get(System.getProperty("user.home"), ".timbiriche");
    private static final Path PROFILE_FILE = BASE_DIR.resolve("profile.json");

    /**
     * Guarda el Jugador en disco (crea .timbiriche si hace falta).
     */
    public static void saveProfile(Jugador j) throws IOException {
        if (!Files.exists(BASE_DIR)) {
            Files.createDirectory(BASE_DIR);
        }
        String json = GSON.toJson(j);
        Files.writeString(PROFILE_FILE, json);
    }

    /**
     * Carga el Jugador de disco, o null si no existe o falla.
     */
    public static Jugador loadProfile() {
        try {
            if (!Files.exists(PROFILE_FILE)) {
                return null;
            }
            String json = Files.readString(PROFILE_FILE);
            return GSON.fromJson(json, Jugador.class);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
