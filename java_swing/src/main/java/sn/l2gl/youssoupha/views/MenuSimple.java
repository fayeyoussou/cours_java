package sn.l2gl.youssoupha.views;

import javax.swing.*;
import java.awt.*;

public class MenuSimple {
    private MenuSimple() {
        /* This utility class should not be instantiated */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("Menu simple");
        fenetre.setSize(500, 300);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = new JMenu("File");
        JMenuItem itemNew = new JMenuItem("New");
        JMenuItem itemOpen = new JMenuItem("Open");
        JMenuItem itemExit = new JMenuItem("Exit");
        menuFile.add(itemNew);
        menuFile.add(itemOpen);
        menuFile.addSeparator();
        menuFile.add(itemExit);

        JMenu menuView = new JMenu("View");
        JCheckBoxMenuItem itemShowToolbar = new JCheckBoxMenuItem("Show toolbar");
        JCheckBoxMenuItem itemDarkMode = new JCheckBoxMenuItem("Dark mode");
        itemShowToolbar.setSelected(true);
        menuView.add(itemShowToolbar);
        menuView.add(itemDarkMode);

        JMenu menuHelp = new JMenu("Help");
        JMenuItem itemDocumentation = new JMenuItem("Documentation");
        JMenuItem itemAbout = new JMenuItem("About");
        menuHelp.add(itemDocumentation);
        menuHelp.add(itemAbout);

        menuBar.add(menuFile);
        menuBar.add(menuView);
        menuBar.add(menuHelp);

        JLabel contenu = new JLabel("Contenu de la fenêtre", SwingConstants.CENTER);
        contenu.setFont(new Font("Arial", Font.BOLD, 22));

        fenetre.setJMenuBar(menuBar);
        fenetre.add(contenu);
        fenetre.setVisible(true);
    }
}
