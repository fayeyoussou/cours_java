package sn.l2gl.youssoupha.events.workers;

/**
 * code 13
 */

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutionException;

// SwingWorker<T, V> : exécute une tâche longue hors EDT, revient sur l'EDT pour mettre à jour l'UI
// T = type du résultat final retourné par doInBackground()
// V = type des résultats intermédiaires envoyés par publish() (Void si aucun)
public class SwingWorkerSimple {
    private SwingWorkerSimple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("SwingWorker — tâche longue sans freeze");
        fenetre.setSize(580, 380);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout(10, 10));

        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        JButton boutonFreeze  = new JButton("❌ Sans SwingWorker (freeze 3s)");
        JButton boutonWorker  = new JButton("✓ Avec SwingWorker");
        JLabel  labelEtat     = new JLabel("En attente…", SwingConstants.CENTER);
        JLabel  labelThread   = new JLabel("", SwingConstants.CENTER);

        boutonFreeze.setBackground(Color.decode("#F44336"));
        boutonFreeze.setForeground(Color.WHITE);
        boutonWorker.setBackground(Color.decode("#4CAF50"));
        boutonWorker.setForeground(Color.WHITE);

        // ❌ MAUVAIS — Thread.sleep sur l'EDT bloque toute l'interface (aucun redraw, aucun clic)
        boutonFreeze.addActionListener(e -> {
            labelEtat.setText("UI gelée pendant 3 secondes…");
            try {
                Thread.sleep(3000); // bloque l'EDT — l'UI ne répond plus du tout
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            labelEtat.setText("Terminé (après le freeze)");
        });

        // ✓ BON — SwingWorker délègue la tâche longue à un thread séparé
        boutonWorker.addActionListener(e -> {
            labelEtat.setText("Chargement en arrière-plan…");
            labelThread.setText("");
            boutonWorker.setEnabled(false);

            // SwingWorker<String, Void> : retourne une String, sans résultats intermédiaires
            SwingWorker<String, Void> worker = new SwingWorker<>() {
                @Override
                protected String doInBackground() throws Exception {
                    // ⚠️ S'exécute HORS EDT — ne jamais modifier de composants Swing ici
                    labelThread.setText("Thread : " + Thread.currentThread().getName()); // incorrect — juste pour démonstration
                    Thread.sleep(3000); // simule une requête réseau ou BDD
                    return "Données chargées avec succès !";
                }

                @Override
                protected void done() {
                    // ✅ S'exécute sur l'EDT — modifications de composants autorisées ici
                    try {
                        String resultat = get(); // récupère la valeur retournée par doInBackground()
                        labelEtat.setText(resultat);
                    } catch (InterruptedException | ExecutionException ex) {
                        // get() lève ExecutionException si doInBackground() a levé une exception
                        labelEtat.setText("Erreur : " + ex.getMessage());
                    } finally {
                        boutonWorker.setEnabled(true);
                    }
                }
            };

            worker.execute(); // démarre la tâche — ne bloque pas, retourne immédiatement
        });

        JTextArea zoneExplication = new JTextArea(
                "À retenir :\n" +
                "• execute()        → démarre la tâche, retourne immédiatement (non bloquant)\n" +
                "• doInBackground() → s'exécute HORS EDT — ne jamais modifier de composants Swing ici\n" +
                "• done()           → s'exécute sur l'EDT — modifications de l'UI autorisées\n" +
                "• get()            → récupère la valeur de doInBackground() — toujours dans un try/catch (ExecutionException)"
        );
        zoneExplication.setEditable(false);
        zoneExplication.setBackground(Color.decode("#F5F5F5"));
        zoneExplication.setFont(new Font("Monospaced", Font.PLAIN, 11));

        panelBoutons.add(boutonFreeze);
        panelBoutons.add(boutonWorker);

        JPanel panelCentre = new JPanel(new GridLayout(2, 1, 5, 5));
        panelCentre.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panelCentre.add(labelEtat);
        panelCentre.add(labelThread);

        fenetre.add(panelBoutons, BorderLayout.NORTH);
        fenetre.add(panelCentre, BorderLayout.CENTER);
        fenetre.add(zoneExplication, BorderLayout.SOUTH);
        fenetre.setVisible(true);
    }
}
