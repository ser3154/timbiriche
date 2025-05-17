/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuego.Componentes;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author joseq
 */
public class DotPanel extends JPanel {

    public DotPanel() {
        setPreferredSize(new Dimension(16, 16));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillOval(0, 0, getWidth(), getHeight());
    }
}
