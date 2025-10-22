<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MindBloom - Plateforme d'apprentissage interactive</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">
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
            background-color: var(--light);
            color: var(--dark);
        }

        /* Navbar Styles */
        .navbar {
            background-color: rgba(255, 255, 255, 0.95);
            backdrop-filter: blur(10px);
            box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
            padding: 1rem 0;
        }

        .navbar-brand {
            font-weight: 700;
            color: var(--primary);
            font-size: 1.5rem;
        }

        .nav-link {
            font-weight: 500;
            color: var(--dark) !important;
            margin: 0 0.5rem;
            transition: all 0.3s ease;
        }

        .nav-link:hover {
            color: var(--primary) !important;
        }

        .nav-link.active {
            color: var(--primary) !important;
            background: linear-gradient(45deg, var(--primary), var(--primary-dark));
            padding: 0.5rem 1.5rem;
            border-radius: 25px;
            color: white !important;
        }

        /* Hero Section */
        .hero {
            background: linear-gradient(135deg, rgba(79, 70, 229, 0.95) 0%, rgba(16, 185, 129, 0.95) 100%),
                        url('https://images.unsplash.com/photo-1516321318423-f06f85e504b3?ixlib=rb-4.0.3') center/cover;
            min-height: 80vh;
            display: flex;
            align-items: center;
            color: white;
            position: relative;
            overflow: hidden;
        }

        .hero::before {
            content: '';
            position: absolute;
            width: 100%;
            height: 100%;
            background: url("data:image/svg+xml,%3Csvg width='60' height='60' viewBox='0 0 60 60' xmlns='http://www.w3.org/2000/svg'%3E%3Cg fill='none' fill-rule='evenodd'%3E%3Cg fill='%23ffffff' fill-opacity='0.1'%3E%3Cpath d='M36 34v-4h-2v4h-4v2h4v4h2v-4h4v-2h-4zm0-30V0h-2v4h-4v2h4v4h2V6h4V4h-4zM6 34v-4H4v4H0v2h4v4h2v-4h4v-2H6zM6 4V0H4v4H0v2h4v4h2V6h4V4H6z'/%3E%3C/g%3E%3C/g%3E%3C/svg%3E");
            opacity: 0.1;
        }

        .hero-content {
            position: relative;
            z-index: 1;
        }

        .hero h1 {
            font-size: 3.5rem;
            font-weight: 700;
            margin-bottom: 1.5rem;
        }

        .hero p {
            font-size: 1.25rem;
            opacity: 0.9;
            margin-bottom: 2rem;
        }

        /* Course Cards */
        .course-card {
            background: white;
            border-radius: 20px;
            padding: 2rem;
            transition: all 0.3s ease;
            border: none;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            height: 100%;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        .course-card:hover {
            transform: translateY(-10px);
            box-shadow: 0 20px 40px rgba(0, 0, 0, 0.2);
        }

        .course-icon {
            width: 80px;
            height: 80px;
            background: linear-gradient(45deg, var(--primary), var(--secondary));
            border-radius: 20px;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 1.5rem;
        }

        .course-icon img {
            width: 40px;
            height: 40px;
        }

        .btn-custom {
            background: linear-gradient(45deg, var(--primary), var(--primary-dark));
            color: white;
            border: none;
            padding: 0.75rem 2rem;
            border-radius: 25px;
            font-weight: 500;
            transition: all 0.3s ease;
        }

        .btn-custom:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(79, 70, 229, 0.4);
            color: white;
        }

        /* Footer */
        footer {
            background: var(--dark);
            color: white;
            padding: 2rem 0;
        }

        footer a {
            color: var(--secondary);
            text-decoration: none;
            transition: color 0.3s ease;
        }

        footer a:hover {
            color: white;
        }

        /* Animations */
        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .animate {
            animation: fadeInUp 0.6s ease forwards;
        }
    </style>
</head>

<body>
    <nav class="navbar navbar-expand-lg fixed-top">
        <div class="container">
            <a class="navbar-brand" href="#">MindBloom</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="index.jsp">Accueil</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="signup.jsp">S'inscrire</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="login.jsp">Se connecter</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <section class="hero">
        <div class="container hero-content text-center">
            <h1 class="animate" style="animation-delay: 0.2s">D�veloppez vos comp�tences avec MindBloom</h1>
            <p class="animate" style="animation-delay: 0.4s">Une plateforme d'apprentissage interactive pour ma�triser de nouvelles comp�tences � votre rythme</p>
            <a href="Etudiant/start_quiz.jsp" class="btn btn-custom btn-lg animate" style="animation-delay: 0.6s">
                Commencer l'aventure
            </a>
        </div>
    </section>

    <section class="py-5">
        <div class="container">
            <h2 class="text-center mb-5">Nos parcours d'apprentissage</h2>
            <div class="row g-4">
                <div class="col-md-4">
                    <div class="course-card animate" style="animation-delay: 0.2s">
                        <div>
                            <div class="course-icon">
                                <img src="https://cdn-icons-png.flaticon.com/512/226/226777.png" alt="Java">
                            </div>
                            <h3 class="h4 text-center mb-3">Java</h3>
                            <p class="text-muted">Ma�trisez Java, du d�veloppement d'applications desktop aux applications web d'entreprise.</p>
                        </div>
                        <a href="Etudiant/start_quiz.jsp?cours=Java" class="btn btn-custom w-100">Commencer</a>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="course-card animate" style="animation-delay: 0.4s">
                        <div>
                            <div class="course-icon">
                                <img src="https://cdn-icons-png.flaticon.com/512/5968/5968350.png" alt="Python">
                            </div>
                            <h3 class="h4 text-center mb-3">Python</h3>
                            <p class="text-muted">D�couvrez Python et ses applications en data science, IA et d�veloppement web.</p>
                        </div>
                        <a href="Etudiant/start_quiz.jsp?cours=Python" class="btn btn-custom w-100">Commencer</a>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="course-card animate" style="animation-delay: 0.6s">
                        <div>
                            <div class="course-icon">
                                <img src="https://cdn-icons-png.flaticon.com/512/2285/2285551.png" alt="Math�matiques">
                            </div>
                            <h3 class="h4 text-center mb-3">Math�matiques</h3>
                            <p class="text-muted">Renforcez vos bases en math�matiques avec des exercices progressifs.</p>
                        </div>
                        <a href="Etudiant/start_quiz.jsp?cours=Math�matiques" class="btn btn-custom w-100">Commencer</a>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <footer class="text-center">
        <div class="container">
            <p>&copy; 2024 MindBloom | <a href="#">Mentions l�gales</a> | <a href="#">Contact</a></p>
        </div>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
