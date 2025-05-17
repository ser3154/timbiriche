/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackboard.ks;

import com.mycompany.blackboard.Blackboard;
import com.mycompany.blackboard.Evento;
import com.mycompany.blackboard.eventos.EventoJugadorListo;
import com.mycompany.blackboard.modelo.Jugador;
import com.mycompany.blackboard.KnowledgeSource;

/**
 *
 * @author joseq
 */
 public class KSEscucharJugadorListo extends KnowledgeSource {

    @Override
    public void procesarEvento(Evento evento, Blackboard blackboard) {
        if (evento instanceof EventoJugadorListo ev) {
            Jugador jugador = ev.getJugador();
            System.out.println("Jugador listo: " + jugador.getNombre());
            // Aquí puedes agregar cualquier lógica adicional si se requiere.
        }
    }
}
