package sn.l2gl.youssoupha.ui.components;

/**
 * code UI 11
 */

import javax.swing.*;

// JSlider : choisir une valeur en deplacant un curseur.
public class Code11JSliderSimple {
    private Code11JSliderSimple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("UI 11 - JSlider");
        fenetre.setSize(500, 220);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JSlider slider = new JSlider(0, 100, 50);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        fenetre.add(slider);
        fenetre.setVisible(true);
    }

    /**
     * CHALLENGE :
     * Ajoutez un JLabel affichant la valeur actuelle du slider.
     * Changez la couleur du JLabel lorsque la valeur depasse 75.
     */
}
