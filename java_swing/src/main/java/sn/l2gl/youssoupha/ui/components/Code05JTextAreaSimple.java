package sn.l2gl.youssoupha.ui.components;

/**
 * code UI 5
 */

import javax.swing.*;

// JTextArea : saisir plusieurs lignes de texte. Toujours l'utiliser dans un JScrollPane.
public class Code05JTextAreaSimple {
    private Code05JTextAreaSimple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("UI 05 - JTextArea");
        fenetre.setSize(450, 280);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea zoneMessage = new JTextArea(8, 30);
        zoneMessage.setLineWrap(true);
        zoneMessage.setWrapStyleWord(true);
        zoneMessage.setText("Ecrivez votre message ici...");

        fenetre.add(new JScrollPane(zoneMessage));
        fenetre.setVisible(true);
    }

    /**
     * CHALLENGE :
     * Ajoutez un JLabel affichant le nombre de caracteres saisis.
     * Limitez ensuite le message a 200 caracteres.
     */
}
