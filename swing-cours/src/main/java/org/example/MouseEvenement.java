package org.example;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class MouseEvenement {
    private MouseEvenement() {
        throw new UnsupportedOperationException("Création impossible");
    }

    public static void execute() {

        // CREATION DE LA FENETRE
        JFrame fenetre = new JFrame("Gestion évènement souris avec Swing");
        fenetre.setSize(400, 300);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.setLayout(null); // Positionnement absolu

        // CREATION D'UNE ETIQUETTE CLIQUABLE
        JLabel label = new JLabel("Cliquez ici avec la souris");
        label.setBounds(100, 100, 200, 30);

        // AJOUT DU LISTENER SUR LE LABEL
        label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // QUAND LA SOURIS CLIQUE SUR LE COMPOSANT
                afficherInfos(e, "mouseClicked");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // QUAND LE BOUTON DE LA SOURIS EST ENFONCÉ
                afficherInfos(e, "mousePressed");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // QUAND LE BOUTON DE LA SOURIS EST RELÂCHÉ
                afficherInfos(e, "mouseReleased");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // QUAND LA SOURIS ENTRE DANS LA ZONE DU COMPOSANT
                afficherInfos(e, "mouseEntered");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // QUAND LA SOURIS QUITTE LA ZONE DU COMPOSANT
                afficherInfos(e, "mouseExited");
            }
        });

        // AJOUT DES ELEMENTS A LA FENETRE
        fenetre.add(label);
        fenetre.setVisible(true);
    }

    private static void afficherInfos(MouseEvent e, String type) {
        Object source = e.getSource();
        long timestamp = e.getWhen();
        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        System.out.println("Événement souris détecté : " + type);
        System.out.println("Composant source : " + source.getClass().getSimpleName());
        System.out.println("Date et heure : " + sdf.format(date));
        System.out.println("Position : (" + e.getX() + ", " + e.getY() + ")");
        System.out.println("Paramètre d'identification : " + e.paramString());
        System.out.println("----------------------------");
    }
}
