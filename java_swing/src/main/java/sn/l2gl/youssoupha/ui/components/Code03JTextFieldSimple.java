package sn.l2gl.youssoupha.ui.components;

/**
 * code UI 3
 */

import javax.swing.*;
import java.awt.*;

// JTextField : saisir du texte sur une seule ligne.
public class Code03JTextFieldSimple {
    private Code03JTextFieldSimple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("UI 03 - JTextField");
        fenetre.setSize(420, 180);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 50));

        JLabel label = new JLabel("Nom :");
        JTextField champNom = new JTextField(20);
        champNom.setText("Votre nom");
        JButton bouton = new JButton("Valider");

        bouton.addActionListener(e -> {
            String nom = champNom.getText().trim();
            if (nom.length() < 3) {
                JOptionPane.showMessageDialog(
                        fenetre,
                        "Taille insuffisante"
                );
            } else {
                JOptionPane.showMessageDialog(
                        fenetre,
                        "Bonjour, " + nom + ", Bienvenue dans Swing UI"
                );
            }

        });

        fenetre.add(label);
        fenetre.add(champNom);
        fenetre.add(bouton);
        fenetre.setVisible(true);
    }

    /**
     * CHALLENGE :
     * Refusez un nom vide et affichez un message d'erreur dans une popup.
     * Effacez ensuite le champ apres l'affichage du message de bienvenue.
     */
}
