/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcLobby;

import blackboard.IV;
import com.mycompany.blackboard.modelo.Jugador;

import java.util.*;

/**
 *
 * @author joseq
 */
public class ModeloLobbyJuego {

    private static final String[] AVATARES = {
        "GATO.png", "LEIA.png", "PINGUINO.png", "RANA.png", "ROBOCOB.png"
    };

    private final List<Jugador> jugadores = new ArrayList<>();
    private final int tamañoTablero;
    private final List<IV<ModeloLobbyJuego>> observers = new ArrayList<>();

    public ModeloLobbyJuego(int tamañoTablero, Jugador host) {
        this.tamañoTablero = tamañoTablero;
        this.jugadores.add(host);
    }

    public void addObserver(IV<ModeloLobbyJuego> obs) {
        observers.add(obs);
    }

    private void notifyObservers() {
        for (IV<ModeloLobbyJuego> o : observers) {
            o.update(this);
        }
    }

    public void addJugador(Jugador j) {
        if (!jugadores.contains(j)) {
            jugadores.add(j);
            notifyObservers();
        }
    }

    public void clearJugadores() {
        jugadores.clear();
        notifyObservers();
    }

    public List<Jugador> getJugadores() {
        return Collections.unmodifiableList(jugadores);
    }

    public int getTamañoTablero() {
        return tamañoTablero;
    }

    public boolean listoParaIniciar() {
        int n = jugadores.size();
        return n >= 2 && n <= 4;
    }

    /**
     * Cambia el avatar del jugador en la posición idx, avanzando o
     * retrocediendo delta (+1 o -1) en el array AVATARES. Sustituye la
     * instancia de Jugador por una nueva con el avatar cambiado.
     */
    public void cycleAvatar(int idx, int delta) {
        Jugador old = jugadores.get(idx);
        String currentPath = old.getRutaAvatar();    // o getAvatarPath() según tu clase Jugador
        // buscar su posición en el array
        int pos = 0;
        for (int i = 0; i < AVATARES.length; i++) {
            if (AVATARES[i].equals(currentPath)) {
                pos = i;
                break;
            }
        }
        // calcular nueva posición
        int newPos = (pos + delta + AVATARES.length) % AVATARES.length;
        String newAvatar = AVATARES[newPos];

        // crear un Jugador nuevo con los mismos datos, salvo la ruta del avatar
        Jugador nuevo = new Jugador(
                old.getNombre(),
                old.getColorHex(),
                newAvatar,
                old.isListo()
        );
        // reemplazar en la lista
        jugadores.set(idx, nuevo);
        notifyObservers();
    }

}
