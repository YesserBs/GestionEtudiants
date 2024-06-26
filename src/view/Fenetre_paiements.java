package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.AppartementController;
import controller.PaiementController;
import model.Paiement;

import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class Fenetre_paiements extends JFrame {
	
	private int id;
	private int id_immeuble;
	
	TableModel3 model = new TableModel3();
	PaiementController paiementC = new PaiementController();
	AppartementController appartementC = new AppartementController();

	
    // Déclaration des composants en haut de la classe
    JButton retourButton = new JButton("Retour");
    JLabel appartementLabel;
    JLabel remarques = new JLabel("Remarques:");
    JLabel textArea = new JLabel();
    JScrollPane scrollPaneText = new JScrollPane(textArea); 
    //String[] columnNames = {"Colonne 1", "Colonne 2"};
    JTable table = new JTable(model);
    JScrollPane scrollPaneTable = new JScrollPane(table);
    JTextArea remarquesArea = new JTextArea(10, 20);
    JScrollPane scrollPaneRemarques = new JScrollPane(remarquesArea); 
    JPanel topPanel = new JPanel();
    JPanel centerPanel = new JPanel(new BorderLayout());
    JPanel remarquesPanel = new JPanel(new BorderLayout());
    
    JPanel p12 = new JPanel();
    JButton bajouter = new JButton("Ajouter");
    JButton bsupprimer = new JButton("Supprimer");
    JLabel lRecherche = new JLabel("Recharcher paiement");
    
    JPanel pLower = new JPanel(new BorderLayout());
    JPanel buttonAndTablePanel = new JPanel(new BorderLayout());
    JTextField t_recherche = new JTextField(10);
    JButton rechercher = new JButton("rechercher");
    JPanel p_enregistrer = new JPanel();
    JButton b_enregistrer = new JButton("enregistrer remarques");

    public Fenetre_paiements(int id, int id_immeuble) {
    	this.id = id;
    	this.id_immeuble = id_immeuble;
    	model.charger(paiementC.getAllPaiements(id_immeuble, id));
    	remarquesArea.setText(appartementC.getRemarques(id_immeuble, id));
    	
        setTitle("Appartement numero 5");
        setSize(710, 500);
        setLocation(390, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        retourButton.addActionListener(x->{
            fermerToutesLesFenetres();
            new Fenetre_appartements(id_immeuble); // Crée et affiche une nouvelle fenêtre
        });
        
        bajouter.addActionListener(x->{
            new Saisie_nouveau_paiement(id_immeuble, id);
        });
        
        b_enregistrer.addActionListener(x->{
        	appartementC.setRemarques(id_immeuble, id, remarquesArea.getText());
        });
        
		bsupprimer.addActionListener(x->{
			int index = table.getSelectedRow();
			if (index == -1) {
				JOptionPane.showMessageDialog(this, "selcetionnez une ligne");
			}
			else {
				int res = JOptionPane.showConfirmDialog(this, "voulez vous supprimer ce paiement?", "confirmation", JOptionPane.YES_NO_OPTION);
				
				if (res==0) {
					int IDrow = (int) model.getValueAt(index, 0);
					paiementC.supprimerPaiement(IDrow, id_immeuble, id);
					model.charger(paiementC.getAllPaiements(id_immeuble, id));
				}
			}
		});
		
		rechercher.addActionListener(x->{
			model.charger(paiementC.rechercherParMC(t_recherche.getText(), id_immeuble, id));
		});

		appartementLabel  = new JLabel("Appartement numero " + id);
        // Ajout de la marge au bouton "Retour"
        JPanel retourButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        retourButtonPanel.setBorder(new EmptyBorder(3, 3, 0, 180)); // Ajoute une marge en haut et à gauche
        retourButtonPanel.add(retourButton);

        // Création d'un nouveau JPanel pour le label "Appartement numero 5"
        JPanel appartementLabelPanel = new JPanel(new BorderLayout());
        appartementLabelPanel.add(appartementLabel, BorderLayout.CENTER);

        // Ajout des composants au layout du haut
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(retourButtonPanel);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(appartementLabelPanel);
        topPanel.add(Box.createHorizontalGlue());

        Paiement p = paiementC.getDernier(id_immeuble, id);
        if (p != null) {
            LocalDate today = LocalDate.now();
            java.util.Date date = new java.util.Date(p.getPaye_le().getTime());
            LocalDate paye_le = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long daysBetween = ChronoUnit.DAYS.between(paye_le, today);
            if (daysBetween == 0) {
                textArea.setText("Payé aujourd'hui\n");
            }else if(daysBetween < 0) {
                textArea.setText("Payé en avance\n");
            }
            else {
            	textArea.setText("Dèrnière fois payé il y a " + daysBetween + " jours\n");
            }
        } else {
            textArea.setText("Aucun paiement pour le moment\n");
        }
        
        /*
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);*/
        EmptyBorder margin = new EmptyBorder(0, 20, 0, 0); // 10 pixels de marge à gauche

	     // Appliquez la bordure à votre JLabel
	     textArea.setBorder(margin);
        scrollPaneText.setMinimumSize(new Dimension(0, 100)); // Définit la hauteur minimale à 100

        JPanel pTextField = new JPanel();
        pTextField.add(lRecherche);
        pTextField.add(t_recherche);
        pTextField.add(rechercher);
        buttonAndTablePanel.add(pTextField, BorderLayout.NORTH);

        // Ajoutez le tableau au centre
        buttonAndTablePanel.add(scrollPaneTable, BorderLayout.CENTER);

        // Ajout des boutons en bas
        p12.add(bsupprimer);
        p12.add(bajouter);
        buttonAndTablePanel.add(p12, BorderLayout.SOUTH);

        // Maintenant, ajoutez buttonAndTablePanel à pLower
        pLower.add(buttonAndTablePanel, BorderLayout.CENTER);

        // Ajout des composants au layout du centre
        centerPanel.add(scrollPaneText, BorderLayout.NORTH);
        centerPanel.add(pLower, BorderLayout.CENTER);

        // Configuration du JTextArea pour les remarques
        remarquesArea.setLineWrap(true);
        remarquesArea.setWrapStyleWord(true);

        // Ajout des composants au JPanel pour les remarques
        remarquesPanel.add(remarques, BorderLayout.NORTH);
        remarquesPanel.add(scrollPaneRemarques, BorderLayout.CENTER);
        remarquesPanel.add(b_enregistrer, BorderLayout.SOUTH);

        // Ajout des JPanel au layout principal
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(remarquesPanel, BorderLayout.EAST);
        
        setVisible(true);
    }
    
    private void fermerToutesLesFenetres() {
        Window[] fenetres = JFrame.getWindows();
        for (Window fenetre : fenetres) {
            fenetre.dispose();
        }
    }
}