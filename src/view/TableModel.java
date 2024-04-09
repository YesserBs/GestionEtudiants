package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Personne;

public class TableModel extends AbstractTableModel{
	
	private List<Personne> liste = new ArrayList<>();
	private String[] titres = {"Id", "Nom", "Age"};
	
	
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
		case 1: return liste.get(l).getNom();
		case 2: return liste.get(l).getAge();
		}
		return null;
	}
	
	public String getColumnName(int column) {
		return titres[column];
	}
	
	public void charger(List<Personne> l) {
		this.liste=l;
		fireTableDataChanged();
	}
}

