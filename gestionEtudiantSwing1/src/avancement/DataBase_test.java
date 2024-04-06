package avancement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase_test {
    public static void main(String[] args) {
        // Paramètres de connexion à la base de données
    	String url = "jdbc:mysql://localhost:3307/test?useSSL=false&requireSSL=true";
    	String user = "root"; // Nom d'utilisateur MySQL
        String password = "admin"; // Mot de passe MySQL

        // Déclaration des objets de connexion
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Connexion à la base de données
            conn = DriverManager.getConnection(url, user, password);

            // Création de l'objet Statement pour exécuter la requête SQL
            stmt = conn.createStatement();

            // Exécution de la requête SQL
            rs = stmt.executeQuery("SELECT * FROM personne");

            // Parcours des résultats et affichage
            while (rs.next()) {
                int id = rs.getInt("id"); // Récupération de la colonne id
                String nom = rs.getString("nom"); // Récupération de la colonne nom
                String age = rs.getString("age"); // Récupération de la colonne prenom

                // Affichage des données
                System.out.println("ID: " + id + ", Nom: " + nom + ", Age: " + age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermeture des ressources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


