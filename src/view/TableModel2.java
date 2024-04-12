package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Immeuble;
import model.Personne;

public class TableModel2 extends AbstractTableModel{
	
	private List<Immeuble> liste = new ArrayList<>();
	private String[] titres = {"ID", "Nom de l'immeuble"};
	
	
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
		}
		return null;
	}
	
	public String getColumnName(int column) {
		return titres[column];
	}
	
	public void charger(List<Immeuble> liste) {
		this.liste=liste;
		fireTableDataChanged();
	}
}


