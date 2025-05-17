/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcLobby;

import com.mycompany.blackboard.modelo.Jugador;
import com.mycompany.timbirichenetwork.Cliente;
import com.mycompany.timbirichenetwork.EventoRed;
import com.google.gson.Gson;
import mvcJuego.MainJuego;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author joseq
 */
public class ControladorLobbyJuego {

    private final ModeloLobbyJuego modelo;
    private final VistaLobby       vista;
    private final JFrame           frame;
    private final Cliente          cliente;
    private final Gson             gson;
    private final boolean          soyHost;

    public ControladorLobbyJuego(ModeloLobbyJuego modelo,
                                 VistaLobby vista,
                                 Cliente cliente,
                                 Gson gson,
                                 boolean soyHost)
    {
        this.modelo  = modelo;
        this.vista   = vista;
        this.cliente = cliente;
        this.gson    = gson;
        this.soyHost = soyHost;

        // MVC wiring
        modelo.addObserver(vista);
        vista.setControlador(this);

        // Crear ventana
        frame = new JFrame("Lobby de Juego");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(vista);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /** Invocado cuando un jugador llega por red (JOIN). */
    public void onJugadorConectado(Jugador j) {
        modelo.addJugador(j);
        if (soyHost) {
            // Reenvía START con lista y tamaño actual
            try {
                String listJson = gson.toJson(modelo.getJugadores());
                cliente.send(EventoRed.start(listJson, modelo.getTamañoTablero()));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                    frame,
                    "Error enviando START: " + ex.getMessage(),
                    "Error de red",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    /**
     * Invocado al recibir el evento START desde red,
     * con la lista completa de jugadores y el tamaño acordado.
     */
    public void onStart(List<Jugador> jugadores, int tamaño) {
        // Reconstruye el modelo con la lista y tamaño recibidos
        modelo.clearJugadores();
        jugadores.forEach(modelo::addJugador);
        // Ahora el botón “Listo” se habilitará según listoParaIniciar()
    }

    /** Invocado al pulsar “Listo”: cierra Lobby y arranca la partida. */
    public void onContinuar() {
        frame.dispose();

        // Si soy host envío el primer turno
        if (soyHost) {
            try {
                cliente.send(EventoRed.turn(0));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                    frame,
                    "Error enviando TURN: " + ex.getMessage(),
                    "Error de red",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }

        // Arranca el juego en modo red/local
        MainJuego.mainCon(
            modelo.getJugadores(),
            modelo.getTamañoTablero(),
            cliente,
            gson
        );
    }
}
