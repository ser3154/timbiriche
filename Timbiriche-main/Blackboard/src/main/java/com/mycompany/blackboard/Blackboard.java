/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.blackboard;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joseq
 */
public class Blackboard {

    private final List<KnowledgeSource> knowledgeSources;
    private final List<Evento> eventos;

    public Blackboard() {
        this.knowledgeSources = new ArrayList<>();
        this.eventos = new ArrayList<>();
    }

    public void registrarKnowledgeSource(KnowledgeSource ks) {
        knowledgeSources.add(ks);
    }

    public void publicarEvento(Evento evento) {
        eventos.add(evento);
        notificarKnowledgeSources(evento);
    }

    private void notificarKnowledgeSources(Evento evento) {
        for (KnowledgeSource ks : knowledgeSources) {
            ks.procesarEvento(evento, this);
        }
    }

    public List<Evento> getEventos() {
        return eventos;
    }
}
