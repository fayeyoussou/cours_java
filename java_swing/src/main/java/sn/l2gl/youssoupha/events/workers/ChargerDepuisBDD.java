package sn.l2gl.youssoupha.events.workers;

/**
 * code 17
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

// Pattern JDBC + SwingWorker : la requête SQL s'exécute dans doInBackground(), la table se remplit dans done()
// Dans un vrai projet, remplacer simulerChargementBDD() par DriverManager.getConnection() + PreparedStatement
public class ChargerDepuisBDD {
    private ChargerDepuisBDD() {
        /* Classe utilitaire */
    }

    // Objet métier représentant une ligne de la table SQL "etudiant"
    record Etudiant(int id, String nom, String prenom, double note) {
        Object[] toRow() {
            return new Object[]{id, nom, prenom, note};
        }
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("Charger depuis une BDD — pattern JDBC + SwingWorker");
        fenetre.setSize(720, 520);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout(10, 10));

        String[] colonnes = {"ID", "Nom", "Prénom", "Note"};
        DefaultTableModel modele = new DefaultTableModel(colonnes, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        JTable table = new JTable(modele);

        JProgressBar barre = new JProgressBar();
        barre.setStringPainted(true);
        barre.setString("Prêt");

        JButton btnCharger = new JButton("SELECT * FROM etudiant");
        JLabel  labelEtat  = new JLabel("Prêt", SwingConstants.CENTER);

        // Pattern JDBC à remplacer par la vraie connexion
        JTextArea zonePatternJDBC = new JTextArea(
                "Pattern JDBC dans doInBackground() :\n\n" +
                "String sql = \"SELECT id, nom, prenom, note FROM etudiant\";\n" +
                "try (Connection  c  = DriverManager.getConnection(URL, USER, PWD);\n" +
                "     PreparedStatement ps = c.prepareStatement(sql);\n" +
                "     ResultSet rs = ps.executeQuery()) {\n" +
                "    while (rs.next()) {\n" +
                "        liste.add(new Etudiant(\n" +
                "            rs.getInt(\"id\"),     rs.getString(\"nom\"),\n" +
                "            rs.getString(\"prenom\"), rs.getDouble(\"note\")));\n" +
                "    }\n" +
                "}\n" +
                "→ ici simulé par Thread.sleep() + données statiques"
        );
        zonePatternJDBC.setEditable(false);
        zonePatternJDBC.setFont(new Font("Monospaced", Font.PLAIN, 11));
        zonePatternJDBC.setBackground(Color.decode("#F8F8F8"));

        btnCharger.addActionListener(e -> {
            modele.setRowCount(0); // vider la table avant rechargement
            btnCharger.setEnabled(false);
            barre.setIndeterminate(true);
            barre.setString("Connexion…");
            labelEtat.setText("Exécution de la requête SQL…");

            new SwingWorker<List<Etudiant>, Void>() {
                @Override
                protected List<Etudiant> doInBackground() throws Exception {
                    // ⚠️ Hors EDT — la connexion JDBC ne bloque pas l'interface ici
                    return simulerChargementBDD();
                }

                @Override
                protected void done() {
                    // ✅ Sur l'EDT — remplir la JTable avec les données reçues
                    try {
                        List<Etudiant> liste = get(); // get() récupère le résultat de doInBackground()
                        modele.setRowCount(0);        // vider au cas où done() serait appelé plusieurs fois
                        for (Etudiant et : liste) {
                            modele.addRow(et.toRow()); // addRow() ajoute une ligne au DefaultTableModel
                        }
                        barre.setString(liste.size() + " ligne(s) chargée(s)");
                        labelEtat.setText("✓ " + liste.size() + " enregistrement(s) récupéré(s)");
                    } catch (InterruptedException | ExecutionException ex) {
                        // ExecutionException encapsule toute exception levée dans doInBackground()
                        JOptionPane.showMessageDialog(fenetre, "Erreur BDD : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                        labelEtat.setText("Erreur de chargement");
                        barre.setString("Erreur");
                    } finally {
                        barre.setIndeterminate(false);
                        barre.setValue(100);
                        btnCharger.setEnabled(true);
                    }
                }
            }.execute();
        });

        JPanel panelHaut = new JPanel(new BorderLayout(5, 5));
        panelHaut.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        panelHaut.add(btnCharger, BorderLayout.NORTH);
        panelHaut.add(barre, BorderLayout.CENTER);
        panelHaut.add(labelEtat, BorderLayout.SOUTH);

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(table), zonePatternJDBC);
        split.setResizeWeight(0.55);

        JTextArea zoneNote = new JTextArea(
                "À retenir :\n" +
                "• La connexion JDBC (DriverManager.getConnection) doit s'exécuter dans doInBackground()\n" +
                "• Toujours utiliser try-with-resources pour Connection, PreparedStatement et ResultSet\n" +
                "• modele.setRowCount(0) avant d'ajouter des lignes → évite les doublons au rechargement\n" +
                "• get() dans done() peut lever ExecutionException → toujours entourer d'un try/catch"
        );
        zoneNote.setEditable(false);
        zoneNote.setBackground(Color.decode("#F5F5F5"));
        zoneNote.setFont(new Font("Monospaced", Font.PLAIN, 11));

        fenetre.add(panelHaut, BorderLayout.NORTH);
        fenetre.add(split, BorderLayout.CENTER);
        fenetre.add(zoneNote, BorderLayout.SOUTH);
        fenetre.setVisible(true);
    }

    // Remplacer cette méthode par un vrai appel JDBC dans un projet réel
    private static List<Etudiant> simulerChargementBDD() throws InterruptedException {
        Thread.sleep(2000); // simule le temps de réponse de la base de données
        return List.of(
                new Etudiant(1, "Ndiaye",  "Awa",      15.5),
                new Etudiant(2, "Diallo",  "Moussa",   12.0),
                new Etudiant(3, "Fall",    "Fatou",    17.5),
                new Etudiant(4, "Sow",     "Ibrahima",  9.0),
                new Etudiant(5, "Mbaye",   "Aïssatou", 14.5),
                new Etudiant(6, "Camara",  "Seydou",   11.0),
                new Etudiant(7, "Bah",     "Mariama",  16.0)
        );
    }
}
