package sn.l2gl.youssoupha.events;

/**
 * code 5
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// WindowListener : réagir aux événements de cycle de vie d'une fenêtre (ouverture, fermeture…)
// FocusListener : réagir au gain ou à la perte de focus d'un composant
public class WindowEtFocusListenerExemple {
    private WindowEtFocusListenerExemple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("WindowListener & FocusListener");
        fenetre.setSize(500, 350);
        fenetre.setLocationRelativeTo(null);
        // DO_NOTHING_ON_CLOSE : la fermeture est entièrement gérée par le WindowListener
        fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fenetre.setLayout(new BorderLayout(10, 10));

        JPanel panelFormulaire = new JPanel(new GridLayout(5, 2, 10, 10));
        panelFormulaire.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JTextField champNom = new JTextField();
        JLabel labelErreurNom = new JLabel();
        labelErreurNom.setForeground(Color.RED);

        JTextField champEmail = new JTextField();
        JLabel labelErreurEmail = new JLabel();
        labelErreurEmail.setForeground(Color.RED);

        // FocusAdapter — valider le champ à la sortie (Tab ou clic sur un autre composant)
        champNom.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // focusGained : le composant vient de recevoir le focus
                champNom.setBackground(Color.decode("#E8F4FD"));
            }
            @Override
            public void focusLost(FocusEvent e) {
                // focusLost : le composant vient de perdre le focus — idéal pour valider
                if (champNom.getText().trim().isEmpty()) {
                    champNom.setBackground(Color.PINK);
                    labelErreurNom.setText("Nom requis");
                } else {
                    champNom.setBackground(Color.WHITE);
                    labelErreurNom.setText("");
                }
            }
        });

        champEmail.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                champEmail.setBackground(Color.decode("#E8F4FD"));
            }
            @Override
            public void focusLost(FocusEvent e) {
                String v = champEmail.getText();
                // Valider le format email uniquement si le champ n'est pas vide
                if (!v.isEmpty() && !v.matches(".+@.+\\..+")) {
                    champEmail.setBackground(Color.PINK);
                    labelErreurEmail.setText("Email invalide (format : nom@domaine.xx)");
                } else {
                    champEmail.setBackground(Color.WHITE);
                    labelErreurEmail.setText("");
                }
            }
        });

        panelFormulaire.add(new JLabel("Nom :"));
        panelFormulaire.add(champNom);
        panelFormulaire.add(new JLabel(""));
        panelFormulaire.add(labelErreurNom);
        panelFormulaire.add(new JLabel("Email :"));
        panelFormulaire.add(champEmail);
        panelFormulaire.add(new JLabel(""));
        panelFormulaire.add(labelErreurEmail);

        // WindowAdapter — intercepter la fermeture pour demander confirmation
        fenetre.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                // windowOpened : déclenché une seule fois, à la première apparition de la fenêtre
                champNom.requestFocusInWindow();
            }
            @Override
            public void windowClosing(WindowEvent e) {
                // windowClosing : déclenché quand l'utilisateur clique sur la croix
                int rep = JOptionPane.showConfirmDialog(
                        fenetre,
                        "Voulez-vous vraiment quitter ?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION
                );
                // dispose() ferme et libère les ressources de la fenêtre
                if (rep == JOptionPane.YES_OPTION) fenetre.dispose();
            }
        });

        fenetre.add(panelFormulaire, BorderLayout.CENTER);
        fenetre.add(new JLabel("  Fermez la fenêtre pour voir la confirmation de WindowListener"), BorderLayout.SOUTH);
        fenetre.setVisible(true);
    }
}
