package sn.l2gl.youssoupha.views;

import javax.swing.*;
import java.awt.*;

public class JLabelEtJbutton {
    private JLabelEtJbutton() {
        /* This utility class should not be instantiated */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("GridLayout simple");
        fenetre.setSize(500, 300);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new GridLayout(2, 2));

        JLabel labelTexte = new JLabel("Label avec texte");
        labelTexte.setHorizontalAlignment(SwingConstants.CENTER);

        javax.swing.JButton bouton = new javax.swing.JButton("Mon bouton");
        JPanel panelBouton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBouton.add(bouton);

        JLabel labelIcone = new JLabel();
        labelIcone.setIcon(new ImageIcon(JLabelEtJbutton.class.getResource("/icons/app.png")));
        labelIcone.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelCouleur = new JLabel("Couleur hexadécimale");
        labelCouleur.setOpaque(true);
        labelCouleur.setBackground(Color.decode("#00AEEF"));
        labelCouleur.setHorizontalAlignment(SwingConstants.CENTER);

        fenetre.add(labelTexte);
        fenetre.add(panelBouton);
        fenetre.add(labelIcone);
        fenetre.add(labelCouleur);

        fenetre.setVisible(true);
    }
}
