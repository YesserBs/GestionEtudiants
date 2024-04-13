package view;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controller.ImmeubleController;
import controller.PersonneController;
import model.Immeuble;
import model.Personne;

public class Acceuil extends JFrame{
	ImmeubleController immeubleC = new ImmeubleController();
	
	TableModel2 model = new TableModel2();
	JTable tableau = new JTable(model);
	JScrollPane jsp = new JScrollPane(tableau);
	
	JLabel l_immeubles = new JLabel("Immeubles");
	JButton b_ajouter = new JButton("ajouter");
	JButton b_supprimer = new JButton("supprimer");
	JPanel p1 = new JPanel(new BorderLayout());
	JPanel p2 = new JPanel(new BorderLayout());
	JPanel p3 = new JPanel(new BorderLayout());
	JPanel for_center = new JPanel();
	JPanel for_distance = new JPanel(new BorderLayout());
	
	public Acceuil() {
		super("gestion des etudiants");
		model.charger(immeubleC.getAllImmeubles());
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
		
        tableau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tableau.rowAtPoint(e.getPoint());
                    if (row != -1) { 
                        JOptionPane.showMessageDialog(null, "Ouvrir l'immeuble " + row);
                    }
                }
            }
        });
        
		b_ajouter.addActionListener(e -> {
			/*
			immeubleC.ajouterImmeuble(new Immeuble(0, "Nouvel immeuble"));
			model.charger(immeubleC.getAllImmeubles());
			*/
        	int lastRowIndex = tableau.getRowCount() - 1;
        	int RealId;
        	if (model.getRowCount()!=0) {
        		RealId = (int) model.getValueAt(lastRowIndex, 0);
        		System.out.println(RealId);
        	}
        	else {
        		RealId = 1;
        	}
			Saisie_nouvel_immeuble pop_up = new Saisie_nouvel_immeuble(RealId + 1);
		});
		
		b_supprimer.addActionListener(x->{
			int index = tableau.getSelectedRow();
			if (index == -1) {
				JOptionPane.showMessageDialog(this, "selcetionnez une ligne");
			}
			else {
				int res = JOptionPane.showConfirmDialog(this, "voulez vous supprimer cet etudiant?", "confirmation", JOptionPane.YES_NO_OPTION);
				
				if (res==0) {
					int id = (int) model.getValueAt(index, 0);
					immeubleC.supprimerImmeuble(id);
					model.charger(immeubleC.getAllImmeubles());
				}
			}
		});
		
        JPanel right_margin = new JPanel(new BorderLayout());
        right_margin.setBorder(new EmptyBorder(5, 10, 5, 10));
        right_margin.add(b_ajouter, BorderLayout.CENTER);
        
        JPanel left_margin = new JPanel(new BorderLayout());
        left_margin.setBorder(new EmptyBorder(5, 10, 5, 10));
        left_margin.add(b_supprimer, BorderLayout.CENTER);
		
		for_distance.add(right_margin, BorderLayout.EAST);
		for_distance.add(left_margin, BorderLayout.WEST);
		for_center.add(l_immeubles);
		p1.add(for_center, BorderLayout.NORTH);
		p1.add(jsp, BorderLayout.CENTER);
		p1.add(for_distance, BorderLayout.SOUTH);
		add(p1);
		
		pack();
	}
}
