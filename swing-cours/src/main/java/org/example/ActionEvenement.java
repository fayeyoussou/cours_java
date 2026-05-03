package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class ActionEvenement {
    private ActionEvenement() {
        throw new UnsupportedOperationException("Creation impossible");
    }
    public static void execute(){

        // CREATION DE LA FENETRE
        JFrame fenetre = new JFrame("Gestion evenement avec swing");
        fenetre.setSize(400, 300);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // AJOUT DU BOUTON DE VALIDATION
        JButton boutonValider = new JButton("Valider");
        boutonValider.setBounds(50, 210, 100, 30);
        // AJOUT DU LISTENER SUR LE BOUTON
        boutonValider.addActionListener(e -> actionPerformed(e));
        // AJOUT DES DIFFERENTS ELEMENTS A LA FENETRE
        fenetre.add(boutonValider);
        fenetre.setVisible(true);
    }
    private static void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        long timestamp = e.getWhen();
        Date date = new Date(timestamp);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        System.out.println("Bouton cliqué : " + source.getText());
        System.out.println("Date et heure de l'action : " + sdf.format(date));
        System.out.println("Commande d'action : " + e.getActionCommand());
        System.out.println("Paramètre d'identification : " + e.paramString());
    }
}
