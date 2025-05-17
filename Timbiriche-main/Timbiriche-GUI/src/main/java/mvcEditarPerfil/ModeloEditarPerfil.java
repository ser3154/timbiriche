/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcEditarPerfil;

import com.mycompany.blackboard.modelo.Jugador;
import blackboard.IV;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joseq
 */
public class ModeloEditarPerfil {

   private Jugador jugadorOriginal;
    private final List<IV<ModeloEditarPerfil>> listeners = new ArrayList<>();

    public ModeloEditarPerfil(Jugador jugadorOriginal) {
        this.jugadorOriginal = jugadorOriginal;
    }

    /** Suscribe un listener para cambios */
    public void addListener(IV<ModeloEditarPerfil> l) {
        listeners.add(l);
    }

    /** Notifica a todos los listeners */
    private void notifyListeners() {
        for (IV<ModeloEditarPerfil> l : listeners) {
            l.update(this);
        }
    }

    /** Obtiene el Jugador a editar */
    public Jugador getJugadorOriginal() {
        return jugadorOriginal;
    }

    /** Cambia el nombre y notifica */
    public void setNombre(String nombre) {
        jugadorOriginal.setNombre(nombre);
        notifyListeners();
    }

    /** Cambia la ruta de avatar y notifica */
    public void setRutaAvatar(String rutaAvatar) {
        jugadorOriginal.setRutaAvatar(rutaAvatar);
        notifyListeners();
    }
}
