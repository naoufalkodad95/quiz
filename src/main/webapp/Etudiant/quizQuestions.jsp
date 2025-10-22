<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.example.mindplomv2.model.Question" %>
<%@ page import="org.example.mindplomv2.dao.QuizDAO" %>
<%@ page import="org.example.mindplomv2.model.Quiz" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.mindplomv2.model.Utilisateur" %>
<%

    // Si la session existe déjà, vous n'avez pas besoin de la redéfinir
    if (session == null || session.getAttribute("utilisateur") == null) {
        response.sendRedirect("../login.jsp");
    } else {
        // L'utilisateur est authentifié, afficher le contenu du quiz
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Avatars - MindBloom Bootstrap 5 Admin Dashboard</title>
    <meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport' />
    <link rel="icon" href="../assets/img/kaiadmin/favicon.ico" type="image/x-icon" />

    <!-- Fonts and icons -->
    <script src="../assets/js/plugin/webfont/webfont.min.js"></script>
    <script>
        WebFont.load({
            google: {"families":["Public Sans:300,400,500,600,700"]},
            custom: {"families":["Font Awesome 5 Solid", "Font Awesome 5 Regular", "Font Awesome 5 Brands", "simple-line-icons"], urls: ['../assets/css/fonts.min.css']},
            active: function() {
                sessionStorage.fonts = true;
            }
        });
    </script>

    <!-- CSS Files -->
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assets/css/plugins.min.css">
    <link rel="stylesheet" href="../assets/css/kaiadmin.min.css">

    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link rel="stylesheet" href="../assets/css/demo.css">
    <style>
        :root {
            --primary: #4F46E5;
            --primary-dark: #4338CA;
            --secondary: #10B981;
            --dark: #1F2937;
            --light: #F9FAFB;
        }

        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, rgba(79, 70, 229, 0.1) 0%, rgba(16, 185, 129, 0.1) 100%);
            min-height: 100vh;
            padding-bottom: 80px;
        }

        .card {
            background: white;
            border-radius: 20px;
            border: none;
            box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
            margin:3rem 15rem;
            animation: fadeInUp 0.6s ease forwards;
        }

        .form-check {
            margin: 1rem 0;
            padding-left: 2rem;
        }

        .form-check-input {
            width: 1.2rem;
            height: 1.2rem;
            margin-top: 0.3rem;
            border: 2px solid #E5E7EB;
            transition: all 0.3s ease;
        }

        .form-check-input:checked {
            background-color: var(--primary);
            border-color: var(--primary);
        }

        .form-check-label {
            font-size: 1rem;
            color: var(--dark);
            padding-left: 0.5rem;
        }

        .btn {
            padding: 0.75rem 2rem;
            border-radius: 12px;
            font-weight: 500;
            transition: all 0.3s ease;
        }

        .btn-primary {
            background: linear-gradient(45deg, var(--primary), var(--primary-dark));
            border: none;
        }

        .btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(79, 70, 229, 0.4);
        }

        .btn-secondary {
            background: #E5E7EB;
            border: none;
            color: var(--dark);
        }

        .btn-success {
            background: linear-gradient(45deg, var(--secondary), #059669);
            border: none;
            padding: 1rem 3rem;
        }

        .btn-success:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(16, 185, 129, 0.4);
        }

        .alert {
            border-radius: 12px;
            padding: 1rem;
            margin-bottom: 1.5rem;
        }



        h2 {
            color: var(--dark);
            font-weight: 600;
            margin-top: 5rem;
        }

        .navigation-buttons {
            display: flex;
            justify-content: space-between;
        }

        .question-number {
            color: var(--primary);
            font-weight: 600;
            margin-bottom: 1rem;
        }
    </style>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Avatars - MindBloom Bootstrap 5 Admin Dashboard</title>
        <meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport' />
        <link rel="icon" href="../assets/img/kaiadmin/favicon.ico" type="image/x-icon" />

        <!-- Fonts and icons -->
        <script src="../assets/js/plugin/webfont/webfont.min.js"></script>
        <script>
            WebFont.load({
                google: {"families":["Public Sans:300,400,500,600,700"]},
                custom: {"families":["Font Awesome 5 Solid", "Font Awesome 5 Regular", "Font Awesome 5 Brands", "simple-line-icons"], urls: ['../assets/css/fonts.min.css']},
                active: function() {
                    sessionStorage.fonts = true;
                }
            });
        </script>

        <!-- CSS Files -->
        <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="../assets/css/plugins.min.css">
        <link rel="stylesheet" href="../assets/css/kaiadmin.min.css">

        <!-- CSS Just for demo purpose, don't include it in your project -->
        <link rel="stylesheet" href="../assets/css/demo.css">
    </head>
