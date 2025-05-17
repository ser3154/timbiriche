/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuego;

import com.mycompany.blackboard.modelo.Jugador;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *
 * @author joseq
 */
public class TableroPanel extends JPanel {

    private final int tamañoTablero;
    private final List<Jugador> jugadores;
    private final String[][] tablero;

    /**
     * @param jugadores lista de jugadores (debe tener al menos 2 elementos)
     * @param tamañoTablero número de casillas por fila/columna
     * @param tablero matriz tamañoTablero×tamañoTablero con "X"/"O"/null
     */
    public TableroPanel(List<Jugador> jugadores,
            int tamañoTablero,
            String[][] tablero) {
        this.jugadores = jugadores;
        this.tamañoTablero = tamañoTablero;
        this.tablero = tablero;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = getWidth();
        int h = getHeight();
        int cellSize = Math.min(w, h) / tamañoTablero;

        // Dibujar cuadros completados
        for (int row = 0; row < tamañoTablero; row++) {
            for (int col = 0; col < tamañoTablero; col++) {
                String mark = tablero[row][col];
                if ("X".equals(mark)) {
                    // Color del primer jugador
                    Color c = Color.decode(jugadores.get(0).getColorHex());
                    g.setColor(c);
                } else if ("O".equals(mark)) {
                    // Color del segundo jugador
                    Color c = Color.decode(jugadores.get(1).getColorHex());
                    g.setColor(c);
                } else {
                    continue; // sin cuadro completado
                }
                // Dibujar rectángulo ligeramente más pequeño que la celda
                int x = col * cellSize + 2;
                int y = row * cellSize + 2;
                g.fillRect(x, y, cellSize - 4, cellSize - 4);
            }
        }
    }

}
