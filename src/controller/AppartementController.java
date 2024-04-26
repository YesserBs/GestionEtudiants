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
import model.Paiement;

public class AppartementController {
	
	PaiementController paiementC = new PaiementController();
	ImmeubleController immeubleC = new ImmeubleController();
	
	private Connection cnx;
    
    PreparedStatement stmt = null;
    
    public void ajouterApparts(int nombre) {
	    try {
	    	cnx=SingletonConnection.getInstance();
	    	int id = immeubleC.selectionnerDernier().getId();
	        for (int i = 1; i<=nombre; i++) {
		        String sql = "INSERT INTO locataires.appartement (id, id_immeuble, remarques) VALUES (?, ?, ' Ecrivez vos remarques ici!');";
		        stmt = cnx.prepareStatement(sql);
		        stmt.setInt(1, i);
		        stmt.setInt(2, id);
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

	        String sql = "SELECT * FROM locataires.appartement WHERE id_immeuble = ?";
	        stmt = cnx.prepareStatement(sql);
	        stmt.setInt(1, n);

	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            Paiement p = paiementC.getDernier(n, id);
	            
	            Appartement appart = new Appartement(id);
	            if (p != null) {
	            	appart.setDernierLocataire(p.getNom_loc(), p.getPrenom_loc());
	            	appart.setDernierPaiement(p.getPaye_le());
	            }
	            else {
	            	appart.setDernierLocataire("-vide-", "");
	            }
	            liste.add(appart);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return liste;
	}

    public String getNomImmeuble(int id) {
        String nom = null;
        ResultSet rs = null;
        try {
            cnx = SingletonConnection.getInstance();

            String sql = "SELECT nom FROM locataires.immeuble WHERE id = ?";
            stmt = cnx.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                nom = rs.getString("nom");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nom;
    }

	public String getRemarques(int id_immeuble, int id_appartement) {
        String remarques = null;
        ResultSet rs = null;
        try {
            cnx = SingletonConnection.getInstance();

            String sql = "SELECT remarques FROM locataires.appartement WHERE id = ? and id_immeuble = ?";
            stmt = cnx.prepareStatement(sql);
            stmt.setInt(1, id_appartement);
            stmt.setInt(2, id_immeuble);
            rs = stmt.executeQuery();

            if (rs.next()) {
            	remarques = rs.getString("remarques");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return remarques;
	}
	
	
	public void setRemarques(int id_immeuble, int id_appartement, String remarques) {
        try {
            cnx = SingletonConnection.getInstance();

            String sql = "UPDATE locataires.appartement SET remarques = ? WHERE id = ? and id_immeuble = ?";
            stmt = cnx.prepareStatement(sql);
            stmt.setString(1, remarques);
            stmt.setInt(2, id_appartement);
            stmt.setInt(3, id_immeuble);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
