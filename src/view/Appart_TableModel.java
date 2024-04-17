package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Appartement;
import model.Immeuble;

public class Appart_TableModel extends AbstractTableModel{
	
	private List<Appartement> liste = new ArrayList<>();
	private String[] titres = {"ID", "Dernier payment"};
	
	
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
		case 1: return liste.get(l).getPayement();
		}
		return null;
	}
	
	public String getColumnName(int column) {
		return titres[column];
	}
	
	public void charger(List<Appartement> liste) {
		this.liste=liste;
		fireTableDataChanged();
	}
}