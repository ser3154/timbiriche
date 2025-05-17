/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuego;

import com.mycompany.timbirichenetwork.Cliente;
import com.mycompany.timbirichenetwork.EventoRed;
import com.google.gson.Gson;

import javax.swing.*;
import java.io.IOException;

/**
 *
 * @author joseq
 */
public class ControladorJuego {

    private final ModeloJuego modelo;
    private final VistaJuego  vista;
    private final Cliente     cliente;
    private final Gson        gson;

    public ControladorJuego(ModeloJuego modelo,
                            VistaJuego vista,
                            Cliente cliente,
                            Gson gson)
    {
        this.modelo  = modelo;
        this.vista   = vista;
        this.cliente = cliente;
        this.gson    = gson;

        modelo.addObserver(vista);
        vista.setControlador(this);

        // Escucho MOVE y TURN
        cliente.setListener(ev -> {
            switch (ev.getTipo()) {
                case MOVE -> SwingUtilities.invokeLater(() ->
                    pintarLinea(ev.getY1(), ev.getX1(), ev.getY2(), ev.getX2())
                );
                case TURN -> SwingUtilities.invokeLater(() ->
                    modelo.setTurno(ev.getTurnoIndex())
                );
                default -> {}
            }
        });
    }

    /**
     * Se llama al hacer clic en una arista.
     */
    public void pintarLinea(int y1, int x1, int y2, int x2) {
        modelo.claimEdge(y1, x1, y2, x2);

        try {
            cliente.send(EventoRed.move(y1, x1, y2, x2));
            cliente.send(EventoRed.turn(modelo.getTurnoIndex()));
        } catch (IOException ex) {
            JFrame f = (JFrame) SwingUtilities.getWindowAncestor(vista);
            JOptionPane.showMessageDialog(
                    f,
                    "Error enviando jugada: " + ex.getMessage(),
                    "Error de red",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
