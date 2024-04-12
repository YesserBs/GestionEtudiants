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

public class Test extends JFrame{
	PersonneController personneC = new PersonneController();
	
	TableModel model = new TableModel();
	JTable tableau = new JTable(model);
	JScrollPane jsp = new JScrollPane(tableau);
	
	JButton btn = new JButton("Ajouter");
	JPanel p = new JPanel(new BorderLayout());
	
	public Test() {
		super("gestion des etudiants");
		model.charger(personneC.getAllPersonnes());
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
		
		btn.addActionListener(e -> {
			System.out.println("Hello");
			personneC.ajouterPersonne(new Personne("Ali", 28));
			model.charger(personneC.getAllPersonnes());
		});
		p.add(jsp, BorderLayout.CENTER);
		p.add(btn, BorderLayout.SOUTH);
		add(p);
		
		pack();
	}
	
	public static void main(String[] args) {
		new Test();
	}
}
