package sn.l2gl.youssoupha.events;

/**
 * code 9
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Lambdas : syntaxe concise pour les interfaces fonctionnelles (une seule méthode abstraite)
// Adapters : classes abstraites avec implémentations vides — surcharger uniquement ce dont on a besoin
public class LambdasEtAdapters {
    private LambdasEtAdapters() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("Lambdas & Adapters");
        fenetre.setSize(700, 500);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fenetre.setLayout(new BorderLayout(0, 0));

        // --- Section 1 : Lambda vs Classe anonyme ---
        JPanel panelLambda = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        panelLambda.setBorder(BorderFactory.createTitledBorder("Lambda (interface fonctionnelle = 1 méthode abstraite)"));

        JButton boutonAnonyme  = new JButton("Classe anonyme");
        JButton boutonLambda   = new JButton("Lambda équivalente");
        JLabel  labelResult    = new JLabel("—");
        int[]   compteur       = {0}; // tableau d'un élément pour contourner la règle effectively-final

        // Classe anonyme — impose d'écrire @Override et la signature complète
        boutonAnonyme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compteur[0]++;
                labelResult.setText("Classe anonyme — clics : " + compteur[0]);
            }
        });

        // Lambda — équivalente à la classe anonyme ci-dessus, possible car ActionListener
        // est une interface fonctionnelle (@FunctionalInterface) avec une seule méthode abstraite
        boutonLambda.addActionListener(e -> {
            compteur[0]++;
            labelResult.setText("Lambda — clics : " + compteur[0]);
        });

        panelLambda.add(boutonAnonyme);
        panelLambda.add(boutonLambda);
        panelLambda.add(labelResult);

        JTextArea noteInterfaceFonctionnelle = new JTextArea(
                "✓ Utiliser une lambda : ActionListener, ItemListener, ChangeListener, ListSelectionListener…\n" +
                "✗ Impossible en lambda  : MouseListener, KeyListener, WindowListener (5+ méthodes)"
        );
        noteInterfaceFonctionnelle.setEditable(false);
        noteInterfaceFonctionnelle.setBackground(panelLambda.getBackground());
        noteInterfaceFonctionnelle.setFont(new Font("Monospaced", Font.PLAIN, 11));
        panelLambda.add(noteInterfaceFonctionnelle);

        // --- Section 2 : Adapter vs Interface directe ---
        JPanel panelAdapter = new JPanel(new BorderLayout(10, 10));
        panelAdapter.setBorder(BorderFactory.createTitledBorder("Adapter (surcharger uniquement les méthodes utiles)"));

        JLabel zoneHover = new JLabel("Survolez-moi (MouseAdapter — 3 méthodes au lieu de 5)", SwingConstants.CENTER);
        zoneHover.setOpaque(true);
        zoneHover.setBackground(Color.LIGHT_GRAY);
        zoneHover.setFont(new Font("Arial", Font.PLAIN, 14));
        zoneHover.setPreferredSize(new Dimension(500, 60));

        JLabel labelAdapterResult = new JLabel("—", SwingConstants.CENTER);

        // MouseAdapter est une classe abstraite qui implémente MouseListener avec des corps vides
        // Sans MouseAdapter, MouseListener impose 5 méthodes obligatoires (4 seraient vides et inutiles)
        zoneHover.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                zoneHover.setBackground(Color.decode("#2196F3"));
                zoneHover.setForeground(Color.WHITE);
                labelAdapterResult.setText("mouseEntered()");
            }
            @Override
            public void mouseExited(MouseEvent e) {
                zoneHover.setBackground(Color.LIGHT_GRAY);
                zoneHover.setForeground(Color.BLACK);
                labelAdapterResult.setText("mouseExited()");
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                // getClickCount() pour distinguer simple et double-clic
                labelAdapterResult.setText("mouseClicked() — " + e.getClickCount() + " clic(s)");
            }
        });

        // WindowAdapter — surcharger uniquement windowClosing, les 6 autres restent vides
        fenetre.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                fenetre.dispose();
            }
        });

        JPanel panelZone = new JPanel(new FlowLayout());
        panelZone.add(zoneHover);
        panelAdapter.add(panelZone, BorderLayout.CENTER);
        panelAdapter.add(labelAdapterResult, BorderLayout.SOUTH);

        JTextArea zoneNote = new JTextArea(
                "À retenir :\n" +
                "• Lambda utilisable uniquement avec une interface fonctionnelle (une seule méthode abstraite)\n" +
                "• ActionListener, ItemListener, ChangeListener → 1 méthode → lambda possible\n" +
                "• MouseListener, KeyListener, WindowListener → plusieurs méthodes → utiliser un Adapter\n" +
                "• MouseAdapter, KeyAdapter, WindowAdapter, FocusAdapter → n'override que les méthodes utiles"
        );
        zoneNote.setEditable(false);
        zoneNote.setBackground(Color.decode("#F5F5F5"));
        zoneNote.setFont(new Font("Monospaced", Font.PLAIN, 11));

        JPanel panelPrincipal = new JPanel(new GridLayout(2, 1, 0, 5));
        panelPrincipal.add(panelLambda);
        panelPrincipal.add(panelAdapter);

        fenetre.add(panelPrincipal, BorderLayout.CENTER);
        fenetre.add(zoneNote, BorderLayout.SOUTH);
        fenetre.setVisible(true);
    }
}
