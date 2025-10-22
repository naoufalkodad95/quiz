package org.example.mindplomv2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.example.mindplomv2.model.Utilisateur;
import org.example.mindplomv2.utils.DBConnection;

public class UtilisateurDAO {

    private static final Logger logger = Logger.getLogger(UtilisateurDAO.class.getName());

    /**
     * Retrieves a user from the database by username.
     *
     * @param nomUtilisateur The username to search for.
     * @return A Utilisateur object if found, otherwise null.
     */
    public Utilisateur getUtilisateurByNom(String nomUtilisateur) {
        String query = "SELECT * FROM utilisateurs WHERE nom_utilisateur = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            if (connection == null) {
                logger.severe("Erreur : La connexion à la base de données est null !");
                return null;
            }

            logger.info("Connexion à la base de données établie avec succès.");
            statement.setString(1, nomUtilisateur);

            try (ResultSet resultSet = statement.executeQuery()) {
                logger.info("Requête exécutée : " + query);
                if (resultSet.next()) {
                    logger.info("Utilisateur trouvé : " + nomUtilisateur);
                    Utilisateur utilisateur = new Utilisateur();
                    utilisateur.setId(resultSet.getInt("id"));
                    utilisateur.setNomUtilisateur(resultSet.getString("nom_utilisateur"));
                    utilisateur.setMotDePasse(resultSet.getString("mot_de_passe"));
                    utilisateur.setEmail(resultSet.getString("email"));
                    utilisateur.setRole(resultSet.getString("role"));


                    return utilisateur;
                } else {
                    logger.warning("Aucun utilisateur trouvé pour le nom d'utilisateur : " + nomUtilisateur);
                }
            }
        } catch (SQLException e) {
            logger.severe("Erreur SQL : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Registers a new user in the database.
     *
     * @param utilisateur The user object containing user data.
     * @return True if registration is successful, false otherwise.
     * @throws SQLException If an SQL error occurs.
     */
    public boolean registerUtilisateur(Utilisateur utilisateur) throws SQLException {
        String query = "INSERT INTO utilisateurs (nom_utilisateur, email, mot_de_passe,role) VALUES (?, ?,?,?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            if (connection == null) {
                logger.severe("Erreur : La connexion à la base de données est null !");
                return false;
            }

            logger.info("Connexion à la base de données établie avec succès.");
            statement.setString(1, utilisateur.getNomUtilisateur());
            statement.setString(2, utilisateur.getEmail());
            statement.setString(3, utilisateur.getMotDePasse());
            statement.setString(4, utilisateur.getRole());


            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                logger.info("Utilisateur enregistré avec succès : " + utilisateur.getNomUtilisateur());
                return true;
            } else {
                logger.warning("Échec de l'enregistrement de l'utilisateur : " + utilisateur.getNomUtilisateur());
                return false;
            }
        } catch (SQLException e) {
            logger.severe("Erreur SQL lors de l'enregistrement de l'utilisateur : " + e.getMessage());
            throw e;
        }
    }
}
