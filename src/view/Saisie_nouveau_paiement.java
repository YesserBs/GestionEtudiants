package view;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.sql.Date;

public class Saisie_nouveau_paiement extends JFrame {
    JTextField nomField = new JTextField(20);
    JTextField prenomField = new JTextField(20);
    JTextField loyerField = new JTextField(20);
    JDateChooser suppose_leField = new JDateChooser();
    JDateChooser paye_leField = new JDateChooser();
    
    JLabel nom = new JLabel("Nom ");
    JLabel prenom = new JLabel("Prenom ");
    JLabel loyer = new JLabel("Loyer ");
    JLabel suppose_le = new JLabel("Supposé payé le ");
    JLabel paye_le = new JLabel("Payé le ");
    
    JButton continuer = new JButton("continuer");
    JButton annuler = new JButton("annuler");
    JButton vider = new JButton("vider l'appart");

    JPanel p = new JPanel(new GridLayout(0,2));
    JPanel p1 = new JPanel(new BorderLayout());
    JPanel p2 = new JPanel();
    
    
    public Saisie_nouveau_paiement() {
        super("Saisie d'informations");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        continuer.addActionListener(x->{
            String nomContent = nomField.getText();
            String prenomContent = prenomField.getText();
            String loyerContent = loyerField.getText();
            java.util.Date supposeLeDate = suppose_leField.getDate();
            java.util.Date payeLeDate = paye_leField.getDate();
            System.out.println("Nom: " + nomContent);
            System.out.println("Prenom: " + prenomContent);
            System.out.println("Loyer: " + loyerContent);
            System.out.println("Supposé payé le: " + supposeLeDate);
            System.out.println("Payé le: " + payeLeDate);
            dispose();
        });
        
        annuler.addActionListener(x->{
            dispose();
        });

        p.add(nom);
        p.add(nomField);
        p.add(prenom);
        p.add(prenomField);
        p.add(loyer);
        p.add(loyerField);
        p.add(paye_le);
        p.add(paye_leField);
        p.add(suppose_le);
        p.add(suppose_leField);


        // Création d'un conteneur avec une marge
        JPanel container = new JPanel(new BorderLayout());
        container.add(p, BorderLayout.CENTER);
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout du conteneur à la fenêtre
        p1.add(container, BorderLayout.NORTH);
        p1.add(p2);
        p2.add(annuler);
        p2.add(vider);
        p2.add(continuer);
        add(p1);

        setVisible(true);
        pack();
    }

    public static void main(String[] args) {
        new Saisie_nouveau_paiement();
    }
}