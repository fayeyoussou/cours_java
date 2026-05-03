package sn.l2gl.youssoupha.views;

import javax.swing.*;
import java.awt.*;

public class TypesBoutons {
    private TypesBoutons() {
        /* This utility class should not be instantiated */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("Types de boutons");
        fenetre.setSize(600, 400);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new GridLayout(2, 2, 10, 10));

        JPanel panelBouton = new JPanel();
        panelBouton.setBorder(BorderFactory.createTitledBorder("JButton"));
        JButton bouton = new JButton("OK");
        panelBouton.add(bouton);

        JPanel panelToggle = new JPanel();
        panelToggle.setBorder(BorderFactory.createTitledBorder("JToggleButton"));
        JToggleButton toggleButton = new JToggleButton("Gras");
        toggleButton.setSelected(true);
        panelToggle.add(toggleButton);

        JPanel panelCheckbox = new JPanel();
        panelCheckbox.setBorder(BorderFactory.createTitledBorder("JCheckBox"));
        panelCheckbox.setLayout(new BoxLayout(panelCheckbox, BoxLayout.Y_AXIS));
        JCheckBox checkbox1 = new JCheckBox("J'accepte");
        JCheckBox checkbox2 = new JCheckBox("Recevoir les emails");
        JCheckBox checkbox3 = new JCheckBox("Activer les notifications");
        panelCheckbox.add(checkbox1);
        panelCheckbox.add(checkbox2);
        panelCheckbox.add(checkbox3);

        JPanel panelRadio = new JPanel();
        panelRadio.setBorder(BorderFactory.createTitledBorder("JRadioButton"));
        panelRadio.setLayout(new BoxLayout(panelRadio, BoxLayout.Y_AXIS));
        JRadioButton radio1 = new JRadioButton("Petit");
        JRadioButton radio2 = new JRadioButton("Moyen");
        JRadioButton radio3 = new JRadioButton("Grand");
        ButtonGroup groupe = new ButtonGroup();
        groupe.add(radio1);
        groupe.add(radio2);
        groupe.add(radio3);
        radio2.setSelected(true);
        panelRadio.add(radio1);
        panelRadio.add(radio2);
        panelRadio.add(radio3);

        fenetre.add(panelBouton);
        fenetre.add(panelToggle);
        fenetre.add(panelCheckbox);
        fenetre.add(panelRadio);

        fenetre.setVisible(true);
    }
}
