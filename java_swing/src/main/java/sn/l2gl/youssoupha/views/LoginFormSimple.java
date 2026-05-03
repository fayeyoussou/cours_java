package sn.l2gl.youssoupha.views;

import javax.swing.*;
import java.awt.*;

public class LoginFormSimple {
    private LoginFormSimple() {
        /* This utility class should not be instantiated */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("Login simple");
        fenetre.setSize(400, 220);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel labelLogin = new JLabel("Login :");
        JTextField champLogin = new JTextField();

        JLabel labelPassword = new JLabel("Password :");
        JPasswordField champPassword = new JPasswordField();

        JButton boutonSubmit = new JButton("Submit");

        panel.add(labelLogin);
        panel.add(champLogin);
        panel.add(labelPassword);
        panel.add(champPassword);
        panel.add(new JLabel());
        panel.add(boutonSubmit);

        fenetre.add(panel);
        fenetre.setVisible(true);
    }
}
