# Watt Else

Watt Else est un application de mise en relation des fournisseurs
et des clients de stations de recharge de véhicules électriques.

L'application est sécurisée par un système d'authentification
et d'autorisation basé sur des rôles et l'émission d'un token
à validité limitée dans le temps.

## Dossiers préparatoires

[Cahier des charges](https://docs.google.com/document/d/e/2PACX-1vQSGKRv2ewyTXnGy4xGwPu9AUYJwffWMrehzml4qiLXUbtZirimoYd_n3YNn14xmQ/pub)
  

[Spécifications fonctionnelles](https://docs.google.com/document/d/e/2PACX-1vRK_LfsYbzTFeaLl796EjwhH8-Ab964t2uQ7RRON3qbRpj4f9kfrAM8KjcN0nb5Og/pub)


## Architecture

Application en **3 couches** (présentation, métier, accès aux données),
implémentant le patron de conception **MVC**.

Partie backend en **Java** (séparation entre modèle et contrôleurs) et partie
frontend en **HTML** et **JavaScript** (séparation entre contrôleurs et vues).

Projet Maven contenant :

- 1 projet parent, de type `pom` (*WattElse-Parent*)
- 1 module de type `jar` pour les entités (*WattElse-Entity*)
- 2 modules de type `jar` contenant des interfaces
  (*WattElse-Dao*, *WattElse-Business*)
- 2 modules de type `ejb` contenant des implémentations
  (*WattElse-Dao-Impl*, *WattElse-Business-Impl*)
- 1 module de type `war` pour la partie présentation (*WattElse-Web*)
- 1 module de type `ear` contenant tous les autres modules en dépendance,
  et qui sera déployé sur le serveur d'application.

Le module web contient des contrôleurs en Java, et des ressources statiques
(HTML, CSS, JavaScript) dans le dossier *webapp*.

### Technologie utilisée

Avec le JDK 1.8 :
- **JBoss `7.4`** : Serveur d'application
- **EJB `3.1`** : inversion de contrôle par injection de dépendances dans
  les couches métier et d'accès aux données permettant d'indiquer
  au serveur d'application les implémentations à utiliser pour chaque
  interface.
- **JDBC `4.2`** : Pour interfacer l'application avec une base de donnée
  de type MySql.
- **Rest** : Points d'api dans les contrôleurs de *WattElse-Web*,
  requêtes vers ces points dans les contrôleurs en JavaScript.


## Technologies

***Backend***
- **javax `7.0`** : dépendance déclarée dans le `pom.xml` du parent,
  pour faire du JEE. Portée de type `provided`,
  car fourni par ***JBoss***.
- **log4j api + core `2.17.2`** : dépendance déclarée dans le `pom.xml` du parent,
  fichier `log4j2.xml` dans le projet parent (*src > main > resources*).
  Portée de type `provided`, car fourni par ***JBoss***.
- **mysql-connector-java `8.0.29`** : dépendance déclarée dans le `pom.xml` de
  *WattElse-Dao-Impl*, pour s'interfacer avec une base de données de type MySql.
- **jersey-core `1.4-ea04`** : dépendance déclarée dans le `pom.xml` de
  *WattElse-Web*, pour le filtre de sécurité lié aux autorisations.

***Frontend***
- **font-awesome `4.7.0`** : fichiers dans *webapp*, déclaration dans chaque
  fichier HTML, pour les icônes.
- **bootstrap `5.2.3`** : lien CDN déclaré dans chaque fichier HTML,
  pour la stylisation et le système de grille.

## Déploiement

- Installer le **JDK `1.8`** et **Maven `3.9.5`**, mettre à jour la variable
  d'environnement *path* de Windows pour pointer vers chacun de leur dossier *bin*.
- Démarrer une instance de **MySql `5.7 ou +`** et y exécuter le fichier *watt_else_db_import.sql*,
  situé à la racine du projet.
- Installer **JBoss `7.4`** et renseigner dans le fichier *deploy_on_jboss.bat*,
  situé à la racine du projet, le chemin vers **JBoss**.
- Exécuter *deploy_on_jboss.bat*.
- Accéder à l'application par l'url suivante : http://localhost:8080/connection.html.
