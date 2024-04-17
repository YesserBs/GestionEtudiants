package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Appartement;
import model.Immeuble;

public class AppartementController {
	
	ImmeubleController immeubleC = new ImmeubleController();
	
	private Connection cnx;
    
    PreparedStatement stmt = null;
    
    public void ajouterApparts(int nombre) {
	    try {
	    	cnx=SingletonConnection.getInstance();
	    	int id = immeubleC.selectionnerDernier().getId();
	        for (int i = 1; i<=nombre; i++) {
		        String sql = "INSERT INTO locataires.appartement (id, id_immeuble, remarques) VALUES (?, ?, 'Remarques');";
		        stmt = cnx.prepareStatement(sql);
		        stmt.setInt(1, id);
		        stmt.setInt(2, i);
		        stmt.executeUpdate();
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
    
    public List<Appartement> getAllAppartements(int n) {
		List<Appartement> liste=new ArrayList<Appartement>();
	    ResultSet rs = null;
	    try {
	    	cnx=SingletonConnection.getInstance();

	        String sql = "SELECT * FROM locataires.appartement WHERE id = ?";
	        stmt = cnx.prepareStatement(sql);
	        stmt.setInt(1, n);

	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            int id = rs.getInt("id_immeuble");
	            Appartement immeuble = new Appartement(id);
	            liste.add(immeuble);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return liste;
	}
}
