package sn.l2gl.youssoupha.views;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class JLabelAvecJPanel {
    private JLabelAvecJPanel() {
        /* This utility class should not be instantiated */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("JLabel avec JPanel");
        fenetre.setSize(500, 300);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel();
        label.setText("Mon JLabel");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(Color.BLUE);
        label.setIcon(new ImageIcon(Objects.requireNonNull(JLabelAvecJPanel.class.getResource("/icons/app.png"))));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        label.setToolTipText("Ceci est un JLabel");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        panel.add(label, BorderLayout.CENTER);

        fenetre.add(panel);
        fenetre.setVisible(true);
    }
}
