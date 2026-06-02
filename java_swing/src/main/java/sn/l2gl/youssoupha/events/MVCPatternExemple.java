package sn.l2gl.youssoupha.events;

/**
 * code 11
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Pattern MVC : Modèle (données), Vue (composants Swing), Contrôleur (logique événementielle)
// Le contrôleur écoute la vue, agit sur le modèle, puis ordonne à la vue de se rafraîchir
public class MVCPatternExemple {
    private MVCPatternExemple() {
        /* Classe utilitaire */
    }

    // --- MODÈLE : données pures, aucune dépendance vers Swing ---
    static class EtudiantModele {
        private final List<String[]> liste = new ArrayList<>();

        public void ajouter(String nom, String prenom, double note) {
            liste.add(new String[]{nom, prenom, String.valueOf(note)});
        }

        public void supprimer(int index) {
            if (index >= 0 && index < liste.size()) liste.remove(index);
        }

        // Retourner une copie défensive pour ne pas exposer la liste interne
        public List<String[]> getTous() { return new ArrayList<>(liste); }
        public int getTaille()          { return liste.size(); }
    }

    // --- VUE : composants Swing uniquement, aucune logique métier ---
    static class EtudiantVue extends JPanel {
        // Champs accessibles par le contrôleur
        final JTextField    champNom    = new JTextField(12);
        final JTextField    champPrenom = new JTextField(12);
        final JSpinner      spinnerNote = new JSpinner(new SpinnerNumberModel(10.0, 0.0, 20.0, 0.5));
        final JButton       btnAjouter  = new JButton("Ajouter");
        final JButton       btnSupprimer = new JButton("Supprimer");
        // DefaultTableModel exposé pour que le contrôleur puisse le rafraîchir
        final DefaultTableModel tableModele = new DefaultTableModel(new String[]{"Nom", "Prénom", "Note"}, 0);
        final JTable            table       = new JTable(tableModele);
        final JLabel            labelStatut = new JLabel("0 étudiant(s)");

        EtudiantVue() {
            setLayout(new BorderLayout(10, 10));
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JPanel panelSaisie = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
            panelSaisie.setBorder(BorderFactory.createTitledBorder("Formulaire (Vue)"));
            panelSaisie.add(new JLabel("Nom :"));    panelSaisie.add(champNom);
            panelSaisie.add(new JLabel("Prénom :")); panelSaisie.add(champPrenom);
            panelSaisie.add(new JLabel("Note :"));   panelSaisie.add(spinnerNote);
            panelSaisie.add(btnAjouter);
            panelSaisie.add(btnSupprimer);

            add(panelSaisie, BorderLayout.NORTH);
            add(new JScrollPane(table), BorderLayout.CENTER);
            add(labelStatut, BorderLayout.SOUTH);
        }

        // La vue ne connaît pas le modèle — elle expose uniquement ses composants
        public JButton getBtnAjouter()   { return btnAjouter;  }
        public JButton getBtnSupprimer() { return btnSupprimer; }

        // rafraichir() : met la table à jour à partir des données fournies par le contrôleur
        public void rafraichir(List<String[]> etudiants) {
            tableModele.setRowCount(0);
            for (String[] e : etudiants) tableModele.addRow(e);
            labelStatut.setText(etudiants.size() + " étudiant(s)");
        }
    }

    // --- CONTRÔLEUR : reçoit les événements de la vue, agit sur le modèle, ordonne le rafraîchissement ---
    static class EtudiantControleur {
        private final EtudiantModele modele;
        private final EtudiantVue    vue;

        EtudiantControleur(EtudiantModele modele, EtudiantVue vue) {
            this.modele = modele;
            this.vue    = vue;
            // Le contrôleur branche les listeners — la vue n'a aucune logique ici
            vue.getBtnAjouter().addActionListener(e   -> ajouter());
            vue.getBtnSupprimer().addActionListener(e -> supprimer());
        }

        private void ajouter() {
            String nom    = vue.champNom.getText().trim();
            String prenom = vue.champPrenom.getText().trim();
            if (nom.isEmpty() || prenom.isEmpty()) {
                JOptionPane.showMessageDialog(vue, "Nom et prénom requis");
                return;
            }
            double note = (double) vue.spinnerNote.getValue();
            modele.ajouter(nom, prenom, note); // 1. mettre à jour le modèle
            vue.rafraichir(modele.getTous());   // 2. rafraîchir la vue
            vue.champNom.setText("");
            vue.champPrenom.setText("");
        }

        private void supprimer() {
            int row = vue.table.getSelectedRow();
            if (row < 0) { JOptionPane.showMessageDialog(vue, "Sélectionnez une ligne"); return; }
            modele.supprimer(row);           // 1. mettre à jour le modèle
            vue.rafraichir(modele.getTous()); // 2. rafraîchir la vue
        }
    }

    public static void afficher() {
        SwingUtilities.invokeLater(() -> {
            JFrame fenetre = new JFrame("Pattern MVC — Modèle / Vue / Contrôleur");
            fenetre.setSize(700, 450);
            fenetre.setLocationRelativeTo(null);
            fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Instanciation dans l'ordre : modèle → vue → contrôleur
            // Le contrôleur a besoin des deux autres — le modèle et la vue sont indépendants
            EtudiantModele modele = new EtudiantModele();
            EtudiantVue    vue    = new EtudiantVue();
            new EtudiantControleur(modele, vue);

            JTextArea zoneNote = new JTextArea(
                    "À retenir :\n" +
                    "• Modèle     : données pures, sans dépendance Swing — testable indépendamment\n" +
                    "• Vue        : composants Swing uniquement, aucune logique métier\n" +
                    "• Contrôleur : branche les listeners, appelle le modèle, ordonne le rafraîchissement de la vue\n" +
                    "• Ordre d'instanciation : Modèle → Vue → Contrôleur (le contrôleur dépend des deux autres)"
            );
            zoneNote.setEditable(false);
            zoneNote.setBackground(Color.decode("#F5F5F5"));
            zoneNote.setFont(new Font("Monospaced", Font.PLAIN, 11));

            fenetre.add(vue, BorderLayout.CENTER);
            fenetre.add(zoneNote, BorderLayout.SOUTH);
            fenetre.setVisible(true);
        });
    }
}
