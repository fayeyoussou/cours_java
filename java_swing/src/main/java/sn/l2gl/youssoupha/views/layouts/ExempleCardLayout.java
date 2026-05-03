package sn.l2gl.youssoupha.views.layouts;

import javax.swing.*;
import java.awt.*;

public class ExempleCardLayout {
    private ExempleCardLayout() {
        /* This utility class should not be instantiated */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("CardLayout");
        fenetre.setSize(500, 300);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.add(creerPanel());
        fenetre.setVisible(true);
    }

    public static JPanel creerPanel() {
        JPanel principal = new JPanel(new BorderLayout());

        CardLayout cardLayout = new CardLayout();
        JPanel cartes = new JPanel(cardLayout);
        cartes.add(creerCarte("Vue 1", Color.RED), "vue1");
        cartes.add(creerCarte("Vue 2", Color.BLUE), "vue2");
        cartes.add(creerCarte("Vue 3", Color.GREEN), "vue3");

        JButton boutonSuivant = new JButton("Suivant");
        boutonSuivant.addActionListener(e -> cardLayout.next(cartes));

        principal.add(cartes, BorderLayout.CENTER);
        principal.add(boutonSuivant, BorderLayout.SOUTH);

        return principal;
    }

    private static JPanel creerCarte(String texte, Color couleur) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(couleur);
        panel.add(new JLabel(texte));
        return panel;
    }
}
