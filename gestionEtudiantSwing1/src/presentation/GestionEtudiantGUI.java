package presentation;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import metier.Etudiant;
import metier.GestionEtudiantImplArrayList;
import metier.IGestionEtudiant;

public class GestionEtudiantGUI extends JFrame {
	JLabel lnom=new JLabel("Nom");
	JLabel lprenom=new JLabel("Prenom");
	JLabel lfiliere=new JLabel("Filiere");
	JLabel lsexe=new JLabel("Sexe");
	JLabel lrecher=new JLabel("Rechercher pr mot clé");
	JTextField tnom=new JTextField(10);
	JTextField tprenom=new JTextField(10);
	JTextField trecherche=new JTextField(10);
	JButton bajouter=new JButton("Ajouter");
	JButton bannuler=new JButton("Annuler");
	JButton bsupprimer=new JButton("Supprimer");
	JButton brechercher=new JButton("Rechercher");
	JRadioButton bh=new JRadioButton("M");
	JRadioButton bf=new JRadioButton("F");
	ButtonGroup bg=new ButtonGroup();
	JComboBox<String> combfiliere=new JComboBox<String>(new String[] {"math","physique","informatique"});
	
	JPanel p1=new JPanel(new BorderLayout());
	JPanel p2=new JPanel(new BorderLayout());
	JPanel p11=new JPanel(new GridLayout(2,2));
	JPanel p12=new JPanel();
	
	JPanel p21=new JPanel();
	
	JPanel pnom=new JPanel();
	JPanel pprenom=new JPanel();
	JPanel pfiliere=new JPanel();
	JPanel psexe=new JPanel();
	
	TableModele tm = new TableModele();
	JTable tableau = new JTable(tm);
	JScrollPane jsp = new JScrollPane(tableau);
	
	IGestionEtudiant gestion=new GestionEtudiantImplArrayList();
	

	
	public GestionEtudiantGUI()
	{
        super("gestion des etudiants");
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
		
		pnom.add(lnom);
		pnom.add(tnom);
		
		pprenom.add(lprenom);
		pprenom.add(tprenom);
		
		pfiliere.add(lfiliere);
		pfiliere.add(combfiliere);
		
		psexe.add(lsexe);
		psexe.add(bf);
		psexe.add(bh);
		bg.add(bf);
		bg.add(bh);
		
		p11.add(pnom);p11.add(pprenom);p11.add(pfiliere);p11.add(psexe);
		p1.add(p11);
		p12.add(bajouter);p12.add(bannuler);p12.add(bsupprimer);
		p1.add(p12,BorderLayout.SOUTH);
		add(p1,BorderLayout.NORTH);
		
		p21.add(lrecher);p21.add(trecherche);p21.add(brechercher);
		
		p2.add(p21,BorderLayout.NORTH);
		p2.add(jsp);
		add(p2);
		
		bajouter.addActionListener(x->{
			String nom = tnom.getText();
			String prenom = tprenom.getText();
			String filiere = (String) combfiliere.getSelectedItem();
			String sexe = "";
			
			if (bh.isSelected()) {
				sexe = "H";
			}
			else {
				sexe = "F";
			}
			
			if (nom.equals("")||prenom.equals("")||nom.equals("")) {
				JOptionPane.showMessageDialog(this, "erreur de saisie");
			}
			else {
				gestion.ajouterEtudiant(new Etudiant(nom,prenom,filiere,sexe));
				tm.charger(gestion.getAllEtudiants());
			}
		});
		
		bannuler.addActionListener(e->{
			tnom.setText("");
			tprenom.setText("");
			bg.clearSelection();
		});
		
		
		
		brechercher.addActionListener(x->{
			tm.charger(gestion.getEtudiantBMC(trecherche.getText()));
		});
		
		
		bsupprimer.addActionListener(x->{
			int index = tableau.getSelectedRow();
			if (index == -1) {
				JOptionPane.showMessageDialog(this, "selcetionnez une ligne");
			}
			else {
				int res = JOptionPane.showConfirmDialog(this, "voulez vous supprimer cet etudiant?", "confirmation", JOptionPane.YES_NO_OPTION);
				
				if (res==0) {
					int id = (int) tm.getValueAt(index, 0);
					gestion.supprimerEtudiant(id);
					tm.charger(gestion.getAllEtudiants());
				}
			}
		});
		
		pack();
		
	}
	
	

	public static void main(String[] args) {
		new GestionEtudiantGUI();
	}
	

}
