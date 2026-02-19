# Projet Quiz en Ligne

## Description
Développement en équipe d’une plateforme web de gestion et participation aux quiz en ligne.  
Le système permet à l’administrateur de gérer les utilisateurs, les cours et les quiz, et aux étudiants de participer aux quiz et de consulter leurs résultats.

---

## Fonctionnalités

### Administrateur
- Gestion des utilisateurs (ajout, modification, suppression).  
- Gestion des cours et quiz (ajout, modification, suppression).  
- Visualisation des résultats des étudiants.  

### Étudiant
- Consultation des cours disponibles.  
- Participation aux quiz.  
- Consultation des scores et historique des quiz.  

---

## Architecture

- **Backend** : Java EE (Servlets, JSP)  
- **ORM** : Hibernate pour la gestion des entités et de la base de données  
- **Base de données** : MySQL  
- **Serveur** : Apache Tomcat  
- **Build** : Maven  

**Design Patterns utilisés :**  
- MVC (Model-View-Controller)  
- DAO (Data Access Object)  
- Singleton (Gestion Hibernate)  
- Factory (Création des entités)  
- Observer (Résultats et notifications)  

---

## Installation

1. **Cloner le projet** :  
   ```bash
   git clone https://github.com/votre-utilisateur/quiz-jee.git

Importer le projet dans IntelliJ / Eclipse en tant que projet Maven.

Configurer la base de données (MySQL) et mettre à jour les informations dans hibernate.cfg.xml.

Construire le projet avec Maven :

mvn clean install

Déployer sur Tomcat :

Copier le .war dans le dossier webapps de Tomcat.

Démarrer Tomcat.

Accéder à l’application :

http://localhost:8080/quiz-jee
Structure du projet
/MavenWebApp
│
├── /src/main/java
│   └── com.votreorganisation
│       ├── model       # Entités (User, Course, Quiz, Question)
│       ├── dao         # DAO pour accéder aux données
│       ├── service     # Services métiers
│       └── servlets    # Servlets pour les requêtes
│
├── /src/main/webapp
│   ├── /admin
│   │   └── dashboard.jsp
│   ├── /student
│   │   └── student_dashboard.jsp
│   └── index.jsp
│
├── /WEB-INF
│   └── web.xml
└── pom.xml
Contribuer

Fork le projet.

Créez une branche pour votre fonctionnalité :

git checkout -b feature/ma-fonctionnalite

Commit vos changements :

git commit -m "Ajouter une fonctionnalité X"

Poussez la branche :

git push origin feature/ma-fonctionnalite

Ouvrez une Pull Request.

Auteur

Nom : Kodad Naoufal

Email : naoufalkodad@gmail.com

Année : 2025
