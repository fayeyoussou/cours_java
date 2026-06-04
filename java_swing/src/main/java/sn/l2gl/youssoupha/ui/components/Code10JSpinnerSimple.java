package sn.l2gl.youssoupha.ui.components;

/**
 * code UI 10
 */

import javax.swing.*;
import java.awt.*;

// JSpinner : saisir une valeur encadree avec des boutons precedent/suivant.
public class Code10JSpinnerSimple {
    private Code10JSpinnerSimple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("UI 10 - JSpinner");
        fenetre.setSize(420, 180);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 50));

        JSpinner spinnerAge = new JSpinner(new SpinnerNumberModel(18, 0, 120, 1));

        fenetre.add(new JLabel("Age :"));
        fenetre.add(spinnerAge);
        fenetre.setVisible(true);
    }

    /**
     * CHALLENGE :
     * Ajoutez un JLabel affichant la valeur actuelle du spinner.
     * Modifiez ensuite le pas pour avancer de 5 en 5.
     */
}
