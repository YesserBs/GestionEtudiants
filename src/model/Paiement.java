package model;

import java.util.Date;

public class Paiement {
	private int id;
	private String nom_loc;
	private String prenom_loc;
	private int loyer;
	private Date paye_le;
	private Date suppose_le;
	private int id_appartement;
	private int id_immeuble;
	
	public Paiement(int id, String nom_loc, String prenom_loc, int loyer, Date paye_le, Date suppose_le,int id_appartement,int id_immeuble) {
		this.id = id;
		this.nom_loc = nom_loc;
		this.prenom_loc = prenom_loc;
		this.loyer = loyer;
		this.paye_le = paye_le;
		this.suppose_le = suppose_le;
		this.id_appartement = id_appartement;
		this.id_immeuble = id_immeuble;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom_loc() {
		return nom_loc;
	}

	public void setNom_loc(String nom_loc) {
		this.nom_loc = nom_loc;
	}

	public String getPrenom_loc() {
		return prenom_loc;
	}

	public void setPrenom_loc(String prenom_loc) {
		this.prenom_loc = prenom_loc;
	}

	public int getLoyer() {
		return loyer;
	}

	public void setLoyer(int loyer) {
		this.loyer = loyer;
	}

	public Date getPaye_le() {
		return paye_le;
	}

	public void setPaye_le(Date paye_le) {
		this.paye_le = paye_le;
	}

	public Date getSuppose_le() {
		return suppose_le;
	}

	public void setSuppose_le(Date suppose_le) {
		this.suppose_le = suppose_le;
	}

	public int getId_appartement() {
		return id_appartement;
	}

	public void setId_appartement(int id_appartement) {
		this.id_appartement = id_appartement;
	}

	public int getId_immeuble() {
		return id_immeuble;
	}

	public void setId_immeuble(int id_immeuble) {
		this.id_immeuble = id_immeuble;
	}
}
