package sn.l2gl.youssoupha.events;

/**
 * code 12
 */

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

// Interface complète reprenant la structure visuelle du cours UI et y ajoutant tous les événements :
// ActionListener, ItemListener, DocumentListener, ListSelectionListener, SwingWorker, WindowAdapter
public class InterfaceCompleteEvents {
    private InterfaceCompleteEvents() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        SwingUtilities.invokeLater(() -> {
            JFrame fenetre = new JFrame("Interface Swing Dynamic — Application complète");
            fenetre.setSize(950, 620);
            fenetre.setLocationRelativeTo(null);
            // DO_NOTHING_ON_CLOSE : le WindowListener gère la fermeture
            fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            fenetre.setLayout(new BorderLayout(0, 0));

            // --- Modèle de données ---
            String[] colonnes = {"ID", "Nom", "Prénom", "Note"};
            DefaultTableModel modele = new DefaultTableModel(colonnes, 0) {
                @Override public boolean isCellEditable(int r, int c) { return false; }
            };
            JTable table = new JTable(modele);
            // TableRowSorter : tri par clic sur l'en-tête + filtre textuel
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modele);
            table.setRowSorter(sorter);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            int[] idAuto = {1};

            // --- Header ---
            JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
            header.setBackground(Color.decode("#2D6CDF"));
            JLabel titreApp = new JLabel("Gestion Étudiants — Swing Dynamic");
            titreApp.setFont(new Font("Arial", Font.BOLD, 20));
            titreApp.setForeground(Color.WHITE);
            JButton btnCharger   = new JButton("Charger (async)");
            JButton btnExporter  = new JButton("Exporter CSV");
            JToggleButton btnMode = new JToggleButton("Lecture seule");
            header.add(titreApp);
            header.add(btnCharger);
            header.add(btnExporter);
            header.add(btnMode);

            // --- Sidebar ---
            JPanel sidebar = new JPanel();
            sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
            sidebar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            sidebar.setBackground(Color.decode("#F1F3F5"));
            sidebar.setPreferredSize(new Dimension(160, 0));
            JButton btnDashboard = new JButton("Dashboard");
            JButton btnFormulaire = new JButton("Formulaire");
            JButton btnStats     = new JButton("Statistiques");
            for (JButton b : new JButton[]{btnDashboard, btnFormulaire, btnStats}) {
                b.setAlignmentX(Component.CENTER_ALIGNMENT);
                sidebar.add(b);
                sidebar.add(Box.createVerticalStrut(8));
            }

            // --- Panneau formulaire de détail (EAST) ---
            JPanel panelDetail = new JPanel();
            panelDetail.setLayout(new BoxLayout(panelDetail, BoxLayout.Y_AXIS));
            panelDetail.setBorder(BorderFactory.createTitledBorder("Détail sélectionné"));
            panelDetail.setPreferredSize(new Dimension(200, 0));

            JTextField dId = new JTextField(); dId.setEditable(false); dId.setMaximumSize(new Dimension(Short.MAX_VALUE, 28));
            JTextField dNom    = new JTextField(); dNom.setMaximumSize(new Dimension(Short.MAX_VALUE, 28));
            JTextField dPrenom = new JTextField(); dPrenom.setMaximumSize(new Dimension(Short.MAX_VALUE, 28));
            JSpinner  dNote    = new JSpinner(new SpinnerNumberModel(10.0, 0.0, 20.0, 0.5)); dNote.setMaximumSize(new Dimension(Short.MAX_VALUE, 28));
            JButton btnSauver  = new JButton("Sauvegarder"); btnSauver.setAlignmentX(Component.CENTER_ALIGNMENT);
            JButton btnSuppr   = new JButton("Supprimer");   btnSuppr.setAlignmentX(Component.CENTER_ALIGNMENT);
            for (String[] pair : new String[][]{{"ID :", null}, {"Nom :", null}, {"Prénom :", null}, {"Note :", null}}) {
                panelDetail.add(new JLabel(pair[0]));
                panelDetail.add(Box.createVerticalStrut(3));
            }
            // Réassembler proprement
            panelDetail.removeAll();
            panelDetail.add(new JLabel("ID :"));    panelDetail.add(dId);    panelDetail.add(Box.createVerticalStrut(5));
            panelDetail.add(new JLabel("Nom :"));   panelDetail.add(dNom);   panelDetail.add(Box.createVerticalStrut(5));
            panelDetail.add(new JLabel("Prénom :")); panelDetail.add(dPrenom); panelDetail.add(Box.createVerticalStrut(5));
            panelDetail.add(new JLabel("Note :"));  panelDetail.add(dNote);  panelDetail.add(Box.createVerticalStrut(10));
            panelDetail.add(btnSauver); panelDetail.add(Box.createVerticalStrut(5)); panelDetail.add(btnSuppr);

            // --- Zone centrale : recherche + table ---
            JPanel champAjout = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
            champAjout.setBorder(BorderFactory.createTitledBorder("Ajouter"));
            JTextField champNom    = new JTextField(10);
            JTextField champPrenom = new JTextField(10);
            JSpinner   spinNote    = new JSpinner(new SpinnerNumberModel(10.0, 0.0, 20.0, 0.5));
            JButton    btnAjouter  = new JButton("Ajouter");
            champAjout.add(new JLabel("Nom :"));    champAjout.add(champNom);
            champAjout.add(new JLabel("Prénom :")); champAjout.add(champPrenom);
            champAjout.add(new JLabel("Note :"));   champAjout.add(spinNote);
            champAjout.add(btnAjouter);

            JPanel panelRecherche = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
            JTextField champRecherche = new JTextField(22);
            JLabel labelCount = new JLabel("0 ligne(s)");
            JProgressBar barre = new JProgressBar();
            barre.setStringPainted(true); barre.setString("Prêt"); barre.setPreferredSize(new Dimension(150, 18));
            panelRecherche.add(new JLabel("Rechercher :"));
            panelRecherche.add(champRecherche);
            panelRecherche.add(labelCount);
            panelRecherche.add(barre);

            JPanel panelCentre = new JPanel(new BorderLayout(5, 5));
            panelCentre.add(champAjout, BorderLayout.NORTH);
            panelCentre.add(panelRecherche, BorderLayout.CENTER);

            // --- Footer ---
            JLabel footer = new JLabel("  Prêt");
            footer.setBackground(Color.decode("#E9ECEF"));
            footer.setOpaque(true);

            // =================== LISTENERS ===================

            // ListSelectionListener : remplir le formulaire de détail à chaque sélection
            table.getSelectionModel().addListSelectionListener(e -> {
                if (e.getValueIsAdjusting()) return;
                int row = table.getSelectedRow();
                if (row < 0) return;
                int mr = table.convertRowIndexToModel(row); // vue → modèle (important si tri actif)
                dId.setText(String.valueOf(modele.getValueAt(mr, 0)));
                dNom.setText((String) modele.getValueAt(mr, 1));
                dPrenom.setText((String) modele.getValueAt(mr, 2));
                dNote.setValue(modele.getValueAt(mr, 3));
            });

            // DocumentListener : filtrage en temps réel sans clic sur un bouton
            champRecherche.getDocument().addDocumentListener(new DocumentListener() {
                @Override public void insertUpdate(DocumentEvent e) { filtrer(); }
                @Override public void removeUpdate(DocumentEvent e) { filtrer(); }
                @Override public void changedUpdate(DocumentEvent e) { }
                private void filtrer() {
                    String m = champRecherche.getText().trim();
                    sorter.setRowFilter(m.isEmpty() ? null : RowFilter.regexFilter("(?i)" + Pattern.quote(m)));
                    labelCount.setText(table.getRowCount() + " ligne(s)");
                }
            });

            // ActionListener : ajouter une ligne
            btnAjouter.addActionListener(e -> {
                String nom = champNom.getText().trim(), prenom = champPrenom.getText().trim();
                if (nom.isEmpty() || prenom.isEmpty()) { JOptionPane.showMessageDialog(fenetre, "Nom et prénom requis"); return; }
                modele.addRow(new Object[]{idAuto[0]++, nom, prenom, spinNote.getValue()});
                champNom.setText(""); champPrenom.setText("");
                labelCount.setText(modele.getRowCount() + " ligne(s)");
                footer.setText("  Étudiant ajouté");
            });

            // ActionListener : sauvegarder les modifications du formulaire de détail
            btnSauver.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row < 0) return;
                int mr = table.convertRowIndexToModel(row);
                modele.setValueAt(dNom.getText(),    mr, 1); // setValueAt() modifie une cellule
                modele.setValueAt(dPrenom.getText(), mr, 2);
                modele.setValueAt(dNote.getValue(),  mr, 3);
                footer.setText("  Ligne " + mr + " mise à jour");
            });

            // ActionListener : supprimer la ligne sélectionnée
            btnSuppr.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row < 0) return;
                modele.removeRow(table.convertRowIndexToModel(row));
                labelCount.setText(modele.getRowCount() + " ligne(s)");
                footer.setText("  Ligne supprimée");
            });

            // ItemListener : basculer le mode lecture seule
            btnMode.addItemListener(e -> {
                boolean lecture = btnMode.isSelected();
                btnAjouter.setEnabled(!lecture);
                btnSauver.setEnabled(!lecture);
                btnSuppr.setEnabled(!lecture);
                footer.setText("  Mode : " + (lecture ? "lecture seule" : "édition"));
            });

            // ActionListener + SwingWorker : chargement asynchrone sans bloquer l'EDT
            btnCharger.addActionListener(e -> {
                modele.setRowCount(0);
                btnCharger.setEnabled(false);
                barre.setIndeterminate(true);
                barre.setString("Chargement…");
                footer.setText("  Chargement en cours…");

                new SwingWorker<Object[][], Void>() {
                    @Override
                    protected Object[][] doInBackground() throws Exception {
                        Thread.sleep(1800); // simule une requête BDD ou API
                        return new Object[][]{
                                {idAuto[0]++, "Ndiaye",  "Awa",      15.5},
                                {idAuto[0]++, "Diallo",  "Moussa",   12.0},
                                {idAuto[0]++, "Fall",    "Fatou",    17.5},
                                {idAuto[0]++, "Sow",     "Ibrahima",  9.0},
                                {idAuto[0]++, "Mbaye",   "Aïssatou", 14.5}
                        };
                    }
                    @Override
                    protected void done() {
                        try {
                            for (Object[] ligne : get()) modele.addRow(ligne);
                            barre.setString("Chargé");
                            labelCount.setText(modele.getRowCount() + " ligne(s)");
                            footer.setText("  " + modele.getRowCount() + " enregistrements chargés");
                        } catch (InterruptedException | ExecutionException ex) {
                            barre.setString("Erreur");
                            JOptionPane.showMessageDialog(fenetre, "Erreur : " + ex.getMessage());
                        } finally {
                            barre.setIndeterminate(false);
                            btnCharger.setEnabled(true);
                        }
                    }
                }.execute();
            });

            // ActionListener : export CSV (construction de la chaîne sur l'EDT — données légères)
            btnExporter.addActionListener(e -> {
                StringBuilder csv = new StringBuilder("ID,Nom,Prénom,Note\n");
                for (int i = 0; i < modele.getRowCount(); i++) {
                    for (int j = 0; j < modele.getColumnCount(); j++) {
                        csv.append(modele.getValueAt(i, j));
                        if (j < modele.getColumnCount() - 1) csv.append(",");
                    }
                    csv.append("\n");
                }
                JOptionPane.showMessageDialog(fenetre, new JScrollPane(new JTextArea(csv.toString(), 10, 30)),
                        "Export CSV", JOptionPane.INFORMATION_MESSAGE);
            });

            // WindowAdapter : confirmation avant fermeture
            fenetre.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int rep = JOptionPane.showConfirmDialog(fenetre, "Quitter l'application ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (rep == JOptionPane.YES_OPTION) fenetre.dispose();
                }
            });

            // --- Assemblage ---
            JPanel panelNord = new JPanel(new BorderLayout());
            panelNord.add(header, BorderLayout.NORTH);
            panelNord.add(panelCentre, BorderLayout.SOUTH);

            fenetre.add(panelNord, BorderLayout.NORTH);
            fenetre.add(sidebar, BorderLayout.WEST);
            fenetre.add(new JScrollPane(table), BorderLayout.CENTER);
            fenetre.add(panelDetail, BorderLayout.EAST);
            fenetre.add(footer, BorderLayout.SOUTH);
            fenetre.setVisible(true);
        });
    }
}
