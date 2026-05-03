package sn.l2gl.youssoupha.views.layouts;

import javax.swing.*;
import java.awt.*;

public class ExempleBorderLayout {
    private ExempleBorderLayout() {
        /* This utility class should not be instantiated */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("BorderLayout");
        fenetre.setSize(500, 300);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.add(creerPanel());
        fenetre.setVisible(true);
    }

    public static JPanel creerPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.add(creerZone("NORTH", Color.RED), BorderLayout.NORTH);
        panel.add(creerZone("SOUTH", Color.BLUE), BorderLayout.SOUTH);
        panel.add(creerZone("WEST", Color.GREEN), BorderLayout.WEST);
        panel.add(creerZone("EAST", Color.ORANGE), BorderLayout.EAST);
        panel.add(creerZone("CENTER", Color.PINK), BorderLayout.CENTER);
        return panel;
    }

    private static JPanel creerZone(String texte, Color couleur) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(couleur);
        panel.add(new JLabel(texte));
        return panel;
    }
}
