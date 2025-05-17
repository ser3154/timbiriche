/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackboard;

/**
 *
 * @author joseq
 */
public class BlackboardBridge {

    private static Blackboard instanciaGlobal;

    public static void setBlackboard(Blackboard blackboard) {
        BlackboardBridge.instanciaGlobal = blackboard;
    }

    public static void recibirEventoDesdeRed(Evento evento) {
        if (instanciaGlobal != null) {
            instanciaGlobal.publicarEvento(evento);
        } else {
            System.err.println("Blackboard aún no ha sido inicializado.");
        }
    }

}
