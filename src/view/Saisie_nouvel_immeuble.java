package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.AppartementController;
import controller.ImmeubleController;
import model.Immeuble;

public class Saisie_nouvel_immeuble extends JFrame {
	
		private String n;
		private String nom;
	
		JPanel p2 = new JPanel();
		JPanel p1 = new JPanel(new GridLayout(0, 2));
		JPanel p0 = new JPanel(new BorderLayout());
		JLabel lnom = new JLabel("Nom: ");
		JLabel lnombre = new JLabel("Nombre d'appartements");
		
		JTextField tfnom = new JTextField(); 
		JTextField tfnombre = new JTextField(); 
		
		JButton bcontinuer = new JButton("Continuer");
		JButton bannuler = new JButton("annuler");

	    public Saisie_nouvel_immeuble() {
	        super("Ajout immeuble");
	        setLocationRelativeTo(null);
	        bcontinuer.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {  
	            	nom = tfnom.getText();
	            	n = tfnombre.getText();
	            	int n_entier = Integer.parseInt(n);
	            	// ===> Ajouter des données a la BD
	            	ImmeubleController immeubleC = new ImmeubleController();
	            	immeubleC.ajouterImmeuble(new Immeuble(nom));
	            	fermerToutesLesFenetres();
	            	new Acceuil(n_entier);
	            }
	        });
	        
	        bannuler.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {            
	            	dispose();
	            }
	        });
	        
	        p2.add(bannuler);
	        p2.add(bcontinuer);
	        p1.add(lnom);
	        p1.add(tfnom);
	        p1.add(lnombre);
	        p1.add(tfnombre);
	        p0.add(p1, BorderLayout.NORTH);
	        p0.add(p2, BorderLayout.SOUTH);
	        add(p0);
	        
	        pack();
	        setVisible(true);
	    }
	    

	    private void fermerToutesLesFenetres() {
	        Window[] fenetres = JFrame.getWindows();
	        for (Window fenetre : fenetres) {
	            fenetre.dispose();
	        }
	    }
}
