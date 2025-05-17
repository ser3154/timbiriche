/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcEditarPerfil;

import com.mycompany.blackboard.modelo.Jugador;
import util.ProfileStorage;
import mvcTamanoTablero.ModeloTamanoTablero;
import mvcTamanoTablero.VistaTamanoTablero;
import mvcTamanoTablero.ControladorTamanoTablero;

import javax.swing.*;
import java.io.IOException;

/**
 *
 * @author joseq
 */
public class ControladorEditarPerfil {

    private final ModeloEditarPerfil modelo;
    private final VistaEditarPerfil vista;
    private final JFrame frame;

    /**
     * Constructor principal: recibe el Jugador a editar
     */
    public ControladorEditarPerfil(Jugador jugadorOriginal) {
        this.modelo = new ModeloEditarPerfil(jugadorOriginal);
        this.vista = new VistaEditarPerfil();
        modelo.addListener(vista);
        vista.setControlador(this);

        frame = new JFrame("Editar Perfil");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(vista);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Constructor por defecto: jugador vacio con avatar GATO.png
     */
    public ControladorEditarPerfil() {
        this(new Jugador("", "#000000", "GATO.png", false));
    }

    /**
     * Invocado por la vista al pulsar “Guardar”
     */
    public void onGuardar() {
        Jugador updated = modelo.getJugadorOriginal();

        // 1) Guardar en disco
        try {
            ProfileStorage.saveProfile(updated);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    frame,
                    "No se pudo guardar el perfil: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        // 2) Cerrar esta ventana
        frame.dispose();

        // 3) Arrancar selector de tamaño con el perfil cargado
        ModeloTamanoTablero m = new ModeloTamanoTablero(updated);
        VistaTamanoTablero v = new VistaTamanoTablero();
        new ControladorTamanoTablero(m, v, updated);
    }

    /**
     * Invocado por la vista al pulsar “Cancelar”
     */
    public void onCancelar() {
        frame.dispose();
    }

    /**
     * Invocado por la vista al cambiar el texto del nombre
     */
    public void onNombreChanged(String nuevoNombre) {
        modelo.setNombre(nuevoNombre);
    }

    /**
     * Invocado por la vista al cambiar el avatar
     */
    public void onAvatarChanged(String nuevaRuta) {
        modelo.setRutaAvatar(nuevaRuta);
    }

}
