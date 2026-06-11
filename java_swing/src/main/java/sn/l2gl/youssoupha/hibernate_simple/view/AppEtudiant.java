package sn.l2gl.youssoupha.hibernate_simple.view;

import sn.l2gl.youssoupha.hibernate.util.HibernateUtil;
import sn.l2gl.youssoupha.hibernate_simple.controller.EtudiantController;

import javax.swing.*;
import java.awt.*;

/**
 * Fenêtre principale du CRUD « Étudiants » (Hibernate + Swing, MVC).
 * Conformément au pattern MVC, le point d'entrée ne connaît que le contrôleur :
 * c'est ce dernier qui instancie le modèle (DAO) et la vue.
 */
public class AppEtudiant {

    private final EtudiantController controleur = new EtudiantController();

    public AppEtudiant() {
        JFrame fenetre = new JFrame("Gestion des étudiants — Hibernate + Swing (MVC)");
        fenetre.setSize(800, 500);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // fermeture gérée manuellement

        fenetre.add(controleur.getVue(), BorderLayout.CENTER); // la vue est fournie par le contrôleur
        fenetre.setVisible(true);

        fenetre.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                quitter(fenetre);
            }
        });
    }

    private void quitter(JFrame fenetre) {
        int confirmation = JOptionPane.showConfirmDialog(fenetre,
                "Quitter l'application ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation != JOptionPane.YES_OPTION) return;
        HibernateUtil.shutdown();
        fenetre.dispose();
        System.exit(0);
    }

    /** Lance l'application sur l'Event Dispatch Thread. */
    public static void lancer() {
        SwingUtilities.invokeLater(AppEtudiant::new);
    }

    public static void main(String[] args) {
        lancer();
    }
}
