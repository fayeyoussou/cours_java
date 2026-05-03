package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class KeyEvenement {
    private KeyEvenement() {
        throw new UnsupportedOperationException("Création impossible");
    }

    public static void execute() {

        // CREATION DE LA FENETRE
        JFrame fenetre = new JFrame("Gestion évènement clavier avec Swing");
        fenetre.setSize(400, 300);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.setLayout(null); // Positionnement absolu

        // CREATION D'UN CHAMP DE TEXTE POUR LA SAISIE
        JTextField champTexte = new JTextField();
        champTexte.setBounds(100, 100, 200, 30);
        JButton boutonValider = new JButton("Valider");
        boutonValider.setBounds(50, 210, 100, 30);
        // AJOUT DU LISTENER SUR LE BOUTON
        boutonValider.addActionListener(e -> actionPerformed(champTexte));
        // AJOUT DU LISTENER CLAVIER SUR LE CHAMP
        champTexte.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // QUAND UNE TOUCHE EST TAPÉE (CHAR)
                afficherInfos(e, "keyTyped");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // QUAND UNE TOUCHE EST ENFONCÉE
                afficherInfos(e, "keyPressed");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // QUAND UNE TOUCHE EST RELÂCHÉE
                afficherInfos(e, "keyReleased");
            }
        });

        // AJOUT DES ELEMENTS A LA FENETRE
        fenetre.add(champTexte);
        fenetre.add(boutonValider);
        fenetre.setVisible(true);
    }
    private static void actionPerformed(JTextField textField) {
        System.out.println(textField.getText());
    }

    private static void afficherInfos(KeyEvent e, String type) {
        Object source = e.getSource();
        long timestamp = e.getWhen();
        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        System.out.println("Événement clavier détecté : " + type);
        System.out.println("Composant source : " + source.getClass().getSimpleName());
        System.out.println("Date et heure : " + sdf.format(date));
        System.out.println("Code touche : " + e.getKeyCode() + " (" + KeyEvent.getKeyText(e.getKeyCode()) + ")");
        System.out.println("Caractère associé : '" + e.getKeyChar() + "'");
        System.out.println("Paramètre d'identification : " + e.paramString());
        System.out.println("----------------------------");
    }
}
