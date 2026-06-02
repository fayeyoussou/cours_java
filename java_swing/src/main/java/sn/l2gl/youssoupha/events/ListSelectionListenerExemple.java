package sn.l2gl.youssoupha.events;

/**
 * code 7
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

// ListSelectionListener : réagir à la sélection dans une JList ou une JTable
// Branché sur le SelectionModel du composant, pas sur le composant directement
public class ListSelectionListenerExemple {
    private ListSelectionListenerExemple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("ListSelectionListener");
        fenetre.setSize(720, 440);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout(10, 10));

        // --- JTable ---
        String[] colonnes = {"ID", "Nom", "Prénom", "Note"};
        Object[][] donnees = {
                {1, "Ndiaye",  "Awa",      15.5},
                {2, "Diallo",  "Moussa",   12.0},
                {3, "Fall",    "Fatou",    17.5},
                {4, "Sow",     "Ibrahima",  9.0},
                {5, "Mbaye",   "Aïssatou", 14.5}
        };
        DefaultTableModel modele = new DefaultTableModel(donnees, colonnes) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // table en lecture seule
            }
        };
        JTable table = new JTable(modele);

        // --- Formulaire de détail rempli à la sélection ---
        JPanel panelDetail = new JPanel(new GridLayout(5, 2, 10, 10));
        panelDetail.setBorder(BorderFactory.createTitledBorder("Détail sélectionné"));
        panelDetail.setPreferredSize(new Dimension(260, 0));

        JTextField champId     = new JTextField();
        JTextField champNom    = new JTextField();
        JTextField champPrenom = new JTextField();
        JTextField champNote   = new JTextField();
        champId.setEditable(false);
        champNom.setEditable(false);
        champPrenom.setEditable(false);
        champNote.setEditable(false);

        panelDetail.add(new JLabel("ID :"));    panelDetail.add(champId);
        panelDetail.add(new JLabel("Nom :"));   panelDetail.add(champNom);
        panelDetail.add(new JLabel("Prénom :")); panelDetail.add(champPrenom);
        panelDetail.add(new JLabel("Note :"));  panelDetail.add(champNote);

        // ListSelectionListener branché sur le modèle de sélection de la JTable
        table.getSelectionModel().addListSelectionListener(e -> {
            // getValueIsAdjusting() = true pendant la sélection en cours — ignorer ces états transitoires
            if (e.getValueIsAdjusting()) return;
            int row = table.getSelectedRow();
            if (row < 0) return; // aucune ligne sélectionnée

            // getValueAt(ligne, colonne) lit la valeur dans le modèle
            champId.setText(    String.valueOf(table.getValueAt(row, 0)));
            champNom.setText(   (String) table.getValueAt(row, 1));
            champPrenom.setText((String) table.getValueAt(row, 2));
            champNote.setText(  String.valueOf(table.getValueAt(row, 3)));
        });

        // --- JList avec le même pattern ---
        JList<String> listeLangages = new JList<>(new String[]{"Java", "Python", "JavaScript", "PHP", "Rust"});
        listeLangages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JLabel labelSelection = new JLabel("Sélectionnez un élément");

        // Même interface ListSelectionListener — getValueIsAdjusting() à vérifier ici aussi
        listeLangages.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String val = listeLangages.getSelectedValue();
                if (val != null) labelSelection.setText("Sélectionné : " + val);
            }
        });

        JPanel panelListe = new JPanel(new BorderLayout(5, 5));
        panelListe.setBorder(BorderFactory.createTitledBorder("JList"));
        panelListe.setPreferredSize(new Dimension(180, 0));
        panelListe.add(new JScrollPane(listeLangages), BorderLayout.CENTER);
        panelListe.add(labelSelection, BorderLayout.SOUTH);

        JTextArea zoneNote = new JTextArea(
                "À retenir :\n" +
                "• Branché sur getSelectionModel() — pas sur la JTable ou JList directement\n" +
                "• getValueIsAdjusting() = true pendant la sélection → toujours vérifier avant d'agir\n" +
                "• table.getSelectedRow() retourne -1 si aucune ligne n'est sélectionnée\n" +
                "• table.convertRowIndexToModel(row) → convertir indice vue → modèle quand le tri est actif"
        );
        zoneNote.setEditable(false);
        zoneNote.setBackground(Color.decode("#F5F5F5"));
        zoneNote.setFont(new Font("Monospaced", Font.PLAIN, 11));

        fenetre.add(new JScrollPane(table), BorderLayout.CENTER);
        fenetre.add(panelDetail, BorderLayout.EAST);
        fenetre.add(panelListe, BorderLayout.WEST);
        fenetre.add(zoneNote, BorderLayout.SOUTH);
        fenetre.setVisible(true);
    }
}
