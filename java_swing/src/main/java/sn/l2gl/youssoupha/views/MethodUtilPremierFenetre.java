package sn.l2gl.youssoupha.views;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MethodUtilPremierFenetre {
    private MethodUtilPremierFenetre() {
        /* This utility class should not be instantiated */
    }

    public static void afficher(){
        JFrame fenetre = new JFrame("Application");

        // Taille et position
        fenetre.setSize(800, 600);
        fenetre.setMinimumSize(new Dimension(400, 300));
        fenetre.setLocationRelativeTo(null);
        // fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH); // plein écran

        // Comportement à la fermeture
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // .DISPOSE_ON_CLOSE -> ferme juste la fenêtre
        // .HIDE_ON_CLOSE -> cache la fenêtre
        // .DO_NOTHING_ON_CLOSE -> intercepter manuellement

        // Icône dans la barre de titre
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(MethodUtilPremierFenetre.class.getResource("/icons/app.png")));
        fenetre.setIconImage(icon.getImage());

// Empêcher le redimensionnement
// fenetre.setResizable(false);
        fenetre.setVisible(true);
    }
}
