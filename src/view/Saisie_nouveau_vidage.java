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

public class Saisie_nouveau_vidage extends JFrame {
		
    JDateChooser date = new JDateChooser();
    
    JLabel nom = new JLabel("Vider l'immeuble a partir du");
    
    JButton continuer = new JButton("continuer");
    JButton annuler = new JButton("annuler");

    JPanel container;
    JPanel p = new JPanel();
    JPanel p2 = new JPanel();
    
    
    public Saisie_nouveau_vidage(int id_immeuble, int id_appartement) {
        super("Saisie d'informations");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(275, 140);
        
        continuer.addActionListener(x->{
            
        });
        
        annuler.addActionListener(x->{
            dispose();
        });

        // Création d'un conteneur avec une marge
        container = new JPanel(new BorderLayout());
        p.add(annuler);
        p.add(continuer);
        p2.add(nom);
        container.add(p2, BorderLayout.NORTH);
        container.add(date, BorderLayout.CENTER);
        container.add(p, BorderLayout.SOUTH);
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(container);

        setVisible(true);
    }

    private void fermerToutesLesFenetres() {
        Window[] fenetres = JFrame.getWindows();
        for (Window fenetre : fenetres) {
            fenetre.dispose();
        }
    }
    
    public static void main(String args[]) {
    	new Saisie_nouveau_vidage(200, 200);
    }
}