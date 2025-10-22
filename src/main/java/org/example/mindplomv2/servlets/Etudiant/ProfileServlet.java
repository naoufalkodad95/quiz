package org.example.mindplomv2.servlets.Etudiant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.mindplomv2.dao.ScoreDAO;
import org.example.mindplomv2.model.Score;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ProfileServlet.class.getName());

    public ProfileServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = null;
        Integer userId = (Integer) session.getAttribute("userId");

        try {

            List<Score> scores = ScoreDAO.getUserScores(userId);

            // Set the scores attribute for the JSP to display
            request.setAttribute("scores", scores);

            // Check if no scores are found and set an error message if needed
            if (scores.isEmpty()) {
                request.setAttribute("error", "Aucun score trouvé pour cet utilisateur.");
            }

            // Forward the request to the profile JSP
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } catch (Exception e) {
            // Log the error and redirect to an error page
            logger.log(Level.SEVERE, "Erreur lors de la récupération des scores de l'utilisateur", e);
            response.sendRedirect("error.jsp");
        }
    }
}
