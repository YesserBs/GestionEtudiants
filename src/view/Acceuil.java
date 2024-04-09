package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.PersonneController;
import model.Personne;

public class Acceuil extends JFrame{
	PersonneController personneC = new PersonneController();
	
	TableModel model = new TableModel();
	JTable tableau = new JTable(model);
	JScrollPane jsp = new JScrollPane(tableau);
	
	JButton btn = new JButton("Ajouter");
	JPanel p = new JPanel(new BorderLayout());
	
	public Acceuil() {
		super("gestion des etudiants");
		model.charger(personneC.getAllEtudiants());
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
		
		btn.addActionListener(e -> {
			System.out.println("Hello");
			personneC.ajouterEtudiant(new Personne("Ali", 28));
			model.charger(personneC.getAllEtudiants());
		});
		p.add(jsp, BorderLayout.CENTER);
		p.add(btn, BorderLayout.SOUTH);
		add(p);
		
		pack();
		
	}
}
