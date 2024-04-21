package model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Appartement {
	private int id;
	private int id_immeuble;
	private String remarques;
	private String dernier_locataire;
	private Date dernier_paiement;
	
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

	public void setDernierLocataire(String nom_loc, String prenom_loc) {
		dernier_locataire = nom_loc + " " + prenom_loc;
	}
	
	public String getDernierLocataire() {
		return dernier_locataire;
	}

	public void setDernierPaiement(Date paye_le) {
		dernier_paiement = paye_le;
	}

	public Date getDernierPaiement() {
		return dernier_paiement;
	}
	
	public String getStatus() {
	    if (dernier_paiement != null) {
	        long diffInMillies = Math.abs(new Date().getTime() - dernier_paiement.getTime());
	        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	        return "il y a " + diff + " jours";
		}else {
	        return "-vide-";
	    }
	}
}
