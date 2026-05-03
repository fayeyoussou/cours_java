package sn.l2gl.youssoupha.views;

import javax.swing.*;
import java.awt.*;

public class JPanelSimple {
    private JPanelSimple() {
        /* This utility class should not be instantiated */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("JPanel simple");
        fenetre.setSize(500, 300);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new GridLayout(2, 2));

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.BLUE);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.RED);

        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.GREEN);

        JPanel panel4 = new JPanel();
        panel4.setBackground(Color.PINK);

        fenetre.add(panel1);
        fenetre.add(panel2);
        fenetre.add(panel3);
        fenetre.add(panel4);
        fenetre.setVisible(true);
    }
}
