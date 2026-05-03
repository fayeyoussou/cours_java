package org.example;

import javax.swing.*;
import java.awt.*;

public abstract class LayoutAvecMenuExemple {
    private LayoutAvecMenuExemple() {
        throw new UnsupportedOperationException("Création impossible");
    }

    public static void execute() {

        // CREATION DE LA FENETRE PRINCIPALE
        JFrame fenetre = new JFrame("Interface complète avec Layouts et Menu");
        fenetre.setSize(600, 400);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout()); // Layout principal

        // ---- AJOUT DE LA BARRE DE MENU EN HAUT
        JMenuBar barreMenu = new JMenuBar();

        // CREATION DES MENUS
        JMenu menuFichier = new JMenu("Fichier");
        JMenu menuEdition = new JMenu("Édition");
        JMenu menuAide = new JMenu("Aide");

        // AJOUT DES ÉLÉMENTS AUX MENUS
        menuFichier.add(new JMenuItem("Nouveau"));
        menuFichier.add(new JMenuItem("Ouvrir"));
        menuFichier.addSeparator();
        JMenuItem itemQuitter = new JMenuItem("Quitter");
        itemQuitter.addActionListener(actionEffectue -> System.exit(0));
        menuFichier.add(itemQuitter);

        menuEdition.add(new JMenuItem("Copier"));
        menuEdition.add(new JMenuItem("Coller"));

        menuAide.add(new JMenuItem("À propos"));

        // AJOUT DES MENUS A LA BARRE
        barreMenu.add(menuFichier);
        barreMenu.add(menuEdition);
        barreMenu.add(menuAide);

        // AJOUT DE LA BARRE DE MENU À LA FENÊTRE
        fenetre.setJMenuBar(barreMenu);

        // ---- ZONE NORD : PANEL EN FlowLayout (barre de boutons)
        JPanel panelHaut = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelHaut.add(new JButton("Accueil"));
        panelHaut.add(new JButton("Profil"));
        panelHaut.add(new JButton("Déconnexion"));
        fenetre.add(panelHaut, BorderLayout.NORTH);

        // ---- ZONE CENTRE : PANEL EN GridLayout (grille de boutons)
        JPanel panelCentre = new JPanel(new GridLayout(2, 2, 10, 10));
        panelCentre.add(new JButton("Option 1"));
        panelCentre.add(new JButton("Option 2"));
        panelCentre.add(new JButton("Option 3"));
        panelCentre.add(new JButton("Option 4"));
        fenetre.add(panelCentre, BorderLayout.CENTER);

        // ---- ZONE SUD : FOOTER SIMPLE
        JLabel footer = new JLabel("© Youdev 2025 - Mon application Swing", SwingConstants.CENTER);
        fenetre.add(footer, BorderLayout.SOUTH);

        // AFFICHAGE DE LA FENETRE
        fenetre.setVisible(true);
    }
}
