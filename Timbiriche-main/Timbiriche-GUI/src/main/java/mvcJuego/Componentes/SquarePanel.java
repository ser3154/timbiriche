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
public class SquarePanel extends JPanel {

    public SquarePanel() {
        setPreferredSize(new Dimension(40, 40));
        setBackground(new Color(50, 50, 50));
    }

    public void setOwnerColor(Color c) {
        setBackground(c);
    }
}
