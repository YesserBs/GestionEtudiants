package view;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import controller.PaiementController;
import model.Paiement;

import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Saisie_nouveau_paiement extends JFrame {
	
	PaiementController paiementC = new PaiementController();
	
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
    
    
    public Saisie_nouveau_paiement(int id_immeuble, int id_appartement) {
        super("Saisie d'informations");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        continuer.addActionListener(x->{
            String nomContent = nomField.getText();
            String prenomContent = prenomField.getText();
            String loyerContent = loyerField.getText();
            Date payeLeDate = paye_leField.getDate();
            Date supposeLeDate = suppose_leField.getDate();
            
            if(nomContent.isEmpty() || prenomContent.isEmpty() || loyerContent.isEmpty() || payeLeDate == null || supposeLeDate == null) {
                JOptionPane.showMessageDialog(null, "Les champs ne doivent pas être vides");
                return;
            }
            
            try {
                int loyerContentInt = Integer.parseInt(loyerContent);
                paiementC.ajouterPaiement(id_immeuble, id_appartement, nomContent, prenomContent, loyerContentInt, payeLeDate, supposeLeDate);
                fermerToutesLesFenetres();
                new Fenetre_paiements(id_appartement, id_immeuble);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Le loyer doit être un nombre entier", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        Paiement paiement = paiementC.getDernier(id_immeuble, id_appartement);
        if (paiement != null) {
        	nomField.setText(paiement.getNom_loc());
        	prenomField.setText(String.valueOf(paiement.getPrenom_loc())); //getPrenom_loc() doit etre String
        	loyerField.setText(String.valueOf(paiement.getLoyer())); //paiement.getLoyer() doit etre String
            
            java.util.Date date = new java.util.Date(paiement.getSuppose_le().getTime());
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MONTH, 1);
            date = cal.getTime();

            suppose_leField.setDate(date);
        }
        
        
        annuler.addActionListener(x->{
            dispose();
        });
        
        vider.addActionListener(x->{
            dispose();
            new Saisie_nouveau_vidage(id_immeuble, id_appartement);
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

    private void fermerToutesLesFenetres() {
        Window[] fenetres = JFrame.getWindows();
        for (Window fenetre : fenetres) {
            fenetre.dispose();
        }
    }
}