/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuego.Componentes;

import mvcJuego.ControladorJuego;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author joseq
 */
public class EdgeButton extends JButton {

    private ControladorJuego controller;
    private final int y1, x1, y2, x2;

    public EdgeButton(int y1, int x1, int y2, int x2) {
        this.y1 = y1;
        this.x1 = x1;
        this.y2 = y2;
        this.x2 = x2;
        setPreferredSize(new Dimension(
                y1 == y2 ? 40 : 10,
                x1 == x2 ? 40 : 10
        ));
        setContentAreaFilled(false);
        setBorderPainted(false);
        addActionListener(e -> {
            if (controller != null) {
                controller.pintarLinea(y1, x1, y2, x2);
            }
        });
    }

    /**
     * Inyección del controlador desde VistaJuego.setControlador(...)
     */
    public void setController(ControladorJuego controller) {
        this.controller = controller;
    }

    /**
     * Cuando se reclama la arista, la coloreamos y deshabilitamos
     */
    public void setClaimed(Color c) {
        setBackground(c);
        setOpaque(true);
        setEnabled(false);
    }
}
