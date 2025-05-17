/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Serva
 */
public class Linea {
   private boolean marcada;

    /**
     * Constructor que inicializa la línea como no marcada.
     */
    public Linea() {
        this.marcada = false;
    }

    /**
     * Marca la línea como ocupada.
     */
    public void marcar() {
        this.marcada = true;
    }

    /**
     * Verifica si la línea ya fue marcada.
     * @return True si la línea está marcada, False si aún está libre.
     */
    public boolean estaMarcada() {
        return marcada;
    }
}
