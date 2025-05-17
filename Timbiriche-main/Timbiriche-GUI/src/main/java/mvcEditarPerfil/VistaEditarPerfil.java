/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcEditarPerfil;

import com.mycompany.blackboard.modelo.Jugador;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.net.URL;

/**
 *
 * @author joseq
 */
public class VistaEditarPerfil extends JPanel implements PerfilModeloListener {

    private ControladorEditarPerfil controlador;

    private final JTextField txtNombre = new JTextField();
    private final JToggleButton[] avatarButtons;
    private final JButton btnGuardar = new JButton("Guardar");
    private final JButton btnCancelar = new JButton("Cancelar");

    private static final String[] AVATARES = {
        "GATO.png", "LEIA.png", "PINGUINO.png", "RANA.png", "ROBOCOB.png"
    };

    public VistaEditarPerfil() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(400, 500));
        setOpaque(false);

        add(Box.createVerticalStrut(20));
        JLabel lblTitle = new JLabel("Editar Perfil", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 32));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(CENTER_ALIGNMENT);
        add(lblTitle);
        add(Box.createVerticalStrut(30));

        // Campo Nombre
        JLabel lblNombre = new JLabel("Nombre", SwingConstants.CENTER);
        lblNombre.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setAlignmentX(CENTER_ALIGNMENT);
        add(lblNombre);
        add(Box.createVerticalStrut(10));

        txtNombre.setMaximumSize(new Dimension(250, 30));
        txtNombre.setAlignmentX(CENTER_ALIGNMENT);
        txtNombre.getDocument().addDocumentListener(new DocumentListener() {
            private void upd() {
                controlador.onNombreChanged(txtNombre.getText().trim());
            }

            public void insertUpdate(DocumentEvent e) {
                upd();
            }

            public void removeUpdate(DocumentEvent e) {
                upd();
            }

            public void changedUpdate(DocumentEvent e) {
                upd();
            }
        });
        add(txtNombre);
        add(Box.createVerticalStrut(30));

        // Selección de Avatar
        JLabel lblAvatar = new JLabel("Avatar", SwingConstants.CENTER);
        lblAvatar.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblAvatar.setForeground(Color.WHITE);
        lblAvatar.setAlignmentX(CENTER_ALIGNMENT);
        add(lblAvatar);
        add(Box.createVerticalStrut(10));

        JPanel pnlAvatares = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        pnlAvatares.setOpaque(false);
        ButtonGroup group = new ButtonGroup();
        avatarButtons = new JToggleButton[AVATARES.length];
        for (int i = 0; i < AVATARES.length; i++) {
            String file = AVATARES[i];
            JToggleButton btn = new JToggleButton();
            btn.setActionCommand(file);
            btn.setFocusable(false);
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);

            URL url = getClass().getClassLoader().getResource("Avatares/" + file);
            if (url != null) {
                Image img = new ImageIcon(url).getImage()
                        .getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                btn.setIcon(new ImageIcon(img));
            }

            btn.addActionListener(e
                    -> controlador.onAvatarChanged(e.getActionCommand())
            );
            group.add(btn);
            pnlAvatares.add(btn);
            avatarButtons[i] = btn;
        }
        add(pnlAvatares);
        add(Box.createVerticalStrut(30));

        // Guardar / Cancelar
        JPanel pnlAcc = new JPanel();
        pnlAcc.setOpaque(false);
        btnGuardar.addActionListener(e -> controlador.onGuardar());
        btnCancelar.addActionListener(e -> controlador.onCancelar());
        pnlAcc.add(btnGuardar);
        pnlAcc.add(btnCancelar);
        add(pnlAcc);
    }

    public void setControlador(ControladorEditarPerfil c) {
        this.controlador = c;
    }

    /**
     * Este es el único método abstracto: actualiza UI desde el modelo
     */
    @Override
    public void update(ModeloEditarPerfil modelo) {
        Jugador j = modelo.getJugadorOriginal();
        txtNombre.setText(j.getNombre());
        String ruta = j.getRutaAvatar();
        for (JToggleButton btn : avatarButtons) {
            btn.setSelected(btn.getActionCommand().equals(ruta));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int w = getWidth(), h = getHeight();
        g2.setPaint(new GradientPaint(0, 0, new Color(180, 0, 0), w, h, new Color(120, 0, 0)));
        g2.fillRect(0, 0, w, h);
    }
}
