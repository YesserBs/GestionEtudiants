package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Immeuble;
import model.Personne;

public class ImmeubleController {
	
	private Connection cnx;
    PreparedStatement stmt = null;
    
    public void ajouterImmeuble(Immeuble e) {
	    try {
	    	cnx=SingletonConnection.getInstance();

	        // Préparer la requête SQL
	        String sql = "INSERT INTO locataires.immeuble (nom) VALUES (?)";
	        stmt = cnx.prepareStatement(sql);
	        stmt.setString(1, e.getNom());

	        // Exécuter la requête
	        stmt.executeUpdate();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } 
	}


	public List<Immeuble> getAllImmeubles() {
		List<Immeuble> liste=new ArrayList<Immeuble>();
	    ResultSet rs = null;
	    try {
	    	cnx=SingletonConnection.getInstance();

	        String sql = "SELECT id, nom FROM locataires.immeuble";
	        stmt = cnx.prepareStatement(sql);

	        // Exécuter la requête
	        rs = stmt.executeQuery();

	        // Parcourir les résultats et les ajouter à la liste
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String nom = rs.getString("nom");
	            Immeuble immeuble = new Immeuble(id, nom);
	            liste.add(immeuble);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return liste;
	}
	
	public Immeuble selectionnerDernier() {
		Immeuble immeuble = null;
	    ResultSet rs = null;
	    try {
	    	cnx=SingletonConnection.getInstance();

	        String sql = "SELECT id, nom FROM locataires.immeuble ORDER BY id DESC LIMIT 1";
	        stmt = cnx.prepareStatement(sql);

	        // Exécuter la requête
	        rs = stmt.executeQuery();

	        // S'il y a des résultats, récupérer le dernier enregistrement
	        if (rs.next()) {
	            int id = rs.getInt("id");
	            String nom = rs.getString("nom");
	            immeuble = new Immeuble(id, nom);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    return immeuble;
	}

	public void supprimerImmeuble(int id) {
		try {
	    	cnx=SingletonConnection.getInstance();

	        // Préparer la requête SQL
	        String sql = "DELETE FROM locataires.immeuble WHERE id = ?";
	        stmt = cnx.prepareStatement(sql);
	        stmt.setInt(1, id);

	        // Exécuter la requête
	        int rowsAffected = stmt.executeUpdate();
	        
	        // Vérifier si une ligne a été supprimée
	        if (rowsAffected > 0) {
	            System.out.println("Suppression effectuée");
	        } else {
	            System.out.println("Aucune personne avec l'ID spécifié trouvée dans la base de données.");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } 
	}
}
