package org.example;

import javax.swing.*;
import java.awt.*;

public abstract class LayoutCombineExemple {
    private LayoutCombineExemple() {
        throw new UnsupportedOperationException("Création impossible");
    }

    public static void execute() {

        // CREATION DE LA FENETRE PRINCIPALE
        JFrame fenetre = new JFrame("Exemple combiné de Layouts avec Swing");
        fenetre.setSize(500, 400);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout()); // Layout principal

        // ---- ZONE NORD : PANEL EN FlowLayout (barre de boutons)
        JPanel panelHaut = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelHaut.add(new JButton("Accueil"));
        panelHaut.add(new JButton("Profil"));
        panelHaut.add(new JButton("Déconnexion"));
        fenetre.add(panelHaut, BorderLayout.NORTH);

        // ---- ZONE CENTRE : PANEL EN GridLayout (grille de boutons)
        JPanel panelCentre = new JPanel(new GridLayout(2, 2, 5, 5));
        panelCentre.add(new JButton("Option 1"));
        panelCentre.add(new JButton("Option 2"));
        panelCentre.add(new JButton("Option 3"));
        panelCentre.add(new JButton("Option 4"));
        fenetre.add(panelCentre, BorderLayout.CENTER);

        // ---- ZONE SUD : FOOTER SIMPLE
        JLabel footer = new JLabel("© 2025 - Mon application Swing", SwingConstants.CENTER);
        fenetre.add(footer, BorderLayout.SOUTH);

        // AFFICHAGE DE LA FENETRE
        fenetre.setVisible(true);
    }
}
