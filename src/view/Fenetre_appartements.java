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

import controller.AppartementController;
import controller.ImmeubleController;

public class Fenetre_appartements extends JFrame{
	private int id_immeuble;
	
	AppartementController appartementC = new AppartementController();
		
	Appart_TableModel model = new Appart_TableModel();
	
	JTable tableau = new JTable(model);
	JScrollPane jsp = new JScrollPane(tableau);
	
	JLabel l_immeubles = new JLabel("Appartements");
	JPanel p1 = new JPanel(new BorderLayout());
	JPanel p2 = new JPanel(new BorderLayout());
	JPanel p3 = new JPanel(new BorderLayout());
	JPanel for_center = new JPanel();
	JPanel for_distance = new JPanel(new BorderLayout());
	
	public Fenetre_appartements(int id_immeuble) {
		super("2eme fenetre");
		this.id_immeuble = id_immeuble;
		model.charger(appartementC.getAllAppartements(id_immeuble));
		
		
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
		
        tableau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tableau.rowAtPoint(e.getPoint());
                    if (row != -1) { 
                        JOptionPane.showMessageDialog(null, "Ouvrir l'appartement " + row);
                    }
                }
            }
        });
		
		for_center.add(l_immeubles);
		p1.add(for_center, BorderLayout.NORTH);
		p1.add(jsp, BorderLayout.CENTER);
		p1.add(for_distance, BorderLayout.SOUTH);
		add(p1);
		
		pack();
	}
	
	public static void main(String[] args) {
		new Fenetre_appartements(79);
	}
}
