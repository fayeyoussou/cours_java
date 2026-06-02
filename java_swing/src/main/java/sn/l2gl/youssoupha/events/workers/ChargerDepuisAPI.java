package sn.l2gl.youssoupha.events.workers;

/**
 * code 18
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

// Pattern API REST + SwingWorker : appel HTTP dans doInBackground(), table remplie dans done()
// Dans un vrai projet, remplacer simulerRequeteAPI() par HttpURLConnection ou HttpClient (Java 11+)
public class ChargerDepuisAPI {
    private ChargerDepuisAPI() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("Charger depuis une API REST — pattern SwingWorker");
        fenetre.setSize(720, 520);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout(10, 10));

        String[] colonnes = {"ID", "Nom", "Prénom", "Email"};
        DefaultTableModel modele = new DefaultTableModel(colonnes, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        JTable table = new JTable(modele);

        JProgressBar barre = new JProgressBar();
        barre.setStringPainted(true);
        barre.setString("En attente…");

        JButton btnGet     = new JButton("GET /api/etudiants");
        JLabel  labelEtat  = new JLabel("En attente d'une requête…", SwingConstants.CENTER);

        JTextArea zonePatternHttp = new JTextArea(
                "Pattern HttpURLConnection dans doInBackground() :\n\n" +
                "URL url = new URL(\"https://api.exemple.com/etudiants\");\n" +
                "HttpURLConnection con = (HttpURLConnection) url.openConnection();\n" +
                "con.setRequestMethod(\"GET\");\n" +
                "con.setRequestProperty(\"Accept\", \"application/json\");\n" +
                "con.setConnectTimeout(5000);   // timeout de connexion\n" +
                "con.setReadTimeout(10000);     // timeout de lecture\n\n" +
                "try (BufferedReader r = new BufferedReader(\n" +
                "         new InputStreamReader(con.getInputStream()))) {\n" +
                "    // lire et assembler la réponse JSON, puis parser avec Gson / Jackson\n" +
                "}\n" +
                "→ ici simulé par Thread.sleep() + données statiques"
        );
        zonePatternHttp.setEditable(false);
        zonePatternHttp.setFont(new Font("Monospaced", Font.PLAIN, 11));
        zonePatternHttp.setBackground(Color.decode("#F8F8F8"));

        btnGet.addActionListener(e -> {
            modele.setRowCount(0);
            btnGet.setEnabled(false);
            barre.setIndeterminate(true);
            barre.setString("Requête en cours…");
            labelEtat.setText("GET https://api.exemple.com/etudiants…");

            new SwingWorker<List<Object[]>, Void>() {
                @Override
                protected List<Object[]> doInBackground() throws Exception {
                    // ⚠️ Hors EDT — l'appel réseau ne bloque pas l'interface
                    return simulerRequeteAPI();
                }

                @Override
                protected void done() {
                    // ✅ Sur l'EDT — afficher les données JSON parsées dans la table
                    try {
                        List<Object[]> lignes = get();
                        modele.setRowCount(0);
                        for (Object[] ligne : lignes) {
                            modele.addRow(ligne); // chaque tableau Object[] = une ligne de la table
                        }
                        barre.setString("200 OK — " + lignes.size() + " résultat(s)");
                        labelEtat.setText("✓ " + lignes.size() + " enregistrement(s) reçus — 200 OK");
                    } catch (InterruptedException | ExecutionException ex) {
                        // Afficher l'erreur sans crash — l'API peut être indisponible
                        barre.setString("Erreur réseau");
                        labelEtat.setText("API indisponible : " + ex.getMessage());
                        JOptionPane.showMessageDialog(fenetre, "Erreur réseau : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        barre.setIndeterminate(false);
                        btnGet.setEnabled(true);
                    }
                }
            }.execute();
        });

        JPanel panelHaut = new JPanel(new BorderLayout(5, 5));
        panelHaut.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        panelHaut.add(btnGet, BorderLayout.NORTH);
        panelHaut.add(barre, BorderLayout.CENTER);
        panelHaut.add(labelEtat, BorderLayout.SOUTH);

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(table), zonePatternHttp);
        split.setResizeWeight(0.5);

        fenetre.add(panelHaut, BorderLayout.NORTH);
        fenetre.add(split, BorderLayout.CENTER);
        fenetre.setVisible(true);
    }

    // Remplacer cette méthode par un vrai appel HTTP + parsing JSON (Gson, Jackson…)
    private static List<Object[]> simulerRequeteAPI() throws InterruptedException {
        Thread.sleep(2500); // simule la latence réseau
        return List.of(
                new Object[]{1, "Ndiaye",  "Awa",      "awa.ndiaye@l2gl.isi.sn"},
                new Object[]{2, "Diallo",  "Moussa",   "moussa.diallo@l2gl.isi.sn"},
                new Object[]{3, "Fall",    "Fatou",    "fatou.fall@l2gl.isi.sn"},
                new Object[]{4, "Sow",     "Ibrahima", "ibrahima.sow@l2gl.isi.sn"},
                new Object[]{5, "Mbaye",   "Aïssatou", "aissatou.mbaye@l2gl.isi.sn"}
        );
    }
}
