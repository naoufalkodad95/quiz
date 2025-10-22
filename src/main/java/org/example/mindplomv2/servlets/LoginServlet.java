package org.example.mindplomv2.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.example.mindplomv2.dao.UtilisateurDAO;
import org.example.mindplomv2.model.Utilisateur;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect to the login page if accessed via GET
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get username and password from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        logger.info("Tentative de connexion pour l'utilisateur : " + username);

        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

        try {
            // Retrieve user from the database
            Utilisateur utilisateur = utilisateurDAO.getUtilisateurByNom(username);

            if (utilisateur != null) {
                // Placeholder for secure password comparison (e.g., BCrypt)
                boolean isPasswordValid = utilisateur.getMotDePasse().equals(password);

                if (isPasswordValid) {
                    // Log successful authentication
                    logger.info("Utilisateur authentifié avec succès : " + username);

                    // Create a new session or retrieve the existing one
                    HttpSession session = request.getSession(true);
                    session.setAttribute("utilisateur", utilisateur);
                    logger.info("Session utilisateur créée pour : " + username);

                    // Check the role and redirect to the appropriate page
                    String role = utilisateur.getRole(); // Assume "role" is set in the user object

                    if ("admin".equals(role)) {
                        response.sendRedirect("Admin/admin_dashboard.jsp");
                    } else if ("etudiant".equals(role)) {
                        response.sendRedirect("Etudiant/start_quiz.jsp");
                    } else {
                        // If the role is unknown or not set
                        response.sendRedirect("error.jsp?message=Role inconnu.");
                    }

                    // Redirect to the dashboard or home page
                } else {
                    // Log failed authentication
                    logger.warning("Mot de passe incorrect pour : " + username);

                    // Redirect back to login with error code
                    response.sendRedirect("login.jsp?error=invalid_credentials");
                }
            } else {
                // Log user not found
                logger.warning("Utilisateur non trouvé : " + username);

                // Redirect back to login with error code
                response.sendRedirect("login.jsp?error=user_not_found");
            }
        } catch (Exception e) {
            // Log the exception with a stack trace
            logger.log(Level.SEVERE, "Erreur lors de l'authentification pour l'utilisateur : " + username, e);

            // Redirect to a generic error page
            response.sendRedirect("error.jsp?message=Erreur lors de l'authentification. Veuillez réessayer.");
        }
    }
}
