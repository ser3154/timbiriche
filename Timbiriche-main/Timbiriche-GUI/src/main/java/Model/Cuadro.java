/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Serva
 */
public class Cuadro {
   private String jugador;

    /**
     * Constructor que inicializa el cuadro sin dueño.
     */
    public Cuadro() {
        this.jugador = "";
    }

    /**
     * Asigna un jugador ganador a este cuadro.
     * @param jugador Símbolo o identificador del jugador (por ejemplo "X" o "O").
     */
    public void asignarJugador(String jugador) {
        this.jugador = jugador;
    }

    /**
     * Devuelve el identificador del jugador que ganó este cuadro.
     * @return String del jugador ("X", "O" o "" si no ha sido asignado).
     */
    public String getJugador() {
        return jugador;
    }

    /**
     * Indica si el cuadro ya ha sido asignado a un jugador.
     * @return True si el cuadro tiene un dueño; False si sigue vacío.
     */
    public boolean estaCompleto() {
        return !jugador.isEmpty();
    }
}
