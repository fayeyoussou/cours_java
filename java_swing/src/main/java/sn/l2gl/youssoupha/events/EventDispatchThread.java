package sn.l2gl.youssoupha.events;

/**
 * code 10
 */

import javax.swing.*;
import java.awt.*;

// EDT (Event Dispatch Thread) : thread unique gérant toute l'interface Swing
// Règle d'or : TOUTE création et modification de composants doit se faire sur l'EDT
public class EventDispatchThread {
    private EventDispatchThread() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        // invokeLater() planifie le Runnable sur l'EDT — le thread main n'est PAS l'EDT
        SwingUtilities.invokeLater(() -> {
            JFrame fenetre = new JFrame("Event Dispatch Thread");
            fenetre.setSize(620, 420);
            fenetre.setLocationRelativeTo(null);
            fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fenetre.setLayout(new BorderLayout(10, 10));

            JTextArea zoneLog = new JTextArea();
            zoneLog.setEditable(false);
            zoneLog.setFont(new Font("Monospaced", Font.PLAIN, 12));

            JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

            JButton boutonOk      = new JButton("✓ Modification sur l'EDT");
            JButton boutonDanger  = new JButton("✗ Modification hors EDT (dangereux)");
            JButton boutonVerif   = new JButton("Vérifier le thread courant");
            JLabel  labelEtat     = new JLabel("", SwingConstants.CENTER);

            boutonOk.setBackground(Color.decode("#4CAF50"));
            boutonOk.setForeground(Color.WHITE);
            boutonDanger.setBackground(Color.decode("#F44336"));
            boutonDanger.setForeground(Color.WHITE);

            // Ce listener s'exécute déjà sur l'EDT — tous les ActionListener Swing le sont
            boutonOk.addActionListener(e -> {
                zoneLog.append("[EDT] Thread : " + Thread.currentThread().getName() + "\n");
                labelEtat.setForeground(Color.decode("#4CAF50"));
                labelEtat.setText("✓ Exécuté correctement sur l'EDT");
            });

            // Simulation d'une modification depuis un thread ordinaire — comportement incorrect
            boutonDanger.addActionListener(e -> {
                new Thread(() -> {
                    // Modifier des composants Swing hors EDT cause des bugs visuels aléatoires
                    // (redessins manqués, deadlocks, états incohérents)
                    zoneLog.append("[HORS EDT] Thread : " + Thread.currentThread().getName() + " ← dangereux\n");

                    // Pour revenir sur l'EDT depuis n'importe quel thread : invokeLater()
                    SwingUtilities.invokeLater(() -> {
                        labelEtat.setForeground(Color.RED);
                        labelEtat.setText("⚠ Modification hors EDT — toujours utiliser invokeLater()");
                    });
                }).start();
            });

            // isEventDispatchThread() : vérifier si le code courant s'exécute sur l'EDT
            boutonVerif.addActionListener(e -> {
                boolean surEdt = SwingUtilities.isEventDispatchThread();
                zoneLog.append("isEventDispatchThread() = " + surEdt
                        + " — thread : " + Thread.currentThread().getName() + "\n");
            });

            panelBoutons.add(boutonOk);
            panelBoutons.add(boutonDanger);
            panelBoutons.add(boutonVerif);

            JTextArea zoneRegle = new JTextArea(
                    "À retenir :\n" +
                    "• Toute création et modification de composants Swing doit se faire sur l'EDT\n" +
                    "• invokeLater(r)   → planifie r sur l'EDT et retourne immédiatement (recommandé)\n" +
                    "• invokeAndWait(r) → planifie r et BLOQUE jusqu'à exécution (ne jamais appeler depuis l'EDT)\n" +
                    "• Tous les listeners s'exécutent déjà sur l'EDT — pas besoin d'invokeLater dans un ActionListener\n" +
                    "• isEventDispatchThread() → vérifier si le code courant tourne sur l'EDT (utile en débogage)"
            );
            zoneRegle.setEditable(false);
            zoneRegle.setBackground(Color.decode("#F5F5F5"));
            zoneRegle.setFont(new Font("Monospaced", Font.PLAIN, 11));

            fenetre.add(panelBoutons, BorderLayout.NORTH);
            fenetre.add(new JScrollPane(zoneLog), BorderLayout.CENTER);
            fenetre.add(zoneRegle, BorderLayout.SOUTH);
            fenetre.setVisible(true);
        });
    }
}
