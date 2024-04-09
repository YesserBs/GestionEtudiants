package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.Personne;

public class PersonneController{
	
	String url = "jdbc:mysql://localhost:3307/test?useSSL=false&requireSSL=true";
	String user = "root"; // Nom d'utilisateur MySQL
    String password = "admin"; // Mot de passe MySQL


    public void ajouterPersonne(Personne e) {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    try {

	        // Établir la connexion à la base de données
	        conn = DriverManager.getConnection(url, user, password);

	        // Préparer la requête SQL
	        String sql = "INSERT INTO personne (nom, age) VALUES (?, ?)";
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, e.getNom());
	        stmt.setInt(2, e.getAge());

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


	public List<Personne> getAllPersonnes() {
		List<Personne> liste=new ArrayList<Personne>();
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {

	        // Établir la connexion à la base de données
	        conn = DriverManager.getConnection(url, user, password);

	        // Préparer la requête SQL
	        String sql = "SELECT id, nom, age FROM personne";
	        stmt = conn.prepareStatement(sql);

	        // Exécuter la requête
	        rs = stmt.executeQuery();

	        // Parcourir les résultats et les ajouter à la liste
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String nom = rs.getString("nom");
	            int age = rs.getInt("age");
	            Personne personne = new Personne(id, nom, age);
	            liste.add(personne);
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

	public void supprimerPersonne(int id) {
		
		List<Personne> liste = getAllPersonnes();
		for(Iterator<Personne>i=liste.iterator();i.hasNext();)
		{
			Personne e=i.next();
			if(e.getId()==id)
				// supprimer de la base BD celui qui a cette id
				System.out.println("Suppression effectuée");
		}

	}
}
