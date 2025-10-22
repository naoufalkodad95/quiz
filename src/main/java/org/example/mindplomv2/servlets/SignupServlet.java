package org.example.mindplomv2.servlets;

import org.example.mindplomv2.dao.UtilisateurDAO;
import org.example.mindplomv2.model.Utilisateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SignupServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect to the signup page
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("re_password");

        // Validate form data
        if (username == null || username.isEmpty() ||
            email == null || email.isEmpty() ||
            password == null || password.isEmpty() ||
            rePassword == null || rePassword.isEmpty()) {
            request.setAttribute("errorMessage", "Tous les champs sont obligatoires.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        if (!password.equals(rePassword)) {
            request.setAttribute("errorMessage", "Les mots de passe ne correspondent pas.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        // Create a new user
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNomUtilisateur(username);
        utilisateur.setEmail(email);
        utilisateur.setMotDePasse(password);

        // Save the user using DAO
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        try {
            boolean success = utilisateurDAO.registerUtilisateur(utilisateur);
            if (success) {
                // Redirect to login page upon successful registration
                response.sendRedirect("login.jsp?signup=success");
            } else {
                // Show error if registration failed
                request.setAttribute("errorMessage", "Erreur lors de l'enregistrement. Le nom d'utilisateur ou l'email est peut-être déjà utilisé.");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle server-side errors gracefully
            request.setAttribute("errorMessage", "Erreur serveur : " + e.getMessage());
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }
}
