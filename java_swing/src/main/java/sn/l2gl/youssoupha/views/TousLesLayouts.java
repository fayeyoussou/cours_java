package sn.l2gl.youssoupha.views;

import sn.l2gl.youssoupha.views.layouts.*;

import javax.swing.*;

public class TousLesLayouts {
    private TousLesLayouts() {
        /* This utility class should not be instantiated */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("Tous les layouts");
        fenetre.setSize(700, 500);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("FlowLayout", ExempleFlowLayout.creerPanel());
        tabs.addTab("BorderLayout", ExempleBorderLayout.creerPanel());
        tabs.addTab("GridLayout", ExempleGridLayout.creerPanel());
        tabs.addTab("GridBagLayout", ExempleGridBagLayout.creerPanel());
        tabs.addTab("BoxLayout", ExempleBoxLayout.creerPanel());
        tabs.addTab("CardLayout", ExempleCardLayout.creerPanel());

        fenetre.add(tabs);
        fenetre.setVisible(true);
    }
}
