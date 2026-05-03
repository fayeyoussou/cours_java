package sn.l2gl.youssoupha.views;

import javax.swing.*;
import java.awt.*;

public class FormulaireAvance {
    private FormulaireAvance() {
        /* This utility class should not be instantiated */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("Advance form");
        fenetre.setSize(600, 500);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField champNom = new JTextField();

        JSpinner spinnerAge = new JSpinner(new SpinnerNumberModel(18, 0, 100, 1));

        JSlider sliderNiveau = new JSlider(0, 100, 50);
        sliderNiveau.setPaintTicks(true);
        sliderNiveau.setPaintLabels(true);
        sliderNiveau.setMajorTickSpacing(5);

        JComboBox<String> comboPays = new JComboBox<>(new String[]{"Sénégal", "France", "Canada", "Maroc"});

        JList<String> listeLangages = new JList<>(new String[]{"Java", "Python", "JavaScript", "PHP"});

        JTextArea zoneMessage = new JTextArea();

        JButton boutonSubmit = new JButton("Submit");

        panel.add(new JLabel("Nom :"));
        panel.add(champNom);

        panel.add(new JLabel("Age :"));
        panel.add(spinnerAge);

        panel.add(new JLabel("Niveau :"));
        panel.add(sliderNiveau);

        panel.add(new JLabel("Pays :"));
        panel.add(comboPays);

        panel.add(new JLabel("Langage :"));
        panel.add(new JScrollPane(listeLangages));

        panel.add(new JLabel("Message :"));
        panel.add(new JScrollPane(zoneMessage));

        panel.add(new JLabel());
        panel.add(boutonSubmit);

        fenetre.add(panel);
        fenetre.setVisible(true);
    }
}
