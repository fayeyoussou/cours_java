package sn.l2gl.youssoupha.ui;

import javax.swing.*;
import java.awt.*;

public class JPanelSimple {
    private JPanelSimple() {
        /* This utility class should not be instantiated */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("JPanel simple");
        fenetre.setSize(900, 900);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new GridLayout(3, 3));

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.BLUE);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.RED);

        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.GREEN);

        JPanel panel4 = new JPanel();
        panel4.setBackground(Color.PINK);
        JPanel panel5 = new JPanel();
        panel3.setBackground(Color.CYAN);

        JPanel panel6 = new JPanel();
        panel4.setBackground(Color.white);

        fenetre.add(panel1);
        fenetre.add(panel2);
        fenetre.add(panel5);
        fenetre.add(panel3);
        fenetre.add(panel4);
        fenetre.add(panel6);

        fenetre.setVisible(true);
    }
}
