package sn.l2gl.youssoupha.views;

import javax.swing.*;
import java.awt.*;

public class JPanelDansJPanel {
    private JPanelDansJPanel() {
        /* This utility class should not be instantiated */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("JPanel dans JPanel");
        fenetre.setSize(600, 400);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new GridLayout(2, 2, 10, 10));

        JPanel panel1 = creerGrandPanel(Color.BLUE);
        JPanel panel2 = creerGrandPanel(Color.RED);
        JPanel panel3 = creerGrandPanel(Color.GREEN);
        JPanel panel4 = creerGrandPanel(Color.PINK);

        fenetre.add(panel1);
        fenetre.add(panel2);
        fenetre.add(panel3);
        fenetre.add(panel4);

        fenetre.setVisible(true);
    }

    private static JPanel creerGrandPanel(Color couleur) {
        JPanel grandPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        grandPanel.setBackground(couleur);
        grandPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel petitPanel1 = new JPanel();
        petitPanel1.setBackground(Color.WHITE);

        JPanel petitPanel2 = new JPanel();
        petitPanel2.setBackground(Color.LIGHT_GRAY);

        JPanel petitPanel3 = new JPanel();
        petitPanel3.setBackground(Color.DARK_GRAY);

        grandPanel.add(petitPanel1);
        grandPanel.add(petitPanel2);
        grandPanel.add(petitPanel3);

        return grandPanel;
    }
}
