/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcTamanoTablero;

import blackboard.IV;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author joseq
 */
public class VistaTamanoTablero extends JPanel implements IV<ModeloTamanoTablero> {

    private ControladorTamanoTablero ctrl;
    private final JButton btn10 = new JButton("10 x 10");
    private final JButton btn20 = new JButton("20 x 20");
    private final JButton btn30 = new JButton("30 x 30");
    private final JToggleButton rbServidor = new JToggleButton("Servidor");
    private final JToggleButton rbUnirse   = new JToggleButton("Unirse");
    private final JButton btnContinuar     = new JButton("Continuar");

    public VistaTamanoTablero() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);

        // Fila 0: tamaño
        c.gridy = 0; add(new JLabel("Seleccione Tamaño:"), c);
        // Fila 1: botones de tamaño
        c.gridy = 1; add(btn10, c);
        c.gridy = 2; add(btn20, c);
        c.gridy = 3; add(btn30, c);
        // Fila 4: rol
        ButtonGroup role = new ButtonGroup();
        role.add(rbServidor); role.add(rbUnirse);
        c.gridy = 4; add(rbServidor, c);
        c.gridy = 5; add(rbUnirse, c);
        // Fila 6: continuar
        btnContinuar.setEnabled(false);
        c.gridy = 6; add(btnContinuar, c);

        // Listeners internos
        btn10.addActionListener(e -> ctrl.onSizeSelected(10));
        btn20.addActionListener(e -> ctrl.onSizeSelected(20));
        btn30.addActionListener(e -> ctrl.onSizeSelected(30));
        rbServidor.addActionListener(e -> ctrl.onServidorSelected());
        rbUnirse  .addActionListener(e -> ctrl.onUnirseSelected());
        btnContinuar.addActionListener(e -> ctrl.onContinuar());
    }

    public void setControlador(ControladorTamanoTablero c) {
        this.ctrl = c;
    }

    @Override
    public void update(ModeloTamanoTablero m) {
        btnContinuar.setEnabled(m.isSizeSelected());
    }
}
