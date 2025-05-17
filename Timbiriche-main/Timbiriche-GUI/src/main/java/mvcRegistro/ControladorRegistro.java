/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcRegistro;

import com.mycompany.blackboard.modelo.Jugador;
import javax.swing.*;

/**
 *
 * @author joseq
 */
public class ControladorRegistro {

    private final ModeloRegistro modelo;
    private final VistaRegistro vista;
    private final JFrame frame;

    public ControladorRegistro(ModeloRegistro modelo, VistaRegistro vista) {
        this.modelo = modelo;
        this.vista = vista;

        // 1) Registro de la vista como observadora del modelo
        modelo.addObserver(vista);

        // 2) Conectar controlador y vista
        vista.setControlador(this);

        // 3) Crear ventana y mostrar
        frame = new JFrame("Registro de Jugador");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(vista);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Se llama al pulsar "Registrar"
     */
    public void onRegistrar() {
        // Actualizar modelo (dispara notifyObservers y refresca la vista)
        modelo.setNombre(vista.getNombre());
        modelo.setColorHex(vista.getColorHex());
        modelo.setRutaAvatar(vista.getRutaAvatar());

        // Crear el Jugador
        Jugador jugador = modelo.crearJugador();

        // Cerrar registro y pasar al siguiente módulo
        frame.dispose();

        // Ejemplo de llamada al siguiente controlador:
        // new ControladorTamanoTablero(new ModeloTamanoTablero(jugador),
        //                              new VistaTamanoTablero());
    }
}
