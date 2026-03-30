# Inventory Management Application

Application web de gestion d'inventaire développée avec **JSP**, **Servlet**, **Hibernate** et **Jakarta EE**, suivant le pattern **MVC** et un **DAO générique**.

---

## Technologies utilisées

- Java 11
- Jakarta EE (Servlet 5, JSP 3, JSTL 2)
- Hibernate 6 (ORM)
- MySQL 8
- Maven 3
- Bootstrap 4
- Apache Tomcat 10

---

## Prérequis

Avant de lancer le projet, assurez-vous d'avoir installé :

- JDK 11 ou supérieur → https://adoptium.net
- Maven 3.x → https://maven.apache.org
- MySQL 8.x → https://dev.mysql.com/downloads
- Apache Tomcat 10.x → https://tomcat.apache.org/download-10.cgi

Vérification :
```bash
java -version
mvn -version
mysql --version
```

---

## Installation et lancement

### 1. Cloner ou extraire le projet

```bash
cd inventory-management
```

### 2. Créer la base de données MySQL

```sql
mysql -u root -p
CREATE DATABASE inventorymanagement;
EXIT;
```

### 3. Configurer la connexion base de données

Ouvrir `src/main/resources/hibernate.cfg.xml` et modifier :

```xml
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">VOTRE_MOT_DE_PASSE</property>
```

### 4. Compiler le projet

```bash
mvn clean package
```

Le fichier WAR est généré dans :
```
target/inventory-management-1.0-SNAPSHOT.war
```

### 5. Déployer sur Tomcat 10

```bash
# Copier le WAR dans Tomcat (adapter le chemin)
copy target\inventory-management-1.0-SNAPSHOT.war C:\tomcat10\webapps\inventory-management.war

# Démarrer Tomcat
C:\tomcat10\bin\startup.bat        # Windows
/opt/tomcat10/bin/startup.sh       # Linux / Mac
```

### 6. Ouvrir l'application

```
http://localhost:8080/inventory-management/
```

> Les tables `users` et `products` sont créées automatiquement par Hibernate au premier démarrage.

---

## Structure du projet

```
inventory-management/
├── pom.xml
└── src/main/
    ├── java/com/example/
    │   ├── model/
    │   │   ├── User.java               # Entité utilisateur
    │   │   └── Product.java            # Entité produit
    │   ├── dao/
    │   │   ├── GenericDAO.java         # Interface DAO générique
    │   │   ├── GenericDAOImpl.java     # Implémentation abstraite CRUD
    │   │   ├── UserDAO.java            # DAO spécifique utilisateur
    │   │   └── ProductDAO.java         # DAO spécifique produit
    │   ├── controller/
    │   │   ├── UserListServlet.java
    │   │   ├── UserFormServlet.java
    │   │   ├── UserCreateServlet.java
    │   │   ├── UserUpdateServlet.java
    │   │   ├── UserDeleteServlet.java
    │   │   ├── ProductListServlet.java
    │   │   ├── ProductFormServlet.java
    │   │   ├── ProductCreateServlet.java
    │   │   ├── ProductUpdateServlet.java
    │   │   └── ProductDeleteServlet.java
    │   ├── util/
    │   │   └── HibernateUtil.java      # Factory Hibernate (singleton)
    │   └── listener/
    │       └── HibernateListener.java  # Init/shutdown Hibernate
    ├── resources/
    │   └── hibernate.cfg.xml           # Configuration base de données
    └── webapp/
        ├── WEB-INF/
        │   └── web.xml
        ├── header.jsp
        ├── footer.jsp
        ├── index.jsp
        ├── user-list.jsp
        ├── user-form.jsp
        ├── product-list.jsp
        ├── product-form.jsp
        └── error.jsp
```

---

## Video Demo



https://github.com/user-attachments/assets/d43349ce-a658-4df2-b8fa-b8e955b70051


## Fonctionnalités

### Gestion des utilisateurs
- Lister tous les utilisateurs
- Ajouter un utilisateur
- Modifier un utilisateur (mot de passe optionnel)
- Supprimer un utilisateur

### Gestion des produits
- Lister tous les produits avec badges de stock (vert / orange / rouge)
- Rechercher un produit par nom ou description
- Ajouter un produit
- Modifier un produit
- Supprimer un produit

---

## Architecture MVC

```
Navigateur  →  Servlet (Controller)  →  DAO  →  Hibernate  →  MySQL
                     ↓
                  JSP (View)
```

- **Modèle** : `User`, `Product`, `GenericDAO`, `UserDAO`, `ProductDAO`
- **Vue** : fichiers JSP + JSTL
- **Contrôleur** : Servlets Jakarta EE

---

## Problèmes fréquents

**Erreur 404 après déploiement**
→ Vérifier que le WAR est bien renommé `inventory-management.war` dans `webapps/`
→ Redémarrer Tomcat après la copie du WAR

**Erreur de connexion MySQL**
→ Vérifier que MySQL est démarré
→ Vérifier le mot de passe dans `hibernate.cfg.xml`

**Port 8080 déjà utilisé**
→ Libérer le port : `netstat -ano | findstr :8080` puis `taskkill /PID XXXX /F`

**Incompatibilité Jakarta / javax**
→ Utiliser obligatoirement **Tomcat 10+** (pas Tomcat 7, 8 ou 9)

---

## Auteur

Projet réalisé dans le cadre du cours **MLIAEdu** — Jakarta EE + Hibernate + DAO Générique.
