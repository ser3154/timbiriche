/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcLobby.componentes;

import com.mycompany.blackboard.modelo.Jugador;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 *
 * @author joseq
 */
public class PlayerCell extends JPanel {

   private final JLabel lblAvatar = new JLabel();
    private final JLabel lblName   = new JLabel("", SwingConstants.CENTER);
    private final JLabel lblCheck  = new JLabel();
    private final JButton btnLeft  = new JButton("←");
    private final JButton btnRight = new JButton("→");

    private Jugador jugador;

    public PlayerCell() {
        setLayout(new BorderLayout(5, 5));
        setOpaque(false);

        // Indicador de listo / no listo
        lblCheck.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblCheck, BorderLayout.NORTH);

        // Avatar
        lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblAvatar, BorderLayout.CENTER);

        // Nombre
        lblName.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblName.setForeground(Color.WHITE);
        add(lblName, BorderLayout.SOUTH);

        // Flechas para cambiar avatar
        JPanel pnlArrows = new JPanel(new BorderLayout());
        pnlArrows.setOpaque(false);
        btnLeft .setFocusPainted(false);
        btnRight.setFocusPainted(false);
        pnlArrows.add(btnLeft,  BorderLayout.WEST);
        pnlArrows.add(btnRight, BorderLayout.EAST);
        add(pnlArrows, BorderLayout.PAGE_END);
    }

    /**
     * Pone los datos del jugador en esta celda.
     */
    public void setJugador(Jugador j) {
        this.jugador = j;

        // Usa el getter correcto: getRutaAvatar()
        String avatarFile = j.getRutaAvatar();
        ImageIcon icon = null;
        if (avatarFile != null) {
            URL url = getClass().getClassLoader().getResource("Avatares/" + avatarFile);
            if (url != null) {
                icon = new ImageIcon(
                    new ImageIcon(url)
                        .getImage()
                        .getScaledInstance(64,64,Image.SCALE_SMOOTH)
                );
            }
        }
        lblAvatar.setIcon(icon);
        lblName.setText(j.getNombre());

        // Marca de listo o no listo (ej. check.png / cross.png en resources)
        String markFile = j.isListo() ? "check.png" : "cross.png";
        URL markUrl = getClass().getClassLoader().getResource(markFile);
        lblCheck.setIcon(markUrl != null ? new ImageIcon(markUrl) : null);

        // Solo habilitar flechas si el jugador NO está listo
        boolean editable = !j.isListo();
        btnLeft .setEnabled(editable);
        btnRight.setEnabled(editable);
    }

    /**
     * Limpia la celda (sin jugador).
     */
    public void clear() {
        jugador = null;
        lblAvatar.setIcon(null);
        lblName.setText("");
        lblCheck.setIcon(null);
        btnLeft .setEnabled(false);
        btnRight.setEnabled(false);
    }

    /** Para que el controlador pueda enganchar acciones en las flechas */
    public JButton getLeftButton()  { return btnLeft; }
    public JButton getRightButton() {
        return btnRight;
    }
}
