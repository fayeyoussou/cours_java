package org.example;

import javax.swing.*;

public abstract class PremiereFenetre {
    public static void execute(){
        JFrame fenetre = new JFrame("Première fenêtre java swing du cours de l2");
        fenetre.setSize(400, 300);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.setLayout(null);

        JLabel labelNom = new JLabel("Nom :");
        labelNom.setBounds(50, 50, 100, 20);
        JTextField champNom = new JTextField();
        champNom.setBounds(60, 90, 150, 20);
        fenetre.add(champNom);
        JButton boutonValider = new JButton("Valider");
        boutonValider.setBounds(50, 130, 100, 30);
        fenetre.add(labelNom);
        fenetre.add(boutonValider);
        fenetre.setVisible(true);
    }
}
