package presentation;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import metier.Etudiant;

public class TableModele extends AbstractTableModel{
	
	private List<Etudiant> liste = new ArrayList<>();
	private String[] titres = {"Id", "Nom", "Prenom", "Filiere", "Sexe"};
	
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return liste.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return titres.length;
	}

	@Override
	public Object getValueAt(int l, int c) {
		switch(c) {
		case 0: return liste.get(l).getId();
		case 1: return liste.get(l).getNom();
		case 2: return liste.get(l).getPrenom();
		case 3: return liste.get(l).getFiliere();
		case 4: return liste.get(l).getSexe();
		}
		return null;
	}
	
	public void charger(List<Etudiant> l) {
		this.liste=l;
		fireTableDataChanged();
	}
	
	public String getColumnName(int column) {
		return titres[column];
	}
}
