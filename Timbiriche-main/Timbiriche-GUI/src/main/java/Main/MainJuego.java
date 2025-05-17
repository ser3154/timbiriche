// src/main/java/mvcJuego/MainJuego.java
package mvcJuego;

import com.mycompany.blackboard.modelo.Jugador;
import com.mycompany.timbirichenetwork.Cliente;
import com.google.gson.Gson;

import javax.swing.*;
import java.util.List;

public class MainJuego {

    /** 
     * Firma antigua, la dejamos por compatibilidad si la usas sin red.
     */
    public static void mainCon(List<Jugador> jugadores, int tamaño) {
        mainCon(jugadores, tamaño, null, null);
    }

    /**
     * Nueva firma para integración en red.
     */
    public static void mainCon(List<Jugador> jugadores,
                               int tamaño,
                               Cliente cliente,
                               Gson gson) 
    {
        // 1) Modelo
        ModeloJuego modelo = new ModeloJuego(jugadores, tamaño);

        // 2) Vista
        VistaJuego vista = new VistaJuego(modelo, jugadores, tamaño);

        // 3) Controlador (le pasamos cliente y gson para que maneje red)
        ControladorJuego controlador = new ControladorJuego(modelo, vista, cliente, gson);

        // 4) Montar en ventana Swing
        JFrame frame = new JFrame("Timbiriche");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(vista);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // arranca una partida local de ejemplo 2 jugadores 10×10
        SwingUtilities.invokeLater(() ->
            mainCon(
                List.of(
                    new Jugador("Alice", "#FF0000", "GATO.png", false),
                    new Jugador("Bob",   "#00FF00", "PINGUINO.png", false)
                ), 
                10
            )
        );
    }
}
