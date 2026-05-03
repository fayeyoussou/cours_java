package org.example;

import javax.swing.*;
import java.awt.*;

/**
 * Le GridLayout organise les composants dans une grille à lignes et colonnes fixes.
 *
 * Tous les composants ont la même taille.
 *
 * On peut définir :
 *
 * Le nombre de lignes (rows)
 *
 * Le nombre de colonnes (columns)
 *
 * L’espacement horizontal et vertical entre les cellules
 */
public abstract class GridLayoutExemple {
    private GridLayoutExemple() {
        throw new UnsupportedOperationException("Création impossible");
    }

    public static void execute() {

        // CREATION DE LA FENETRE PRINCIPALE
        JFrame fenetre = new JFrame("Exemple GridLayout avec Swing");
        fenetre.setSize(400, 300);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // DEFINITION DU LAYOUT EN GRILLE : 3 lignes, 2 colonnes
        fenetre.setLayout(new GridLayout(2, 4, 10, 10));
        // (lignes, colonnes, espacement horizontal, espacement vertical)

        // CREATION ET AJOUT DES BOUTONS
        fenetre.add(new JButton("Bouton 1"));
        fenetre.add(new JButton("Bouton 2"));
        fenetre.add(new JButton("Bouton 3"));
        fenetre.add(new JButton("Bouton 4"));
        fenetre.add(new JButton("Bouton 5"));
        fenetre.add(new JButton("Bouton 6"));

        // AFFICHAGE DE LA FENETRE
        fenetre.setVisible(true);
    }
}
