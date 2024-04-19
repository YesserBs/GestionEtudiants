package view;

import javax.swing.*;
import java.awt.*;

public class Fenetre_paiements extends JFrame {
    public Fenetre_paiements() {
        setTitle("Appartement numero 5");
        setSize(670, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création du bouton "retour"
        JButton retourButton = new JButton("Retour");

        // Création du label "Appartement numero 5"
        JLabel appartementLabel = new JLabel("Appartement numero 5");

        // Création du texte scrollable
        JTextArea textArea = new JTextArea(5, 20);
        textArea.setText("Insérez ici le long texte que vous souhaitez afficher."); // Ajoute du texte
        textArea.setEditable(false); // Rend le JTextArea non modifiable
        JScrollPane scrollPaneText = new JScrollPane(textArea); 
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPaneText.setMinimumSize(new Dimension(0, 100)); // Définit la hauteur minimale à 100

        // Création du tableau scrollable
        String[] columnNames = {"Colonne 1", "Colonne 2"};
        Object[][] data = {
            {"Donnée 1", "Donnée 2"},
            {"Donnée 3", "Donnée 4"}
        };
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPaneTable = new JScrollPane(table);

        // Ajout des composants au layout
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(retourButton);
        leftPanel.add(appartementLabel);
        leftPanel.add(scrollPaneText);
        leftPanel.add(scrollPaneTable);

        // Création du JTextArea pour les remarques
        JTextArea remarquesArea = new JTextArea(10, 20);
        JScrollPane scrollPaneRemarques = new JScrollPane(remarquesArea); 
        remarquesArea.setLineWrap(true);
        remarquesArea.setWrapStyleWord(true);

        // Ajout des JPanel au layout principal
        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(scrollPaneRemarques, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Fenetre_paiements().setVisible(true);
            }
        });
    }
}