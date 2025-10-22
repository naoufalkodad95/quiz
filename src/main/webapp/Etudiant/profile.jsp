<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.example.mindplomv2.model.Score" %>
<%@ page import="org.example.mindplomv2.model.Utilisateur" %>
<%

    // Si la session existe déjà, vous n'avez pas besoin de la redéfinir
    if (session == null || session.getAttribute("utilisateur") == null) {
        response.sendRedirect("../login.jsp");
    } else {
        // L'utilisateur est authentifié, afficher le contenu du quiz
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profil - MindBloom</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">MindBloom</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="start_quiz.jsp">star quize</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="../logout">Se déconnecter</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Profile Section -->
<div class="container mt-5">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <h2 class="text-center">Bienvenue, <%= ((Utilisateur) session.getAttribute("utilisateur")).getNomUtilisateur() %>!</h2>
            <p class="text-center">Voici un aperçu de vos résultats.</p>

            <div class="card mt-4">
                <div class="card-header text-center">
                    <h4>Vos Quiz Terminés</h4>
                </div>
                <div class="card-body">
                    <!-- Check if there is an error message -->
                    <%
                        String errorMessage = (String) request.getAttribute("error");
                        if (errorMessage != null) {
                    %>
                    <div class="alert alert-warning text-center">
                        <%= errorMessage %>
                    </div>
                    <% } %>

                    <%
                        // Retrieve scores list from request scope
                        List<Score> scores = (List<Score>) request.getAttribute("scores");

                        // Check if scores list is available and not empty
                        if (scores != null && !scores.isEmpty()) {
                    %>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Nom du Quiz</th>
                            <th>Score</th>
                            <th>Date</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            // Iterate through each score and display it in the table
                            for (Score score : scores) {
                        %>
                        <tr>
                            <td><%= score.getQuizId() %></td>
                            <td><%= score.getScore() %></td>
                            <td><%= score.getDateTaken() %></td>
                            <td>
                                <a href="startQuiz?quizId=<%= score.getQuizId() %>" class="btn btn-primary btn-sm">Reprendre</a>
                            </td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                    <% } else { %>
                    <div class="alert alert-warning text-center">Vous n'avez terminé aucun quiz pour le moment.</div>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
<%
    }
%>
