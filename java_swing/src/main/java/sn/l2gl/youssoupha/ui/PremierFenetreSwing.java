package sn.l2gl.youssoupha.ui;

import javax.swing.*;

public class PremierFenetreSwing {
    public static void afficher() {
        SwingUtilities.invokeLater(() -> {
            JFrame fenetre = new JFrame("Ma première fenêtre");

            fenetre.setSize(900, 900);
            fenetre.setLocationRelativeTo(null); // centrée
            fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            fenetre.setVisible(true);
        });

    }
}
