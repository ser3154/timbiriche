/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcLobby;

import blackboard.IV;
import com.mycompany.blackboard.modelo.Jugador;
import mvcLobby.componentes.PlayerCell;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *
 * @author joseq
 */
public class VistaLobby extends JPanel implements IV<ModeloLobbyJuego> {

    private ControladorLobbyJuego controlador;
    private final PlayerCell[] cells = new PlayerCell[4];
    private final JButton btnListo = new JButton("Listo");

    public VistaLobby() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
        setOpaque(false);

        // Título
        JLabel title = new JLabel("TIMBIRICHE", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        // Grid 2x2 de celdas
        JPanel grid = new JPanel(new GridLayout(2, 2, 20, 20));
        grid.setOpaque(false);
        for (int i = 0; i < 4; i++) {
            cells[i] = new PlayerCell();
            grid.add(cells[i]);
        }
        add(grid, BorderLayout.CENTER);

        // Botón “Listo”
        JPanel south = new JPanel();
        south.setOpaque(false);
        btnListo.setEnabled(false);
        btnListo.setFont(new Font("SansSerif", Font.BOLD, 24));
        btnListo.addActionListener(e -> controlador.onContinuar());
        south.add(btnListo);
        add(south, BorderLayout.SOUTH);
    }

    public void setControlador(ControladorLobbyJuego c) {
        this.controlador = c;
    }

    @Override
    public void update(ModeloLobbyJuego m) {
        List<Jugador> lista = m.getJugadores();
        for (int i = 0; i < 4; i++) {
            if (i < lista.size()) {
                cells[i].setJugador(lista.get(i));
            } else {
                cells[i].clear();
            }
        }
        btnListo.setEnabled(m.listoParaIniciar());
        repaint();
    }

    /**
     * Permite al controlador enganchar listeners en las celdas
     */
    public PlayerCell getCell(int idx) {
        return cells[idx];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int w = getWidth(), h = getHeight();
        g2.setPaint(new GradientPaint(
                0, 0, new Color(180, 0, 0),
                w, h, new Color(120, 0, 0)
        ));
        g2.fillRect(0, 0, w, h);
    }
}
