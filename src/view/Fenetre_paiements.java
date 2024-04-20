package view;

import javax.swing.*;
import java.awt.*;

public class Fenetre_paiements extends JFrame {
    // Déclaration des composants en haut de la classe
    JButton retourButton = new JButton("Retour");
    JLabel appartementLabel = new JLabel("Appartement numero 5");
    JLabel remarques = new JLabel("Remarques:");
    JTextArea textArea = new JTextArea(5, 20);
    JScrollPane scrollPaneText = new JScrollPane(textArea); 
    String[] columnNames = {"Colonne 1", "Colonne 2"};
    Object[][] data = {
        {"Donnée 1", "Donnée 2"},
        {"Donnée 3", "Donnée 4"},
        {"Donnée 1", "Donnée 2"},
        {"Donnée 3", "Donnée 4"},
        {"Donnée 1", "Donnée 2"},
        {"Donnée 3", "Donnée 4"},
        {"Donnée 1", "Donnée 2"},
        {"Donnée 3", "Donnée 4"},
        {"Donnée 1", "Donnée 2"},
        {"Donnée 3", "Donnée 4"},
        {"Donnée 1", "Donnée 2"},
        {"Donnée 3", "Donnée 4"},
        {"Donnée 1", "Donnée 2"},
        {"Donnée 3", "Donnée 4"},
        {"Donnée 1", "Donnée 2"},
        {"Donnée 3", "Donnée 4"},
        {"Donnée 1", "Donnée 2"},
        {"Donnée 3", "Donnée 4"},
        {"Donnée 1", "Donnée 2"},
        {"Donnée 3", "Donnée 4"},
        {"Donnée 1", "Donnée 2"},
        {"Donnée 3", "Donnée 4"}
    };
    JTable table = new JTable(data, columnNames);
    JScrollPane scrollPaneTable = new JScrollPane(table);
    JTextArea remarquesArea = new JTextArea(10, 20);
    JScrollPane scrollPaneRemarques = new JScrollPane(remarquesArea); 
    JPanel topPanel = new JPanel();
    JPanel centerPanel = new JPanel(new BorderLayout());
    JPanel remarquesPanel = new JPanel(new BorderLayout());
    
    JPanel p12 = new JPanel();
    JButton bajouter = new JButton("Ajouter");
    JButton bsupprimer = new JButton("Supprimer");
    JLabel lRecherche = new JLabel("Recharcher par date");
    
    JPanel pLower = new JPanel(new BorderLayout());
    JPanel buttonAndTablePanel = new JPanel(new BorderLayout());
    JTextField textField = new JTextField(10);
    JButton rechercher = new JButton("rechercher");

    public Fenetre_paiements() {
        setTitle("Appartement numero 5");
        setSize(669, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configuration du texte scrollable
        textArea.setText("Porchain paiement dans 24 jours\nAucun problème a signaler Aucun problème a signaler Aucun problème a signaler Aucun problème a signaler Aucun problème a signaler Aucun problème a signaler Aucun problème a signaler Aucun problème a signaler Aucun problème a signaler Aucun problème a signaler Aucun problème a signaler Aucun problème a signaler Aucun problème a signaler Aucun problème a signaler Aucun problème a signaler Aucun problème a signaler"); // Ajoute du texte
        textArea.setEditable(false); // Rend le JTextArea non modifiable
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPaneText.setMinimumSize(new Dimension(0, 100)); // Définit la hauteur minimale à 100

        // Ajout des composants au layout du haut
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(retourButton);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(appartementLabel);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(Box.createHorizontalGlue());

        // Ajoutez le JTextField en haut
        JPanel pTextField = new JPanel();
        pTextField.add(lRecherche);
        pTextField.add(textField);
        pTextField.add(rechercher);
        buttonAndTablePanel.add(pTextField, BorderLayout.NORTH);

        // Ajoutez le tableau au centre
        buttonAndTablePanel.add(scrollPaneTable, BorderLayout.CENTER);

        // Ajout des boutons en bas
        p12.add(bajouter);
        p12.add(bsupprimer);
        buttonAndTablePanel.add(p12, BorderLayout.SOUTH);

        // Maintenant, ajoutez buttonAndTablePanel à pLower
        pLower.add(buttonAndTablePanel, BorderLayout.CENTER);

        // Ajout des composants au layout du centre
        centerPanel.add(scrollPaneText, BorderLayout.NORTH);
        centerPanel.add(pLower, BorderLayout.CENTER);

        // Configuration du JTextArea pour les remarques
        remarquesArea.setLineWrap(true);
        remarquesArea.setWrapStyleWord(true);

        // Ajout des composants au JPanel pour les remarques
        remarquesPanel.add(remarques, BorderLayout.NORTH);
        remarquesPanel.add(scrollPaneRemarques, BorderLayout.CENTER);

        // Ajout des JPanel au layout principal
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(remarquesPanel, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Fenetre_paiements().setVisible(true);
            }
        });
    }
}