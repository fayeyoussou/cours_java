package sn.l2gl.youssoupha.views.layouts;

import javax.swing.*;
import java.awt.*;

public class ExempleGridBagLayout {
    private ExempleGridBagLayout() {
        /* This utility class should not be instantiated */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("GridBagLayout");
        fenetre.setSize(500, 300);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.add(creerPanel());
        fenetre.setVisible(true);
    }

    public static JPanel creerPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        panel.add(creerCellule("1", Color.RED), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(creerCellule("2", Color.BLUE), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(creerCellule("Large", Color.GREEN), gbc);

        return panel;
    }

    private static JPanel creerCellule(String texte, Color couleur) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(couleur);
        panel.add(new JLabel(texte));
        return panel;
    }
}
