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
	
	String url = "jdbc:mysql://localhost:3307/test?useSSL=false&requireSSL=true";
	String user = "root"; // Nom d'utilisateur MySQL
    String password = "admin"; // Mot de passe MySrQL
    
    Connection conn = null;
    PreparedStatement stmt = null;
    
    public void ajouterImmeuble(Immeuble e) {
	    try {

	        // Établir la connexion à la base de données
	        conn = DriverManager.getConnection(url, user, password);

	        // Préparer la requête SQL
	        String sql = "INSERT INTO locataires.immeuble (nom) VALUES (?)";
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, e.getNom());

	        // Exécuter la requête
	        stmt.executeUpdate();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } finally {
	        // Fermer les ressources
	        try {
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}


	public List<Immeuble> getAllImmeubles() {
		List<Immeuble> liste=new ArrayList<Immeuble>();
	    ResultSet rs = null;
	    try {
	        conn = DriverManager.getConnection(url, user, password);

	        String sql = "SELECT id, nom FROM locataires.immeuble";
	        stmt = conn.prepareStatement(sql);

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
	    } finally {
	        // Fermer les ressources
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		return liste;
	}

	public void supprimerImmeuble(int id) {
		try {
	        conn = DriverManager.getConnection(url, user, password);

	        // Préparer la requête SQL
	        String sql = "DELETE FROM locataires.immeuble WHERE id = ?";
	        stmt = conn.prepareStatement(sql);
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
	    } finally {
	        // Fermer les ressources
	        try {
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
}
