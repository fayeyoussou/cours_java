package sn.l2gl.youssoupha.hibernate.view;

import lombok.Getter;
import sn.l2gl.youssoupha.hibernate.model.models.Categorie;
import sn.l2gl.youssoupha.hibernate.model.models.Produit;
import sn.l2gl.youssoupha.hibernate.model.models.Vente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * VUE (MVC) des produits : composants Swing uniquement.
 * Gère l'affichage des deux relations (catégorie via combo, ventes via liste multi-sélection).
 */
@Getter
public class ProduitView extends JPanel {

    private final DefaultTableModel modele = new DefaultTableModel(
            new String[]{"Id", "Code", "Libellé", "PU", "Catégorie", "Ventes"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable table = new JTable(modele);

    private final JTextField champCode = new JTextField(5);
    private final JTextField champLibelle = new JTextField(20);
    private final JTextField champPu = new JTextField(8);
    private final JComboBox<Categorie> comboCategorie = new JComboBox<>();
    private final JList<Vente> listeVentes = new JList<>(new DefaultListModel<>());

    private final JButton boutonNouveau = new JButton("Nouveau");
    private final JButton boutonEnregistrer = new JButton("Enregistrer");
    private final JButton boutonSupprimer = new JButton("Supprimer");

    public ProduitView() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Les éléments des listes/combos s'affichent via la méthode toString() des entités.
        listeVentes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listeVentes.setVisibleRowCount(5);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(construireFormulaire(), BorderLayout.SOUTH);
    }

    private JPanel construireFormulaire() {
        JPanel saisie = new JPanel(new GridBagLayout());
        saisie.setBorder(BorderFactory.createTitledBorder("Produit"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0; saisie.add(new JLabel("Code :"), gbc);
        gbc.gridx = 1; saisie.add(champCode, gbc);
        gbc.gridx = 2; saisie.add(new JLabel("Libellé :"), gbc);
        gbc.gridx = 3; saisie.add(champLibelle, gbc);

        gbc.gridx = 0; gbc.gridy = 1; saisie.add(new JLabel("Prix unitaire :"), gbc);
        gbc.gridx = 1; saisie.add(champPu, gbc);
        gbc.gridx = 2; saisie.add(new JLabel("Catégorie :"), gbc);
        gbc.gridx = 3; saisie.add(comboCategorie, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.NORTHWEST;
        saisie.add(new JLabel("Ventes :"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        saisie.add(new JScrollPane(listeVentes), gbc);

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

    public void afficher(List<Produit> produits) {
        modele.setRowCount(0);
        for (Produit p : produits) {
            String categorie = p.getCategorie() == null ? "" : p.getCategorie().getCode();
            String ventes = p.getVentes() == null ? "" :
                    p.getVentes().stream().map(Vente::getNumero).collect(Collectors.joining(", "));
            modele.addRow(new Object[]{p.getId(), p.getCode(), p.getLibelle(), p.getPu(), categorie, ventes});
        }
    }

    /** Recharge la combo des catégories (option « aucune » en tête) et la liste des ventes. */
    public void rechargerReferences(List<Categorie> categories, List<Vente> ventes) {
        comboCategorie.removeAllItems();
        comboCategorie.addItem(null);
        for (Categorie c : categories) comboCategorie.addItem(c);

        DefaultListModel<Vente> m = (DefaultListModel<Vente>) listeVentes.getModel();
        m.clear();
        for (Vente v : ventes) m.addElement(v);
    }

    public void remplir(Produit p) {
        champCode.setText(p.getCode());
        champLibelle.setText(p.getLibelle());
        champPu.setText(String.valueOf(p.getPu()));
        selectionnerCategorie(p.getCategorie());
        selectionnerVentes(p.getVentes());
    }

    private void selectionnerCategorie(Categorie categorie) {
        if (categorie == null) {
            comboCategorie.setSelectedItem(null);
            return;
        }
        for (int i = 0; i < comboCategorie.getItemCount(); i++) {
            Categorie c = comboCategorie.getItemAt(i);
            if (c != null && c.getCode().equals(categorie.getCode())) {
                comboCategorie.setSelectedIndex(i);
                return;
            }
        }
    }

    private void selectionnerVentes(List<Vente> ventes) {
        listeVentes.clearSelection();
        if (ventes == null || ventes.isEmpty()) return;
        List<String> numeros = ventes.stream().map(Vente::getNumero).collect(Collectors.toList());
        DefaultListModel<Vente> m = (DefaultListModel<Vente>) listeVentes.getModel();
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < m.size(); i++) {
            if (numeros.contains(m.get(i).getNumero())) indices.add(i);
        }
        listeVentes.setSelectedIndices(indices.stream().mapToInt(Integer::intValue).toArray());
    }

    public void reinitialiser() {
        champCode.setText("");
        champLibelle.setText("");
        champPu.setText("");
        comboCategorie.setSelectedItem(null);
        listeVentes.clearSelection();
        table.clearSelection();
        champCode.requestFocusInWindow();
    }

    /** Id du produit de la ligne sélectionnée, ou null. */
    public Long getIdSelectionne() {
        int ligne = table.getSelectedRow();
        return ligne < 0 ? null : Long.valueOf(String.valueOf(modele.getValueAt(ligne, 0)));
    }

    /** Ventes actuellement sélectionnées dans la liste. */
    public List<Vente> getVentesSelectionnees() {
        return new ArrayList<>(listeVentes.getSelectedValuesList());
    }

    public Categorie getCategorieSelectionnee() {
        return (Categorie) comboCategorie.getSelectedItem();
    }
}
