/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuego;

import blackboard.IV;
import com.mycompany.blackboard.modelo.Jugador;

import java.util.*;

/**
 *
 * @author joseq
 */
public class ModeloJuego {

    private final List<Jugador> jugadores;
    private final Map<Jugador, Integer> puntuaciones;
    private final int tamaño;
    private int turnoIdx = 0;

    // Observadores MVC
    private final List<IV<ModeloJuego>> observers = new ArrayList<>();

    // Estado de aristas y cuadros (como antes)...
    private final boolean[][] hEdgesTaken;
    private final boolean[][] vEdgesTaken;
    private final String[][] hEdgesOwnerColor;
    private final String[][] vEdgesOwnerColor;
    private final boolean[][] squaresClaimed;
    private final String[][] squaresOwnerColor;

    public ModeloJuego(List<Jugador> jugadores, int tamaño) {
        this.jugadores = new ArrayList<>(jugadores);
        this.tamaño = tamaño;
        this.puntuaciones = new HashMap<>();
        for (Jugador j : jugadores) {
            puntuaciones.put(j, 0);
        }

        hEdgesTaken = new boolean[tamaño][tamaño - 1];
        vEdgesTaken = new boolean[tamaño - 1][tamaño];
        hEdgesOwnerColor = new String[tamaño][tamaño - 1];
        vEdgesOwnerColor = new String[tamaño - 1][tamaño];
        squaresClaimed = new boolean[tamaño - 1][tamaño - 1];
        squaresOwnerColor = new String[tamaño - 1][tamaño - 1];
    }

    public void addObserver(IV<ModeloJuego> obs) {
        observers.add(obs);
    }

    private void notifyObservers() {
        observers.forEach(o -> o.update(this));
    }

    public Jugador getJugadorActual() {
        return jugadores.get(turnoIdx);
    }

    public int getPuntuacion(Jugador j) {
        return puntuaciones.getOrDefault(j, 0);
    }

    public int getTurnoIndex() {
        return turnoIdx;
    }

    /**
     * Permite fijar el turno (p.ej. al recibir TURN por red)
     */
    public void setTurno(int idx) {
        this.turnoIdx = idx;
        notifyObservers();
    }

    public boolean isEdgeTaken(int y1, int x1, int y2, int x2) {
        // … tu implementación …
        if (y1 == y2) {
            return hEdgesTaken[y1][Math.min(x1, x2)];
        } else {
            return vEdgesTaken[Math.min(y1, y2)][x1];
        }
    }

    public String getEdgeOwnerColorHex(int y1, int x1, int y2, int x2) {
        if (y1 == y2) {
            return hEdgesOwnerColor[y1][Math.min(x1, x2)];
        } else {
            return vEdgesOwnerColor[Math.min(y1, y2)][x1];
        }
    }

    public boolean isSquareClaimed(int y, int x) {
        return squaresClaimed[y][x];
    }

    public String getSquareOwnerColorHex(int y, int x) {
        return squaresOwnerColor[y][x];
    }

    public void claimEdge(int y1, int x1, int y2, int x2) {
        Jugador actual = getJugadorActual();
        String color = actual.getColorHex();
        // … marca arista y cuadros, actualiza puntuaciones …
        // tras procesar, si no completó cuadro:
        // turnoIdx = (turnoIdx+1) % jugadores.size();
        notifyObservers();
    }

    public boolean isGameOver() {
        int total = (tamaño - 1) * (tamaño - 1);
        int suma = puntuaciones.values().stream().mapToInt(i -> i).sum();
        return suma >= total;
    }

    public Jugador getGanador() {
        return puntuaciones.entrySet()
                .stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .get().getKey();
    }
}
