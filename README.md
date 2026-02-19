# 📝 Projet Quiz en Ligne – 2025


---

## 🔹 Description
Plateforme web de gestion et participation aux **quiz en ligne**.  
- Les **administrateurs** peuvent gérer les utilisateurs, les cours et les quiz.  
- Les **étudiants** peuvent participer aux quiz et consulter leurs scores.  

Ce projet est développé dans le cadre d’un projet universitaire avec une architecture **Java EE complète**.

---

## 🔹 Fonctionnalités

### Administrateur
- Gestion des utilisateurs (ajout, modification, suppression).  
- Gestion des cours et quiz (ajout, modification, suppression).  
- Visualisation des résultats des étudiants.

### Étudiant
- Consultation des cours disponibles.  
- Participation aux quiz.  
- Consultation des scores et historique des quiz.

---

## 🔹 Architecture & Stack Technique

- **Backend** : Java EE (Servlets, JSP)  
- **ORM** : Hibernate pour la gestion des entités et de la base de données  
- **Base de données** : MySQL  
- **Serveur** : Apache Tomcat  
- **Build & Dépendances** : Maven  

**Design Patterns utilisés :**  
- MVC (Model-View-Controller)  
- DAO (Data Access Object)  
- Singleton (Gestion Hibernate)  
- Factory (Création des entités)  
- Observer (Résultats et notifications)  

---

## 🔹 Installation

1. **Cloner le projet :**
```bash
git clone https://github.com/votre-utilisateur/quiz-jee.git
```

2. **Importer le projet** dans IntelliJ / Eclipse en tant que projet Maven.  

3. **Configurer la base de données** (MySQL) et mettre à jour `hibernate.cfg.xml`.  

4. **Construire le projet avec Maven :**
```bash
mvn clean install
```

5. **Déployer sur Tomcat :**
- Copier le `.war` dans le dossier `webapps` de Tomcat  
- Démarrer Tomcat  

6. **Accéder à l’application :**
```
http://localhost:8080/quiz-jee
```

---

## 🔹 Structure du Projet

```
/MavenWebApp
│
├── /src/main/java
│   └── com.votreorganisation
│       ├── model       # Entités : User, Course, Quiz, Question
│       ├── dao         # DAO pour accéder aux données
│       ├── service     # Services métiers
│       └── servlets    # Servlets pour gérer les requêtes
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
```

---

## 🔹 Contribution

1. Fork le projet  
2. Créez une branche pour votre fonctionnalité :  
```bash
git checkout -b feature/ma-fonctionnalite
```  
3. Commit vos changements :  
```bash
git commit -m "Ajouter une fonctionnalité X"
```  
4. Poussez la branche :  
```bash
git push origin feature/ma-fonctionnalite
```  
5. Ouvrez une Pull Request

---

## 🔹 Auteur
- **Nom** : Kodad Naoufal , Arbib Oualid, Amine Moufid
- **Email** : naoufalkodad@gmail.com  
- **Année** : 2025  

---

