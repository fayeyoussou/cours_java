package sn.l2gl.youssoupha.ui.components;

/**
 * code UI 12
 */

import javax.swing.*;

// JProgressBar : afficher l'avancement d'une operation.
public class Code12JProgressBarSimple {
    private Code12JProgressBarSimple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("UI 12 - JProgressBar");
        fenetre.setSize(450, 180);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JProgressBar progression = new JProgressBar(0, 100);
        progression.setValue(65);
        progression.setStringPainted(true);

        fenetre.add(progression);
        fenetre.setVisible(true);
    }

    /**
     * CHALLENGE :
     * Ajoutez deux boutons pour augmenter et diminuer la progression.
     * Empechez la valeur de sortir de l'intervalle 0 a 100.
     */
}
