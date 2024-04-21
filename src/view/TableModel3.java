package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Paiement;

public class TableModel3 extends AbstractTableModel{
	
	private List<Paiement> liste = new ArrayList<>();
	private String[] titres = {"ID", "Nom du locataire", "Prénom", "Loyer", "Payé le", "Supposé payé le"};
	
	
	@Override
	public int getRowCount() {
		return liste.size();
	}

	@Override
	public int getColumnCount() {
		return titres.length;
	}

	@Override
	public Object getValueAt(int l, int c) {
		switch(c) {
		case 0: return liste.get(l).getId();
		case 1: return liste.get(l).getNom_loc();
		case 2: return liste.get(l).getPrenom_loc();
		case 3: return liste.get(l).getLoyer();
		case 4: return liste.get(l).getPaye_le();
		case 5: return liste.get(l).getSuppose_le();
		case 6: return liste.get(l).getId_immeuble();
		case 7: return liste.get(l).getId_appartement();
		}
		return null;
	}
	
	public String getColumnName(int column) {
		return titres[column];
	}
	
	public void charger(List<Paiement> liste) {
		this.liste=liste;
		fireTableDataChanged();
	}
}


