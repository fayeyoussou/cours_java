package sn.l2gl.youssoupha.hibernate.view;

import lombok.Getter;
import sn.l2gl.youssoupha.hibernate.model.models.Produit;
import sn.l2gl.youssoupha.hibernate.model.models.Vente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * VUE (MVC) des ventes : composants Swing uniquement.
 * Les produits liés sont choisis via une liste multi-sélection.
 */
@Getter
public class VenteView extends JPanel {

    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final DefaultTableModel modele = new DefaultTableModel(
            new String[]{"Numéro", "Montant", "Date", "Produits"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable table = new JTable(modele);

    private final JTextField champNumero = new JTextField(8);
    private final JTextField champMontant = new JTextField(8);
    private final JTextField champDate = new JTextField(16);
    private final JList<Produit> listeProduits = new JList<>(new DefaultListModel<>());

    private final JButton boutonNouveau = new JButton("Nouveau");
    private final JButton boutonEnregistrer = new JButton("Enregistrer");
    private final JButton boutonSupprimer = new JButton("Supprimer");

    public VenteView() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // La liste affiche les produits via leur méthode toString().
        listeProduits.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listeProduits.setVisibleRowCount(5);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(construireFormulaire(), BorderLayout.SOUTH);

        champDate.setText(LocalDateTime.now().format(FORMAT));
    }

    private JPanel construireFormulaire() {
        JPanel saisie = new JPanel(new GridBagLayout());
        saisie.setBorder(BorderFactory.createTitledBorder("Vente"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0; saisie.add(new JLabel("Numéro :"), gbc);
        gbc.gridx = 1; saisie.add(champNumero, gbc);
        gbc.gridx = 2; saisie.add(new JLabel("Montant :"), gbc);
        gbc.gridx = 3; saisie.add(champMontant, gbc);

        gbc.gridx = 0; gbc.gridy = 1; saisie.add(new JLabel("Date (aaaa-mm-jj hh:mm) :"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        saisie.add(champDate, gbc);
        gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.NORTHWEST;
        saisie.add(new JLabel("Produits :"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        saisie.add(new JScrollPane(listeProduits), gbc);

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

    public void afficher(List<Vente> ventes) {
        modele.setRowCount(0);
        for (Vente v : ventes) {
            String date = v.getDate() == null ? "" : v.getDate().format(FORMAT);
            String produits = v.getProduits() == null ? "" :
                    v.getProduits().stream().map(Produit::getCode).collect(Collectors.joining(", "));
            modele.addRow(new Object[]{v.getNumero(), v.getMontant(), date, produits});
        }
    }

    /** Recharge la liste des produits disponibles. */
    public void rechargerProduits(List<Produit> produits) {
        DefaultListModel<Produit> m = (DefaultListModel<Produit>) listeProduits.getModel();
        m.clear();
        for (Produit p : produits) m.addElement(p);
    }

    public void remplir(Vente v) {
        champNumero.setText(v.getNumero());
        champNumero.setEditable(false); // numéro = clé primaire
        champMontant.setText(String.valueOf(v.getMontant()));
        champDate.setText(v.getDate() == null ? "" : v.getDate().format(FORMAT));
    }

    /** Présélectionne dans la liste les produits dont l'id figure dans idsLies. */
    public void selectionnerProduits(List<Long> idsLies) {
        listeProduits.clearSelection();
        DefaultListModel<Produit> m = (DefaultListModel<Produit>) listeProduits.getModel();
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < m.size(); i++) {
            if (idsLies.contains(m.get(i).getId())) indices.add(i);
        }
        listeProduits.setSelectedIndices(indices.stream().mapToInt(Integer::intValue).toArray());
    }

    public void reinitialiser() {
        champNumero.setText("");
        champNumero.setEditable(true);
        champMontant.setText("");
        champDate.setText(LocalDateTime.now().format(FORMAT));
        listeProduits.clearSelection();
        table.clearSelection();
        champNumero.requestFocusInWindow();
    }

    public String getNumeroSelectionne() {
        int ligne = table.getSelectedRow();
        return ligne < 0 ? null : String.valueOf(modele.getValueAt(ligne, 0));
    }

    public List<Long> getProduitsSelectionnesIds() {
        return listeProduits.getSelectedValuesList().stream()
                .map(Produit::getId)
                .collect(Collectors.toList());
    }
}
