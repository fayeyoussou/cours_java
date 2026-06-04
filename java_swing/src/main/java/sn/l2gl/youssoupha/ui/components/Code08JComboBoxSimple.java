package sn.l2gl.youssoupha.ui.components;

/**
 * code UI 8
 */

import javax.swing.*;
import java.awt.*;

// JComboBox : choisir une valeur dans une liste deroulante.
public class Code08JComboBoxSimple {
    private Code08JComboBoxSimple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("UI 08 - JComboBox");
        fenetre.setSize(420, 180);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 50));

        JComboBox<String> comboClasse = new JComboBox<>(new String[]{"L1", "L2", "L3", "Master"});
        comboClasse.setSelectedItem("L2");

        fenetre.add(new JLabel("Classe :"));
        fenetre.add(comboClasse);
        fenetre.setVisible(true);
    }

    /**
     * CHALLENGE :
     * Ajoutez les classes L2GL, L2IAGE et L2GDA.
     * Affichez la classe choisie dans un JLabel.
     */
}