<body>
<div class="wrapper">
    <!-- Sidebar -->


    <!-- End Sidebar -->

    <div class="main-panel w-100">
        <div class="main-header w-100">
            <div class="main-header-logo ">
                <!-- Logo Header -->
                <div class="logo-header" data-background-color="dark">

                    <a href="start_quiz.jsp" class="logo">
                        <img src="../assets/img/kaiadmin/logo_light.svg" alt="navbar brand" class="navbar-brand" height="20">
                    </a>
                    <div class="nav-toggle">
                        <button class="btn btn-toggle toggle-sidebar">
                            <i class="gg-menu-right"></i>
                        </button>
                        <button class="btn btn-toggle sidenav-toggler">
                            <i class="gg-menu-left"></i>
                        </button>
                    </div>
                    <button class="topbar-toggler more">
                        <i class="gg-more-vertical-alt"></i>
                    </button>

                </div>
                <!-- End Logo Header -->
            </div>
            <!-- Navbar Header -->
            <nav class="navbar navbar-header navbar-header-transparent navbar-expand-lg border-bottom "data-background-color="dark">
                <div class="logo-header   d-flex justify-content-start  " >

                    <a href="start_quiz.jsp" class="logo " alt="navbar brand" class="navbar-brand"  >
                        <img src="../assets/img/kaiadmin/favicon.svg" >
                        <span class="text-light" >MindBloom</span>
                    </a>
                </div>

                <div class="container-fluid d-flex justify-content-center align-items-center">
                    <nav class="navbar navbar-expand-lg hover">
                        <ul class="navbar-nav d-flex gap-3">
                            <li class="nav-item">
                                <a class="nav-link" href="../index.jsp">Accueil</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="start_quiz.jsp">Choisir un Quiz</a>
                            </li>
                        </ul>
                    </nav>
                </div>




                <ul class="navbar-nav topbar-nav ms-md-auto align-items-center">
                    <li class="nav-item topbar-icon dropdown hidden-caret d-flex d-lg-none">
                        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false" aria-haspopup="true">
                            <i class="fa fa-search"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-search animated fadeIn">
                            <form class="navbar-left navbar-form nav-search">
                                <div class="input-group">
                                    <input type="text" placeholder="Search ..." class="form-control">
                                </div>
                            </form>
                        </ul>
                    </li>
                    <li class="nav-item topbar-icon dropdown hidden-caret">
                        <a class="nav-link dropdown-toggle" href="#" id="messageDropdown" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fa fa-envelope"></i>
                        </a>
                        <ul class="dropdown-menu messages-notif-box animated fadeIn" aria-labelledby="messageDropdown">
                            <li>
                                <div class="dropdown-title d-flex justify-content-between align-items-center">
                                    Messages
                                    <a href="#" class="small">Mark all as read</a>
                                </div>
                            </li>
                            <li>
                                <div class="message-notif-scroll scrollbar-outer">
                                    <div class="notif-center">
                                        <a href="#">
                                            <div class="notif-img">
                                                <img src="../assets/img/jm_denis.jpg" alt="Img Profile">
                                            </div>
                                            <div class="notif-content">
                                                <span class="subject">Jimmy Denis</span>
                                                <span class="block">
															How are you ?
														</span>
                                                <span class="time">5 minutes ago</span>
                                            </div>
                                        </a>
                                        <a href="#">
                                            <div class="notif-img">
                                                <img src="../assets/img/chadengle.jpg" alt="Img Profile">
                                            </div>
                                            <div class="notif-content">
                                                <span class="subject">Chad</span>
                                                <span class="block">
															Ok, Thanks !
														</span>
                                                <span class="time">12 minutes ago</span>
                                            </div>
                                        </a>
                                        <a href="#">
                                            <div class="notif-img">
                                                <img src="../assets/img/mlane.jpg" alt="Img Profile">
                                            </div>
                                            <div class="notif-content">
                                                <span class="subject">Jhon Doe</span>
                                                <span class="block">
															Ready for the meeting today...
														</span>
                                                <span class="time">12 minutes ago</span>
                                            </div>
                                        </a>
                                        <a href="#">
                                            <div class="notif-img">
                                                <img src="../assets/img/talha.jpg" alt="Img Profile">
                                            </div>
                                            <div class="notif-content">
                                                <span class="subject">Talha</span>
                                                <span class="block">
															Hi, Apa Kabar ?
														</span>
                                                <span class="time">17 minutes ago</span>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <a class="see-all" href="javascript:void(0);">See all messages<i class="fa fa-angle-right"></i> </a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item topbar-icon dropdown hidden-caret">
                        <a class="nav-link dropdown-toggle" href="#" id="notifDropdown" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fa fa-bell"></i>
                            <span class="notification">4</span>
                        </a>
                        <ul class="dropdown-menu notif-box animated fadeIn" aria-labelledby="notifDropdown">
                            <li>
                                <div class="dropdown-title">You have 4 new notification</div>
                            </li>
                            <li>
                                <div class="notif-scroll scrollbar-outer">
                                    <div class="notif-center">
                                        <a href="#">
                                            <div class="notif-icon notif-primary"> <i class="fa fa-user-plus"></i> </div>
                                            <div class="notif-content">
														<span class="block">
															New user registered
														</span>
                                                <span class="time">5 minutes ago</span>
                                            </div>
                                        </a>
                                        <a href="#">
                                            <div class="notif-icon notif-success"> <i class="fa fa-comment"></i> </div>
                                            <div class="notif-content">
														<span class="block">
															Rahmad commented on Admin
														</span>
                                                <span class="time">12 minutes ago</span>
                                            </div>
                                        </a>
                                        <a href="#">
                                            <div class="notif-img">
                                                <img src="../assets/img/profile2.jpg" alt="Img Profile">
                                            </div>
                                            <div class="notif-content">
														<span class="block">
															Reza send messages to you
														</span>
                                                <span class="time">12 minutes ago</span>
                                            </div>
                                        </a>
                                        <a href="#">
                                            <div class="notif-icon notif-danger"> <i class="fa fa-heart"></i> </div>
                                            <div class="notif-content">
														<span class="block">
															Farrah liked Admin
														</span>
                                                <span class="time">17 minutes ago</span>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <a class="see-all" href="javascript:void(0);">See all notifications<i class="fa fa-angle-right"></i> </a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item topbar-icon dropdown hidden-caret">
                        <a class="nav-link" data-bs-toggle="dropdown" href="#" aria-expanded="false">
                            <i class="fas fa-layer-group"></i>
                        </a>
                        <div class="dropdown-menu quick-actions animated fadeIn">
                            <div class="quick-actions-header">
                                <span class="title mb-1">Quick Actions</span>
                                <span class="subtitle op-7">Shortcuts</span>
                            </div>
                            <div class="quick-actions-scroll scrollbar-outer">
                                <div class="quick-actions-items">
                                    <div class="row m-0">
                                        <a class="col-6 col-md-4 p-0" href="#">
                                            <div class="quick-actions-item">
                                                <div class="avatar-item bg-danger rounded-circle">
                                                    <i class="far fa-calendar-alt"></i>
                                                </div>
                                                <span class="text">Calendar</span>
                                            </div>
                                        </a>
                                        <a class="col-6 col-md-4 p-0" href="#">
                                            <div class="quick-actions-item">
                                                <div class="avatar-item bg-warning rounded-circle">
                                                    <i class="fas fa-map"></i>
                                                </div>
                                                <span class="text">Maps</span>
                                            </div>
                                        </a>
                                        <a class="col-6 col-md-4 p-0" href="#">
                                            <div class="quick-actions-item">
                                                <div class="avatar-item bg-info rounded-circle">
                                                    <i class="fas fa-file-excel"></i>
                                                </div>
                                                <span class="text">Reports</span>
                                            </div>
                                        </a>
                                        <a class="col-6 col-md-4 p-0" href="#">
                                            <div class="quick-actions-item">
                                                <div class="avatar-item bg-success rounded-circle">
                                                    <i class="fas fa-envelope"></i>
                                                </div>
                                                <span class="text">Emails</span>
                                            </div>
                                        </a>
                                        <a class="col-6 col-md-4 p-0" href="#">
                                            <div class="quick-actions-item">
                                                <div class="avatar-item bg-primary rounded-circle">
                                                    <i class="fas fa-file-invoice-dollar"></i>
                                                </div>
                                                <span class="text">Invoice</span>
                                            </div>
                                        </a>
                                        <a class="col-6 col-md-4 p-0" href="#">
                                            <div class="quick-actions-item">
                                                <div class="avatar-item bg-secondary rounded-circle">
                                                    <i class="fas fa-credit-card"></i>
                                                </div>
                                                <span class="text">Payments</span>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>

                    <li class="nav-item topbar-user dropdown hidden-caret">
                        <a class="dropdown-toggle profile-pic" data-bs-toggle="dropdown" href="#" aria-expanded="false">
                            <div class="avatar-sm">
                                <img src="../assets/img/profile.jpg" alt="..." class="avatar-img rounded-circle">
                            </div>
                            <span class="profile-username ">
										<span class="op-7">Bonjeur,</span> <span class="fw-bold"> <%= ((Utilisateur) session.getAttribute("utilisateur")).getNomUtilisateur() %></span>
									</span>
                        </a>
                        <ul class="dropdown-menu dropdown-user animated fadeIn">
                            <div class="dropdown-user-scroll scrollbar-outer">
                                <li>
                                    <div class="user-box">
                                        <div class="avatar-lg"><img src="../assets/img/profile.jpg" alt="image profile" class="avatar-img rounded"></div>
                                        <div class="u-text">
                                            <h4 class="text-light"> <%= ((Utilisateur) session.getAttribute("utilisateur")).getNomUtilisateur()  %></h4>
                                            <p class=" text-light"><%= ((Utilisateur) session.getAttribute("utilisateur")).getEmail() %></p>
                                            <a href="profile.jsp" class="btn btn-xs btn-secondary btn-sm">View Profile</a>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#">Account Setting</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="../logout">Logout</a>

                                </li>
                            </div>
                        </ul>
                    </li>
                </ul>
            </nav>
            <!-- End Navbar -->
        </div>

        <div class="container">
            <%
                String quizIdParam = request.getParameter("quizId");
                if (quizIdParam == null || quizIdParam.isEmpty()) {
            %>
            <div class="alert alert-danger text-center">
                ID du quiz manquant. Veuillez réessayer.
            </div>
            <%
            } else {
                try {
                    int quizId = Integer.parseInt(quizIdParam);
                    Quiz quiz = QuizDAO.getQuizById(quizId);
                    List<Question> questions = QuizDAO.getQuestionsByQuizId(quizId);

                    if (quiz != null && questions != null && !questions.isEmpty()) {
            %>
            <h2 class="text-center ">Quiz: <%= quiz.getName() %></h2>
            <form method="post" action="<%= request.getContextPath() %>/Etudiant/endQuiz">
                <input type="hidden" name="quizId" value="<%= quiz.getId() %>" />

                <%
                    int index = 1;
                    for (Question question : questions) {
                %>
                <div id="question_<%= index %>" class="card" style="display: <%= index == 1 ? "block" : "none" %>;">
                    <div class="card-body">
                        <div class="question-number">Question <%= index++ %></div>
                        <h5 class="mt-4"><%= question.getQuestionText() %></h5>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="question_<%= question.getId() %>" value="a" required>
                            <label class="form-check-label"><%= question.getOptionA() %></label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="question_<%= question.getId() %>" value="b">
                            <label class="form-check-label"><%= question.getOptionB() %></label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="question_<%= question.getId() %>" value="c">
                            <label class="form-check-label"><%= question.getOptionC() %></label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="question_<%= question.getId() %>" value="d">
                            <label class="form-check-label"><%= question.getOptionD() %></label>
                        </div>
                    </div>
                    <div class="navigation-buttons px-4 pb-4">
                        <button type="button" class="btn btn-secondary" onclick="showPreviousQuestion(<%= index - 1 %>)">
                            <i class="fas fa-arrow-left me-2"></i> Précédent
                        </button>
                        <button type="button" class="btn btn-primary" onclick="showNextQuestion(<%= index - 1 %>)">
                            Suivant <i class="fas fa-arrow-right ms-2"></i>
                        </button>
                    </div>
                </div>
                <%
                    }
                %>
                <div class="text-center mt-5">
                    <button type="submit" class="btn btn-success">
                        Terminer le Quiz <i class="fas fa-check ms-2"></i>
                    </button>
                </div>
            </form>
            <%
            } else {
            %>
            <div class="alert alert-warning text-center">
                Aucune question disponible pour ce quiz.
            </div>
            <%
                }
            } catch (NumberFormatException e) {
            %>
            <div class="alert alert-danger text-center">
                ID du quiz invalide. Veuillez réessayer.
            </div>
            <%
                    }
                }
            %>
        </div>

        <footer class="text-center">
            <div class="container">
                <p>&copy; 2024 MindBloom | <a href="#">Mentions l�gales</a> | <a href="#">Contact</a></p>
            </div>
        </footer>    </div>

    <!-- Custom template | don't include it in your project! -->
    <div class="custom-template">
        <div class="title">Settings</div>
        <div class="custom-content">
            <div class="switcher">
                <div class="switch-block">
                    <h4>Logo Header</h4>
                    <div class="btnSwitch">
                        <button type="button" class=" selected changeLogoHeaderColor" data-color="dark"></button>
                        <button type="button" class="selected changeLogoHeaderColor" data-color="blue"></button>
                        <button type="button" class="changeLogoHeaderColor" data-color="purple"></button>
                        <button type="button" class="changeLogoHeaderColor" data-color="light-blue"></button>
                        <button type="button" class="changeLogoHeaderColor" data-color="green"></button>
                        <button type="button" class="changeLogoHeaderColor" data-color="orange"></button>
                        <button type="button" class="changeLogoHeaderColor" data-color="red"></button>
                        <button type="button" class="changeLogoHeaderColor" data-color="white"></button>
                        <br />
                        <button type="button" class="changeLogoHeaderColor" data-color="dark2"></button>
                        <button type="button" class="changeLogoHeaderColor" data-color="blue2"></button>
                        <button type="button" class="changeLogoHeaderColor" data-color="purple2"></button>
                        <button type="button" class="changeLogoHeaderColor" data-color="light-blue2"></button>
                        <button type="button" class="changeLogoHeaderColor" data-color="green2"></button>
                        <button type="button" class="changeLogoHeaderColor" data-color="orange2"></button>
                        <button type="button" class="changeLogoHeaderColor" data-color="red2"></button>
                    </div>
                </div>
                <div class="switch-block">
                    <h4>Navbar Header</h4>
                    <div class="btnSwitch">
                        <button type="button" class="changeTopBarColor" data-color="dark"></button>
                        <button type="button" class="changeTopBarColor" data-color="blue"></button>
                        <button type="button" class="changeTopBarColor" data-color="purple"></button>
                        <button type="button" class="changeTopBarColor" data-color="light-blue"></button>
                        <button type="button" class="changeTopBarColor" data-color="green"></button>
                        <button type="button" class="changeTopBarColor" data-color="orange"></button>
                        <button type="button" class="changeTopBarColor" data-color="red"></button>
                        <button type="button" class="changeTopBarColor" data-color="white"></button>
                        <br />
                        <button type="button" class="changeTopBarColor" data-color="dark2"></button>
                        <button type="button" class="selected changeTopBarColor" data-color="blue2"></button>
                        <button type="button" class="changeTopBarColor" data-color="purple2"></button>
                        <button type="button" class="changeTopBarColor" data-color="light-blue2"></button>
                        <button type="button" class="changeTopBarColor" data-color="green2"></button>
                        <button type="button" class="changeTopBarColor" data-color="orange2"></button>
                        <button type="button" class="changeTopBarColor" data-color="red2"></button>
                    </div>
                </div>
                <div class="switch-block">
                    <h4>Sidebar</h4>
                    <div class="btnSwitch">
                        <button type="button" class="selected changeSideBarColor" data-color="white"></button>
                        <button type="button" class="changeSideBarColor" data-color="dark"></button>
                        <button type="button" class="changeSideBarColor" data-color="dark2"></button>
                    </div>
                </div>
            </div>
        </div>
        <div class="custom-toggle">
            <i class="icon-settings"></i>
        </div>
    </div>
    <!-- End Custom template -->
