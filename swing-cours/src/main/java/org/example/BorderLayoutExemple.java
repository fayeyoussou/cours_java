package org.example;

import javax.swing.*;
import java.awt.*;

public abstract class BorderLayoutExemple {
    private BorderLayoutExemple() {
        throw new UnsupportedOperationException("Création impossible");
    }

    /**
     *  Explication du BorderLayout
     * Il divise la fenêtre en 5 zones principales :
     *
     * NORTH : en haut
     *
     * SOUTH : en bas
     *
     * EAST : à droite
     *
     * WEST : à gauche
     *
     * CENTER : au centre (zone principale)
     *
     * Chaque zone peut contenir un seul composant.
     *
     * Idéal pour organiser une interface avec des zones bien définies (ex. : barre de menu en haut, contenu au centre, etc.).
     */
    public static void execute() {

        // CREATION DE LA FENETRE PRINCIPALE
        JFrame fenetre = new JFrame("Exemple BorderLayout avec Swing");
        fenetre.setSize(500, 400);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // DEFINITION DU GESTIONNAIRE DE LAYOUT
        fenetre.setLayout(new BorderLayout(10, 10)); 
        // (espacement horizontal, espacement vertical)

        // CREATION DES BOUTONS POUR CHAQUE ZONE
        JButton boutonNord = new JButton("Nord");
        JButton boutonSud = new JButton("Sud");
        JButton boutonEst = new JButton("Est");
        JButton boutonOuest = new JButton("Ouest");
        JButton boutonCentre = new JButton("Centre");

        // AJOUT DES COMPOSANTS AUX ZONES CORRESPONDANTES
        fenetre.add(boutonNord, BorderLayout.NORTH);   // Haut de la fenêtre
        fenetre.add(boutonSud, BorderLayout.SOUTH);   // Bas de la fenêtre
        fenetre.add(boutonEst, BorderLayout.EAST);    // Droite
        fenetre.add(boutonOuest, BorderLayout.WEST);  // Gauche
        fenetre.add(boutonCentre, BorderLayout.CENTER); // Centre (zone principale)

        // AFFICHAGE DE LA FENETRE
        fenetre.setVisible(true);
    }
}
