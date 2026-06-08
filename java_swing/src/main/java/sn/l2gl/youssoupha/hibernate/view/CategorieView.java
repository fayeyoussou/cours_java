package sn.l2gl.youssoupha.hibernate.view;

import lombok.Getter;
import sn.l2gl.youssoupha.hibernate.model.models.Categorie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * VUE (MVC) des catégories : uniquement des composants Swing, aucune logique métier
 * ni accès base de données. Le contrôleur branche les écouteurs et appelle ces méthodes.
 */
@Getter
public class CategorieView extends JPanel {

    // Table en lecture seule (l'édition passe par le formulaire)
    private final DefaultTableModel modele = new DefaultTableModel(new String[]{"Code", "Libellé"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable table = new JTable(modele);

    // Recherche
    private final JTextField champRecherche = new JTextField(20);
    private final JButton boutonChercher = new JButton("Rechercher");
    private final JButton boutonTout = new JButton("Tout afficher");

    // Formulaire
    private final JTextField champCode = new JTextField(10);
    private final JTextField champLibelle = new JTextField(20);
    private final JButton boutonNouveau = new JButton("Nouveau");
    private final JButton boutonEnregistrer = new JButton("Enregistrer");
    private final JButton boutonSupprimer = new JButton("Supprimer");

    public CategorieView() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(construireRecherche(), BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(construireFormulaire(), BorderLayout.SOUTH);
    }

    private JPanel construireRecherche() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Recherche par libellé"));
        panel.add(new JLabel("Libellé :"));
        panel.add(champRecherche);
        panel.add(boutonChercher);
        panel.add(boutonTout);
        return panel;
    }

    private JPanel construireFormulaire() {
        JPanel saisie = new JPanel(new GridLayout(2, 2, 10, 8));
        saisie.setBorder(BorderFactory.createTitledBorder("Catégorie"));
        saisie.add(new JLabel("Code :"));
        saisie.add(champCode);
        saisie.add(new JLabel("Libellé :"));
        saisie.add(champLibelle);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        actions.add(boutonNouveau);
        actions.add(boutonEnregistrer);
        actions.add(boutonSupprimer);

        JPanel bas = new JPanel(new BorderLayout());
        bas.add(saisie, BorderLayout.CENTER);
        bas.add(actions, BorderLayout.SOUTH);
        return bas;
    }

    // --- Méthodes appelées par le contrôleur pour rafraîchir l'affichage ---

    /** Remplit la table à partir des catégories fournies par le contrôleur. */
    public void afficher(List<Categorie> categories) {
        modele.setRowCount(0);
        for (Categorie c : categories) {
            modele.addRow(new Object[]{c.getCode(), c.getLibelle()});
        }
    }

    /** Recopie une catégorie dans le formulaire (mode édition). */
    public void remplir(Categorie c) {
        champCode.setText(c.getCode());
        champCode.setEditable(false); // le code est la clé primaire : non modifiable
        champLibelle.setText(c.getLibelle());
    }

    /** Vide le formulaire (mode création). */
    public void reinitialiser() {
        champCode.setText("");
        champCode.setEditable(true);
        champLibelle.setText("");
        table.clearSelection();
        champCode.requestFocusInWindow();
    }

    /** Code de la ligne sélectionnée dans la table, ou null si aucune. */
    public String getCodeSelectionne() {
        int ligne = table.getSelectedRow();
        return ligne < 0 ? null : String.valueOf(modele.getValueAt(ligne, 0));
    }
}
