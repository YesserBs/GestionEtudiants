package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
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

            String sql = "SELECT * FROM locataires.paiement WHERE id_immeuble = ? and id_appartement = ? and nom_loc != ''";
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

        // Trier la liste des paiements par date supposée de paiement du plus récent au plus ancien
        Collections.sort(liste, (p1, p2) -> p2.getSuppose_le().compareTo(p1.getSuppose_le()));

        return liste;
    }

	public void ajouterPaiement(int id_immeuble, int id_appartement, String nomContent, String prenomContent, int loyerContent, java.util.Date payeLeDate, java.util.Date supposeLeDate) {
	    try {
	        cnx = SingletonConnection.getInstance();

	        String sql = "INSERT INTO locataires.paiement (nom_loc, prenom_loc, loyer, paye_le, suppose_le, id_appartement, id_immeuble) VALUES (?, ?, ?, ?, ?, ?, ?)";
	        stmt = cnx.prepareStatement(sql);
	        stmt.setString(1, nomContent);
	        stmt.setString(2, prenomContent);
	        stmt.setInt(3, loyerContent);
	        stmt.setDate(4, new java.sql.Date(payeLeDate.getTime()));
	        stmt.setDate(5, new java.sql.Date(supposeLeDate.getTime()));
	        stmt.setInt(6, id_appartement);
	        stmt.setInt(7, id_immeuble);
	        stmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void supprimerPaiement(int id, int id_immeuble, int id_appartement) {
		try {
	    	cnx=SingletonConnection.getInstance();

	        // Préparer la requête SQL
	        String sql = "DELETE FROM locataires.paiement WHERE id = ? and id_immeuble = ? and id_appartement = ?";
	        stmt = cnx.prepareStatement(sql);
	        stmt.setInt(1, id);
	        stmt.setInt(2, id_immeuble);
	        stmt.setInt(3, id_appartement);
	        int rowsAffected = stmt.executeUpdate();
	        
	        // Vérifier si une ligne a été supprimée
	        if (rowsAffected > 0) {
	        } else {
	            System.out.println("Aucune personne avec l'ID spécifié trouvée dans la base de données.");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } 
	}
	
	public Paiement getDernier(int id_immeuble, int id_appartement) {
	    Paiement dernierPaiement = null;
	    ResultSet rs = null;
	    try {
	        cnx = SingletonConnection.getInstance();

	        String sql = "SELECT * FROM locataires.paiement where id_immeuble = ? and id_appartement = ? ORDER BY paye_le DESC LIMIT 1";
	        stmt = cnx.prepareStatement(sql);
	        stmt.setInt(1, id_immeuble);
	        stmt.setInt(2, id_appartement);
	        rs = stmt.executeQuery();

	        // Récupérer le premier (et unique) enregistrement
	        if (rs.next()) {
	            int id = rs.getInt("id");
	            String nom_loc = rs.getString("nom_loc");
	            String prenom_loc = rs.getString("prenom_loc");
	            int loyer = rs.getInt("loyer");
	            Date paye_le = rs.getDate("paye_le");
	            Date suppose_le = rs.getDate("suppose_le");
	            int id_imm = rs.getInt("id_immeuble");
	            int id_app = rs.getInt("id_appartement");

	            dernierPaiement = new Paiement(id, nom_loc, prenom_loc, loyer, paye_le, suppose_le, id_imm, id_app);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dernierPaiement;
	}

	public List<Paiement> rechercherParMC(String chaine, int id_immeuble, int id) {
	    List<Paiement> liste=new ArrayList<Paiement>();
	    ResultSet rs = null;
	    try {
	        cnx=SingletonConnection.getInstance();

	        String sql = "SELECT * FROM locataires.paiement WHERE (nom_loc LIKE ? OR prenom_loc LIKE ? OR CAST(loyer AS CHAR) LIKE ? OR CAST(paye_le AS CHAR) LIKE ?) and id_immeuble = ? and id_appartement = ? and nom_loc != ''";
	        stmt = cnx.prepareStatement(sql);
	        stmt.setString(1, "%" + chaine + "%");
	        stmt.setString(2, "%" + chaine + "%");
	        stmt.setString(3, "%" + chaine + "%");
	        stmt.setString(4, "%" + chaine + "%");
	        stmt.setInt(5, id_immeuble);
	        stmt.setInt(6, id);
	        rs = stmt.executeQuery();

	        // Parcourir les résultats et les ajouter à la liste
	        while (rs.next()) {
	            int ID = rs.getInt("id");
	            String nom_loc = rs.getString("nom_loc");
	            String prenom_loc = rs.getString("prenom_loc");
	            int loyer = rs.getInt("loyer");
	            Date paye_le = rs.getDate("paye_le");
	            Date suppose_le = rs.getDate("suppose_le");
	            int id_imm = rs.getInt("id_immeuble");
	            int id_app = rs.getInt("id_appartement");
	            
	            Paiement paiement = new Paiement(ID, nom_loc, prenom_loc, loyer, paye_le, suppose_le, id_imm, id_app);
	            liste.add(paiement);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    Collections.sort(liste, (p1, p2) -> p2.getSuppose_le().compareTo(p1.getSuppose_le()));

	    return liste;
	}
}
