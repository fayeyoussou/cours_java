package sn.l2gl.youssoupha.ui.components;

/**
 * code UI 9
 */

import javax.swing.*;

// JList : afficher une liste avec selection simple ou multiple.
public class Code09JListSimple {
    private Code09JListSimple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("UI 09 - JList");
        fenetre.setSize(420, 260);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JList<String> liste = new JList<>(new String[]{"Java", "Python", "JavaScript", "PHP", "C#"});
        liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        liste.setSelectedIndex(0);

        fenetre.add(new JScrollPane(liste));
        fenetre.setVisible(true);
    }

    /**
     * CHALLENGE :
     * Autorisez la selection de plusieurs langages.
     * Ajoutez un bouton affichant toutes les valeurs selectionnees.
     */
}
