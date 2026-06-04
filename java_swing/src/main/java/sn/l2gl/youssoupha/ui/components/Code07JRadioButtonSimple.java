package sn.l2gl.youssoupha.ui.components;

/**
 * code UI 7
 */

import javax.swing.*;
import java.awt.*;

// JRadioButton : proposer un choix unique grace a ButtonGroup.
public class Code07JRadioButtonSimple {
    private Code07JRadioButtonSimple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("UI 07 - JRadioButton");
        fenetre.setSize(420, 220);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new GridLayout(3, 1, 5, 5));

        JRadioButton petit = new JRadioButton("Petit");
        JRadioButton moyen = new JRadioButton("Moyen", true);
        JRadioButton grand = new JRadioButton("Grand");

        ButtonGroup groupe = new ButtonGroup();
        groupe.add(petit);
        groupe.add(moyen);
        groupe.add(grand);

        fenetre.add(petit);
        fenetre.add(moyen);
        fenetre.add(grand);
        fenetre.setVisible(true);
    }

    /**
     * CHALLENGE :
     * Ajoutez un bouton qui affiche la taille selectionnee.
     * Ajoutez une quatrieme option "Tres grand".
     */
}
