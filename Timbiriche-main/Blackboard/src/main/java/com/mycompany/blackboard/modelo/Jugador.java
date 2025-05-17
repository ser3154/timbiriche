package com.mycompany.blackboard.modelo;



/**
 * Representa un jugador en el juego Timbiriche. Simplificado: el avatar es
 * siempre un ImageIcon vacío para no lanzar NPE.
 */
public class Jugador {

   private String nombre;
    private String colorHex;
    private String rutaAvatar;
    private boolean listo;

    public Jugador(String nombre, String colorHex, String rutaAvatar, boolean listo) {
        this.nombre     = nombre;
        this.colorHex   = colorHex;
        this.rutaAvatar = rutaAvatar;
        this.listo      = listo;
    }

    // ================= Getters =================

    public String getNombre() {
        return nombre;
    }

    public String getColorHex() {
        return colorHex;
    }

    public String getRutaAvatar() {
        return rutaAvatar;
    }

    public boolean isListo() {
        return listo;
    }

    // ================= Setters =================

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public void setRutaAvatar(String rutaAvatar) {
        this.rutaAvatar = rutaAvatar;
    }

    public void setListo(boolean listo) {
        this.listo = listo;
    }

    // ================ equals/hashCode ================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jugador)) return false;
        Jugador j = (Jugador) o;
        return nombre != null && nombre.equals(j.nombre);
    }

    @Override
    public int hashCode() {
        return nombre != null ? nombre.hashCode() : 0;
    }
}
