package sn.l2gl.youssoupha.events.workers;

/**
 * code 14
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

// publish/process : envoyer des résultats intermédiaires vers l'EDT pendant que doInBackground() tourne
// Utile pour afficher une progression ligne par ligne plutôt qu'attendre la fin complète
public class SwingWorkerPublishProcess {
    private SwingWorkerPublishProcess() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("SwingWorker — publish() / process()");
        fenetre.setSize(650, 500);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout(10, 10));

        String[] colonnes = {"ID", "Nom", "Prénom", "Note"};
        DefaultTableModel modele = new DefaultTableModel(colonnes, 0);
        JTable table = new JTable(modele);

        JProgressBar barre = new JProgressBar(0, 100);
        barre.setStringPainted(true); // affiche le pourcentage en texte sur la barre

        JButton boutonCharger = new JButton("Charger 100 lignes progressivement");
        JLabel labelEtat = new JLabel("Prêt", SwingConstants.CENTER);

        boutonCharger.addActionListener(e -> {
            modele.setRowCount(0); // vider la table avant de recharger
            boutonCharger.setEnabled(false);
            barre.setValue(0);
            labelEtat.setText("Chargement…");

            // SwingWorker<List<String[]>, String[]> :
            // T = List<String[]> résultat final — V = String[] unité envoyée par publish()
            SwingWorker<List<String[]>, String[]> worker = new SwingWorker<>() {
                @Override
                protected List<String[]> doInBackground() throws Exception {
                    List<String[]> tous = new ArrayList<>();
                    String[] noms    = {"Ndiaye", "Diallo", "Fall", "Sow", "Mbaye"};
                    String[] prenoms = {"Awa", "Moussa", "Fatou", "Ibrahima", "Aïssatou"};

                    for (int i = 0; i < 100; i++) {
                        Thread.sleep(30); // simule une lecture BDD ligne par ligne
                        String[] ligne = {
                                String.valueOf(i + 1),
                                noms[i % noms.length],
                                prenoms[i % prenoms.length],
                                String.format("%.1f", 10.0 + (i % 10))
                        };
                        tous.add(ligne);
                        // publish() envoie une ou plusieurs valeurs vers process() sur l'EDT
                        // Swing regroupe automatiquement les appels publish() pour réduire les redraws
                        publish(ligne);
                        // setProgress() déclenche un PropertyChangeEvent "progress" (valeur 0..100)
                        setProgress(i + 1);
                    }
                    return tous;
                }

                @Override
                protected void process(List<String[]> chunks) {
                    // process() s'exécute sur l'EDT avec les valeurs regroupées depuis publish()
                    // chunks peut contenir plusieurs lignes si publish() a été appelé rapidement
                    for (String[] ligne : chunks) {
                        modele.addRow(ligne); // ajouter chaque ligne reçue à la table
                    }
                    labelEtat.setText(modele.getRowCount() + " lignes chargées…");
                }

                @Override
                protected void done() {
                    // done() sur l'EDT — toutes les lignes sont arrivées
                    try {
                        int total = get().size();
                        labelEtat.setText("✓ " + total + " lignes chargées");
                        barre.setValue(100);
                    } catch (InterruptedException | ExecutionException ex) {
                        labelEtat.setText("Erreur : " + ex.getMessage());
                    } finally {
                        boutonCharger.setEnabled(true);
                    }
                }
            };

            // PropertyChangeListener : connecter setProgress() du worker à la JProgressBar
            worker.addPropertyChangeListener(evt -> {
                if ("progress".equals(evt.getPropertyName())) {
                    barre.setValue((Integer) evt.getNewValue());
                }
            });

            worker.execute();
        });

        JPanel panelHaut = new JPanel(new BorderLayout(5, 5));
        panelHaut.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        panelHaut.add(boutonCharger, BorderLayout.NORTH);
        panelHaut.add(barre, BorderLayout.CENTER);
        panelHaut.add(labelEtat, BorderLayout.SOUTH);

        JTextArea zoneNote = new JTextArea(
                "À retenir :\n" +
                "• publish(valeurs) → envoie des résultats intermédiaires depuis doInBackground() vers process()\n" +
                "• process(chunks)  → s'exécute sur l'EDT — Swing regroupe les appels publish() automatiquement\n" +
                "• setProgress(0..100) → déclenche PropertyChangeEvent \"progress\" sur l'EDT\n" +
                "• PropertyChangeListener → connecter setProgress() du worker à une JProgressBar"
        );
        zoneNote.setEditable(false);
        zoneNote.setBackground(Color.decode("#F5F5F5"));
        zoneNote.setFont(new Font("Monospaced", Font.PLAIN, 11));

        fenetre.add(panelHaut, BorderLayout.NORTH);
        fenetre.add(new JScrollPane(table), BorderLayout.CENTER);
        fenetre.add(zoneNote, BorderLayout.SOUTH);
        fenetre.setVisible(true);
    }
}
