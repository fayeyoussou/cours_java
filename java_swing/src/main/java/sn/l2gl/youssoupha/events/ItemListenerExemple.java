package sn.l2gl.youssoupha.events;

/**
 * code 2
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

// ItemListener : détecter la sélection/désélection d'une case à cocher ou d'une liste déroulante
public class ItemListenerExemple {
    private ItemListenerExemple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("ItemListener");
        fenetre.setSize(520, 320);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout(10, 10));

        JPanel panelTop = new JPanel(new GridLayout(2, 1, 10, 10));
        panelTop.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 15));

        // --- Section JCheckBox ---
        JPanel panelCheck = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        panelCheck.setBorder(BorderFactory.createTitledBorder("JCheckBox"));
        JCheckBox checkAccepter = new JCheckBox("J'accepte les conditions");
        JButton boutonValider = new JButton("Valider");
        boutonValider.setEnabled(false); // désactivé tant que la case n'est pas cochée

        // ItemListener sur JCheckBox : SELECTED quand coché, DESELECTED quand décoché
        checkAccepter.addItemListener(e -> {
            // ItemEvent.SELECTED : l'élément vient d'être activé
            // ItemEvent.DESELECTED : l'élément vient d'être désactivé
            boutonValider.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
        });

        panelCheck.add(checkAccepter);
        panelCheck.add(boutonValider);

        // --- Section JComboBox ---
        JPanel panelCombo = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        panelCombo.setBorder(BorderFactory.createTitledBorder("JComboBox"));
        JComboBox<String> comboPays = new JComboBox<>(new String[]{"Sénégal", "Mali", "Côte d'Ivoire", "Guinée"});
        JLabel labelVilles = new JLabel("Villes : —");

        // ItemListener sur JComboBox : déclenché 2× lors d'un changement (DESELECTED puis SELECTED)
        // Filtrer sur SELECTED pour n'exécuter la logique qu'une seule fois
        comboPays.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                // e.getItem() retourne l'objet sélectionné (String ici)
                String pays = (String) e.getItem();
                labelVilles.setText("Villes : " + obtenirVilles(pays));
            }
        });

        panelCombo.add(new JLabel("Pays :"));
        panelCombo.add(comboPays);
        panelCombo.add(labelVilles);

        panelTop.add(panelCheck);
        panelTop.add(panelCombo);

        JTextArea zoneNote = new JTextArea(
                "À retenir :\n" +
                "• ItemEvent.SELECTED   → élément coché / sélectionné\n" +
                "• ItemEvent.DESELECTED → élément décoché / désélectionné\n" +
                "• Pour JComboBox, ItemListener est déclenché 2× par changement\n" +
                "• Pour JCheckBox/JRadioButton, ActionListener fonctionne aussi (1× seulement)"
        );
        zoneNote.setEditable(false);
        zoneNote.setBackground(Color.decode("#F5F5F5"));
        zoneNote.setFont(new Font("Monospaced", Font.PLAIN, 11));

        fenetre.add(panelTop, BorderLayout.CENTER);
        fenetre.add(zoneNote, BorderLayout.SOUTH);
        fenetre.setVisible(true);
    }

    private static String obtenirVilles(String pays) {
        return switch (pays) {
            case "Sénégal" -> "Dakar, Thiès, Saint-Louis";
            case "Mali" -> "Bamako, Sikasso, Mopti";
            case "Côte d'Ivoire" -> "Abidjan, Bouaké, Yamoussoukro";
            default -> "—";
        };
    }
}
