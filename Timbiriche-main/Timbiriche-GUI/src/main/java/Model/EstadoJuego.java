/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * Clase que representa el estado actual del juego
 * Sirve como objeto de transferencia entre componentes y para comunicación en red
 *
 * @author Serva
 */
public class EstadoJuego {
    private String jugadorActual;
    private int puntosX;
    private int puntosO;
    
    public EstadoJuego(String jugadorActual, int puntosX, int puntosO) {
        this.jugadorActual = jugadorActual;
        this.puntosX = puntosX;
        this.puntosO = puntosO;
    }
    
    public String getJugadorActual() {
        return jugadorActual;
    }
    
    public int getPuntosX() {
        return puntosX;
    }
    
    public int getPuntosO() {
        return puntosO;
    }
    
    @Override
    public String toString() {
        return "EstadoJuego{" + 
                "jugadorActual=" + jugadorActual + 
                ", puntosX=" + puntosX + 
                ", puntosO=" + puntosO + 
                '}';
    }
}
