/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackboard.ks;

import com.mycompany.blackboard.Blackboard;
import com.mycompany.blackboard.Evento;
import com.mycompany.blackboard.eventos.EventoIniciarPartida;
import com.mycompany.blackboard.eventos.EventoJugadorListo;
import com.mycompany.blackboard.modelo.Jugador;
import com.mycompany.blackboard.KnowledgeSource;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joseq
 */
public class KSEvaluarJugadoresListos extends KnowledgeSource {

    private final List<Jugador> jugadoresListos = new ArrayList<>();
    private int tamañoTablero = 10; // Puedes modificarlo desde fuera si se necesita

    @Override
    public void procesarEvento(Evento evento, Blackboard blackboard) {
        if (evento instanceof EventoJugadorListo ev) {
            Jugador jugador = ev.getJugador();

            if (!jugadoresListos.contains(jugador)) {
                jugadoresListos.add(jugador);
                System.out.println("Jugador agregado: " + jugador.getNombre());
            }

            System.out.println("Jugadores listos: " + jugadoresListos.size());

            if (jugadoresListos.size() >= 2) {
                EventoIniciarPartida eventoInicio = new EventoIniciarPartida(jugadoresListos, tamañoTablero);
                blackboard.publicarEvento(eventoInicio);
            }
        }
    }

    public void setTamañoTablero(int tamañoTablero) {
        this.tamañoTablero = tamañoTablero;
    }

    public List<Jugador> getJugadoresListos() {
        return jugadoresListos;
    }
}
