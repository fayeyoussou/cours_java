package sn.l2gl.youssoupha.views.layouts;

import javax.swing.*;
import java.awt.*;

public class ExempleGridLayout {
    private ExempleGridLayout() {
        /* This utility class should not be instantiated */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("GridLayout");
        fenetre.setSize(500, 300);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.add(creerPanel());
        fenetre.setVisible(true);
    }

    public static JPanel creerPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 3, 5, 5));
        panel.add(creerCellule("1", Color.RED));
        panel.add(creerCellule("2", Color.BLUE));
        panel.add(creerCellule("3", Color.GREEN));
        panel.add(creerCellule("4", Color.ORANGE));
        panel.add(creerCellule("5", Color.PINK));
        panel.add(creerCellule("6", Color.CYAN));
        return panel;
    }

    private static JPanel creerCellule(String texte, Color couleur) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(couleur);
        panel.add(new JLabel(texte));
        return panel;
    }
}
