package sn.l2gl.youssoupha.events;

/**
 * code 3
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// KeyListener : écouter le clavier — keyPressed, keyReleased, keyTyped
// KeyAdapter : classe abstraite qui simplifie l'implémentation (n'override que les méthodes utiles)
public class KeyListenerExemple {
    private KeyListenerExemple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("KeyListener & KeyAdapter");
        fenetre.setSize(620, 430);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout(10, 10));

        JPanel panelChamps = new JPanel(new GridLayout(3, 2, 10, 10));
        panelChamps.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 15));

        // --- Champ 1 : filtrage de caractères avec keyTyped ---
        JTextField champChiffres = new JTextField();

        // KeyAdapter — n'override que keyTyped, les 2 autres méthodes restent vides (propre)
        champChiffres.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                // keyTyped : déclenché quand un caractère imprimable est produit
                // e.consume() annule le caractère — il n'apparaîtra pas dans le champ
                if (!Character.isDigit(e.getKeyChar())) e.consume();
            }
        });

        // --- Champ 2 : raccourcis clavier avec keyPressed ---
        JTextField champRaccourci = new JTextField();
        JTextArea zoneLog = new JTextArea(8, 50);
        zoneLog.setEditable(false);
        zoneLog.setFont(new Font("Monospaced", Font.PLAIN, 12));

        champRaccourci.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // keyPressed : déclenché dès que la touche s'enfonce (avant keyTyped)
                // isControlDown() vérifie si Ctrl est maintenu enfoncé simultanément
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
                    zoneLog.append("[Ctrl+S] Sauvegarde déclenchée\n");
                }
                // VK_ESCAPE pour vider le champ
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    champRaccourci.setText("");
                    zoneLog.append("[Échap] Champ vidé\n");
                }
            }
        });

        // --- Champ 3 : KeyListener complet (les 3 méthodes sont obligatoires) ---
        JTextField champComplet = new JTextField();

        // KeyListener (interface) : impose d'implémenter les 3 méthodes — utiliser KeyAdapter si 2 sont inutiles
        champComplet.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                zoneLog.append("[Pressée] " + KeyEvent.getKeyText(e.getKeyCode()) + "\n");
            }
            @Override
            public void keyReleased(KeyEvent e) {
                // keyReleased : déclenché quand la touche est relâchée
            }
            @Override
            public void keyTyped(KeyEvent e) {
                zoneLog.append("[Caractère] '" + e.getKeyChar() + "'\n");
            }
        });

        panelChamps.add(new JLabel("Chiffres seulement (keyTyped + consume) :"));
        panelChamps.add(champChiffres);
        panelChamps.add(new JLabel("Raccourcis Ctrl+S / Échap (keyPressed) :"));
        panelChamps.add(champRaccourci);
        panelChamps.add(new JLabel("KeyListener complet (3 méthodes) :"));
        panelChamps.add(champComplet);

        JTextArea zoneNote = new JTextArea(
                "À retenir :\n" +
                "• keyPressed  → touche enfoncée (avant la frappe) — raccourcis et touches spéciales\n" +
                "• keyTyped    → caractère imprimable produit — e.consume() pour bloquer la frappe\n" +
                "• keyReleased → touche relâchée\n" +
                "• Utiliser KeyAdapter pour n'implémenter que les méthodes utiles\n" +
                "• Codes : VK_ENTER, VK_ESCAPE, VK_F1..F12 — modificateurs : isControlDown(), isShiftDown(), isAltDown()"
        );
        zoneNote.setEditable(false);
        zoneNote.setBackground(Color.decode("#F5F5F5"));
        zoneNote.setFont(new Font("Monospaced", Font.PLAIN, 11));

        fenetre.add(panelChamps, BorderLayout.NORTH);
        fenetre.add(new JScrollPane(zoneLog), BorderLayout.CENTER);
        fenetre.add(zoneNote, BorderLayout.SOUTH);
        fenetre.setVisible(true);
    }
}
