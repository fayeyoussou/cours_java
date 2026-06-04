package sn.l2gl.youssoupha.ui.components;

/**
 * code UI 4
 */

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

// JPasswordField : saisir un texte masque dans un formulaire de connexion.
public class Code04JPasswordFieldSimple {
    private Code04JPasswordFieldSimple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("UI 04 - JPasswordField");
        fenetre.setSize(420, 240);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel formulaire = new JPanel(new GridLayout(3, 2, 10, 10));
        formulaire.setBorder(BorderFactory.createEmptyBorder(35, 35, 35, 35));

        JTextField champLogin = new JTextField(20);
        JPasswordField champMotDePasse = new JPasswordField(20);
        JButton boutonConnexion = new JButton("Se connecter");

        boutonConnexion.addActionListener(e -> {
            String login = champLogin.getText().trim();
            char[] motDePasse = champMotDePasse.getPassword();

            JLabel labelBonjour = new JLabel("Bonjour " + login, SwingConstants.CENTER);
            labelBonjour.setFont(new Font("Arial", Font.BOLD, 20));

            JButton boutonDeconnexion = new JButton("Se déconnecter");
            JPanel accueil = new JPanel(new BorderLayout(10, 10));
            accueil.setBorder(BorderFactory.createEmptyBorder(35, 35, 35, 35));
            accueil.add(labelBonjour, BorderLayout.CENTER);
            accueil.add(boutonDeconnexion, BorderLayout.SOUTH);
            fenetre.setContentPane(accueil);
            fenetre.revalidate();
            fenetre.repaint();
            Arrays.fill(motDePasse, ' ');
            champMotDePasse.setText("");
            boutonDeconnexion.addActionListener(event -> {
                champLogin.setText("");
                champMotDePasse.setText("");
                fenetre.setContentPane(formulaire);
                fenetre.revalidate();
                fenetre.repaint();
                champLogin.requestFocusInWindow();
            });



        });

        formulaire.add(new JLabel("Login :"));
        formulaire.add(champLogin);
        formulaire.add(new JLabel("Mot de passe :"));
        formulaire.add(champMotDePasse);
        formulaire.add(new JLabel());
        formulaire.add(boutonConnexion);

        fenetre.add(formulaire);
        fenetre.setVisible(true);
    }

    /**
     * CHALLENGE :
     * Refusez la connexion si le login ou le mot de passe est vide.
     * Ajoutez une case a cocher permettant d'afficher ou masquer le mot de passe.
     */
}
