package sn.l2gl.youssoupha.events;

/**
 * code 8
 */

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.regex.Pattern;

// DocumentListener : réagir à CHAQUE modification du texte (frappe, copier-coller, setText(), undo)
// Avantage sur KeyListener : capte toutes les modifications quelle qu'en soit l'origine
public class DocumentListenerExemple {
    private DocumentListenerExemple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("DocumentListener — recherche en temps réel + compteur");
        fenetre.setSize(620, 480);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout(10, 10));

        // --- Table de données filtrée en temps réel ---
        String[] colonnes = {"Nom", "Prénom", "Classe", "Ville"};
        Object[][] donnees = {
                {"Ndiaye",  "Awa",      "L2",     "Dakar"},
                {"Diallo",  "Moussa",   "L1",     "Thiès"},
                {"Fall",    "Fatou",    "L3",     "Dakar"},
                {"Sow",     "Ibrahima", "L2",     "Saint-Louis"},
                {"Mbaye",   "Aïssatou", "Master", "Dakar"},
                {"Camara",  "Seydou",   "L1",     "Ziguinchor"},
                {"Bah",     "Mariama",  "L3",     "Tambacounda"}
        };
        DefaultTableModel modele = new DefaultTableModel(donnees, colonnes);
        JTable table = new JTable(modele);
        // TableRowSorter : filtre les lignes visibles sans modifier le modèle sous-jacent
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modele);
        table.setRowSorter(sorter);

        JTextField champRecherche = new JTextField();
        JLabel labelResultats = new JLabel(donnees.length + " résultat(s)");

        // DocumentListener : 3 méthodes obligatoires
        champRecherche.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // insertUpdate : du texte vient d'être ajouté (frappe, collage)
                filtrer();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                // removeUpdate : du texte vient d'être supprimé (touche Suppr, couper)
                filtrer();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                // changedUpdate : changement d'attribut de style — non déclenché pour un JTextField plain
            }

            private void filtrer() {
                String motif = champRecherche.getText().trim();
                if (motif.isEmpty()) {
                    sorter.setRowFilter(null); // null supprime le filtre — toutes les lignes visibles
                } else {
                    // Pattern.quote() échappe les caractères spéciaux regex dans la saisie utilisateur
                    // "(?i)" rend la recherche insensible à la casse
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(motif)));
                }
                labelResultats.setText(table.getRowCount() + " résultat(s)");
            }
        });

        // --- Compteur de caractères sur JTextArea ---
        JPanel panelCompteur = new JPanel(new BorderLayout(5, 5));
        panelCompteur.setBorder(BorderFactory.createTitledBorder("Compteur de caractères (max 200)"));
        JTextArea zoneTexte = new JTextArea(3, 40);
        JLabel labelCompteur = new JLabel("0 / 200");

        // getDocument() donne accès au modèle de texte — fonctionne sur JTextField et JTextArea
        zoneTexte.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { actualiser(); }
            @Override public void removeUpdate(DocumentEvent e) { actualiser(); }
            @Override public void changedUpdate(DocumentEvent e) { }

            private void actualiser() {
                int n = zoneTexte.getText().length();
                labelCompteur.setText(n + " / 200");
                labelCompteur.setForeground(n > 180 ? Color.RED : Color.BLACK);
            }
        });

        panelCompteur.add(new JScrollPane(zoneTexte), BorderLayout.CENTER);
        panelCompteur.add(labelCompteur, BorderLayout.SOUTH);

        JPanel panelRecherche = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        panelRecherche.add(new JLabel("Recherche :"));
        panelRecherche.add(champRecherche);
        champRecherche.setPreferredSize(new Dimension(200, 26));
        panelRecherche.add(labelResultats);

        fenetre.add(panelRecherche, BorderLayout.NORTH);
        fenetre.add(new JScrollPane(table), BorderLayout.CENTER);
        fenetre.add(panelCompteur, BorderLayout.SOUTH);
        fenetre.setVisible(true);
    }
}
