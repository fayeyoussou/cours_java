package sn.l2gl.youssoupha.hibernate_simple.view;

import lombok.Getter;
import sn.l2gl.youssoupha.hibernate_simple.model.Classe;
import sn.l2gl.youssoupha.hibernate_simple.model.Etudiant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * VUE (MVC) des étudiants : composants Swing uniquement.
 * Le matricule est saisi en clé naturelle ; un label rouge affiche à côté
 * le résultat du contrôle d'existence déclenché à la perte de focus.
 */
@Getter
public class EtudiantView extends JPanel {

    /** Format attendu pour les dates saisies/affichées (jj/MM/aaaa). */
    public static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final DefaultTableModel modele = new DefaultTableModel(
            new String[]{"Matricule", "Prénom", "Nom", "Naissance", "Création", "Classe"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable table = new JTable(modele);

    private final JTextField champMatricule = new JTextField(12);
    private final JTextField champPrenom = new JTextField(15);
    private final JTextField champNom = new JTextField(15);
    private final JTextField champDateNaissance = new JTextField(10);
    private final JComboBox<Classe> comboClasse = new JComboBox<>(Classe.values());

    // Date de création : affichée mais non modifiable (posée automatiquement)
    private final JTextField champDateCreation = new JTextField(10);

    // Label « sur le côté » du champ matricule : message de statut
    // (rouge si le matricule existe déjà, vert s'il est disponible)
    private final JLabel labelStatutMatricule = new JLabel(" ");

    private final JButton boutonNouveau = new JButton("Nouveau");
    private final JButton boutonEnregistrer = new JButton("Enregistrer");
    private final JButton boutonSupprimer = new JButton("Supprimer");

    public EtudiantView() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        champDateCreation.setEditable(false);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(construireFormulaire(), BorderLayout.SOUTH);
    }

    private JPanel construireFormulaire() {
        JPanel saisie = new JPanel(new GridBagLayout());
        saisie.setBorder(BorderFactory.createTitledBorder("Étudiant"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6);
        gbc.anchor = GridBagConstraints.WEST;

        // Ligne 0 : matricule + label d'erreur juste à côté
        gbc.gridx = 0; gbc.gridy = 0; saisie.add(new JLabel("Matricule :"), gbc);
        gbc.gridx = 1; saisie.add(champMatricule, gbc);
        gbc.gridx = 2; gbc.gridwidth = 2; saisie.add(labelStatutMatricule, gbc);
        gbc.gridwidth = 1;

        // Ligne 1 : prénom / nom
        gbc.gridx = 0; gbc.gridy = 1; saisie.add(new JLabel("Prénom :"), gbc);
        gbc.gridx = 1; saisie.add(champPrenom, gbc);
        gbc.gridx = 2; saisie.add(new JLabel("Nom :"), gbc);
        gbc.gridx = 3; saisie.add(champNom, gbc);

        // Ligne 2 : date de naissance / classe
        gbc.gridx = 0; gbc.gridy = 2; saisie.add(new JLabel("Naissance (jj/MM/aaaa) :"), gbc);
        gbc.gridx = 1; saisie.add(champDateNaissance, gbc);
        gbc.gridx = 2; saisie.add(new JLabel("Classe :"), gbc);
        gbc.gridx = 3; saisie.add(comboClasse, gbc);

        // Ligne 3 : date de création (lecture seule)
        gbc.gridx = 0; gbc.gridy = 3; saisie.add(new JLabel("Création :"), gbc);
        gbc.gridx = 1; saisie.add(champDateCreation, gbc);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        actions.add(boutonNouveau);
        actions.add(boutonEnregistrer);
        actions.add(boutonSupprimer);

        JPanel bas = new JPanel(new BorderLayout());
        bas.add(saisie, BorderLayout.CENTER);
        bas.add(actions, BorderLayout.SOUTH);
        return bas;
    }

    // --- Méthodes appelées par le contrôleur ---

    /** Remplit la table avec la liste fournie. */
    public void afficher(List<Etudiant> etudiants) {
        modele.setRowCount(0);
        for (Etudiant e : etudiants) {
            modele.addRow(new Object[]{
                    e.getMatricule(),
                    e.getPrenom(),
                    e.getNom(),
                    formater(e.getDateNaissance()),
                    formater(e.getDateCreation()),
                    e.getClasse()
            });
        }
    }

    private String formater(LocalDate date) {
        return date == null ? "" : date.format(FORMAT_DATE);
    }

    /** Recopie un étudiant existant dans le formulaire (mode édition). */
    public void remplir(Etudiant e) {
        champMatricule.setText(e.getMatricule());
        champPrenom.setText(e.getPrenom());
        champNom.setText(e.getNom());
        champDateNaissance.setText(formater(e.getDateNaissance()));
        champDateCreation.setText(formater(e.getDateCreation()));
        comboClasse.setSelectedItem(e.getClasse());
        // En édition, le matricule (clé primaire) ne doit pas changer
        champMatricule.setEditable(false);
        effacerErreurMatricule();
    }

    /** Vide le formulaire et le repositionne en mode création. */
    public void reinitialiser() {
        champMatricule.setText("");
        champPrenom.setText("");
        champNom.setText("");
        champDateNaissance.setText("");
        champDateCreation.setText("");
        comboClasse.setSelectedIndex(0);
        champMatricule.setEditable(true);
        table.clearSelection();
        effacerErreurMatricule();
        champMatricule.requestFocusInWindow();
    }

    /** Affiche un message rouge (matricule déjà pris) à côté du champ matricule. */
    public void afficherErreurMatricule(String message) {
        labelStatutMatricule.setForeground(Color.RED);
        labelStatutMatricule.setText(message);
    }

    /** Affiche un message vert (matricule disponible) à côté du champ matricule. */
    public void afficherMatriculeDisponible(String message) {
        labelStatutMatricule.setForeground(new Color(0, 150, 0));
        labelStatutMatricule.setText(message);
    }

    /** Efface le message de statut du matricule. */
    public void effacerErreurMatricule() {
        labelStatutMatricule.setText(" ");
    }

    /** Matricule de la ligne sélectionnée dans la table, ou null. */
    public String getMatriculeSelectionne() {
        int ligne = table.getSelectedRow();
        return ligne < 0 ? null : String.valueOf(modele.getValueAt(ligne, 0));
    }

    /** Classe actuellement sélectionnée dans la ComboBox. */
    public Classe getClasseSelectionnee() {
        return (Classe) comboClasse.getSelectedItem();
    }
}
