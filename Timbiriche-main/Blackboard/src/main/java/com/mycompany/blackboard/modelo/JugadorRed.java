/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blackboard.modelo;

import java.io.Serializable;

/**
 *
 * @author joseq
 */
public class JugadorRed implements Serializable{
    
     private String nombre;
    private String colorHex;
    private String avatarPath;
    private boolean listo;

    public JugadorRed(String nombre, String colorHex, String avatarPath, boolean listo) {
        this.nombre = nombre;
        this.colorHex = colorHex;
        this.avatarPath = avatarPath;
        this.listo = listo;
    }

    public String getNombre() { return nombre; }
    public String getColorHex() { return colorHex; }
    public String getAvatarPath() { return avatarPath; }
    public boolean isListo() { return listo; }
}
    

