package org.example;

import javax.swing.*;

public abstract class GestionEvenement {
    private GestionEvenement() {
        throw new UnsupportedOperationException("Creation impossible");
    }
    public static void execute(){

        // CREATION DE LA FENETRE
        JFrame fenetre = new JFrame("Gestion evenement avec swing");
        fenetre.setSize(400, 300);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.setLayout(null);

        // CREATION DU FORMULAIRE NOM
        JLabel labelNom = new JLabel("Nom :");
        labelNom.setBounds(50, 50, 100, 20);
        JTextField champNom = new JTextField();
        champNom.setBounds(60, 90, 150, 20);
        // CREATION DU FORMULAIRE PRENOM
        JLabel labelPrenom = new JLabel("Prenom :");
        labelPrenom.setBounds(50, 130, 100, 20);
        JTextField champsPrenom = new JTextField();
        champsPrenom.setBounds(60, 170, 150, 20);
        // AJOUT DU BOUTON DE VALIDATION
        JButton boutonValider = new JButton("Valider");
        boutonValider.setBounds(50, 210, 100, 30);
        // AJOUT DU LISTENER SUR LE BOUTON
        boutonValider.addActionListener(actionEffectue -> JOptionPane.
                showMessageDialog(fenetre, "Bonjour,"+
                        champsPrenom.getText()+" "+champNom.getText()));
        // AJOUT DES DIFFERENTS ELEMENTS A LA FENETRE
        fenetre.add(champNom);
        fenetre.add(labelNom);
        fenetre.add(champsPrenom);
        fenetre.add(labelPrenom);
//        fenetre.add(boutonValider);
        fenetre.setVisible(true);
    }
}
