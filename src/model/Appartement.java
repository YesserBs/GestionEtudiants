package model;

public class Appartement {
	private int id;
	private int id_immeuble;
	private String remarques;
	
	public Appartement(int id, int id_immeuble, String remarques) {
		this.id = id;
		this.id_immeuble = id_immeuble;
		this.remarques = remarques;
	}
	
	public Appartement(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_immeuble() {
		return id_immeuble;
	}

	public void setId_immeuble(int id_immeuble) {
		this.id_immeuble = id_immeuble;
	}

	public String getRemarques() {
		return remarques;
	}

	public void setRemarques(String remarques) {
		this.remarques = remarques;
	}

	public Object getPayement() {
		// A définir
		return null;
	}
}
