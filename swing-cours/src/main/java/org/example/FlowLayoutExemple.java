package org.example;

import javax.swing.*;
import java.awt.*;

public abstract class FlowLayoutExemple {
    private FlowLayoutExemple() {
        throw new UnsupportedOperationException("Création impossible");
    }

    /**
     * Explication du code
     * FlowLayout place les composants horizontalement dans l'ordre d'ajout.
     *
     * Il gère automatiquement le retour à la ligne si l'espace est insuffisant.
     *
     * On peut définir :
     *
     * l’alignement (LEFT, CENTER, RIGHT)
     *
     * les espacements horizontaux et verticaux
     */
    public static void execute() {

        // CREATION DE LA FENETRE PRINCIPALE
        JFrame fenetre = new JFrame("Exemple FlowLayout avec Swing");
        fenetre.setSize(400, 200);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // UTILISATION DU FLOWLAYOUT
        fenetre.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
        // FlowLayout(alignement, espacement horizontal, espacement vertical)

        // CREATION DE PLUSIEURS BOUTONS
        JButton bouton1 = new JButton("Bouton 1");
        JButton bouton2 = new JButton("Bouton 2");
        JButton bouton3 = new JButton("Bouton 3");
        JButton bouton4 = new JButton("Bouton 4");
        JButton bouton5 = new JButton("Bouton 5");

        // AJOUT DES BOUTONS A LA FENETRE
        fenetre.add(bouton1);
        fenetre.add(bouton2);
        fenetre.add(bouton3);
        fenetre.add(bouton4);
        fenetre.add(bouton5);

        // AFFICHAGE DE LA FENETRE
        fenetre.setVisible(true);
    }
}
