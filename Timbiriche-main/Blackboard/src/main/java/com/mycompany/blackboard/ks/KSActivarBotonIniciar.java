/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackboard.ks;

import com.mycompany.blackboard.Blackboard;
import com.mycompany.blackboard.KnowledgeSource;
import com.mycompany.blackboard.eventos.EventoJugadorListo;
import com.mycompany.blackboard.modelo.Jugador;
import com.mycompany.blackboard.Evento;
/**
 *
 * @author joseq
 */
public class KSActivarBotonIniciar extends KnowledgeSource {
    
   private int jugadoresMinimos;

    public KSActivarBotonIniciar(int jugadoresMinimos) {
        this.jugadoresMinimos = jugadoresMinimos;
    }

    @Override
    public void procesarEvento(Evento evento, Blackboard blackboard) {
        if (evento instanceof EventoJugadorListo ev) {
            System.out.println("[KSActivarBotonIniciar] Jugador listo: " + ev.getJugador().getNombre());
            // Aquí podras emitir un nuevo evento si el total de listos >= jugadoresMinimos
        }
    }
    
}
