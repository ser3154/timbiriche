/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcTamanoTablero;

import blackboard.IV;
import com.mycompany.blackboard.modelo.Jugador;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joseq
 */
public class ModeloTamanoTablero {
    
   private int tamaño = 0;
    private final Jugador miJugador;
    private final List<IV<ModeloTamanoTablero>> observers = new ArrayList<>();

    public ModeloTamanoTablero(Jugador miJugador) {
        this.miJugador = miJugador;
    }

    public void addObserver(IV<ModeloTamanoTablero> obs) {
        observers.add(obs);
    }

    private void notifyObservers() {
        observers.forEach(o -> o.update(this));
    }

    /** Fija el tamaño (10, 20 o 30) y notifica */
    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
        notifyObservers();
    }

    public int getTamaño() {
        return tamaño;
    }

    public Jugador getMiJugador() {
        return miJugador;
    }

    /** Permite activar “Continuar” sólo tras seleccionar un tamaño válido */
    public boolean isSizeSelected() {
        return tamaño > 0;
    }
    
}
