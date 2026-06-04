package sn.l2gl.youssoupha.ui.components;

/**
 * code UI 13
 */

import javax.swing.*;

// JTable : afficher des donnees organisees en lignes et colonnes.
public class Code13JTableSimple {
    private Code13JTableSimple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("UI 13 - JTable");
        fenetre.setSize(520, 280);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] colonnes = {"Matricule", "Nom", "Note"};
        Object[][] donnees = {
                {"ET001", "Awa", 15.5},
                {"ET002", "Moussa", 12.0},
                {"ET003", "Fatou", 17.5}
        };
        JTable table = new JTable(donnees, colonnes);

        fenetre.add(new JScrollPane(table));
        fenetre.setVisible(true);
    }

    /**
     * CHALLENGE :
     * Ajoutez deux nouvelles lignes dans le tableau.
     * Rendez ensuite les cellules non modifiables.
     */
}
