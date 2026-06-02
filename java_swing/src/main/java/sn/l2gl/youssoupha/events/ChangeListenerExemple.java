package sn.l2gl.youssoupha.events;

/**
 * code 6
 */

import javax.swing.*;
import java.awt.*;

// ChangeListener : réagir au changement de valeur d'un JSlider, JSpinner ou JTabbedPane
// Une seule méthode : stateChanged(ChangeEvent e)
public class ChangeListenerExemple {
    private ChangeListenerExemple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("ChangeListener");
        fenetre.setSize(600, 400);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout(10, 10));

        JTabbedPane onglets = new JTabbedPane();

        // --- Onglet JSlider ---
        JPanel panelSlider = new JPanel(new GridLayout(3, 2, 10, 10));
        panelSlider.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JSlider sliderVolume = new JSlider(0, 100, 50);
        sliderVolume.setPaintTicks(true);
        sliderVolume.setPaintLabels(true);
        sliderVolume.setMajorTickSpacing(25);

        JLabel labelVolume = new JLabel("Volume : 50");
        JProgressBar barreVolume = new JProgressBar(0, 100);
        barreVolume.setValue(50);
        barreVolume.setStringPainted(true);

        sliderVolume.addChangeListener(e -> {
            int v = sliderVolume.getValue();
            barreVolume.setValue(v);
            // getValueIsAdjusting() = true pendant le glissement, false quand l'utilisateur lâche
            // Permet de sauvegarder uniquement à la fin du geste, pas à chaque pixel
            if (sliderVolume.getValueIsAdjusting()) {
                labelVolume.setText("Volume : " + v + " (en cours…)");
            } else {
                labelVolume.setText("Volume : " + v + " ✓");
            }
        });

        panelSlider.add(new JLabel("Volume :"));
        panelSlider.add(sliderVolume);
        panelSlider.add(labelVolume);
        panelSlider.add(barreVolume);

        // --- Onglet JSpinner ---
        JPanel panelSpinner = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        // SpinnerNumberModel(valeurInitiale, min, max, pas)
        JSpinner spinnerAge = new JSpinner(new SpinnerNumberModel(18, 0, 120, 1));
        JLabel labelAge = new JLabel("Âge : 18");

        // ChangeListener fonctionne sur JSpinner exactement comme sur JSlider
        spinnerAge.addChangeListener(e -> labelAge.setText("Âge : " + spinnerAge.getValue()));

        panelSpinner.add(new JLabel("Âge :"));
        panelSpinner.add(spinnerAge);
        panelSpinner.add(labelAge);

        onglets.addTab("JSlider", panelSlider);
        onglets.addTab("JSpinner", panelSpinner);
        onglets.addTab("Onglet 3", new JLabel("  Contenu chargé au changement d'onglet", SwingConstants.CENTER));

        JLabel labelOnglet = new JLabel("Onglet actif : 0 — JSlider", SwingConstants.CENTER);
        labelOnglet.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        // ChangeListener sur JTabbedPane : déclenché à chaque changement d'onglet
        // Utile pour charger le contenu d'un onglet à la demande (lazy loading)
        onglets.addChangeListener(e -> {
            int i = onglets.getSelectedIndex();
            labelOnglet.setText("Onglet actif : " + i + " — " + onglets.getTitleAt(i));
        });

        JTextArea zoneNote = new JTextArea(
                "À retenir :\n" +
                "• ChangeListener : une seule méthode stateChanged() — compatible avec les lambdas\n" +
                "• JSlider : getValueIsAdjusting() = true pendant le glissement, false quand relâché\n" +
                "• Sauvegarder la valeur uniquement quand getValueIsAdjusting() = false\n" +
                "• JTabbedPane : getSelectedIndex() pour charger le contenu à la demande (lazy loading)\n" +
                "• Fonctionne aussi sur : JProgressBar, ButtonModel"
        );
        zoneNote.setEditable(false);
        zoneNote.setBackground(Color.decode("#F5F5F5"));
        zoneNote.setFont(new Font("Monospaced", Font.PLAIN, 11));

        JPanel panelSud = new JPanel(new BorderLayout(0, 2));
        panelSud.add(labelOnglet, BorderLayout.NORTH);
        panelSud.add(zoneNote, BorderLayout.CENTER);

        fenetre.add(onglets, BorderLayout.CENTER);
        fenetre.add(panelSud, BorderLayout.SOUTH);
        fenetre.setVisible(true);
    }
}
