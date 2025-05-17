// src/main/java/app/Launcher.java
package app;

import com.mycompany.blackboard.modelo.Jugador;
import util.ProfileStorage;
import mvcEditarPerfil.ControladorEditarPerfil;
import mvcTamanoTablero.ControladorTamanoTablero;
import mvcTamanoTablero.ModeloTamanoTablero;
import mvcTamanoTablero.VistaTamanoTablero;

import javax.swing.*;

/**
 * Punto de entrada de la aplicación TIMBIRICHE.
 * Decide si hay perfil guardado y arranca EditarPerfil o Selección de Tamaño.
 */
public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1) Intentar cargar perfil desde ~/.timbiriche/profile.json
            Jugador perfil = ProfileStorage.loadProfile();

            if (perfil == null) {
                // 2a) Si no hay perfil, abrir pantalla de edición
                new ControladorEditarPerfil();
            } else {
                // 2b) Si ya existe, saltar directamente al selector de tamaño
                ModeloTamanoTablero modelo = new ModeloTamanoTablero(perfil);
                VistaTamanoTablero  vista  = new VistaTamanoTablero();
                new ControladorTamanoTablero(modelo, vista, perfil);
            }
        });
    }
}
