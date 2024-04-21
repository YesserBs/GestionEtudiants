package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import model.Immeuble;
import model.Paiement;

public class PaiementController {
	private Connection cnx;
    PreparedStatement stmt = null;
    
	public List<Paiement> getAllPaiements(int id_immeuble, int id_appartement) {
		List<Paiement> liste=new ArrayList<Paiement>();
	    ResultSet rs = null;
	    try {
	    	cnx=SingletonConnection.getInstance();

	        String sql = "SELECT * FROM locataires.paiement WHERE id_immeuble = ? and id_appartement = ?";
	        stmt = cnx.prepareStatement(sql);
	        stmt.setInt(1, id_immeuble);
	        stmt.setInt(2, id_appartement);
	        rs = stmt.executeQuery();

	        // Parcourir les résultats et les ajouter à la liste
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String nom_loc = rs.getString("nom_loc");
	            String prenom_loc = rs.getString("prenom_loc");
	            int loyer = rs.getInt("loyer");
	            Date paye_le = rs.getDate("paye_le");
	            Date suppose_le = rs.getDate("suppose_le");
	            
	            Paiement paiement = new Paiement(id, nom_loc, prenom_loc, loyer, paye_le, suppose_le, id_immeuble, id_appartement);
	            liste.add(paiement);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return liste;
	}
}
