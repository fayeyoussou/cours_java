package sn.l2gl.youssoupha.views.layouts;

import javax.swing.*;
import java.awt.*;

public class ExempleBoxLayout {
    private ExempleBoxLayout() {
        /* This utility class should not be instantiated */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("BoxLayout");
        fenetre.setSize(500, 300);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.add(creerPanel());
        fenetre.setVisible(true);
    }

    public static JPanel creerPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(creerLigne("Ligne 1", Color.RED));
        panel.add(creerLigne("Ligne 2", Color.BLUE));
        panel.add(creerLigne("Ligne 3", Color.GREEN));
        return panel;
    }

    private static JPanel creerLigne(String texte, Color couleur) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        panel.setBackground(couleur);
        panel.add(new JLabel(texte));
        return panel;
    }
}
