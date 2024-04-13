package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Immeuble;

public class AppartementController {
	
	String url = "jdbc:mysql://localhost:3307/test?useSSL=false&requireSSL=true";
	String user = "root"; // Nom d'utilisateur MySQL
    String password = "admin"; // Mot de passe MySrQL
    
    Connection conn = null;
    PreparedStatement stmt = null;
    
    public void ajouterApparts(int id, int n) {
	    try {
	        conn = DriverManager.getConnection(url, user, password);
	        
	        // Préparer la requête SQL et l'executer n fois 
	        for (int i = 1; i<=n; i++) {
		        String sql = "INSERT INTO locataires.appartement (id, id_immeuble, remarques) VALUES (?, ?, 'Remarques');";
		        stmt = conn.prepareStatement(sql);
		        stmt.setInt(1, id);
		        stmt.setInt(2, i);
		        stmt.executeUpdate();
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
