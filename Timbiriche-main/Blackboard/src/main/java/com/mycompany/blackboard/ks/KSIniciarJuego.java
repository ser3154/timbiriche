/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackboard.ks;

import com.mycompany.blackboard.modelo.Jugador;
import mvcJuego.MainJuego;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author joseq
 */
public class KSIniciarJuego {

    private JTextField txtTamanio;
    private JButton btnIniciar;
    private JPanel panelPrincipal;

    /**
     * @param jugadores Lista de jugadores ya registrados en el lobby.
     */
    public KSIniciarJuego(List<Jugador> jugadores) {
        // Construye tu UI (NetBeans GUI builder o a mano)
        JFrame frame = new JFrame("Iniciar Juego");
        frame.setContentPane(panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);

        // Listener para el botón Iniciar
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int tamañoTablero = Integer.parseInt(txtTamanio.getText().trim());
                    // Arranca la partida local MVC sin red:
                    SwingUtilities.invokeLater(()
                            -> MainJuego.mainCon(jugadores, tamañoTablero)
                    );
                    frame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "Introduce un número válido para el tamaño del tablero.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        frame.setVisible(true);
    }
}
