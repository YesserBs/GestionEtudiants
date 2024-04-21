package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
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

import controller.AppartementController;
import controller.ImmeubleController;

public class Fenetre_appartements extends JFrame{
	private int id_immeuble;
	
	AppartementController appartementC = new AppartementController();
		
	Appart_TableModel model = new Appart_TableModel();
	
	JTable tableau = new JTable(model);
	JScrollPane jsp = new JScrollPane(tableau);
	
	JButton retour = new JButton("retour");
	
	JLabel l_immeubles;
	JPanel p1 = new JPanel(new BorderLayout());
	JPanel p2 = new JPanel(new BorderLayout());
	JPanel p3 = new JPanel(new BorderLayout());
	JPanel p4 = new JPanel(new BorderLayout());
	JPanel p5 = new JPanel();
	JPanel pUpper = new JPanel(new GridLayout(0, 3));
	JPanel inutile = new JPanel(new BorderLayout());
	
	public Fenetre_appartements(int id_immeuble) {
		super("2eme fenetre");
		this.id_immeuble = id_immeuble;
		model.charger(appartementC.getAllAppartements(id_immeuble));
		l_immeubles = new JLabel(appartementC.getNomImmeuble(id_immeuble));
		
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(390, 200);
		
        tableau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tableau.rowAtPoint(e.getPoint());
                    if (row != -1) { 
                        //JOptionPane.showMessageDialog(null, "Ouvrir l'appartement " + row);
                    	fermerToutesLesFenetres();
                        new Fenetre_paiements(row+1, id_immeuble);
                    }
                }
            }
        });
        
        retour.addActionListener(x->{
        	fermerToutesLesFenetres();
            new Acceuil(-1).setVisible(true); // Crée et affiche une nouvelle fenêtre
        });
        JPanel retourButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        retourButtonPanel.setBorder(new EmptyBorder(3, 3, 0, 5)); // Ajoute une marge en haut et à gauche
        retourButtonPanel.add(retour);
        
        p4.add(retourButtonPanel, BorderLayout.WEST);
        pUpper.add(p4);
        p5.add(l_immeubles);
        pUpper.add(p5);
		p1.add(pUpper, BorderLayout.NORTH);
		p1.add(jsp, BorderLayout.CENTER);
		add(p1);
		
		pack();
	}
	
    private void fermerToutesLesFenetres() {
        Window[] fenetres = JFrame.getWindows();
        for (Window fenetre : fenetres) {
            fenetre.dispose();
        }
    }
}
