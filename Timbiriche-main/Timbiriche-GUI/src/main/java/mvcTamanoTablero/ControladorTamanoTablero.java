/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcTamanoTablero;

import com.mycompany.blackboard.modelo.Jugador;
import com.mycompany.timbirichenetwork.Servidor;
import com.mycompany.timbirichenetwork.Cliente;
import com.mycompany.timbirichenetwork.EventoRed;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import mvcLobby.ControladorLobbyJuego;
import mvcLobby.ModeloLobbyJuego;
import mvcLobby.VistaLobby;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author joseq
 */
public class ControladorTamanoTablero {

    private final ModeloTamanoTablero modelo;
    private final VistaTamanoTablero vista;
    private final JFrame frame;
    private final Jugador miJugador;
    private boolean isHost = true;

    private Servidor servidor;
    private Cliente cliente;
    private ControladorLobbyJuego ctrlLobby;
    private final Gson gson = new Gson();

    private static final String HOST = "localhost";
    private static final int PUERTO = 8888;

    public ControladorTamanoTablero(ModeloTamanoTablero modelo,
            VistaTamanoTablero vista,
            Jugador miJugador) {
        this.modelo = modelo;
        this.vista = vista;
        this.miJugador = miJugador;

        modelo.addObserver(vista);
        vista.setControlador(this);

        frame = new JFrame("Seleccionar Tamaño");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(vista);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void onSizeSelected(int tamaño) {
        modelo.setTamaño(tamaño);
    }

    public void onServidorSelected() {
        isHost = true;
    }

    public void onUnirseSelected() {
        isHost = false;
    }

    public void onContinuar() {
        frame.dispose();
        try {
            if (isHost) {
                servidor = new Servidor(PUERTO);
                servidor.start();
            }
            cliente = new Cliente(HOST, PUERTO, this::handleLobbyEvent);
            cliente.connect();

            ModeloLobbyJuego ml = new ModeloLobbyJuego(modelo.getTamaño(), miJugador);
            VistaLobby vl = new VistaLobby();
            ctrlLobby = new ControladorLobbyJuego(ml, vl, cliente, gson, isHost);

            cliente.send(EventoRed.join(gson.toJson(miJugador)));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Error de red: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void handleLobbyEvent(EventoRed ev) {
        switch (ev.getTipo()) {
            case JOIN -> {
                Jugador j = gson.fromJson(ev.getJugadorJson(), Jugador.class);
                SwingUtilities.invokeLater(() -> ctrlLobby.onJugadorConectado(j));
            }
            case START -> {
                Type listType = new TypeToken<List<Jugador>>() {
                }.getType();
                List<Jugador> todos = gson.fromJson(ev.getJugadorJson(), listType);
                int t = ev.getTamañoTablero();
                SwingUtilities.invokeLater(() -> ctrlLobby.onStart(todos, t));
            }
            default -> {
            }
        }
    }
}
