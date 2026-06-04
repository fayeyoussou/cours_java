package sn.l2gl.youssoupha.ui.components;

/**
 * code UI 14
 */

import javax.swing.*;

// JTabbedPane : organiser plusieurs panneaux dans des onglets.
public class Code14JTabbedPaneSimple {
    private Code14JTabbedPaneSimple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("UI 14 - JTabbedPane");
        fenetre.setSize(500, 280);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane onglets = new JTabbedPane();
        onglets.addTab("Accueil", new JLabel("Page d'accueil", SwingConstants.CENTER));
        onglets.addTab("Etudiants", new JLabel("Liste des etudiants", SwingConstants.CENTER));
        onglets.addTab("Parametres", new JLabel("Parametres", SwingConstants.CENTER));

        fenetre.add(onglets);
        fenetre.setVisible(true);
    }

    /**
     * CHALLENGE :
     * Ajoutez un onglet "Notes" contenant une JTable.
     * Ajoutez une icone ou une infobulle a chaque onglet.
     */
}
