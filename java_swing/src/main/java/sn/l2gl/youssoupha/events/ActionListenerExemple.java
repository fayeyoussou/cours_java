package sn.l2gl.youssoupha.events;

/**
 * code 1
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// ActionListener : l'événement le plus courant — clic bouton, Enter dans un champ, sélection menu
public class ActionListenerExemple {
    private ActionListenerExemple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("ActionListener");
        fenetre.setSize(550, 380);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout(10, 10));

        JPanel panelSaisie = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JTextField champNom = new JTextField(15);
        JButton boutonValider = new JButton("Valider");
        JButton boutonEffacer = new JButton("Effacer");
        panelSaisie.add(new JLabel("Nom :"));
        panelSaisie.add(champNom);
        panelSaisie.add(boutonValider);
        panelSaisie.add(boutonEffacer);

        JTextArea zoneLog = new JTextArea(10, 45);
        zoneLog.setEditable(false);
        zoneLog.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Classe anonyme — syntaxe verbose qui oblige à implémenter la méthode nommée
        boutonValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // e.getSource() retourne le composant qui a déclenché l'événement
                String source = ((JButton) e.getSource()).getText();
                zoneLog.append("[" + source + "] nom saisi : " + champNom.getText() + "\n");
            }
        });

        // Lambda — syntaxe concise Java 8+, identique à la classe anonyme ci-dessus
        // Possible car ActionListener n'a qu'UNE seule méthode abstraite (interface fonctionnelle)
        boutonEffacer.addActionListener(e -> {
            zoneLog.setText("");         // vider le journal
            champNom.setText("");        // vider le champ
            champNom.requestFocus();     // redonner le focus au champ
        });

        // ActionListener sur JTextField : déclenché quand l'utilisateur appuie sur Entrée
        champNom.addActionListener(e -> zoneLog.append("[Entrée] valeur : " + champNom.getText() + "\n"));

        // Menu avec ActionListener sur JMenuItem
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFichier = new JMenu("Fichier");
        JMenuItem itemNouvel = new JMenuItem("Nouveau");
        JMenuItem itemQuitter = new JMenuItem("Quitter");
        itemNouvel.addActionListener(e -> zoneLog.append("[Menu] Nouveau sélectionné\n"));
        // e.getActionCommand() retourne le texte du composant (utile pour distinguer plusieurs items)
        itemQuitter.addActionListener(e -> System.exit(0));
        menuFichier.add(itemNouvel);
        menuFichier.addSeparator();
        menuFichier.add(itemQuitter);
        menuBar.add(menuFichier);

        fenetre.setJMenuBar(menuBar);
        JTextArea zoneNote = new JTextArea(
                "À retenir :\n" +
                "• Fonctionne sur : JButton, JMenuItem, JTextField (Enter), JComboBox, JRadioButton, Timer\n" +
                "• e.getSource()        → composant ayant déclenché l'événement\n" +
                "• e.getActionCommand() → texte associé (souvent le label du bouton)\n" +
                "• Interface fonctionnelle (1 méthode) → remplaçable par une lambda"
        );
        zoneNote.setEditable(false);
        zoneNote.setBackground(Color.decode("#F5F5F5"));
        zoneNote.setFont(new Font("Monospaced", Font.PLAIN, 11));

        fenetre.add(panelSaisie, BorderLayout.NORTH);
        fenetre.add(new JScrollPane(zoneLog), BorderLayout.CENTER);
        fenetre.add(zoneNote, BorderLayout.SOUTH);
        fenetre.setVisible(true);
    }
}
