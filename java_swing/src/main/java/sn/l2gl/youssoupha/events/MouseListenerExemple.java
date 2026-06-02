package sn.l2gl.youssoupha.events;

/**
 * code 4
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

// MouseListener : clic, double-clic, clic droit, survol (entered/exited)
// MouseMotionListener : déplacement et glissement de la souris
public class MouseListenerExemple {
    private MouseListenerExemple() {
        /* Classe utilitaire */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("MouseListener & MouseAdapter");
        fenetre.setSize(620, 460);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout(10, 10));

        // Zone de dessin : écoute le déplacement de la souris
        JPanel zoneDessin = new JPanel();
        zoneDessin.setBackground(Color.WHITE);
        zoneDessin.setBorder(BorderFactory.createTitledBorder("Zone de clic et de survol — déplacez la souris"));
        zoneDessin.setPreferredSize(new Dimension(600, 160));

        // Label avec effet hover
        JLabel labelHover = new JLabel("Survolez-moi", SwingConstants.CENTER);
        labelHover.setFont(new Font("Arial", Font.BOLD, 16));
        labelHover.setOpaque(true);
        labelHover.setBackground(Color.LIGHT_GRAY);
        labelHover.setPreferredSize(new Dimension(200, 60));

        JLabel labelPosition = new JLabel("Position : (-, -)");
        JTextArea zoneLog = new JTextArea(7, 50);
        zoneLog.setEditable(false);
        zoneLog.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Menu contextuel affiché au clic droit
        JPopupMenu menuContextuel = new JPopupMenu();
        menuContextuel.add(new JMenuItem("Copier"));
        menuContextuel.add(new JMenuItem("Coller"));
        menuContextuel.addSeparator();
        menuContextuel.add(new JMenuItem("Effacer la zone"));

        // MouseAdapter sur la zone de dessin — on n'override que mouseClicked
        zoneDessin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // getClickCount() : nombre de clics consécutifs rapides
                if (e.getClickCount() == 2) {
                    zoneLog.append("Double-clic à (" + e.getX() + ", " + e.getY() + ")\n");
                }
                // isRightMouseButton() : détecter le bouton droit pour le menu contextuel
                if (SwingUtilities.isRightMouseButton(e)) {
                    // show() affiche le popup aux coordonnées du clic
                    menuContextuel.show(e.getComponent(), e.getX(), e.getY());
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                // getButton() : 1 = gauche, 2 = molette, 3 = droit
                zoneLog.append("Bouton " + e.getButton() + " pressé à (" + e.getX() + ", " + e.getY() + ")\n");
            }
        });

        // Effet hover : changer la couleur d'un label quand la souris entre ou sort
        labelHover.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // mouseEntered : la souris entre dans la zone du composant
                labelHover.setBackground(Color.decode("#2196F3"));
                labelHover.setForeground(Color.WHITE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                // mouseExited : la souris quitte la zone du composant
                labelHover.setBackground(Color.LIGHT_GRAY);
                labelHover.setForeground(Color.BLACK);
            }
        });

        // MouseMotionAdapter : suivre la position en temps réel (sans clic)
        zoneDessin.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                // mouseMoved : souris se déplace sans bouton pressé
                labelPosition.setText("Position : (" + e.getX() + ", " + e.getY() + ")");
            }
            @Override
            public void mouseDragged(MouseEvent e) {
                // mouseDragged : souris se déplace avec un bouton pressé
                labelPosition.setText("Glissement : (" + e.getX() + ", " + e.getY() + ")");
            }
        });

        zoneDessin.add(labelHover);

        JPanel panelBas = new JPanel(new BorderLayout(5, 5));
        panelBas.add(labelPosition, BorderLayout.NORTH);
        panelBas.add(new JScrollPane(zoneLog), BorderLayout.CENTER);

        fenetre.add(zoneDessin, BorderLayout.NORTH);
        fenetre.add(panelBas, BorderLayout.CENTER);
        fenetre.setVisible(true);
    }
}
