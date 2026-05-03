package sn.l2gl.youssoupha.views.layouts;

import javax.swing.*;
import java.awt.*;

public class ExempleFlowLayout {
    private ExempleFlowLayout() {
        /* This utility class should not be instantiated */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("FlowLayout");
        fenetre.setSize(500, 300);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.add(creerPanel());
        fenetre.setVisible(true);
    }

    public static JPanel creerPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.add(creerPetitPanel("1", Color.RED));
        panel.add(creerPetitPanel("2", Color.BLUE));
        panel.add(creerPetitPanel("3", Color.GREEN));
        panel.add(creerPetitPanel("4", Color.ORANGE));
        return panel;
    }

    private static JPanel creerPetitPanel(String texte, Color couleur) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 70));
        panel.setBackground(couleur);
        panel.add(new JLabel(texte));
        return panel;
    }
}
