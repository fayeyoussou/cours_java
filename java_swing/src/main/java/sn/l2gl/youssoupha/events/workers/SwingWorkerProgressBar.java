package sn.l2gl.youssoupha.events.workers;

/**
 * code 15
 */

import javax.swing.*;
import java.awt.*;

// JProgressBar en mode déterminé (setProgress 0..100) et indéterminé (animation continue)
// Connectée au SwingWorker via un PropertyChangeListener sur "progress"
public class SwingWorkerProgressBar {
    private SwingWorkerProgressBar() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("JProgressBar + SwingWorker");
        fenetre.setSize(560, 350);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout(0, 5));

        // --- Mode déterminé : progression connue (0..100%) ---
        JPanel panelDet = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        panelDet.setBorder(BorderFactory.createTitledBorder("Mode déterminé (progression connue)"));

        JProgressBar barreDet = new JProgressBar(0, 100);
        barreDet.setStringPainted(true); // affiche le % en texte sur la barre
        barreDet.setPreferredSize(new Dimension(280, 22));
        JButton btnDet  = new JButton("Démarrer");
        JLabel  lbDet   = new JLabel("En attente…");

        btnDet.addActionListener(e -> {
            btnDet.setEnabled(false);
            barreDet.setValue(0);
            lbDet.setText("En cours…");

            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    for (int i = 0; i <= 100; i++) {
                        Thread.sleep(40);
                        // setProgress(0..100) déclenche un PropertyChangeEvent "progress" sur l'EDT
                        setProgress(i);
                    }
                    return null;
                }
                @Override
                protected void done() {
                    lbDet.setText("✓ Terminé");
                    btnDet.setEnabled(true);
                }
            };

            // PropertyChangeListener : déclenché à chaque appel de setProgress() dans doInBackground()
            worker.addPropertyChangeListener(evt -> {
                if ("progress".equals(evt.getPropertyName())) {
                    int v = (Integer) evt.getNewValue();
                    barreDet.setValue(v);
                    lbDet.setText(v + "%");
                }
            });

            worker.execute();
        });

        panelDet.add(barreDet);
        panelDet.add(btnDet);
        panelDet.add(lbDet);

        // --- Mode indéterminé : durée inconnue ---
        JPanel panelIndet = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        panelIndet.setBorder(BorderFactory.createTitledBorder("Mode indéterminé (durée inconnue)"));

        JProgressBar barreIndet = new JProgressBar();
        barreIndet.setStringPainted(true);
        barreIndet.setString("En attente…");
        barreIndet.setPreferredSize(new Dimension(280, 22));
        JButton btnIndet = new JButton("Démarrer");
        JLabel  lbIndet  = new JLabel("En attente…");

        btnIndet.addActionListener(e -> {
            btnIndet.setEnabled(false);
            // setIndeterminate(true) : animation va-et-vient, utilisé quand la durée est inconnue
            barreIndet.setIndeterminate(true);
            barreIndet.setString("Chargement…");
            lbIndet.setText("Tâche en cours…");

            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    Thread.sleep(4000); // simule une tâche de durée inconnue
                    return null;
                }
                @Override
                protected void done() {
                    // Arrêter l'animation et indiquer la fin
                    barreIndet.setIndeterminate(false);
                    barreIndet.setValue(100);
                    barreIndet.setString("Terminé !");
                    lbIndet.setText("✓ Terminé");
                    btnIndet.setEnabled(true);
                }
            };
            worker.execute();
        });

        panelIndet.add(barreIndet);
        panelIndet.add(btnIndet);
        panelIndet.add(lbIndet);

        JTextArea zoneNote = new JTextArea(
                "À retenir :\n" +
                "• setIndeterminate(true) → animation va-et-vient pour les tâches de durée inconnue\n" +
                "• setProgress(0..100) dans doInBackground() → déclenche PropertyChangeEvent \"progress\"\n" +
                "• PropertyChangeListener → connecter le worker à la JProgressBar depuis l'EDT\n" +
                "• setStringPainted(true) → affiche un texte sur la barre (par défaut : le pourcentage)"
        );
        zoneNote.setEditable(false);
        zoneNote.setBackground(Color.decode("#F5F5F5"));
        zoneNote.setFont(new Font("Monospaced", Font.PLAIN, 11));

        JPanel panelBarres = new JPanel(new GridLayout(2, 1, 0, 5));
        panelBarres.add(panelDet);
        panelBarres.add(panelIndet);

        fenetre.add(panelBarres, BorderLayout.CENTER);
        fenetre.add(zoneNote, BorderLayout.SOUTH);
        fenetre.setVisible(true);
    }
}
