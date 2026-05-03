package sn.l2gl.youssoupha.views;

import javax.swing.*;
import java.awt.*;

public class InterfaceFinaleEtudiant {
    private InterfaceFinaleEtudiant() {
        /* This utility class should not be instantiated */
    }

    public static void afficher() {
        JFrame fenetre = new JFrame("Interface finale étudiant");
        fenetre.setSize(900, 600);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.add(creerPanel());

        fenetre.setVisible(true);
    }

    public static JPanel creerPanel() {
        JPanel principal = new JPanel(new BorderLayout(10, 10));

        JPanel top = new JPanel(new BorderLayout());
        top.add(creerMenu(), BorderLayout.NORTH);
        top.add(creerHeader(), BorderLayout.CENTER);

        principal.add(top, BorderLayout.NORTH);
        principal.add(creerSidebar(), BorderLayout.WEST);
        principal.add(creerContenu(), BorderLayout.CENTER);
        principal.add(creerFooter(), BorderLayout.SOUTH);

        return principal;
    }

    private static JMenuBar creerMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = new JMenu("File");
        menuFile.add(new JMenuItem("New"));
        menuFile.add(new JMenuItem("Save"));
        menuFile.addSeparator();
        menuFile.add(new JMenuItem("Exit"));

        JMenu menuView = new JMenu("View");
        menuView.add(new JCheckBoxMenuItem("Show sidebar", true));
        menuView.add(new JCheckBoxMenuItem("Dark mode"));

        JMenu menuHelp = new JMenu("Help");
        menuHelp.add(new JMenuItem("Documentation"));
        menuHelp.add(new JMenuItem("About"));

        menuBar.add(menuFile);
        menuBar.add(menuView);
        menuBar.add(menuHelp);

        return menuBar;
    }

    private static JPanel creerHeader() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        header.setBackground(Color.decode("#2D6CDF"));

        JLabel titre = new JLabel("Final UI - Swing components");
        titre.setForeground(Color.WHITE);
        titre.setFont(new Font("Arial", Font.BOLD, 22));

        JButton boutonRefresh = new JButton("Refresh");
        JToggleButton boutonMode = new JToggleButton("Mode actif");

        header.add(titre);
        header.add(boutonRefresh);
        header.add(boutonMode);

        return header;
    }

    private static JPanel creerSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sidebar.setBackground(Color.decode("#F1F3F5"));
        sidebar.setPreferredSize(new Dimension(150, 0));

        JButton btnDashboard = new JButton("Dashboard");
        JButton btnForm = new JButton("Form");
        JButton btnSettings = new JButton("Settings");

        btnDashboard.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnForm.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSettings.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebar.add(btnDashboard);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnForm);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnSettings);

        return sidebar;
    }

    private static JPanel creerContenu() {
        JPanel contenu = new JPanel(new BorderLayout(10, 10));
        contenu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel cartes = new JPanel(new GridLayout(1, 3, 10, 10));
        cartes.add(creerCarte("Students", "120", Color.decode("#B8E986")));
        cartes.add(creerCarte("Courses", "8", Color.decode("#F8E71C")));
        cartes.add(creerCarte("Messages", "24", Color.decode("#7ED6FF")));

        contenu.add(cartes, BorderLayout.NORTH);
        contenu.add(creerFormulaire(), BorderLayout.CENTER);

        return contenu;
    }

    private static JPanel creerCarte(String titre, String valeur, Color couleur) {
        JPanel carte = new JPanel(new GridLayout(2, 1));
        carte.setBackground(couleur);
        carte.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel labelTitre = new JLabel(titre, SwingConstants.CENTER);
        JLabel labelValeur = new JLabel(valeur, SwingConstants.CENTER);
        labelValeur.setFont(new Font("Arial", Font.BOLD, 24));

        carte.add(labelTitre);
        carte.add(labelValeur);

        return carte;
    }

    private static JPanel creerFormulaire() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Student form"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField champNom = new JTextField();
        JPasswordField champPassword = new JPasswordField();
        JSpinner spinnerAge = new JSpinner(new SpinnerNumberModel(18, 10, 80, 1));
        JSlider sliderNiveau = new JSlider(0, 100, 50);
        JComboBox<String> comboClasse = new JComboBox<>(new String[]{"L1", "L2", "L3", "Master"});
        JList<String> listeCours = new JList<>(new String[]{"Java", "C++", "Php", "JavaScript"});
        JTextArea zoneMessage = new JTextArea(4, 20);
        JCheckBox checkAccept = new JCheckBox("I accept");
        JRadioButton radioMale = new JRadioButton("Male");
        JRadioButton radioFemale = new JRadioButton("Female");
        JButton boutonSubmit = new JButton("Submit");

        ButtonGroup groupeGenre = new ButtonGroup();
        groupeGenre.add(radioMale);
        groupeGenre.add(radioFemale);

        ajouterLigne(panel, gbc, 0, "Name :", champNom);
        ajouterLigne(panel, gbc, 1, "Password :", champPassword);
        ajouterLigne(panel, gbc, 2, "Age :", spinnerAge);
        ajouterLigne(panel, gbc, 3, "Level :", sliderNiveau);
        ajouterLigne(panel, gbc, 4, "Class :", comboClasse);
        ajouterLigne(panel, gbc, 5, "Courses :", new JScrollPane(listeCours));
        ajouterLigne(panel, gbc, 6, "Message :", new JScrollPane(zoneMessage));

        JPanel panelGenre = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelGenre.add(radioMale);
        panelGenre.add(radioFemale);
        ajouterLigne(panel, gbc, 7, "Gender :", panelGenre);

        gbc.gridx = 1;
        gbc.gridy = 8;
        panel.add(checkAccept, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        panel.add(boutonSubmit, gbc);

        return panel;
    }

    private static void ajouterLigne(JPanel panel, GridBagConstraints gbc, int ligne, String label, Component champ) {
        gbc.gridx = 0;
        gbc.gridy = ligne;
        gbc.weightx = 0;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.gridy = ligne;
        gbc.weightx = 1;
        panel.add(champ, gbc);
    }

    private static JPanel creerFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setBackground(Color.decode("#E9ECEF"));
        footer.add(new JLabel("Ready"));
        return footer;
    }
}
