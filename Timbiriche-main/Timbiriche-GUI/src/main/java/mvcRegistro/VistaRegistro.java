/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcRegistro;

import blackboard.IV;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author joseq
 */
public class VistaRegistro extends JPanel implements IV<ModeloRegistro> {

    private ControladorRegistro controlador;

    private final JTextField txtNombre = new JTextField(15);
    private final JComboBox<String> cmbColor = new JComboBox<>(new String[]{"#FF0000", "#00FF00", "#0000FF"});
    private final JComboBox<String> cmbAvatar = new JComboBox<>(new String[]{"GATO.png", "LEIA.png", "PINGUINO.png"});
    private final JButton btnRegistrar = new JButton("Registrar");

    public VistaRegistro() {
        setLayout(new GridLayout(4, 2, 5, 5));
        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Color:"));
        add(cmbColor);
        add(new JLabel("Avatar:"));
        add(cmbAvatar);
        add(new JLabel());
        add(btnRegistrar);

        btnRegistrar.addActionListener(e -> {
            if (controlador != null) {
                controlador.onRegistrar();
            }
        });
    }

    /**
     * Este método se llama desde el modelo al notificar
     */
    @Override
    public void update(ModeloRegistro m) {
        txtNombre.setText(m.getNombre());
        cmbColor.setSelectedItem(m.getColorHex());
        cmbAvatar.setSelectedItem(m.getRutaAvatar());
    }

    public String getNombre() {
        return txtNombre.getText();
    }

    public String getColorHex() {
        return (String) cmbColor.getSelectedItem();
    }

    public String getRutaAvatar() {
        return (String) cmbAvatar.getSelectedItem();
    }

    public void setControlador(ControladorRegistro c) {
        this.controlador = c;
    }
}
