/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackboard.eventos;

import com.mycompany.blackboard.Evento;
import com.mycompany.blackboard.modelo.Jugador;
import java.util.List;

/**
 *
 * @author joseq
 */
public class EventoIniciarPartida extends Evento {

    private final List<Jugador> jugadores;
    private final int tamañoTablero;

    public EventoIniciarPartida(List<Jugador> jugadores, int tamañoTablero) {
        super("INICIAR_PARTIDA");
        this.jugadores = jugadores;
        this.tamañoTablero = tamañoTablero;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public int getTamañoTablero() {
        return tamañoTablero;
    }

}
