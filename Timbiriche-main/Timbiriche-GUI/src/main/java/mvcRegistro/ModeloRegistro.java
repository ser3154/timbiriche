/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcRegistro;

import blackboard.IV;
import com.mycompany.blackboard.modelo.Jugador;
import java.util.*;

/**
 *
 * @author joseq
 */
public class ModeloRegistro {

    private String nombre;
    private String colorHex;
    private String rutaAvatar;
    private final List<IV<ModeloRegistro>> observers = new ArrayList<>();

    /**
     * Registra un observador
     */
    public void addObserver(IV<ModeloRegistro> obs) {
        observers.add(obs);
    }

    /**
     * Notifica a todos los observadores del cambio
     */
    private void notifyObservers() {
        for (IV<ModeloRegistro> obs : observers) {
            obs.update(this);
        }
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        notifyObservers();
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
        notifyObservers();
    }

    public void setRutaAvatar(String rutaAvatar) {
        this.rutaAvatar = rutaAvatar;
        notifyObservers();
    }

    public String getNombre() {
        return nombre;
    }

    public String getColorHex() {
        return colorHex;
    }

    public String getRutaAvatar() {
        return rutaAvatar;
    }

    /**
     * Crea el objeto Jugador con los datos actuales
     */
    public Jugador crearJugador() {
        return new Jugador(nombre, colorHex, rutaAvatar, false);
    }

}
