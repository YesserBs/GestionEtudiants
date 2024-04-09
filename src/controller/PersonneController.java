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

	private List<Personne> liste=new ArrayList<Personne>();
	public void ajouterEtudiant(Personne e) {
		liste.add(e);		
	}

	public List<Personne> getAllEtudiants() {
		liste=new ArrayList<Personne>();
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	    	String url = "jdbc:mysql://localhost:3307/test?useSSL=false&requireSSL=true";
	    	String user = "root"; // Nom d'utilisateur MySQL
	        String password = "admin"; // Mot de passe MySQL

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

	public List<Personne> getEtudiantBMC(String mc) {
		// TODO Auto-generated method stub
		List<Personne>l=new ArrayList<Personne>();
		for(Personne e :liste)
			if(e.getNom().contains(mc))
				l.add(e);
		return l;
	}

	public void trierEtudiantParNom() {
		liste.sort((a,b)->a.getNom().compareTo(b.getNom()));
		
	}

	public void supprimerEtudiant(int id) {
		
		for(Iterator<Personne>i=liste.iterator();i.hasNext();)
		{
			Personne e=i.next();
			if(e.getId()==id)
				i.remove();
		}
		
	}

}
