package sn.l2gl.youssoupha.ui.components;

/**
 * code UI 18
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

// JTable + formulaire : valider la saisie puis ajouter une nouvelle ligne.
public class Code18JTableFormSimple {
    private Code18JTableFormSimple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("UI 18 - JTable avec formulaire");
        fenetre.setSize(650, 450);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField champMatricule = new JTextField();
        JTextField champPrenom = new JTextField();
        JTextField champNom = new JTextField();
        JTextField champAge = new JTextField();
        JComboBox<String> comboClasse = new JComboBox<>(new String[]{"L2GL", "L2IAGE", "L2GDA"});

        // GridLayout : aligner les labels et les champs du formulaire.
        JPanel formulaire = new JPanel(new GridLayout(5, 2, 10, 10));
        formulaire.setBorder(BorderFactory.createTitledBorder("Nouvel étudiant"));
        formulaire.add(new JLabel("Matricule :"));
        formulaire.add(champMatricule);
        formulaire.add(new JLabel("Prénom :"));
        formulaire.add(champPrenom);
        formulaire.add(new JLabel("Nom :"));
        formulaire.add(champNom);
        formulaire.add(new JLabel("Âge :"));
        formulaire.add(champAge);
        formulaire.add(new JLabel("Classe :"));
        formulaire.add(comboClasse);

        DefaultTableModel modele = new DefaultTableModel(
                new String[]{"Matricule", "Prénom", "Nom", "Âge", "Classe"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(modele);

        JButton boutonAjouter = new JButton("Ajouter");
        JLabel labelErreur = new JLabel(" ");
        labelErreur.setForeground(Color.RED);

        boutonAjouter.addActionListener(e -> {
            String matricule = champMatricule.getText().trim();
            String prenom = champPrenom.getText().trim();
            String nom = champNom.getText().trim();
            String ageTexte = champAge.getText().trim();
            String classe = (String) comboClasse.getSelectedItem();

            if (matricule.isEmpty() || prenom.isEmpty() || nom.isEmpty() || ageTexte.isEmpty()) {
                labelErreur.setText("Tous les champs sont obligatoires.");
                return;
            }
            if (matriculeExiste(modele, matricule)) {
                labelErreur.setText("Ce matricule existe déjà.");
                return;
            }

            try {
                int age = Integer.parseInt(ageTexte);
                if (age <= 0 || age > 120) {
                    labelErreur.setText("L'âge doit être compris entre 1 et 120.");
                    return;
                }

                modele.addRow(new Object[]{matricule, prenom, nom, age, classe});
                champMatricule.setText("");
                champPrenom.setText("");
                champNom.setText("");
                champAge.setText("");
                comboClasse.setSelectedIndex(0);
                labelErreur.setText(" ");
                champMatricule.requestFocusInWindow();
            } catch (NumberFormatException exception) {
                labelErreur.setText("L'âge doit être un nombre entier.");
            }
        });

        // FlowLayout : placer le bouton et le message de validation sur une ligne.
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        actions.add(boutonAjouter);
        actions.add(labelErreur);

        // BoxLayout : empiler verticalement le formulaire, les actions et la table.
        JPanel contenu = new JPanel();
        contenu.setLayout(new BoxLayout(contenu, BoxLayout.Y_AXIS));
        contenu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formulaire.setMaximumSize(new Dimension(Integer.MAX_VALUE, formulaire.getPreferredSize().height));
        actions.setMaximumSize(new Dimension(Integer.MAX_VALUE, actions.getPreferredSize().height));

        contenu.add(formulaire);
        contenu.add(Box.createVerticalStrut(8));
        contenu.add(actions);
        contenu.add(Box.createVerticalStrut(8));
        contenu.add(new JScrollPane(table));

        fenetre.add(contenu);
        fenetre.setVisible(true);
    }

    private static boolean matriculeExiste(DefaultTableModel modele, String matricule) {
        for (int ligne = 0; ligne < modele.getRowCount(); ligne++) {
            String matriculeExistant = String.valueOf(modele.getValueAt(ligne, 0));
            if (matricule.equalsIgnoreCase(matriculeExistant)) {
                return true;
            }
        }
        return false;
    }

    /**
     * CHALLENGE :
     * Ajoutez un bouton permettant de supprimer la ligne selectionnee.
     * Ajoutez ensuite une recherche par matricule ou par nom.
     * Creer un menu en haut contenant les boutons quitter / aide utiliser votre imagination pour ameliorer et styliser cette partie
     */
}
