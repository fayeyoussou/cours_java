package sn.l2gl.youssoupha.events.workers;

/**
 * code 16
 */

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

// cancel() : interrompre un SwingWorker en cours d'exécution
// Le worker vérifie isCancelled() à chaque itération pour s'arrêter proprement
public class SwingWorkerCancel {
    private SwingWorkerCancel() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("SwingWorker — cancel()");
        fenetre.setSize(560, 340);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout(10, 10));

        JProgressBar barre = new JProgressBar(0, 100);
        barre.setStringPainted(true);
        barre.setString("En attente…");

        JLabel labelEtat = new JLabel("Appuyez sur Démarrer", SwingConstants.CENTER);
        labelEtat.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton btnDemarrer = new JButton("▶ Démarrer");
        JButton btnAnnuler  = new JButton("⛔ Annuler");
        btnAnnuler.setEnabled(false);
        btnAnnuler.setBackground(Color.decode("#F44336"));
        btnAnnuler.setForeground(Color.WHITE);

        // AtomicReference pour partager le worker entre les deux listeners
        AtomicReference<SwingWorker<Void, Void>> workerRef = new AtomicReference<>();

        btnDemarrer.addActionListener(e -> {
            btnDemarrer.setEnabled(false);
            btnAnnuler.setEnabled(true);
            barre.setValue(0);
            barre.setForeground(UIManager.getColor("ProgressBar.foreground"));
            labelEtat.setText("Traitement en cours…");

            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    for (int i = 0; i <= 100; i++) {
                        // isCancelled() : retourne true après un appel à cancel()
                        // Vérifier à chaque itération pour un arrêt propre et rapide
                        if (isCancelled()) return null;
                        Thread.sleep(60);
                        setProgress(i);
                    }
                    return null;
                }

                @Override
                protected void done() {
                    // isCancelled() dans done() distingue la fin normale de l'annulation
                    if (isCancelled()) {
                        labelEtat.setText("⛔ Annulé par l'utilisateur");
                        barre.setString("Annulé");
                        barre.setForeground(Color.ORANGE);
                    } else {
                        labelEtat.setText("✓ Tâche terminée avec succès");
                        barre.setString("Terminé !");
                        barre.setForeground(Color.decode("#4CAF50"));
                    }
                    btnDemarrer.setEnabled(true);
                    btnAnnuler.setEnabled(false);
                }
            };

            worker.addPropertyChangeListener(evt -> {
                if ("progress".equals(evt.getPropertyName())) {
                    int v = (Integer) evt.getNewValue();
                    barre.setValue(v);
                    barre.setString(v + "%");
                    labelEtat.setText("Traitement : " + v + "%");
                }
            });

            workerRef.set(worker);
            worker.execute();
        });

        // cancel(true) : envoie une interruption au thread du worker (mayInterruptIfRunning)
        // Le worker détecte l'interruption via isCancelled() dans sa boucle
        btnAnnuler.addActionListener(e -> {
            SwingWorker<?, ?> w = workerRef.get();
            if (w != null) w.cancel(true);
        });

        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        panelBoutons.add(btnDemarrer);
        panelBoutons.add(btnAnnuler);

        JTextArea zoneNote = new JTextArea(
                "cancel(true)   → interrompt le thread si possible (mayInterruptIfRunning)\n" +
                "isCancelled()  → à vérifier dans la boucle de doInBackground() pour sortir proprement\n" +
                "done()         → appelé dans tous les cas — succès, annulation ou exception"
        );
        zoneNote.setEditable(false);
        zoneNote.setBackground(Color.decode("#F5F5F5"));
        zoneNote.setFont(new Font("Monospaced", Font.PLAIN, 11));

        JPanel panelCentre = new JPanel(new BorderLayout(10, 10));
        panelCentre.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panelCentre.add(barre, BorderLayout.NORTH);
        panelCentre.add(labelEtat, BorderLayout.CENTER);

        fenetre.add(panelBoutons, BorderLayout.NORTH);
        fenetre.add(panelCentre, BorderLayout.CENTER);
        fenetre.add(zoneNote, BorderLayout.SOUTH);
        fenetre.setVisible(true);
    }
}