</div>
<!--   Core JS Files   -->
<script src="../assets/js/core/jquery-3.7.1.min.js"></script>
<script src="../assets/js/core/popper.min.js"></script>
<script src="../assets/js/core/bootstrap.min.js"></script>

<!-- jQuery Scrollbar -->
<script src="../assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
<!-- Moment JS -->
<script src="../assets/js/plugin/moment/moment.min.js"></script>

<!-- Chart JS -->
<script src="../assets/js/plugin/chart.js/chart.min.js"></script>

<!-- jQuery Sparkline -->
<script src="../assets/js/plugin/jquery.sparkline/jquery.sparkline.min.js"></script>

<!-- Chart Circle -->
<script src="../assets/js/plugin/chart-circle/circles.min.js"></script>

<!-- Datatables -->
<script src="../assets/js/plugin/datatables/datatables.min.js"></script>

<!-- Bootstrap Notify -->
<script src="../../assets/js/plugin/bootstrap-notify/bootstrap-notify.min.js"></script>

<!-- jQuery Vector Maps -->
<script src="../assets/js/plugin/jsvectormap/jsvectormap.min.js"></script>
<script src="../assets/js/plugin/jsvectormap/world.js"></script>

<!-- Sweet Alert -->
<script src="../assets/js/plugin/sweetalert/sweetalert.min.js"></script>

<!-- Kaiadmin JS -->
<script src="../assets/js/kaiadmin.min.js"></script>

<!-- Kaiadmin DEMO methods, don't include it in your project! -->
<script src="../assets/js/setting-demo2.js"></script>
<script>
    function showNextQuestion(currentQuestionIndex) {
        const currentQuestion = document.getElementById('question_' + currentQuestionIndex);
        const nextQuestion = document.getElementById('question_' + (currentQuestionIndex + 1));
        if (currentQuestion && nextQuestion) {
            currentQuestion.style.display = 'none';
            nextQuestion.style.display = 'block';
        }
    }

    function showPreviousQuestion(currentQuestionIndex) {
        const currentQuestion = document.getElementById('question_' + currentQuestionIndex);
        const previousQuestion = document.getElementById('question_' + (currentQuestionIndex - 1));
        if (currentQuestion && previousQuestion) {
            currentQuestion.style.display = 'none';
            previousQuestion.style.display = 'block';
        }
    }
</script>
</body>
</html>
<%
    }
%>