/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuego;

import blackboard.IV;
import com.mycompany.blackboard.modelo.Jugador;



import javax.swing.*;
import java.awt.*;
import java.util.List;
import mvcJuego.Componentes.DotPanel;
import mvcJuego.Componentes.EdgeButton;
import mvcJuego.Componentes.SquarePanel;

/**
 *
 * @author joseq
 */
public class VistaJuego extends JPanel implements IV<ModeloJuego> {

    private ControladorJuego controlador;
    private final ModeloJuego modelo;
    private final List<Jugador> jugadores;
    private final int tamaño;

    private final JLabel lblTurno = new JLabel("", SwingConstants.CENTER);
    private final JPanel boardPanel;
    private final JPanel pnlPuntos = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
    private final JLabel lblGanador = new JLabel("", SwingConstants.CENTER);

    private final EdgeButton[][] hEdges;
    private final EdgeButton[][] vEdges;
    private final SquarePanel[][] squares;

    public VistaJuego(ModeloJuego modelo, List<Jugador> jugadores, int tamaño) {
        super(new BorderLayout(5, 5));
        this.modelo = modelo;
        this.jugadores = jugadores;
        this.tamaño = tamaño;

        // Panel de puntuaciones
        for (Jugador j : jugadores) {
            JLabel lp = new JLabel(j.getNombre() + ": 0");
            lp.setName("puntos_" + j.getNombre());
            pnlPuntos.add(lp);
        }

        // Construcción del tablero
        int grid = tamaño * 2 - 1;
        boardPanel = new JPanel(new GridLayout(grid, grid, 2, 2));
        hEdges = new EdgeButton[tamaño][tamaño - 1];
        vEdges = new EdgeButton[tamaño - 1][tamaño];
        squares = new SquarePanel[tamaño - 1][tamaño - 1];

        for (int r = 0; r < grid; r++) {
            for (int c = 0; c < grid; c++) {
                if (r % 2 == 0 && c % 2 == 0) {
                    boardPanel.add(new DotPanel());
                } else if (r % 2 == 0) {
                    int y = r / 2, x = c / 2;
                    EdgeButton btn = new EdgeButton(y, x, y, x + 1);
                    hEdges[y][x] = btn;
                    boardPanel.add(btn);
                } else if (c % 2 == 0) {
                    int y = r / 2, x = c / 2;
                    EdgeButton btn = new EdgeButton(y, x, y + 1, x);
                    vEdges[y][x] = btn;
                    boardPanel.add(btn);
                } else {
                    int y = r / 2, x = c / 2;
                    SquarePanel sq = new SquarePanel();
                    squares[y][x] = sq;
                    boardPanel.add(sq);
                }
            }
        }

        // Montaje de la vista
        add(lblTurno, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(pnlPuntos, BorderLayout.SOUTH);
        add(lblGanador, BorderLayout.PAGE_END);

        // *** Aquí se añade el borde/margen alrededor de todo el panel ***
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void setControlador(ControladorJuego controlador) {
        this.controlador = controlador;
        // Inyección en aristas
        for (int y = 0; y < tamaño; y++) {
            for (int x = 0; x < tamaño - 1; x++) {
                if (hEdges[y][x] != null) {
                    hEdges[y][x].setController(controlador);
                }
            }
        }
        for (int y = 0; y < tamaño - 1; y++) {
            for (int x = 0; x < tamaño; x++) {
                if (vEdges[y][x] != null) {
                    vEdges[y][x].setController(controlador);
                }
            }
        }
    }

    @Override
    public void update(ModeloJuego m) {
        // Turno
        lblTurno.setText("Turno de: " + m.getJugadorActual().getNombre());
        // Puntuaciones
        for (Jugador j : jugadores) {
            int pts = m.getPuntuacion(j);
            for (Component comp : pnlPuntos.getComponents()) {
                if (comp instanceof JLabel && comp.getName().equals("puntos_" + j.getNombre())) {
                    ((JLabel) comp).setText(j.getNombre() + ": " + pts);
                }
            }
        }
        // Aristas y cuadros...
        // (métodos isEdgeTaken, isSquareClaimed, etc.)
        // Fin de juego
        if (m.isGameOver()) {
            String msg = String.format(
                    "¡El ganador es %s con %d puntos!",
                    m.getGanador().getNombre(),
                    m.getPuntuacion(m.getGanador())
            );
            lblGanador.setText(msg);
            JOptionPane.showMessageDialog(this, msg, "Fin de la partida", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
