package sn.l2gl.youssoupha.events.workers;

/**
 * code 19
 */

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.concurrent.ExecutionException;

// DefaultTableModel : ajouter, modifier, supprimer des lignes en temps réel
// Règle : toujours modifier le modèle — ne jamais manipuler la JTable directement
public class JTableDynamique {
    private JTableDynamique() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("JTable dynamique — DefaultTableModel");
        fenetre.setSize(760, 540);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout(10, 10));

        // DefaultTableModel(colonnes, 0) : aucune ligne au départ, s'agrandit dynamiquement
        String[] colonnes = {"ID", "Nom", "Prénom", "Note"};
        DefaultTableModel modele = new DefaultTableModel(colonnes, 0);
        JTable table = new JTable(modele);

        // TableRowSorter : tri par colonne (clic en-tête) ET filtrage des lignes visibles
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modele);
        table.setRowSorter(sorter);

        // --- Formulaire d'ajout ---
        JPanel panelAjout = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        panelAjout.setBorder(BorderFactory.createTitledBorder("Opérations sur la table"));

        JTextField champNom    = new JTextField(10);
        JTextField champPrenom = new JTextField(10);
        JSpinner spinnerNote   = new JSpinner(new SpinnerNumberModel(10.0, 0.0, 20.0, 0.5));

        JButton btnAjouter   = new JButton("Ajouter");
        JButton btnModifier  = new JButton("Modifier");
        JButton btnSupprimer = new JButton("Supprimer");
        JButton btnVider     = new JButton("Vider tout");
        JButton btnAsync     = new JButton("Recharger (SwingWorker)");

        panelAjout.add(new JLabel("Nom :"));    panelAjout.add(champNom);
        panelAjout.add(new JLabel("Prénom :")); panelAjout.add(champPrenom);
        panelAjout.add(new JLabel("Note :"));   panelAjout.add(spinnerNote);
        panelAjout.add(btnAjouter);
        panelAjout.add(btnModifier);
        panelAjout.add(btnSupprimer);
        panelAjout.add(btnVider);
        panelAjout.add(btnAsync);

        // --- Barre de recherche ---
        JPanel panelRecherche = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JTextField champRecherche = new JTextField(22);
        JLabel labelCount = new JLabel("0 ligne(s)");
        panelRecherche.add(new JLabel("Filtrer :"));
        panelRecherche.add(champRecherche);
        panelRecherche.add(labelCount);

        int[] idAuto = {1}; // compteur d'ID auto-incrémenté

        // addRow() ajoute un tableau Object[] en dernière position du modèle
        btnAjouter.addActionListener(e -> {
            String nom    = champNom.getText().trim();
            String prenom = champPrenom.getText().trim();
            if (nom.isEmpty() || prenom.isEmpty()) {
                JOptionPane.showMessageDialog(fenetre, "Nom et prénom requis");
                return;
            }
            modele.addRow(new Object[]{idAuto[0]++, nom, prenom, spinnerNote.getValue()});
            champNom.setText("");
            champPrenom.setText("");
            labelCount.setText(modele.getRowCount() + " ligne(s)");
        });

        // setValueAt(valeur, ligne, colonne) modifie une cellule précise du modèle
        btnModifier.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) { JOptionPane.showMessageDialog(fenetre, "Sélectionnez une ligne"); return; }
            // convertRowIndexToModel() : convertit l'indice vue (après tri/filtre) → indice modèle
            int modelRow = table.convertRowIndexToModel(row);
            if (!champNom.getText().isBlank())    modele.setValueAt(champNom.getText(),    modelRow, 1);
            if (!champPrenom.getText().isBlank())  modele.setValueAt(champPrenom.getText(), modelRow, 2);
            modele.setValueAt(spinnerNote.getValue(), modelRow, 3);
        });

        // removeRow() supprime la ligne à l'indice du modèle — toujours convertir avant
        btnSupprimer.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) { JOptionPane.showMessageDialog(fenetre, "Sélectionnez une ligne"); return; }
            modele.removeRow(table.convertRowIndexToModel(row));
            labelCount.setText(modele.getRowCount() + " ligne(s)");
        });

        // setRowCount(0) vide la table sans détruire la structure des colonnes
        btnVider.addActionListener(e -> {
            modele.setRowCount(0);
            labelCount.setText("0 ligne(s)");
        });

        // Rechargement asynchrone via SwingWorker pour ne pas bloquer l'EDT
        btnAsync.addActionListener(e -> {
            modele.setRowCount(0);
            btnAsync.setEnabled(false);

            new SwingWorker<Object[][], Void>() {
                @Override
                protected Object[][] doInBackground() throws Exception {
                    Thread.sleep(1500); // simule un chargement BDD / API
                    return new Object[][]{
                            {idAuto[0]++, "Ndiaye",  "Awa",      15.5},
                            {idAuto[0]++, "Diallo",  "Moussa",   12.0},
                            {idAuto[0]++, "Fall",    "Fatou",    17.5},
                            {idAuto[0]++, "Sow",     "Ibrahima",  9.0}
                    };
                }
                @Override
                protected void done() {
                    try {
                        for (Object[] ligne : get()) modele.addRow(ligne);
                        labelCount.setText(modele.getRowCount() + " ligne(s)");
                    } catch (InterruptedException | ExecutionException ex) {
                        JOptionPane.showMessageDialog(fenetre, "Erreur : " + ex.getMessage());
                    } finally {
                        btnAsync.setEnabled(true);
                    }
                }
            }.execute();
        });

        // DocumentListener + RowFilter : filtrer les lignes visibles en temps réel
        champRecherche.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { filtrer(); }
            @Override public void removeUpdate(DocumentEvent e) { filtrer(); }
            @Override public void changedUpdate(DocumentEvent e) { }
            private void filtrer() {
                String m = champRecherche.getText().trim();
                // setRowFilter(null) supprime le filtre — toutes les lignes redeviennent visibles
                sorter.setRowFilter(m.isEmpty() ? null : RowFilter.regexFilter("(?i)" + m));
                labelCount.setText(table.getRowCount() + " ligne(s)");
            }
        });

        // Remplir le formulaire à la sélection d'une ligne
        table.getSelectionModel().addListSelectionListener(ev -> {
            if (ev.getValueIsAdjusting()) return;
            int row = table.getSelectedRow();
            if (row < 0) return;
            int mr = table.convertRowIndexToModel(row);
            champNom.setText((String) modele.getValueAt(mr, 1));
            champPrenom.setText((String) modele.getValueAt(mr, 2));
            spinnerNote.setValue(modele.getValueAt(mr, 3));
        });

        JTextArea zoneNote = new JTextArea(
                "À retenir :\n" +
                "• addRow(), setValueAt(), removeRow(), setRowCount(0) → opérations de base sur DefaultTableModel\n" +
                "• Toujours convertir l'indice avant de modifier : table.convertRowIndexToModel(row)\n" +
                "• setRowCount(0) vide la table sans détruire la structure des colonnes\n" +
                "• TableRowSorter : tri par clic en-tête + RowFilter pour filtrer les lignes visibles\n" +
                "• Toutes les modifications du DefaultTableModel doivent se faire sur l'EDT"
        );
        zoneNote.setEditable(false);
        zoneNote.setBackground(Color.decode("#F5F5F5"));
        zoneNote.setFont(new Font("Monospaced", Font.PLAIN, 11));

        JPanel panelSud = new JPanel(new BorderLayout());
        panelSud.add(panelAjout, BorderLayout.NORTH);
        panelSud.add(panelRecherche, BorderLayout.CENTER);
        panelSud.add(zoneNote, BorderLayout.SOUTH);

        fenetre.add(new JScrollPane(table), BorderLayout.CENTER);
        fenetre.add(panelSud, BorderLayout.SOUTH);
        fenetre.setVisible(true);
    }
}
