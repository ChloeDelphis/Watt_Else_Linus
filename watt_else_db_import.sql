-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           5.7.36 - MySQL Community Server (GPL)
-- SE du serveur:                Win64
-- HeidiSQL Version:             12.0.0.6468
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Listage de la structure de la base pour watt_else_db
DROP DATABASE IF EXISTS `watt_else_db`;
CREATE DATABASE IF NOT EXISTS `watt_else_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `watt_else_db`;

-- Listage de la structure de table watt_else_db. gamme_heure_reservation
DROP TABLE IF EXISTS `gamme_heure_reservation`;
CREATE TABLE IF NOT EXISTS `gamme_heure_reservation` (
  `id_he` bigint(20) NOT NULL AUTO_INCREMENT,
  `valeur_heure` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_he`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.gamme_heure_reservation : ~24 rows (environ)
REPLACE INTO `gamme_heure_reservation` (`id_he`, `valeur_heure`) VALUES
	(0, 0),
	(1, 1),
	(2, 2),
	(3, 3),
	(4, 4),
	(5, 5),
	(6, 6),
	(7, 7),
	(8, 8),
	(9, 9),
	(10, 10),
	(11, 11),
	(12, 12),
	(13, 13),
	(14, 14),
	(15, 15),
	(16, 16),
	(17, 17),
	(18, 18),
	(19, 19),
	(20, 20),
	(21, 21),
	(22, 20),
	(23, 23);

-- Listage de la structure de table watt_else_db. gamme_jour_de_semaine
DROP TABLE IF EXISTS `gamme_jour_de_semaine`;
CREATE TABLE IF NOT EXISTS `gamme_jour_de_semaine` (
  `id_jour` bigint(20) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_jour`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.gamme_jour_de_semaine : ~7 rows (environ)
REPLACE INTO `gamme_jour_de_semaine` (`id_jour`, `libelle`) VALUES
	(1, 'Dimanche'),
	(2, 'Lundi'),
	(3, 'Mardi'),
	(4, 'Mercredi'),
	(5, 'Jeudi'),
	(6, 'Vendredi'),
	(7, 'Samedi');

-- Listage de la structure de table watt_else_db. gamme_minutes_reservation
DROP TABLE IF EXISTS `gamme_minutes_reservation`;
CREATE TABLE IF NOT EXISTS `gamme_minutes_reservation` (
  `id_min` bigint(20) NOT NULL AUTO_INCREMENT,
  `gamme_minute` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_min`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.gamme_minutes_reservation : ~4 rows (environ)
REPLACE INTO `gamme_minutes_reservation` (`id_min`, `gamme_minute`) VALUES
	(0, 0),
	(1, 15),
	(2, 30),
	(3, 45);

-- Listage de la structure de table watt_else_db. gamme_motif_banissement
DROP TABLE IF EXISTS `gamme_motif_banissement`;
CREATE TABLE IF NOT EXISTS `gamme_motif_banissement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `raison` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.gamme_motif_banissement : ~0 rows (environ)
REPLACE INTO `gamme_motif_banissement` (`id`, `raison`) VALUES
	(0, 'Mauvais comportement'),
	(1, 'Non-respect répété des horaires de réservation'),
	(2, 'Comportement inapproprié lors de la rencontre avec le propriétaire de la borne'),
	(3, 'Utilisation abusive de la borne (par exemple, dépassement du temps autorisé)'),
	(4, 'Non-paiement des frais de location'),
	(5, 'Tentative de sabotage ou de modification de l’équipement'),
	(6, 'Comportement nuisible ou dangereux pour les autres utilisateurs'),
	(7, 'Non-respect des règles de sécurité liées à la recharge'),
	(8, 'Fourniture d’informations erronées ou trompeuses lors de la réservation');

-- Listage de la structure de table watt_else_db. gamme_motif_de_retrait_borne
DROP TABLE IF EXISTS `gamme_motif_de_retrait_borne`;
CREATE TABLE IF NOT EXISTS `gamme_motif_de_retrait_borne` (
  `id_motif_retrait` bigint(20) NOT NULL AUTO_INCREMENT,
  `motif_retrait` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_motif_retrait`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.gamme_motif_de_retrait_borne : ~0 rows (environ)
REPLACE INTO `gamme_motif_de_retrait_borne` (`id_motif_retrait`, `motif_retrait`) VALUES
	(0, 'Je n\'ai plus envie de louer ma borne'),
	(1, 'Ma borne ne fonctionne plus correctement'),
	(2, 'Je n\'ai plus besoin de la borne pour mes besoins personnels'),
	(3, 'Je rencontre trop de problèmes avec les utilisateurs de la borne'),
	(4, 'Je ne suis plus satisfait du service de la plateforme'),
	(5, 'Je n\'ai pas eu assez de demandes de location pour justifier de garder la borne'),
	(6, 'Des problèmes de sécurité m\'incitent à retirer la borne'),
	(7, 'La borne est trop vieille et ne répond plus aux normes de recharge actuelles');

-- Listage de la structure de table watt_else_db. gamme_motif_fermeture_compte
DROP TABLE IF EXISTS `gamme_motif_fermeture_compte`;
CREATE TABLE IF NOT EXISTS `gamme_motif_fermeture_compte` (
  `Id_Motif_fermeture` bigint(20) NOT NULL AUTO_INCREMENT,
  `Libelle` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`Id_Motif_fermeture`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.gamme_motif_fermeture_compte : ~2 rows (environ)
REPLACE INTO `gamme_motif_fermeture_compte` (`Id_Motif_fermeture`, `Libelle`) VALUES
	(0, 'Compte bannis'),
	(1, 'Utilisateur ne souhaite plus utiliser la plateforme'),
	(2, 'J\' rencontrer des problèmes de paiement ou facturation'),
	(3, 'Aucun appareil compatible avec le service'),
	(4, 'Problèmes techniques récurrents avec la borne'),
	(5, 'Utilisateur ne souhaite plus partager sa borne de recharge'),
	(6, 'Autres raisons personnelles');

-- Listage de la structure de table watt_else_db. gamme_motif_fin_anormale_reservation
DROP TABLE IF EXISTS `gamme_motif_fin_anormale_reservation`;
CREATE TABLE IF NOT EXISTS `gamme_motif_fin_anormale_reservation` (
  `Id_Fin_anormale` bigint(20) NOT NULL AUTO_INCREMENT,
  `Libelle` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id_Fin_anormale`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.gamme_motif_fin_anormale_reservation : ~0 rows (environ)
REPLACE INTO `gamme_motif_fin_anormale_reservation` (`Id_Fin_anormale`, `Libelle`) VALUES
	(0, 'Défaillance matérielle de la borne'),
	(1, 'Problèmes de connexion réseau'),
	(2, 'Interruption de l\'alimentation électrique'),
	(3, 'Erreur de paiement ou transaction échouée'),
	(4, 'Erreur de communication entre la borne et le véhic'),
	(5, 'Surcharge de la borne de recharge'),
	(6, 'Problème logiciel ou bug de la borne');

-- Listage de la structure de table watt_else_db. gamme_puissance
DROP TABLE IF EXISTS `gamme_puissance`;
CREATE TABLE IF NOT EXISTS `gamme_puissance` (
  `id_puissance` bigint(20) NOT NULL AUTO_INCREMENT,
  `puissance` float DEFAULT NULL,
  PRIMARY KEY (`id_puissance`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.gamme_puissance : ~5 rows (environ)
REPLACE INTO `gamme_puissance` (`id_puissance`, `puissance`) VALUES
	(1, 2.3),
	(2, 3.7),
	(3, 7.4),
	(4, 11),
	(5, 22);

-- Listage de la structure de table watt_else_db. gamme_type_de_prise
DROP TABLE IF EXISTS `gamme_type_de_prise`;
CREATE TABLE IF NOT EXISTS `gamme_type_de_prise` (
  `Id_Type_de_prise` bigint(20) NOT NULL AUTO_INCREMENT,
  `Libelle` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`Id_Type_de_prise`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.gamme_type_de_prise : ~7 rows (environ)
REPLACE INTO `gamme_type_de_prise` (`Id_Type_de_prise`, `Libelle`) VALUES
	(0, 'Type 1'),
	(1, 'Type 2'),
	(2, 'Type 3'),
	(3, 'Domestique'),
	(4, 'Combo CCS'),
	(5, 'Green\'up'),
	(6, 'CHAdeMO');

-- Listage de la structure de table watt_else_db. gamme_type_de_tarification
DROP TABLE IF EXISTS `gamme_type_de_tarification`;
CREATE TABLE IF NOT EXISTS `gamme_type_de_tarification` (
  `id_type_tarification` bigint(20) NOT NULL AUTO_INCREMENT,
  `libelle_type_tarification` varchar(254) NOT NULL,
  `abreviation` varchar(50) NOT NULL,
  PRIMARY KEY (`id_type_tarification`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.gamme_type_de_tarification : ~2 rows (environ)
REPLACE INTO `gamme_type_de_tarification` (`id_type_tarification`, `libelle_type_tarification`, `abreviation`) VALUES
	(0, 'par heure', '/h'),
	(1, 'par kilo watt', '/kW');

-- Listage de la structure de table watt_else_db. gamme_type_d_avis
DROP TABLE IF EXISTS `gamme_type_d_avis`;
CREATE TABLE IF NOT EXISTS `gamme_type_d_avis` (
  `Id_type_avis` bigint(20) NOT NULL AUTO_INCREMENT,
  `Libelle_type_avis` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`Id_type_avis`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.gamme_type_d_avis : ~4 rows (environ)
REPLACE INTO `gamme_type_d_avis` (`Id_type_avis`, `Libelle_type_avis`) VALUES
	(0, 'Tout s\'est bien passé'),
	(1, 'La borne n’était pas conforme à l’annonce'),
	(2, 'Fournisseur désagréable'),
	(3, 'Fournisseur accueillant'),
	(4, 'La borne n’était pas disponible à l’heure prévue'),
	(5, 'Le prix de la location était trop élevé par rapport à la qualité'),
	(6, 'La borne a été difficile à utiliser ou mal indiquée');

-- Listage de la structure de table watt_else_db. gamme_type_service
DROP TABLE IF EXISTS `gamme_type_service`;
CREATE TABLE IF NOT EXISTS `gamme_type_service` (
  `Id_service` bigint(20) NOT NULL AUTO_INCREMENT,
  `Libelle_service` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`Id_service`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.gamme_type_service : ~2 rows (environ)
REPLACE INTO `gamme_type_service` (`Id_service`, `Libelle_service`) VALUES
	(0, 'Toilettes'),
	(1, 'Supermarché'),
	(2, 'Lavage de voiture'),
	(3, 'Wi-Fi gratuit'),
	(4, 'Station de vélo électrique'),
	(5, 'Café et restauration rapide'),
	(6, 'Espace détente'),
	(7, 'Vente d\'accessoires pour voiture'),
	(8, 'Réparation automobile'),
	(9, 'Recharge de téléphone mobile'),
	(10, 'Location de trottinettes électriques');

-- Listage de la structure de table watt_else_db. obj_banissement
DROP TABLE IF EXISTS `obj_banissement`;
CREATE TABLE IF NOT EXISTS `obj_banissement` (
  `ID_bannissement` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_raison_banissement` bigint(20) NOT NULL,
  `Id_Utilisateur` bigint(20) NOT NULL,
  `dateDeDebut` datetime NOT NULL,
  `dateDeFin` datetime DEFAULT NULL,
  `commentaire` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`ID_bannissement`),
  KEY `FK_bannir` (`Id_Utilisateur`,`id_raison_banissement`) USING BTREE,
  KEY `FK_banissement_raisondubanissement` (`id_raison_banissement`) USING BTREE,
  CONSTRAINT `FK_banissement_raisondubanissement` FOREIGN KEY (`id_raison_banissement`) REFERENCES `gamme_motif_banissement` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_banissement_utilisateur` FOREIGN KEY (`Id_Utilisateur`) REFERENCES `obj_utilisateur` (`Id_Utilisateur`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.obj_banissement : ~0 rows (environ)

-- Listage de la structure de table watt_else_db. obj_borne
DROP TABLE IF EXISTS `obj_borne`;
CREATE TABLE IF NOT EXISTS `obj_borne` (
  `Id_Station_de_recharge` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL DEFAULT 'Station_name',
  `Id_Utilisateur` bigint(20) NOT NULL,
  `id_motif_retrait` bigint(20) DEFAULT NULL,
  `id_tarification` bigint(20) DEFAULT NULL,
  `Id_Type_de_prise` bigint(20) NOT NULL,
  `Date_ajout` datetime NOT NULL,
  `Date_retrait` datetime DEFAULT NULL,
  `id_puissance` bigint(20) NOT NULL DEFAULT '0',
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `address_display` varchar(254) NOT NULL DEFAULT '',
  `code_postal` varchar(50) NOT NULL,
  PRIMARY KEY (`nom`,`Id_Utilisateur`) USING BTREE,
  UNIQUE KEY `Id_Station_de_recharge` (`Id_Station_de_recharge`),
  KEY `FK_station_de_recharge_motif_de_retrait` (`id_motif_retrait`),
  KEY `FK_station_de_recharge_tarification` (`id_tarification`),
  KEY `FK_station_de_recharge_utilisateur` (`Id_Utilisateur`),
  KEY `FK_station_de_recharge_type_de_prise` (`Id_Type_de_prise`),
  KEY `FK_obj_borne_gamme_puissance` (`id_puissance`),
  CONSTRAINT `FK_obj_borne_gamme_puissance` FOREIGN KEY (`id_puissance`) REFERENCES `gamme_puissance` (`id_puissance`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_station_de_recharge_motif_de_retrait` FOREIGN KEY (`id_motif_retrait`) REFERENCES `gamme_motif_de_retrait_borne` (`id_motif_retrait`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_station_de_recharge_tarification` FOREIGN KEY (`id_tarification`) REFERENCES `obj_tarification_borne` (`id_tarification`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_station_de_recharge_type_de_prise` FOREIGN KEY (`Id_Type_de_prise`) REFERENCES `gamme_type_de_prise` (`Id_Type_de_prise`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_station_de_recharge_utilisateur` FOREIGN KEY (`Id_Utilisateur`) REFERENCES `obj_utilisateur` (`Id_Utilisateur`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.obj_borne : ~25 rows (environ)
REPLACE INTO `obj_borne` (`Id_Station_de_recharge`, `nom`, `Id_Utilisateur`, `id_motif_retrait`, `id_tarification`, `Id_Type_de_prise`, `Date_ajout`, `Date_retrait`, `id_puissance`, `latitude`, `longitude`, `address_display`, `code_postal`) VALUES
	(50, 'Andean goose', 6, NULL, 8, 0, '2025-02-06 20:35:19', NULL, 1, 43.6047, 3.8784, '255 rue de la République, Quartier de l\'Opéra, Marseille, Marseille, Provence-Alpes-Côte d\'Azur, France métropolitaine, 13001, France', '13001'),
	(11, 'Armadillo, giant', 5, NULL, 3, 0, '2025-01-26 20:21:09', NULL, 3, 48.8566, 2.3522, '60 rue de la République, Quartier Latin, Paris, Paris, Île-de-France, France métropolitaine, 75004, France', '75004'),
	(35, 'Ass, asiatic wild', 10, NULL, 2, 0, '2024-04-10 05:49:30', NULL, 5, 43.6108, 3.8767, '180 avenue des Champs-Élysées, Quartier des Champs-Élysées, Paris, Paris, Île-de-France, France métropolitaine, 75008, France', '75008'),
	(34, 'Bat-eared fox', 5, NULL, 10, 2, '2024-08-12 09:40:43', NULL, 3, 43.2965, 5.3698, '175 boulevard de la Croix-Rousse, Quartier de la Croix-Rousse, Lyon, Lyon, Auvergne-Rhône-Alpes, France métropolitaine, 69004, France', '69004'),
	(48, 'Bird, secretary', 2, NULL, 7, 1, '2024-10-15 11:44:37', NULL, 4, 45.75, 4.85, '245 rue de la République, Quartier de la Guillotière, Lyon, Lyon, Auvergne-Rhône-Alpes, France métropolitaine, 69007, France', '69007'),
	(58, 'Blette', 1, NULL, 16, 0, '2025-02-24 00:00:00', NULL, 2, 48.6900603, 2.1872547, '4 bis, Chemin de la Cyprenne, La Cyprenne, La Boissière, Orsay, Palaiseau, Essonne, Île-de-France, France métropolitaine, 91400, France', '91400'),
	(33, 'Boa, mexican', 2, NULL, 10, 2, '2024-07-05 05:53:51', NULL, 2, 48.8496, 2.3376, '170 rue de Rivoli, Quartier du Marais, Paris, Paris, Île-de-France, France métropolitaine, 75004, France', '75004'),
	(29, 'Buffalo, asian water', 10, NULL, 1, 1, '2024-10-30 23:39:19', NULL, 2, 48.8738, 2.3505, '150 rue du Faubourg Saint-Honoré, Quartier des Champs-Élysées, Paris, Paris, Île-de-France, France métropolitaine, 75008, France', '75008'),
	(20, 'Buffalo, wild water', 4, NULL, 4, 1, '2024-08-31 16:10:45', NULL, 4, 43.2965, 5.3698, '105 boulevard de la Croix-Rousse, Quartier de la Croix-Rousse, Lyon, Lyon, Auvergne-Rhône-Alpes, France métropolitaine, 69004, France', '69004'),
	(16, 'Carmine bee-eater', 3, NULL, 8, 1, '2024-03-24 21:42:30', NULL, 5, 43.6047, 3.8784, '85 rue de la République, Quartier de l\'Opéra, Marseille, Marseille, Provence-Alpes-Côte d\'Azur, France métropolitaine, 13001, France', '13001'),
	(27, 'Coatimundi, white-nosed', 7, NULL, 9, 1, '2024-09-29 12:27:52', NULL, 4, 43.6108, 3.8767, '140 avenue des Champs-Élysées, Quartier des Champs-Élysées, Paris, Paris, Île-de-France, France métropolitaine, 75008, France', '75008'),
	(8, 'Colobus, magistrate black', 5, NULL, 1, 1, '2024-06-27 23:40:22', NULL, 2, 48.8738, 2.3505, '45 rue du Faubourg Saint-Honoré, Quartier des Champs-Élysées, Paris, Paris, Île-de-France, France métropolitaine, 75008, France', '75008'),
	(47, 'Common waterbuck', 2, NULL, 1, 1, '2024-12-07 14:20:19', NULL, 3, 43.6108, 3.8767, '240 avenue des Champs-Élysées, Quartier des Champs-Élysées, Paris, Paris, Île-de-France, France métropolitaine, 75008, France', '75008'),
	(3, 'Deer, savannah', 10, NULL, 3, 1, '2024-03-29 13:22:09', NULL, 1, 45.764, 4.8357, '20 rue Victor Hugo, Quartier des Brotteaux, Lyon, Lyon, Auvergne-Rhône-Alpes, France métropolitaine, 69006, France', '69006'),
	(7, 'Dog, bush', 7, NULL, 7, 0, '2024-10-18 15:07:43', NULL, 5, 43.6047, 3.8784, '40 rue de la République, Quartier de l\'Opéra, Marseille, Marseille, Provence-Alpes-Côte d\'Azur, France métropolitaine, 13001, France', '13001'),
	(32, 'Eagle, tawny', 3, NULL, 3, 0, '2024-12-02 21:54:00', NULL, 1, 45.764, 4.8357, '165 rue Victor Hugo, Quartier des Brotteaux, Lyon, Lyon, Auvergne-Rhône-Alpes, France métropolitaine, 69006, France', '69006'),
	(2, 'Eastern indigo snake', 9, NULL, 7, 0, '2024-12-14 02:33:57', NULL, 5, 43.6108, 3.8767, '15 avenue des Champs-Élysées, Quartier des Champs-Élysées, Paris, Paris, Île-de-France, France métropolitaine, 75008, France', '75008'),
	(4, 'Finch, common melba', 6, NULL, 6, 0, '2024-03-21 04:32:06', NULL, 1, 48.8496, 2.3376, '25 rue de Rivoli, Quartier du Marais, Paris, Paris, Île-de-France, France métropolitaine, 75004, France', '75004'),
	(5, 'Fox, crab-eating', 9, NULL, 2, 0, '2024-08-21 11:19:14', NULL, 3, 43.2965, 5.3698, '30 boulevard de la Croix-Rousse, Quartier de la Croix-Rousse, Lyon, Lyon, Auvergne-Rhône-Alpes, France métropolitaine, 69004, France', '69004'),
	(21, 'Fox, pampa gray', 4, NULL, 6, 0, '2024-04-18 14:15:56', NULL, 2, 48.8566, 2.3522, '110 rue de la République, Quartier Latin, Paris, Paris, Île-de-France, France métropolitaine, 75004, France', '75004'),
	(49, 'Genoveva', 8, NULL, 3, 0, '2024-11-20 13:45:39', NULL, 1, 48.8584, 2.347, '250 rue des Écoles, Quartier Latin, Paris, Paris, Île-de-France, France métropolitaine, 75005, France', '75005'),
	(41, 'Glossy ibis', 10, NULL, 6, 0, '2024-05-29 04:51:28', NULL, 1, 48.8584, 2.347, '210 rue des Écoles, Quartier Latin, Paris, Paris, Île-de-France, France métropolitaine, 75005, France', '75005'),
	(38, 'Green heron', 2, NULL, 7, 1, '2024-04-05 00:40:21', NULL, 1, 48.8566, 2.3522, '195 rue de la République, Quartier Latin, Paris, Paris, Île-de-France, France métropolitaine, 75004, France', '75004'),
	(28, 'Gull, silver', 6, NULL, 3, 2, '2024-07-08 02:58:52', NULL, 5, 45.764, 4.8357, '145 rue Victor Hugo, Quartier des Brotteaux, Lyon, Lyon, Auvergne-Rhône-Alpes, France métropolitaine, 69006, France', '69006'),
	(37, 'Hornbill, leadbeateri\'s ground', 2, NULL, 4, 1, '2025-02-18 01:24:30', NULL, 3, 48.8738, 2.3505, '190 rue du Faubourg Saint-Honoré, Quartier des Champs-Élysées, Paris, Paris, Île-de-France, France métropolitaine, 75008, France', '75008'),
	(25, 'Iguana, common green', 8, NULL, 4, 1, '2024-08-04 20:14:09', NULL, 4, 43.2965, 5.3698, '130 boulevard de la Croix-Rousse, Quartier de la Croix-Rousse, Lyon, Lyon, Auvergne-Rhône-Alpes, France métropolitaine, 69004, France', '69004'),
	(65, 'Juliette', 1, NULL, 23, 1, '2025-02-24 00:00:00', NULL, 2, 48.763847, 1.931239, '5, Avenue de Cornouaille, Maurepas, Rambouillet, Yvelines, France métropolitaine, 78310, France', '78310'),
	(24, 'Kudu, greater', 7, NULL, 8, 2, '2024-03-02 01:49:16', NULL, 2, 45.75, 4.85, '125 rue de la République, Quartier de la Guillotière, Lyon, Lyon, Auvergne-Rhône-Alpes, France métropolitaine, 69007, France', '69007'),
	(19, 'Lark, horned', 1, NULL, 1, 1, '2025-01-22 14:40:09', NULL, 5, 48.8496, 2.3376, '100 rue de Rivoli, Quartier du Marais, Paris, Paris, Île-de-France, France métropolitaine, 75004, France', '75004'),
	(17, 'Lava gull', 1, NULL, 4, 0, '2024-09-30 05:26:14', NULL, 1, 48.8738, 2.3505, '90 rue du Faubourg Saint-Honoré, Quartier des Champs-Élysées, Paris, Paris, Île-de-France, France métropolitaine, 75008, France', '75008'),
	(61, 'Libellule', 1, NULL, 19, 0, '2025-02-24 00:00:00', NULL, 3, 43.1748703, 3.1848517, '28, Avenue Pierre Brossolette, Pleine-Vue-sur-Mer, Saint-Pierre-la-Mer, Fleury, Narbonne, Aude, Occitanie, France métropolitaine, 11560, France', '11560'),
	(14, 'Llama', 9, NULL, 3, 0, '2024-03-10 00:32:01', NULL, 5, 43.6108, 3.8767, '75 avenue des Champs-Élysées, Quartier des Champs-Élysées, Paris, Paris, Île-de-France, France métropolitaine, 75008, France', '75008'),
	(70, 'Louis XVI', 5, NULL, 28, 0, '2025-02-24 00:00:00', NULL, 3, 48.8007505, 2.1252266, '9, Rue de Satory, Saint-Louis, Versailles, Yvelines, France métropolitaine, 78000, France', '78000'),
	(62, 'Loup', 1, NULL, 20, 0, '2025-02-24 00:00:00', NULL, 3, 48.79546939858, 2.1289196704215687, 'Rue du Hazard, Saint-Louis, Versailles, Yvelines, France métropolitaine, 78000, France', '78000'),
	(64, 'Lycée', 1, NULL, 22, 0, '2025-02-24 00:00:00', NULL, 2, 48.8183807, 1.9220451, '2, Rue Victor Hugo, Les Cents Arpents, Plaisir, Versailles, Yvelines, France métropolitaine, 78370, France', '78370'),
	(54, 'Ma station', 19, NULL, 12, 1, '2025-02-24 00:00:00', NULL, 4, 48.9552448, 2.5317986, '5, Boulevard de la Pépinière, Clos-Montceleux, Villepinte, Le Raincy, Seine-Saint-Denis, France métropolitaine, 93420, France', '93420'),
	(55, 'Ma station 2', 19, NULL, 13, 3, '2025-02-24 00:00:00', NULL, 2, 48.9552448, 2.5317986, '5, Boulevard de la Pépinière, Clos-Montceleux, Villepinte, Le Raincy, Seine-Saint-Denis, France métropolitaine, 93420, France', '93420'),
	(39, 'Magistrate black colobus', 1, NULL, 10, 0, '2024-06-15 20:15:28', NULL, 1, 45.75, 4.85, '200 rue de la République, Quartier de la Guillotière, Lyon, Lyon, Auvergne-Rhône-Alpes, France métropolitaine, 69007, France', '69007'),
	(63, 'Maman', 1, NULL, 21, 0, '2025-02-24 00:00:00', NULL, 3, 48.802343829572486, 2.0226772580785854, 'Rue Paul Gauguin, Bois-d\'Arcy, Versailles, Yvelines, France métropolitaine, 78390, France', '78390'),
	(59, 'Mouche', 1, NULL, 17, 0, '2025-02-24 00:00:00', NULL, 2, 48.6936089, 2.1761143, '13, Rue de la Dimancherie, Mondétour-Verger, La Boissière, Orsay, Palaiseau, Essonne, France métropolitaine, 91400, France', '91400'),
	(60, 'Moustique', 1, NULL, 18, 1, '2025-02-24 00:00:00', NULL, 1, 48.6900392, 2.1758419, '14, Avenue Jean Jaurès, Mondétour-Verger, La Boissière, Orsay, Palaiseau, Essonne, France métropolitaine, 91400, France', '91400'),
	(69, 'Muche', 5, NULL, 27, 0, '2025-02-24 00:00:00', NULL, 1, 48.7966026, 2.0703234, '25, Rue du Bel-Air, Quartier de l\'Épi d\'Or, Saint-Cyr-l\'École, Versailles, Yvelines, France métropolitaine, 78210, France', '78210'),
	(43, 'North American porcupine', 5, NULL, 4, 1, '2024-03-18 23:34:00', NULL, 3, 48.8496, 2.3376, '220 rue de Rivoli, Quartier du Marais, Paris, Paris, Île-de-France, France métropolitaine, 75004, France', '75004'),
	(18, 'North American porcupine', 10, NULL, 3, 1, '2024-09-03 22:34:17', NULL, 1, 45.75, 4.85, '95 rue de la République, Quartier de la Guillotière, Lyon, Lyon, Auvergne-Rhône-Alpes, France métropolitaine, 69007, France', '69007'),
	(30, 'Nuthatch, red-breasted', 3, NULL, 4, 1, '2024-11-18 11:47:41', NULL, 5, 43.6047, 3.8784, '155 rue de la République, Quartier de l\'Opéra, Marseille, Marseille, Provence-Alpes-Côte d\'Azur, France métropolitaine, 13001, France', '13001'),
	(6, 'Penguin, little blue', 8, NULL, 2, 0, '2024-07-18 13:59:57', NULL, 3, 48.8584, 2.347, '35 rue des Écoles, Quartier Latin, Paris, Paris, Île-de-France, France métropolitaine, 75005, France', '75005'),
	(36, 'Pine squirrel', 7, NULL, 10, 1, '2024-09-28 10:28:10', NULL, 4, 45.75, 4.85, '185 rue de la République, Quartier de la Guillotière, Lyon, Lyon, Auvergne-Rhône-Alpes, France métropolitaine, 69007, France', '69007'),
	(13, 'Radiated tortoise', 6, NULL, 2, 0, '2024-05-05 18:53:31', NULL, 1, 45.764, 4.8357, '70 rue Victor Hugo, Quartier des Brotteaux, Lyon, Lyon, Auvergne-Rhône-Alpes, France métropolitaine, 69006, France', '69006'),
	(31, 'Red-billed toucan', 9, NULL, 9, 1, '2024-11-12 09:28:11', NULL, 1, 48.8566, 2.3522, '160 rue de la République, Quartier Latin, Paris, Paris, Île-de-France, France métropolitaine, 75004, France', '75004'),
	(40, 'Snake, buttermilk', 4, NULL, 1, 1, '2024-04-13 22:20:30', NULL, 1, 48.8738, 2.3505, '205 rue du Faubourg Saint-Honoré, Quartier des Champs-Élysées, Paris, Paris, Île-de-France, France métropolitaine, 75008, France', '75008'),
	(12, 'South African hedgehog', 8, NULL, 1, 1, '2025-02-01 23:10:13', NULL, 2, 48.8496, 2.3376, '65 rue de Rivoli, Quartier du Marais, Paris, Paris, Île-de-France, France métropolitaine, 75004, France', '75004'),
	(46, 'Spotted hyena', 7, NULL, 4, 2, '2024-12-06 13:36:17', NULL, 1, 48.8738, 2.3505, '235 rue du Faubourg Saint-Honoré, Quartier des Champs-Élysées, Paris, Paris, Île-de-France, France métropolitaine, 75008, France', '75008'),
	(15, 'Squirrel, eastern fox', 10, NULL, 5, 1, '2024-08-13 14:50:38', NULL, 3, 48.8584, 2.347, '80 rue des Écoles, Quartier Latin, Paris, Paris, Île-de-France, France métropolitaine, 75005, France', '75005'),
	(22, 'Squirrel, pine', 2, NULL, 8, 2, '2024-09-19 08:22:14', NULL, 3, 43.6047, 3.8784, '115 rue de la République, Quartier de l\'Opéra, Marseille, Marseille, Provence-Alpes-Côte d\'Azur, France métropolitaine, 13001, France', '13001'),
	(66, 'SQY', 1, NULL, 24, 1, '2025-02-24 00:00:00', NULL, 2, 48.7627436, 2.0347392, '29, Avenue du Lycée, Montigny-le-Bretonneux, Versailles, Yvelines, France métropolitaine, 78180, France', '78180'),
	(26, 'Starling, cape', 9, NULL, 8, 1, '2024-05-02 01:11:52', NULL, 3, 48.8584, 2.347, '135 rue des Écoles, Quartier Latin, Paris, Paris, Île-de-France, France métropolitaine, 75005, France', '75005'),
	(51, 'STATION ORSAY 1', 2, NULL, 7, 0, '2025-02-24 09:53:53', NULL, 4, 48.6958505, 2.180619904439658, '27, Rue de Chartres, Les Jamesons-Sud, La Boissière, Orsay, Palaiseau, Essonne, France métropolitaine, 91400, France', '91400'),
	(52, 'STATION ORSAY 2', 1, NULL, 4, 0, '2025-02-24 09:58:02', NULL, 1, 48.698944049999994, 2.1941351525098423, '25, Avenue Saint-Laurent, Orsay Centre, La Bouvêche, Orsay, Palaiseau, Essonne, Île-de-France, France métropolitaine, 91400, France', '91440'),
	(53, 'Station présentation', 2, NULL, 11, 1, '2025-02-24 00:00:00', NULL, 4, 48.5601965, 7.7599132, '1, Rue des Mouettes, Neudorf, Neudorf-Musau, Strasbourg, Bas-Rhin, Grand Est, France métropolitaine, 67100, France', '67100'),
	(45, 'Stork, greater adjutant', 3, NULL, 8, 1, '2024-04-24 17:52:00', NULL, 5, 43.6047, 3.8784, '230 rue de la République, Quartier de l\'Opéra, Marseille, Marseille, Provence-Alpes-Côte d\'Azur, France métropolitaine, 13001, France', '13001'),
	(44, 'Stork, jabiru', 5, NULL, 8, 1, '2024-02-26 01:41:30', NULL, 2, 45.764, 4.8357, '225 rue Victor Hugo, Quartier des Brotteaux, Lyon, Lyon, Auvergne-Rhône-Alpes, France métropolitaine, 69006, France', '69006'),
	(67, 'Truc', 5, NULL, 25, 0, '2025-02-24 00:00:00', NULL, 1, 48.870144, 2.096368, '12, Rue Henri Bouilhet, Marly-le-Roi, Saint-Germain-en-Laye, Yvelines, France métropolitaine, 78160, France', '78160'),
	(42, 'Turkey, wild', 6, NULL, 8, 1, '2025-01-13 12:38:58', NULL, 3, 43.6047, 3.8784, '215 rue de la République, Quartier de l\'Opéra, Marseille, Marseille, Provence-Alpes-Côte d\'Azur, France métropolitaine, 13001, France', '13001'),
	(23, 'Wallaby, river', 3, NULL, 5, 0, '2024-05-10 11:45:35', NULL, 1, 48.8738, 2.3505, '120 rue du Faubourg Saint-Honoré, Quartier des Champs-Élysées, Paris, Paris, Île-de-France, France métropolitaine, 75008, France', '75008'),
	(9, 'Waved albatross', 1, NULL, 6, 0, '2024-09-19 02:01:53', NULL, 4, 45.75, 4.85, '50 rue de la République, Quartier de la Guillotière, Lyon, Lyon, Auvergne-Rhône-Alpes, France métropolitaine, 69007, France', '69007'),
	(10, 'Wolf, common', 2, NULL, 2, 0, '2024-03-06 02:27:03', NULL, 4, 43.2965, 5.3698, '55 boulevard de la Croix-Rousse, Quartier de la Croix-Rousse, Lyon, Lyon, Auvergne-Rhône-Alpes, France métropolitaine, 69004, France', '69004');

-- Listage de la structure de table watt_else_db. obj_carte_bancaire
DROP TABLE IF EXISTS `obj_carte_bancaire`;
CREATE TABLE IF NOT EXISTS `obj_carte_bancaire` (
  `id_Carte` bigint(20) NOT NULL AUTO_INCREMENT,
  `Id_Utilisateur` bigint(20) NOT NULL,
  `dateDAjout` datetime NOT NULL,
  `dateDeRetrait` datetime DEFAULT NULL,
  `numeroDeCarte` varchar(254) NOT NULL,
  `dateDeValidite` datetime NOT NULL,
  `codeDeSecurite` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_Carte`),
  KEY `FK_cartebancaire_utilisateur` (`Id_Utilisateur`),
  CONSTRAINT `FK_cartebancaire_utilisateur` FOREIGN KEY (`Id_Utilisateur`) REFERENCES `obj_utilisateur` (`Id_Utilisateur`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.obj_carte_bancaire : ~5 rows (environ)
REPLACE INTO `obj_carte_bancaire` (`id_Carte`, `Id_Utilisateur`, `dateDAjout`, `dateDeRetrait`, `numeroDeCarte`, `dateDeValidite`, `codeDeSecurite`) VALUES
	(1, 1, '2025-02-20 10:07:58', NULL, '1111111111111111', '2028-02-20 10:08:17', 111),
	(13, 13, '2025-02-20 10:07:58', NULL, '1313131313131313', '2028-02-20 10:08:17', 133),
	(14, 14, '2025-02-20 10:07:58', NULL, '1414141414141414', '2028-02-20 10:08:17', 144),
	(15, 15, '2025-02-20 10:07:58', NULL, '1515151515151515', '2028-02-20 10:08:17', 155),
	(17, 17, '2025-02-20 10:07:58', '2025-02-20 10:11:05', '1717171717171717', '2028-02-20 10:08:17', 177),
	(18, 2, '2025-02-24 00:00:00', NULL, '456788765', '0004-08-12 00:00:00', 645),
	(19, 2, '2025-02-24 00:00:00', NULL, '784245544215456', '2025-02-20 00:00:00', 555),
	(20, 19, '2025-02-24 00:00:00', NULL, '01234567890', '2027-09-26 00:00:00', 123),
	(21, 5, '2025-02-24 00:00:00', NULL, '123412341234', '1993-06-25 00:00:00', 246);

-- Listage de la structure de table watt_else_db. obj_compte_bancaire
DROP TABLE IF EXISTS `obj_compte_bancaire`;
CREATE TABLE IF NOT EXISTS `obj_compte_bancaire` (
  `id_compte_bancaire` bigint(20) NOT NULL AUTO_INCREMENT,
  `Id_Utilisateur` bigint(20) NOT NULL,
  `dateDAjout` datetime NOT NULL,
  `dateDeRetrait` datetime DEFAULT NULL,
  `iban` varchar(50) NOT NULL DEFAULT '',
  `bic` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id_compte_bancaire`) USING BTREE,
  KEY `FK_compte_bancaire_utilisateur` (`Id_Utilisateur`),
  CONSTRAINT `FK_compte_bancaire_utilisateur` FOREIGN KEY (`Id_Utilisateur`) REFERENCES `obj_utilisateur` (`Id_Utilisateur`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.obj_compte_bancaire : ~2 rows (environ)
REPLACE INTO `obj_compte_bancaire` (`id_compte_bancaire`, `Id_Utilisateur`, `dateDAjout`, `dateDeRetrait`, `iban`, `bic`) VALUES
	(1, 13, '2025-02-13 16:51:38', NULL, '23456789087654', '456'),
	(2, 13, '2025-02-19 00:00:00', NULL, 'FR7645645645645678', '7896451FS'),
	(3, 2, '2025-02-24 00:00:00', NULL, '465789', '656'),
	(4, 19, '2025-02-24 00:00:00', NULL, '123456789012', '1234');

-- Listage de la structure de table watt_else_db. obj_evaluation
DROP TABLE IF EXISTS `obj_evaluation`;
CREATE TABLE IF NOT EXISTS `obj_evaluation` (
  `Id_Evaluation` bigint(20) NOT NULL AUTO_INCREMENT,
  `Id_type_avis` bigint(20) DEFAULT NULL,
  `commentaire_additionnel` varchar(254) DEFAULT NULL,
  `note` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id_Evaluation`),
  KEY `FK_commentaire_conducteur_type_d_avis` (`Id_type_avis`),
  CONSTRAINT `FK_commentaire_conducteur_type_d_avis` FOREIGN KEY (`Id_type_avis`) REFERENCES `gamme_type_d_avis` (`Id_type_avis`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.obj_evaluation : ~0 rows (environ)
REPLACE INTO `obj_evaluation` (`Id_Evaluation`, `Id_type_avis`, `commentaire_additionnel`, `note`) VALUES
	(1, 0, NULL, 1),
	(2, NULL, NULL, 2),
	(3, NULL, NULL, 3),
	(4, NULL, NULL, 4),
	(5, NULL, NULL, 5);

-- Listage de la structure de table watt_else_db. obj_facturation
DROP TABLE IF EXISTS `obj_facturation`;
CREATE TABLE IF NOT EXISTS `obj_facturation` (
  `id_facturation` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_user` bigint(20) NOT NULL,
  `Date_reglement` datetime NOT NULL,
  `Montant` decimal(19,4) NOT NULL,
  PRIMARY KEY (`id_facturation`),
  KEY `FK_facturation_user` (`id_user`) USING BTREE,
  CONSTRAINT `FK_facturation_user` FOREIGN KEY (`id_user`) REFERENCES `obj_utilisateur` (`Id_Utilisateur`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.obj_facturation : ~2 rows (environ)
REPLACE INTO `obj_facturation` (`id_facturation`, `id_user`, `Date_reglement`, `Montant`) VALUES
	(1, 1, '2025-02-13 16:52:04', 11.0000),
	(2, 1, '2025-02-16 14:17:59', 12.0000),
	(3, 18, '2025-03-01 00:00:00', 0.0000),
	(4, 3, '2025-03-01 00:00:00', 0.2489),
	(5, 7, '2025-03-01 00:00:00', 0.4790),
	(6, 1, '2025-03-01 00:00:00', 0.0000);

-- Listage de la structure de table watt_else_db. obj_horaire_ouverture_borne
DROP TABLE IF EXISTS `obj_horaire_ouverture_borne`;
CREATE TABLE IF NOT EXISTS `obj_horaire_ouverture_borne` (
  `id_horaire_ouverture` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_he_deb` bigint(20) DEFAULT '0',
  `id_min_deb` bigint(20) DEFAULT '0',
  `id_he_fin` bigint(20) NOT NULL DEFAULT '0',
  `id_min_fin` bigint(20) DEFAULT '0',
  `id_jour` bigint(20) NOT NULL,
  `Id_Station_de_recharge` bigint(20) DEFAULT NULL,
  `Date_debut_HO` datetime DEFAULT NULL,
  `Date_fin_HO` datetime DEFAULT NULL,
  PRIMARY KEY (`id_horaire_ouverture`),
  KEY `FK_horaire_ouverture_gamme__heure` (`id_he_deb`),
  KEY `FK_horaire_ouverture_gamme_minute` (`id_min_deb`),
  KEY `FK_horaire_ouverture_gamme__heure_2` (`id_he_fin`),
  KEY `FK_horaire_ouverture_gamme_minute_2` (`id_min_fin`),
  KEY `FK_horaire_ouverture_joursemaine` (`id_jour`),
  KEY `FK_horaire_ouverture_station_de_recharge` (`Id_Station_de_recharge`),
  CONSTRAINT `FK_horaire_ouverture_gamme__heure` FOREIGN KEY (`id_he_deb`) REFERENCES `gamme_heure_reservation` (`id_he`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_horaire_ouverture_gamme__heure_2` FOREIGN KEY (`id_he_fin`) REFERENCES `gamme_heure_reservation` (`id_he`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_horaire_ouverture_gamme_minute` FOREIGN KEY (`id_min_deb`) REFERENCES `gamme_minutes_reservation` (`id_min`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_horaire_ouverture_gamme_minute_2` FOREIGN KEY (`id_min_fin`) REFERENCES `gamme_minutes_reservation` (`id_min`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_horaire_ouverture_joursemaine` FOREIGN KEY (`id_jour`) REFERENCES `gamme_jour_de_semaine` (`id_jour`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_horaire_ouverture_station_de_recharge` FOREIGN KEY (`Id_Station_de_recharge`) REFERENCES `obj_borne` (`Id_Station_de_recharge`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=381 DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.obj_horaire_ouverture_borne : ~1 rows (environ)
REPLACE INTO `obj_horaire_ouverture_borne` (`id_horaire_ouverture`, `id_he_deb`, `id_min_deb`, `id_he_fin`, `id_min_fin`, `id_jour`, `Id_Station_de_recharge`, `Date_debut_HO`, `Date_fin_HO`) VALUES
	(1, 3, 2, 18, 3, 1, 0, '2025-01-05 16:24:16', NULL),
	(2, 9, 0, 21, 3, 2, 0, '2024-03-26 17:25:54', NULL),
	(3, 2, 1, 15, 2, 3, 0, '2024-03-22 03:29:34', NULL),
	(4, 2, 2, 17, 4, 0, 0, '2025-01-07 03:28:34', NULL),
	(5, 12, 0, 24, 0, 5, 0, '2024-06-01 22:45:35', NULL),
	(6, 1, 0, 18, 0, 6, 0, '2024-06-18 08:56:39', NULL),
	(7, 2, 0, 23, 0, 0, 0, '2024-03-25 17:34:41', NULL),
	(8, 3, 2, 22, 1, 1, 1, '2024-11-15 02:48:14', NULL),
	(9, 12, 0, 18, 3, 2, 1, '2024-03-23 03:26:57', NULL),
	(10, 11, 2, 16, 0, 3, 1, '2024-11-25 16:52:28', NULL),
	(11, 11, 1, 20, 3, 4, 1, '2024-06-03 13:43:18', NULL),
	(12, 1, 0, 21, 1, 5, 1, '2024-03-25 09:38:26', NULL),
	(13, 7, 3, 24, 3, 6, 1, '2025-01-06 21:33:45', NULL),
	(14, 8, 0, 20, 0, 0, 1, '2024-12-16 02:37:33', NULL),
	(15, 9, 3, 15, 1, 1, 2, '2024-06-19 13:58:31', NULL),
	(16, 9, 0, 20, 2, 2, 2, '2024-07-08 12:35:47', NULL),
	(17, 1, 3, 21, 3, 3, 2, '2024-08-29 21:49:19', NULL),
	(18, 3, 3, 15, 2, 0, 2, '2024-06-02 23:32:32', NULL),
	(19, 12, 0, 18, 1, 5, 2, '2024-04-26 22:22:30', NULL),
	(20, 2, 3, 23, 2, 6, 2, '2024-06-29 04:20:08', NULL),
	(21, 9, 0, 20, 0, 0, 2, '2024-07-25 07:59:52', NULL),
	(22, 9, 2, 24, 0, 1, 3, '2024-11-14 05:39:19', NULL),
	(23, 1, 1, 12, 3, 2, 3, '2025-01-20 16:36:18', NULL),
	(24, 4, 2, 19, 1, 3, 3, '2025-02-17 18:19:30', NULL),
	(25, 8, 1, 18, 2, 4, 3, '2024-10-02 21:27:36', NULL),
	(26, 1, 1, 15, 1, 5, 3, '2024-09-26 01:12:56', NULL),
	(27, 10, 2, 13, 0, 6, 3, '2024-07-05 07:52:14', NULL),
	(28, 10, 2, 21, 3, 0, 3, '2024-09-12 05:34:02', NULL),
	(29, 4, 0, 20, 3, 1, 4, '2024-05-10 16:40:43', NULL),
	(30, 8, 0, 17, 3, 2, 4, '2024-11-01 17:21:06', NULL),
	(31, 5, 2, 18, 2, 3, 4, '2024-09-14 11:17:56', NULL),
	(32, 8, 3, 21, 3, 4, 4, '2024-12-01 07:15:31', NULL),
	(33, 4, 2, 13, 1, 5, 4, '2024-08-25 17:55:19', NULL),
	(34, 11, 1, 20, 1, 6, 4, '2024-04-19 06:26:47', NULL),
	(35, 5, 2, 18, 1, 0, 4, '2024-05-25 11:02:25', NULL),
	(36, 1, 1, 24, 2, 1, 5, '2024-03-31 12:47:22', NULL),
	(37, 10, 0, 13, 3, 2, 5, '2024-12-14 07:20:10', NULL),
	(38, 6, 0, 12, 2, 3, 5, '2024-10-09 09:01:33', NULL),
	(39, 5, 2, 22, 1, 4, 5, '2024-07-05 02:57:24', NULL),
	(40, 2, 3, 13, 2, 5, 5, '2024-05-06 22:45:09', NULL),
	(41, 11, 2, 21, 0, 6, 5, '2024-06-01 20:13:06', NULL),
	(42, 12, 3, 23, 2, 0, 5, '2025-01-15 18:40:35', NULL),
	(43, 4, 1, 14, 0, 1, 6, '2024-12-14 20:38:44', NULL),
	(44, 10, 0, 16, 1, 2, 6, '2024-09-21 14:34:40', NULL),
	(45, 2, 0, 15, 1, 3, 6, '2024-06-08 23:41:14', NULL),
	(46, 8, 0, 17, 2, 4, 6, '2024-09-19 15:05:35', NULL),
	(47, 8, 0, 16, 2, 5, 6, '2024-08-03 02:17:23', NULL),
	(48, 9, 3, 15, 3, 6, 6, '2024-11-30 01:08:48', NULL),
	(49, 10, 1, 20, 1, 0, 6, '2025-01-27 03:09:42', NULL),
	(50, 12, 2, 15, 1, 1, 7, '2024-08-27 12:21:11', NULL),
	(51, 6, 2, 14, 3, 2, 7, '2024-09-09 03:03:21', NULL),
	(52, 6, 3, 12, 2, 3, 7, '2024-12-14 13:46:05', NULL),
	(53, 11, 1, 14, 3, 4, 7, '2024-10-09 08:23:40', NULL),
	(54, 1, 3, 12, 2, 5, 7, '2024-02-29 21:42:07', NULL),
	(55, 12, 1, 24, 3, 6, 7, '2024-12-02 11:40:58', NULL),
	(56, 4, 2, 18, 0, 0, 7, '2024-03-09 08:41:52', NULL),
	(57, 2, 1, 13, 3, 1, 8, '2024-06-12 13:09:30', NULL),
	(58, 10, 1, 20, 1, 2, 8, '2024-10-04 17:34:24', NULL),
	(59, 9, 1, 21, 1, 3, 8, '2024-07-17 16:10:28', NULL),
	(60, 2, 1, 14, 1, 4, 8, '2025-01-27 21:53:06', NULL),
	(61, 5, 1, 24, 3, 5, 8, '2025-01-19 14:29:21', NULL),
	(62, 6, 3, 24, 3, 6, 8, '2024-09-15 14:47:18', NULL),
	(63, 10, 0, 22, 2, 0, 8, '2024-09-05 09:25:13', NULL),
	(64, 6, 2, 22, 2, 1, 9, '2024-05-23 21:57:29', NULL),
	(65, 7, 3, 20, 2, 2, 9, '2025-01-22 01:16:30', NULL),
	(66, 1, 0, 17, 2, 3, 9, '2024-06-30 15:57:30', NULL),
	(67, 7, 2, 20, 0, 4, 9, '2024-09-29 19:06:27', NULL),
	(68, 9, 3, 22, 1, 5, 9, '2024-09-28 21:50:22', NULL),
	(69, 3, 3, 18, 2, 6, 9, '2024-05-01 18:18:35', NULL),
	(70, 3, 0, 22, 1, 0, 9, '2024-05-16 11:55:38', NULL),
	(71, 1, 1, 13, 1, 1, 10, '2025-02-11 10:32:31', NULL),
	(72, 1, 0, 16, 1, 2, 10, '2025-01-12 00:59:44', NULL),
	(73, 6, 0, 18, 1, 3, 10, '2024-04-19 08:24:16', NULL),
	(74, 8, 1, 18, 0, 4, 10, '2024-07-07 15:06:15', NULL),
	(75, 2, 0, 16, 2, 5, 10, '2024-05-10 17:25:17', NULL),
	(76, 10, 1, 13, 2, 6, 10, '2025-01-18 12:05:28', NULL),
	(77, 8, 1, 13, 3, 0, 10, '2024-09-08 15:00:40', NULL),
	(78, 2, 1, 12, 2, 1, 11, '2024-11-27 23:24:03', NULL),
	(79, 12, 3, 21, 1, 2, 11, '2024-07-02 09:09:10', NULL),
	(80, 10, 0, 15, 3, 3, 11, '2024-04-28 07:55:38', NULL),
	(81, 2, 2, 20, 3, 4, 11, '2024-11-09 00:42:06', NULL),
	(82, 5, 2, 20, 2, 5, 11, '2024-05-16 00:16:49', NULL),
	(83, 10, 0, 14, 2, 6, 11, '2024-03-31 19:44:06', NULL),
	(84, 4, 0, 23, 3, 0, 11, '2024-09-04 09:58:30', NULL),
	(85, 1, 3, 20, 2, 1, 12, '2024-12-03 12:51:02', NULL),
	(86, 10, 3, 21, 2, 2, 12, '2024-09-02 23:36:32', NULL),
	(87, 6, 0, 23, 2, 3, 12, '2024-11-23 07:36:01', NULL),
	(88, 4, 2, 15, 3, 4, 12, '2024-09-26 10:22:36', NULL),
	(89, 10, 3, 17, 2, 5, 12, '2025-01-29 05:03:07', NULL),
	(90, 11, 2, 13, 0, 6, 12, '2024-10-26 13:19:39', NULL),
	(91, 2, 0, 24, 0, 0, 12, '2024-09-11 11:10:39', NULL),
	(92, 1, 2, 24, 3, 1, 13, '2024-10-07 13:33:59', NULL),
	(93, 10, 3, 17, 2, 2, 13, '2025-02-18 10:12:29', NULL),
	(94, 4, 1, 17, 1, 3, 13, '2024-09-03 08:39:51', NULL),
	(95, 9, 0, 23, 1, 4, 13, '2025-02-08 00:07:41', NULL),
	(96, 2, 3, 23, 2, 5, 13, '2024-11-28 03:29:14', NULL),
	(97, 8, 1, 15, 2, 6, 13, '2024-09-15 03:02:09', NULL),
	(98, 2, 2, 17, 3, 0, 13, '2025-01-29 09:28:05', NULL),
	(99, 4, 3, 20, 1, 1, 14, '2024-05-07 04:42:11', NULL),
	(100, 12, 1, 17, 1, 2, 14, '2025-01-13 16:48:41', NULL),
	(101, 1, 3, 13, 3, 3, 14, '2024-08-23 08:23:47', NULL),
	(102, 11, 1, 24, 2, 4, 14, '2024-07-23 12:32:29', NULL),
	(103, 6, 0, 17, 2, 5, 14, '2024-06-02 03:50:40', NULL),
	(104, 5, 1, 13, 0, 6, 14, '2024-05-08 02:59:10', NULL),
	(105, 3, 2, 17, 0, 0, 14, '2024-08-25 02:22:50', NULL),
	(106, 6, 1, 18, 0, 1, 15, '2024-03-03 08:46:14', NULL),
	(107, 8, 2, 18, 3, 2, 15, '2024-03-23 11:15:11', NULL),
	(108, 11, 1, 16, 2, 3, 15, '2024-03-13 13:10:59', NULL),
	(109, 8, 1, 14, 1, 4, 15, '2024-07-11 01:28:27', NULL),
	(110, 12, 0, 15, 0, 5, 15, '2024-06-02 00:15:11', NULL),
	(111, 4, 1, 23, 2, 6, 15, '2024-08-11 22:24:29', NULL),
	(112, 11, 0, 21, 0, 0, 15, '2024-05-06 07:20:38', NULL),
	(113, 8, 3, 16, 2, 1, 16, '2024-09-29 11:48:40', NULL),
	(114, 2, 0, 23, 3, 2, 16, '2024-10-20 14:29:10', NULL),
	(115, 5, 0, 21, 2, 3, 16, '2024-08-16 17:48:23', NULL),
	(116, 7, 2, 15, 0, 4, 16, '2024-09-18 00:53:02', NULL),
	(117, 11, 2, 22, 2, 5, 16, '2024-05-27 07:05:04', NULL),
	(118, 6, 3, 22, 2, 6, 16, '2024-03-03 01:20:22', NULL),
	(119, 3, 2, 16, 0, 0, 16, '2024-09-13 08:37:54', NULL),
	(120, 12, 3, 23, 2, 1, 17, '2024-12-02 19:47:44', NULL),
	(121, 4, 1, 14, 2, 2, 17, '2024-06-15 02:31:33', NULL),
	(122, 7, 1, 24, 3, 3, 17, '2024-10-04 18:55:57', NULL),
	(123, 11, 1, 13, 0, 4, 17, '2024-09-13 19:03:08', NULL),
	(124, 8, 1, 20, 3, 5, 17, '2024-07-15 07:22:15', NULL),
	(125, 11, 2, 20, 3, 6, 17, '2025-02-13 03:48:32', NULL),
	(126, 1, 0, 14, 2, 0, 17, '2024-04-17 08:55:45', NULL),
	(127, 2, 2, 17, 1, 1, 18, '2024-03-07 20:59:12', NULL),
	(128, 1, 1, 17, 0, 2, 18, '2024-06-30 21:56:54', NULL),
	(129, 8, 3, 13, 1, 3, 18, '2024-07-25 05:52:04', NULL),
	(130, 3, 2, 15, 0, 4, 18, '2024-07-02 11:46:16', NULL),
	(131, 2, 0, 12, 3, 5, 18, '2025-01-04 11:25:37', NULL),
	(132, 2, 0, 24, 0, 6, 18, '2024-07-01 07:50:35', NULL),
	(133, 8, 1, 24, 0, 0, 18, '2024-07-19 00:25:25', NULL),
	(134, 4, 2, 12, 1, 1, 19, '2024-09-17 22:23:39', NULL),
	(135, 3, 1, 12, 2, 2, 19, '2024-08-16 12:50:56', NULL),
	(136, 2, 0, 14, 2, 3, 19, '2024-10-20 07:35:35', NULL),
	(137, 8, 0, 21, 2, 4, 19, '2024-03-09 16:43:15', NULL),
	(138, 2, 1, 12, 0, 5, 19, '2024-07-18 03:32:08', NULL),
	(139, 12, 1, 15, 0, 6, 19, '2025-01-29 09:55:00', NULL),
	(140, 3, 1, 18, 0, 0, 19, '2024-08-23 23:06:55', NULL),
	(141, 10, 0, 13, 2, 1, 20, '2024-03-04 00:34:44', NULL),
	(142, 2, 0, 19, 1, 2, 20, '2024-07-15 02:49:17', NULL),
	(143, 9, 3, 21, 0, 3, 20, '2024-05-24 03:37:43', NULL),
	(144, 8, 0, 18, 2, 4, 20, '2024-03-16 21:08:52', NULL),
	(145, 12, 0, 22, 3, 5, 20, '2024-04-28 08:39:06', NULL),
	(146, 7, 1, 19, 0, 6, 20, '2024-09-08 02:09:17', NULL),
	(147, 6, 0, 24, 1, 0, 20, '2024-06-24 15:43:34', NULL),
	(148, 1, 1, 18, 0, 1, 21, '2024-09-15 14:02:12', NULL),
	(149, 8, 2, 14, 1, 2, 21, '2024-08-20 09:50:55', NULL),
	(150, 12, 0, 24, 1, 3, 21, '2024-08-14 10:05:35', NULL),
	(151, 11, 0, 23, 0, 4, 21, '2024-06-23 05:04:28', NULL),
	(152, 3, 0, 14, 2, 5, 21, '2024-10-29 20:22:53', NULL),
	(153, 8, 0, 15, 3, 6, 21, '2024-10-22 15:41:05', NULL),
	(154, 6, 2, 23, 3, 0, 21, '2024-08-15 03:25:03', NULL),
	(155, 9, 3, 18, 2, 1, 22, '2024-08-26 21:17:20', NULL),
	(156, 5, 0, 23, 1, 2, 22, '2024-06-01 04:36:56', NULL),
	(157, 7, 3, 12, 0, 3, 22, '2024-04-08 19:28:29', NULL),
	(158, 4, 0, 17, 0, 4, 22, '2024-04-08 02:04:42', NULL),
	(159, 4, 2, 13, 1, 5, 22, '2025-01-23 10:58:33', NULL),
	(160, 8, 1, 23, 3, 6, 22, '2024-07-03 11:07:44', NULL),
	(161, 9, 1, 16, 2, 0, 22, '2024-07-28 04:10:14', NULL),
	(162, 2, 1, 13, 3, 1, 23, '2024-10-17 10:13:16', NULL),
	(163, 12, 0, 23, 3, 2, 23, '2024-07-10 13:34:10', NULL),
	(164, 1, 3, 19, 1, 3, 23, '2024-10-07 14:09:35', NULL),
	(165, 10, 1, 24, 3, 4, 23, '2024-03-03 09:25:50', NULL),
	(166, 10, 3, 22, 1, 5, 23, '2024-10-08 00:45:47', NULL),
	(167, 9, 0, 23, 1, 6, 23, '2024-03-19 00:44:10', NULL),
	(168, 12, 2, 22, 1, 0, 23, '2024-04-23 01:03:41', NULL),
	(169, 7, 3, 19, 1, 1, 24, '2025-02-18 01:28:30', NULL),
	(170, 6, 3, 22, 2, 2, 24, '2024-09-25 01:57:13', NULL),
	(171, 7, 3, 14, 3, 3, 24, '2024-11-04 05:55:51', NULL),
	(172, 3, 3, 20, 2, 4, 24, '2024-06-17 18:24:15', NULL),
	(173, 1, 3, 19, 2, 5, 24, '2024-06-24 08:24:17', NULL),
	(174, 3, 0, 22, 0, 6, 24, '2024-10-22 01:57:41', NULL),
	(175, 11, 0, 20, 0, 0, 24, '2024-04-20 08:25:02', NULL),
	(176, 5, 2, 16, 3, 1, 25, '2024-10-30 03:57:38', NULL),
	(177, 11, 0, 24, 1, 2, 25, '2024-11-14 14:04:58', NULL),
	(178, 11, 1, 14, 2, 3, 25, '2024-04-03 18:21:19', NULL),
	(179, 3, 1, 15, 2, 4, 25, '2024-09-10 05:20:58', NULL),
	(180, 8, 3, 16, 3, 5, 25, '2024-06-07 23:40:02', NULL),
	(181, 7, 0, 18, 1, 6, 25, '2024-11-07 02:42:33', NULL),
	(182, 8, 2, 19, 3, 0, 25, '2024-12-19 02:20:38', NULL),
	(183, 10, 3, 20, 0, 1, 26, '2024-07-09 07:27:04', NULL),
	(184, 1, 2, 24, 2, 2, 26, '2025-01-18 03:33:46', NULL),
	(185, 11, 2, 12, 1, 3, 26, '2025-02-09 08:35:12', NULL),
	(186, 8, 3, 22, 1, 4, 26, '2024-08-30 21:47:10', NULL),
	(187, 5, 0, 19, 0, 5, 26, '2024-11-02 11:15:39', NULL),
	(188, 10, 2, 16, 1, 6, 26, '2024-07-10 23:49:35', NULL),
	(189, 10, 2, 19, 3, 0, 26, '2024-10-27 13:08:10', NULL),
	(190, 3, 3, 24, 0, 1, 27, '2024-09-10 00:46:06', NULL),
	(191, 4, 2, 23, 1, 2, 27, '2024-09-18 20:35:26', NULL),
	(192, 7, 1, 18, 2, 3, 27, '2024-04-16 10:04:45', NULL),
	(193, 9, 0, 24, 1, 4, 27, '2024-11-24 01:08:28', NULL),
	(194, 5, 1, 13, 2, 5, 27, '2024-04-28 09:40:36', NULL),
	(195, 12, 3, 14, 2, 6, 27, '2024-09-01 08:46:52', NULL),
	(196, 10, 0, 23, 0, 0, 27, '2024-07-25 23:17:59', NULL),
	(197, 4, 0, 17, 0, 1, 28, '2024-10-21 15:52:06', NULL),
	(198, 1, 0, 13, 3, 2, 28, '2024-04-24 15:23:50', NULL),
	(199, 2, 2, 18, 1, 3, 28, '2024-10-03 02:21:41', NULL),
	(200, 3, 3, 16, 3, 4, 28, '2024-07-08 17:55:23', NULL),
	(201, 4, 0, 12, 2, 5, 28, '2024-11-15 06:10:44', NULL),
	(202, 7, 0, 21, 2, 6, 28, '2024-05-25 03:04:02', NULL),
	(203, 6, 1, 24, 3, 0, 28, '2024-10-30 19:01:14', NULL),
	(204, 8, 2, 12, 3, 1, 29, '2024-04-08 14:31:31', NULL),
	(205, 4, 0, 23, 1, 2, 29, '2024-12-20 22:38:17', NULL),
	(206, 4, 1, 14, 2, 3, 29, '2024-07-03 09:58:11', NULL),
	(207, 12, 0, 20, 3, 4, 29, '2024-11-07 18:29:00', NULL),
	(208, 2, 2, 20, 1, 5, 29, '2024-05-21 20:26:52', NULL),
	(209, 2, 1, 17, 3, 6, 29, '2024-03-26 04:27:01', NULL),
	(210, 7, 1, 18, 1, 0, 29, '2025-01-07 13:28:39', NULL),
	(211, 9, 0, 20, 1, 1, 30, '2024-06-23 00:24:12', NULL),
	(212, 3, 3, 19, 2, 2, 30, '2025-01-23 22:41:15', NULL),
	(213, 12, 2, 24, 4, 3, 30, '2024-12-10 05:14:27', NULL),
	(214, 5, 0, 15, 3, 4, 30, '2025-01-09 17:57:06', NULL),
	(215, 10, 3, 14, 3, 5, 30, '2024-05-24 22:33:16', NULL),
	(216, 12, 2, 23, 4, 6, 30, '2024-10-13 02:56:10', NULL),
	(217, 12, 0, 17, 2, 0, 30, '2024-12-30 05:35:54', NULL),
	(218, 7, 2, 23, 3, 1, 31, '2024-09-03 06:35:30', NULL),
	(219, 5, 0, 15, 1, 2, 31, '2025-02-18 23:46:44', NULL),
	(220, 7, 2, 17, 1, 3, 31, '2024-05-18 23:45:02', NULL),
	(221, 7, 0, 19, 3, 4, 31, '2025-01-25 05:12:43', NULL),
	(222, 8, 3, 15, 1, 5, 31, '2024-08-21 15:49:28', NULL),
	(223, 5, 3, 23, 1, 6, 31, '2025-02-09 21:56:20', NULL),
	(224, 4, 2, 18, 0, 0, 31, '2024-10-09 16:31:47', NULL),
	(225, 2, 0, 20, 3, 1, 32, '2025-01-30 11:20:22', NULL),
	(226, 7, 2, 16, 0, 2, 32, '2024-12-14 15:01:32', NULL),
	(227, 11, 0, 12, 0, 3, 32, '2024-10-02 17:15:41', NULL),
	(228, 12, 0, 12, 2, 4, 32, '2024-09-23 12:38:59', NULL),
	(229, 4, 1, 13, 1, 5, 32, '2024-12-21 01:28:18', NULL),
	(230, 3, 1, 24, 2, 6, 32, '2024-11-15 15:33:39', NULL),
	(231, 5, 0, 15, 1, 0, 32, '2024-12-11 11:11:57', NULL),
	(232, 2, 3, 17, 3, 1, 33, '2024-07-26 20:38:09', NULL),
	(233, 10, 0, 14, 2, 2, 33, '2024-06-06 04:19:25', NULL),
	(234, 12, 2, 20, 2, 3, 33, '2024-07-29 06:44:09', NULL),
	(235, 3, 1, 12, 2, 4, 33, '2024-10-19 23:45:30', NULL),
	(236, 6, 2, 21, 2, 5, 33, '2025-01-20 02:16:05', NULL),
	(237, 4, 3, 16, 3, 6, 33, '2024-06-02 13:49:24', NULL),
	(238, 3, 2, 24, 2, 0, 33, '2024-09-11 02:44:09', NULL),
	(239, 7, 2, 23, 3, 1, 34, '2024-08-28 00:45:24', NULL),
	(240, 4, 0, 24, 3, 2, 34, '2024-03-19 08:59:09', NULL),
	(241, 12, 3, 17, 3, 3, 34, '2024-09-25 17:32:35', NULL),
	(242, 1, 0, 12, 0, 4, 34, '2024-08-07 03:18:02', NULL),
	(243, 11, 2, 16, 3, 5, 34, '2024-03-20 04:27:35', NULL),
	(244, 9, 3, 20, 0, 6, 34, '2025-02-22 04:26:07', NULL),
	(245, 3, 1, 20, 3, 0, 34, '2024-05-30 15:02:02', NULL),
	(246, 9, 0, 21, 2, 1, 35, '2024-12-11 07:53:44', NULL),
	(247, 4, 0, 16, 1, 2, 35, '2024-04-17 14:39:23', NULL),
	(248, 3, 3, 21, 3, 3, 35, '2025-02-16 02:05:10', NULL),
	(249, 10, 2, 23, 0, 4, 35, '2024-06-14 07:24:02', NULL),
	(250, 4, 3, 16, 2, 5, 35, '2024-03-12 21:01:12', NULL),
	(251, 11, 2, 18, 3, 6, 35, '2024-07-14 11:44:28', NULL),
	(252, 1, 2, 23, 1, 0, 35, '2024-10-25 06:22:07', NULL),
	(253, 4, 0, 21, 1, 1, 36, '2024-04-11 07:09:52', NULL),
	(254, 11, 0, 17, 3, 2, 36, '2024-05-02 10:31:19', NULL),
	(255, 7, 3, 24, 2, 3, 36, '2025-01-11 18:45:12', NULL),
	(256, 3, 1, 19, 2, 4, 36, '2024-10-11 08:23:51', NULL),
	(257, 4, 2, 14, 0, 5, 36, '2024-10-27 17:39:51', NULL),
	(258, 11, 2, 17, 1, 6, 36, '2024-11-16 11:30:38', NULL),
	(259, 4, 0, 23, 2, 0, 36, '2025-01-11 22:54:48', NULL),
	(260, 10, 0, 14, 1, 1, 37, '2024-06-21 17:36:59', NULL),
	(261, 6, 1, 20, 1, 2, 37, '2024-11-21 17:41:21', NULL),
	(262, 1, 0, 19, 1, 3, 37, '2024-03-14 23:27:36', NULL),
	(263, 4, 0, 13, 0, 4, 37, '2025-02-18 21:03:36', NULL),
	(264, 6, 2, 24, 2, 5, 37, '2024-04-17 10:59:38', NULL),
	(265, 11, 0, 14, 1, 6, 37, '2024-10-27 08:43:28', NULL),
	(266, 6, 0, 24, 3, 0, 37, '2025-01-18 04:46:44', NULL),
	(267, 3, 3, 23, 3, 1, 38, '2024-06-06 17:57:00', NULL),
	(268, 7, 2, 12, 3, 2, 38, '2024-03-16 23:25:05', NULL),
	(269, 7, 1, 15, 1, 3, 38, '2024-04-14 22:17:20', NULL),
	(270, 6, 0, 23, 3, 4, 38, '2024-11-05 04:31:51', NULL),
	(271, 12, 0, 16, 2, 5, 38, '2024-02-29 21:39:16', NULL),
	(272, 10, 1, 24, 0, 6, 38, '2024-08-08 01:20:46', NULL),
	(273, 7, 2, 13, 2, 0, 38, '2025-01-21 09:12:18', NULL),
	(274, 6, 1, 15, 0, 1, 39, '2025-02-20 11:37:46', NULL),
	(275, 11, 2, 21, 1, 2, 39, '2024-06-24 09:02:42', NULL),
	(276, 4, 0, 15, 3, 3, 39, '2024-04-21 14:16:13', NULL),
	(277, 6, 3, 12, 1, 4, 39, '2024-09-28 07:59:03', NULL),
	(278, 5, 3, 21, 1, 5, 39, '2025-01-22 03:01:18', NULL),
	(279, 12, 3, 24, 1, 6, 39, '2024-10-16 22:23:45', NULL),
	(280, 6, 2, 24, 1, 0, 39, '2024-03-06 03:45:03', NULL),
	(281, 3, 0, 15, 3, 1, 40, '2024-06-30 00:33:22', NULL),
	(282, 9, 0, 18, 0, 2, 40, '2025-01-09 10:42:17', NULL),
	(283, 2, 1, 14, 3, 3, 40, '2024-04-03 08:03:35', NULL),
	(284, 3, 0, 15, 1, 4, 40, '2024-12-12 06:40:22', NULL),
	(285, 11, 0, 15, 3, 5, 40, '2024-06-01 02:44:56', NULL),
	(286, 8, 2, 17, 2, 6, 40, '2024-04-28 12:14:57', NULL),
	(287, 10, 0, 15, 0, 0, 40, '2024-02-24 12:59:24', NULL),
	(288, 7, 1, 22, 0, 1, 41, '2024-07-16 04:11:33', NULL),
	(289, 3, 0, 14, 2, 2, 41, '2024-07-24 15:41:07', NULL),
	(290, 7, 3, 21, 0, 3, 41, '2025-01-15 22:05:14', NULL),
	(291, 10, 0, 12, 0, 4, 41, '2024-09-13 04:23:23', NULL),
	(292, 11, 0, 14, 2, 5, 41, '2024-10-29 16:44:39', NULL),
	(293, 8, 0, 17, 3, 6, 41, '2024-08-15 11:26:31', NULL),
	(294, 7, 1, 24, 0, 0, 41, '2024-05-19 02:32:08', NULL),
	(295, 12, 3, 20, 2, 1, 42, '2024-08-15 20:44:29', NULL),
	(296, 8, 3, 19, 2, 2, 42, '2024-11-27 12:28:28', NULL),
	(297, 6, 3, 24, 2, 3, 42, '2024-04-25 05:45:11', NULL),
	(298, 6, 2, 22, 1, 4, 42, '2024-03-04 14:08:37', NULL),
	(299, 11, 0, 24, 1, 5, 42, '2024-05-19 22:05:31', NULL),
	(300, 10, 0, 13, 3, 6, 42, '2024-06-02 02:39:45', NULL),
	(301, 11, 1, 20, 0, 0, 42, '2024-03-16 04:33:38', NULL),
	(302, 11, 1, 14, 2, 1, 43, '2025-02-07 07:24:15', NULL),
	(303, 8, 1, 23, 1, 2, 43, '2025-01-01 06:02:38', NULL),
	(304, 2, 2, 24, 0, 3, 43, '2025-01-24 22:43:03', NULL),
	(305, 10, 1, 17, 0, 4, 43, '2024-11-23 06:02:31', NULL),
	(306, 7, 1, 12, 3, 5, 43, '2024-06-04 06:55:09', NULL),
	(307, 2, 1, 23, 0, 6, 43, '2024-05-20 17:43:17', NULL),
	(308, 4, 2, 14, 3, 0, 43, '2024-09-17 04:31:11', NULL),
	(309, 4, 2, 17, 0, 1, 44, '2024-08-21 13:41:01', NULL),
	(310, 5, 1, 12, 3, 2, 44, '2025-02-19 10:13:43', NULL),
	(311, 7, 3, 20, 0, 3, 44, '2024-08-10 02:59:08', NULL),
	(312, 5, 3, 18, 0, 4, 44, '2024-08-28 14:47:01', NULL),
	(313, 1, 2, 12, 0, 5, 44, '2024-07-15 04:03:47', NULL),
	(314, 3, 0, 21, 2, 6, 44, '2024-08-27 09:58:21', NULL),
	(315, 7, 0, 15, 2, 0, 44, '2024-10-19 18:39:12', NULL),
	(316, 4, 3, 15, 2, 1, 45, '2024-11-12 17:27:30', NULL),
	(317, 6, 0, 23, 1, 2, 45, '2024-06-09 23:00:12', NULL),
	(318, 10, 1, 20, 2, 3, 45, '2024-07-10 15:33:41', NULL),
	(319, 6, 2, 15, 0, 4, 45, '2024-03-05 22:55:06', NULL),
	(320, 3, 2, 16, 0, 5, 45, '2024-07-22 23:20:47', NULL),
	(321, 7, 1, 19, 0, 6, 45, '2025-02-10 03:32:11', NULL),
	(322, 5, 3, 12, 0, 0, 45, '2024-05-10 21:35:56', NULL),
	(323, 1, 0, 15, 3, 1, 46, '2024-10-18 18:09:25', NULL),
	(324, 4, 1, 12, 3, 2, 46, '2024-10-09 14:08:34', NULL),
	(325, 9, 1, 12, 3, 3, 46, '2025-02-15 11:16:17', NULL),
	(326, 2, 1, 16, 1, 4, 46, '2024-09-10 02:52:07', NULL),
	(327, 1, 2, 12, 0, 5, 46, '2024-12-18 00:10:12', NULL),
	(328, 11, 0, 19, 2, 6, 46, '2024-11-18 17:04:18', NULL),
	(329, 4, 2, 17, 0, 0, 46, '2025-01-28 23:21:58', NULL),
	(330, 10, 3, 19, 0, 1, 47, '2024-07-03 05:58:51', NULL),
	(331, 3, 0, 24, 1, 2, 47, '2024-05-25 06:15:31', NULL),
	(332, 2, 2, 14, 2, 3, 47, '2024-08-02 17:53:54', NULL),
	(333, 10, 0, 17, 1, 4, 47, '2024-11-08 14:59:19', NULL),
	(334, 11, 1, 19, 3, 5, 47, '2024-09-08 19:17:20', NULL),
	(335, 6, 0, 20, 2, 6, 47, '2025-02-22 03:42:43', NULL),
	(336, 2, 0, 23, 2, 0, 47, '2024-11-06 14:54:05', NULL),
	(337, 8, 3, 14, 3, 1, 48, '2024-12-13 07:57:53', NULL),
	(338, 8, 3, 24, 0, 2, 48, '2025-01-19 23:01:51', NULL),
	(339, 7, 2, 21, 1, 3, 48, '2024-08-23 13:40:58', NULL),
	(340, 2, 0, 24, 0, 4, 48, '2024-07-12 12:03:53', NULL),
	(341, 5, 0, 23, 3, 5, 48, '2024-07-30 14:08:03', NULL),
	(342, 6, 0, 13, 1, 6, 48, '2024-05-26 05:39:58', NULL),
	(343, 4, 2, 23, 1, 0, 48, '2024-11-27 21:35:07', NULL),
	(344, 10, 1, 20, 1, 1, 49, '2025-02-15 16:00:53', NULL),
	(345, 5, 1, 15, 3, 2, 49, '2024-08-20 08:15:29', NULL),
	(346, 2, 0, 24, 3, 3, 49, '2025-01-28 04:43:54', NULL),
	(347, 4, 2, 22, 2, 4, 49, '2025-01-27 00:02:16', NULL),
	(348, 4, 3, 20, 3, 5, 49, '2024-11-08 09:43:03', NULL),
	(349, 2, 3, 17, 3, 6, 49, '2024-03-26 03:17:13', NULL),
	(350, 6, 3, 19, 2, 0, 49, '2024-08-07 22:04:03', NULL),
	(351, 11, 0, 21, 0, 1, 50, '2024-11-01 20:51:22', NULL),
	(352, 1, 1, 12, 3, 2, 50, '2024-07-03 08:23:50', NULL),
	(353, 7, 1, 21, 0, 3, 50, '2025-02-01 19:09:50', NULL),
	(354, 5, 0, 17, 3, 4, 50, '2024-12-21 09:14:41', NULL),
	(355, 6, 3, 18, 0, 5, 50, '2024-08-05 19:44:35', NULL),
	(356, 3, 2, 19, 3, 6, 50, '2024-08-17 11:54:14', NULL),
	(357, 11, 0, 22, 0, 0, 50, '2024-10-09 20:39:46', NULL),
	(358, 8, 0, 11, 0, 2, 62, '2025-02-24 13:07:06', NULL),
	(359, 13, 0, 18, 0, 2, 62, '2025-02-24 13:07:58', NULL),
	(360, 8, 0, 11, 0, 7, 63, '2025-02-24 13:07:58', NULL),
	(361, 12, 0, 18, 0, 1, 63, '2025-02-24 13:07:58', NULL),
	(362, 8, 0, 11, 0, 2, 64, '2025-02-24 13:07:06', NULL),
	(363, 12, 0, 18, 0, 2, 64, '2025-02-24 13:07:58', NULL),
	(364, 8, 0, 11, 0, 7, 65, '2025-02-24 13:07:58', NULL),
	(365, 12, 0, 18, 0, 1, 65, '2025-02-24 13:07:58', NULL),
	(366, 8, 0, 11, 0, 2, 66, '2025-02-24 13:07:06', NULL),
	(367, 12, 0, 18, 0, 2, 66, '2025-02-24 13:07:58', NULL),
	(368, 8, 0, 11, 0, 7, 67, '2025-02-24 13:07:58', NULL),
	(369, 12, 0, 18, 0, 1, 67, '2025-02-24 13:07:58', NULL),
	(370, 11, 0, 18, 0, 2, 70, '2025-02-24 13:07:58', NULL),
	(371, 8, 0, 11, 0, 1, 69, '2025-02-24 13:07:58', NULL),
	(372, 12, 0, 18, 0, 1, 69, '2025-02-24 13:07:58', NULL),
	(373, 8, 0, 11, 0, 1, 70, '2025-02-24 13:07:58', NULL),
	(374, 11, 0, 21, 0, 1, 64, '2024-11-01 20:51:22', NULL),
	(375, 1, 1, 12, 3, 2, 64, '2024-07-03 08:23:50', NULL),
	(376, 7, 1, 21, 0, 3, 64, '2025-02-01 19:09:50', NULL),
	(377, 5, 0, 17, 3, 4, 64, '2024-12-21 09:14:41', NULL),
	(378, 6, 3, 18, 0, 5, 64, '2024-08-05 19:44:35', NULL),
	(379, 3, 2, 19, 3, 6, 64, '2024-08-17 11:54:14', NULL),
	(380, 11, 0, 22, 0, 0, 64, '2024-10-09 20:39:46', NULL);

-- Listage de la structure de table watt_else_db. obj_periode_de_fermeture_borne
DROP TABLE IF EXISTS `obj_periode_de_fermeture_borne`;
CREATE TABLE IF NOT EXISTS `obj_periode_de_fermeture_borne` (
  `id_periode_fermeture` bigint(20) NOT NULL AUTO_INCREMENT,
  `Id_Station_de_recharge` bigint(20) NOT NULL,
  `date_de_debut` datetime NOT NULL,
  `date_de_fin` datetime DEFAULT NULL,
  PRIMARY KEY (`id_periode_fermeture`),
  KEY `FK_periode_de_fermeture_station_de_recharge` (`Id_Station_de_recharge`),
  CONSTRAINT `FK_periode_de_fermeture_station_de_recharge` FOREIGN KEY (`Id_Station_de_recharge`) REFERENCES `obj_borne` (`Id_Station_de_recharge`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.obj_periode_de_fermeture_borne : ~0 rows (environ)

-- Listage de la structure de table watt_else_db. obj_reservation
DROP TABLE IF EXISTS `obj_reservation`;
CREATE TABLE IF NOT EXISTS `obj_reservation` (
  `Id_reservation` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_vehicule` bigint(20) NOT NULL,
  `id_borne` bigint(20) NOT NULL,
  `date_reservation` date NOT NULL,
  `id_heure_debut_reservation` bigint(20) NOT NULL,
  `id_minute_debut_reservation` bigint(20) NOT NULL,
  `id_heure_fin_reservation` bigint(20) NOT NULL,
  `id_minute_fin_reservation` bigint(20) NOT NULL,
  `id_facturation` bigint(20) NOT NULL,
  `Date_enreg_resa` datetime NOT NULL,
  `id_Carte` bigint(20) DEFAULT NULL,
  `Quantite_energie_consomee` float DEFAULT NULL,
  `Cout_recharge` decimal(19,4) DEFAULT NULL,
  `Id_Evaluation` bigint(20) DEFAULT NULL,
  `Date_debut_recharge` datetime DEFAULT NULL,
  `Date_fin_recharge` datetime DEFAULT NULL,
  `date_de_validation_paie` datetime DEFAULT NULL,
  `date_de_fin_ano` datetime DEFAULT NULL,
  `Id_Fin_anormale` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_reservation`) USING BTREE,
  KEY `FK_transaction_reservation_motif_fin_anormale` (`Id_Fin_anormale`),
  KEY `FK_transaction_reservation_cartebancaire` (`id_Carte`),
  KEY `FK_transaction_reservation_commentaire_conducteur` (`Id_Evaluation`),
  KEY `FK_transaction_reservation_reglement_periodique` (`id_facturation`),
  KEY `FK_transacrion_borne` (`id_borne`),
  KEY `FK_transaction_vehicule` (`id_vehicule`),
  KEY `FK_transaction_reservation_gamme__heure` (`id_heure_debut_reservation`) USING BTREE,
  KEY `FK_transaction_reservation_gamme_minute` (`id_minute_debut_reservation`) USING BTREE,
  KEY `FK_transaction_reservation_gamme__heure_2` (`id_heure_fin_reservation`) USING BTREE,
  KEY `FK_transaction_reservation_gamme_minute_2` (`id_minute_fin_reservation`) USING BTREE,
  CONSTRAINT `FK_obj_reservation_gamme_heure_reservation` FOREIGN KEY (`id_heure_debut_reservation`) REFERENCES `gamme_heure_reservation` (`id_he`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_obj_reservation_gamme_heure_reservation_2` FOREIGN KEY (`id_heure_fin_reservation`) REFERENCES `gamme_heure_reservation` (`id_he`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_obj_reservation_gamme_minutes_reservation` FOREIGN KEY (`id_minute_debut_reservation`) REFERENCES `gamme_minutes_reservation` (`id_min`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_obj_reservation_gamme_minutes_reservation_2` FOREIGN KEY (`id_minute_fin_reservation`) REFERENCES `gamme_minutes_reservation` (`id_min`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_transacrion_borne` FOREIGN KEY (`id_borne`) REFERENCES `obj_borne` (`Id_Station_de_recharge`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_transaction_reservation_cartebancaire` FOREIGN KEY (`id_Carte`) REFERENCES `obj_carte_bancaire` (`id_Carte`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_transaction_reservation_commentaire_conducteur` FOREIGN KEY (`Id_Evaluation`) REFERENCES `obj_evaluation` (`Id_Evaluation`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_transaction_reservation_motif_fin_anormale` FOREIGN KEY (`Id_Fin_anormale`) REFERENCES `gamme_motif_fin_anormale_reservation` (`Id_Fin_anormale`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_transaction_reservation_reglement_periodique` FOREIGN KEY (`id_facturation`) REFERENCES `obj_facturation` (`id_facturation`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_transaction_vehicule` FOREIGN KEY (`id_vehicule`) REFERENCES `obj_vehicule` (`Id_Vehicule`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.obj_reservation : ~21 rows (environ)
REPLACE INTO `obj_reservation` (`Id_reservation`, `id_vehicule`, `id_borne`, `date_reservation`, `id_heure_debut_reservation`, `id_minute_debut_reservation`, `id_heure_fin_reservation`, `id_minute_fin_reservation`, `id_facturation`, `Date_enreg_resa`, `id_Carte`, `Quantite_energie_consomee`, `Cout_recharge`, `Id_Evaluation`, `Date_debut_recharge`, `Date_fin_recharge`, `date_de_validation_paie`, `date_de_fin_ano`, `Id_Fin_anormale`) VALUES
	(1, 1, 8, '2025-02-19', 2, 3, 3, 2, 1, '2025-02-19 11:43:03', NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, NULL),
	(2, 1, 21, '2025-02-15', 8, 1, 9, 2, 1, '2025-02-15 08:00:00', 13, 10, 5.4500, 4, '2025-02-15 08:15:00', '2025-02-15 09:00:00', NULL, NULL, NULL),
	(3, 2, 22, '2025-02-15', 9, 0, 10, 1, 2, '2025-02-15 08:15:00', 13, 15, 6.5000, 3, '2025-02-15 08:30:00', '2025-02-15 09:45:00', NULL, NULL, NULL),
	(4, 3, 23, '2025-02-15', 10, 2, 11, 0, 1, '2025-02-15 09:00:00', 1, 12, 7.2000, 5, '2025-02-15 09:30:00', '2025-02-15 10:30:00', NULL, NULL, NULL),
	(5, 4, 24, '2025-02-15', 11, 3, 12, 0, 1, '2025-02-15 09:30:00', 1, 18, 8.0000, 4, '2025-02-15 10:00:00', '2025-02-15 11:00:00', NULL, NULL, NULL),
	(6, 5, 25, '2025-02-15', 12, 0, 13, 3, 2, '2025-02-15 10:00:00', 1, 8, 4.7500, 3, '2025-02-15 11:00:00', '2025-02-15 12:00:00', NULL, NULL, NULL),
	(7, 6, 26, '2025-02-15', 13, 1, 14, 2, 2, '2025-02-15 11:00:00', 1, 10, 5.9500, 5, '2025-02-15 12:30:00', '2025-02-16 13:30:00', NULL, NULL, NULL),
	(8, 7, 27, '2025-02-15', 14, 0, 15, 3, 1, '2025-02-15 12:00:00', 1, 20, 9.3000, 4, '2025-02-15 13:00:00', NULL, NULL, NULL, NULL),
	(9, 8, 28, '2025-02-15', 15, 0, 16, 3, 1, '2025-02-15 13:00:00', 1, 25, 10.5000, 3, '2025-02-15 14:00:00', NULL, NULL, NULL, NULL),
	(10, 9, 29, '2025-02-15', 16, 0, 17, 3, 2, '2025-02-15 14:00:00', 1, 30, 11.0000, 5, '2025-02-15 15:00:00', '2025-02-15 16:00:00', NULL, NULL, NULL),
	(11, 10, 10, '2025-02-15', 17, 0, 18, 0, 1, '2025-02-15 15:00:00', 1, 18, 9.7500, 4, '2025-02-15 16:00:00', '2025-02-15 17:00:00', NULL, NULL, NULL),
	(12, 11, 11, '2025-02-16', 8, 3, 9, 3, 2, '2025-02-16 08:00:00', 1, 14, 7.0000, 3, '2025-02-16 08:15:00', '2025-02-16 09:00:00', NULL, NULL, NULL),
	(13, 3, 12, '2025-02-16', 9, 3, 10, 1, 1, '2025-02-16 08:30:00', 1, 22, 8.1000, 5, '2025-02-16 09:00:00', '2025-02-16 10:00:00', NULL, NULL, NULL),
	(14, 3, 13, '2025-02-16', 10, 0, 11, 3, 2, '2025-02-16 09:00:00', 1, 16, 7.8500, 4, '2025-02-16 10:00:00', NULL, NULL, NULL, NULL),
	(15, 6, 14, '2025-02-16', 11, 1, 12, 3, 1, '2025-02-16 10:30:00', 1, 24, 9.1500, 3, '2025-02-16 11:00:00', '2025-02-16 12:00:00', NULL, NULL, NULL),
	(16, 6, 15, '2025-02-16', 12, 3, 13, 1, 2, '2025-02-25 11:00:00', 1, 12, 8.2000, 5, '2025-02-16 12:00:00', '2025-02-16 13:00:00', NULL, NULL, NULL),
	(17, 2, 16, '2025-02-16', 13, 1, 14, 1, 1, '2025-02-27 12:30:00', 1, 14, 7.5000, 4, '2025-02-16 13:00:00', '2025-02-16 14:00:00', NULL, NULL, NULL),
	(18, 6, 17, '2025-02-16', 14, 0, 15, 1, 2, '2025-02-26 13:00:00', 1, 30, 10.0000, 3, '2025-02-16 14:00:00', '2025-02-16 15:00:00', NULL, NULL, NULL),
	(19, 6, 18, '2025-02-16', 15, 1, 16, 3, 1, '2025-02-25 14:30:00', 1, 22, 9.3000, 5, '2025-02-16 15:00:00', '2025-02-16 16:00:00', NULL, NULL, NULL),
	(20, 6, 19, '2025-02-16', 16, 2, 17, 3, 2, '2025-02-27 15:30:00', 1, 14, 8.5000, 4, '2025-02-16 16:00:00', '2025-02-16 17:00:00', NULL, NULL, NULL),
	(21, 7, 20, '2025-02-16', 17, 2, 18, 0, 1, '2025-02-28 16:30:00', 1, 18, 10.2000, 4, '2025-02-16 17:00:00', NULL, NULL, NULL, NULL),
	(22, 13, 30, '2025-02-19', 8, 0, 9, 0, 3, '2025-02-23 00:00:00', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL),
	(23, 14, 16, '2025-02-19', 11, 0, 11, 3, 4, '2025-02-24 00:00:00', 1, 0.0284472, 0.2489, 4, '2025-02-24 09:48:42', '2025-02-24 09:48:47', NULL, NULL, NULL),
	(24, 14, 30, '2025-02-19', 11, 0, 11, 3, 4, '2025-02-24 00:00:00', 1, 0, 0.0000, 4, NULL, NULL, NULL, NULL, NULL),
	(25, 16, 7, '2025-02-19', 9, 0, 9, 3, 5, '2025-02-24 00:00:00', 1, 0.0435844, 0.4790, 4, '2025-02-24 10:20:43', '2025-02-24 10:20:50', NULL, NULL, NULL),
	(26, 17, 62, '2025-02-19', 8, 0, 8, 3, 6, '2025-02-24 00:00:00', NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, NULL),
	(27, 17, 62, '2025-02-19', 8, 0, 8, 3, 6, '2025-02-24 00:00:00', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL),
	(28, 17, 63, '2025-02-19', 8, 0, 8, 3, 6, '2025-02-24 00:00:00', NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, NULL),
	(29, 17, 63, '2025-02-19', 8, 0, 8, 3, 6, '2025-02-24 00:00:00', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL),
	(30, 17, 64, '2025-02-19', 8, 0, 8, 3, 6, '2025-02-24 00:00:00', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL),
	(31, 17, 64, '2025-02-19', 8, 0, 8, 3, 6, '2025-02-24 00:00:00', NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL),
	(32, 17, 65, '2025-02-19', 8, 0, 8, 3, 6, '2025-02-24 00:00:00', NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, NULL),
	(33, 17, 65, '2025-02-19', 8, 0, 8, 3, 6, '2025-02-24 00:00:00', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL),
	(34, 17, 66, '2025-02-19', 8, 0, 8, 3, 6, '2025-02-24 00:00:00', NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL),
	(35, 17, 66, '2025-02-19', 8, 0, 8, 3, 6, '2025-02-24 00:00:00', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL),
	(36, 17, 67, '2025-02-19', 8, 0, 8, 3, 6, '2025-02-24 00:00:00', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL),
	(37, 17, 67, '2025-02-19', 8, 0, 8, 3, 6, '2025-02-24 00:00:00', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL),
	(38, 17, 69, '2025-02-19', 8, 0, 8, 3, 6, '2025-02-24 00:00:00', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL),
	(39, 17, 69, '2025-02-19', 8, 0, 8, 3, 6, '2025-02-24 00:00:00', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL),
	(40, 17, 70, '2025-02-19', 8, 0, 8, 3, 6, '2025-02-24 00:00:00', NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL),
	(41, 17, 70, '2025-02-19', 8, 0, 8, 3, 6, '2025-02-24 00:00:00', NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL),
	(42, 17, 66, '2025-03-01', 15, 0, 16, 0, 6, '2025-02-24 00:00:00', NULL, NULL, NULL, 2, '2025-02-24 13:26:37', NULL, NULL, NULL, NULL);

-- Listage de la structure de table watt_else_db. obj_services
DROP TABLE IF EXISTS `obj_services`;
CREATE TABLE IF NOT EXISTS `obj_services` (
  `ID_offre` bigint(20) NOT NULL AUTO_INCREMENT,
  `Id_Station_de_recharge` bigint(20) NOT NULL,
  `Id_service` bigint(20) NOT NULL,
  `date_ajout_service` datetime NOT NULL,
  `date_retrait_service` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_offre`),
  KEY `FK_offrir_station_de_recharge` (`Id_Station_de_recharge`),
  KEY `FK_offrir_type_service` (`Id_service`),
  CONSTRAINT `FK_offrir_station_de_recharge` FOREIGN KEY (`Id_Station_de_recharge`) REFERENCES `obj_borne` (`Id_Station_de_recharge`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_offrir_type_service` FOREIGN KEY (`Id_service`) REFERENCES `gamme_type_service` (`Id_service`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.obj_services : ~0 rows (environ)

-- Listage de la structure de table watt_else_db. obj_session
DROP TABLE IF EXISTS `obj_session`;
CREATE TABLE IF NOT EXISTS `obj_session` (
  `id_session` bigint(20) NOT NULL AUTO_INCREMENT,
  `token` varchar(50) NOT NULL,
  `timestamp` timestamp NOT NULL,
  `id_utilisateur` bigint(20) NOT NULL,
  PRIMARY KEY (`id_session`),
  UNIQUE KEY `id_utilisateur` (`id_utilisateur`),
  CONSTRAINT `FK_session_utilisateur` FOREIGN KEY (`id_utilisateur`) REFERENCES `obj_utilisateur` (`Id_Utilisateur`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.obj_session : ~4 rows (environ)
REPLACE INTO `obj_session` (`id_session`, `token`, `timestamp`, `id_utilisateur`) VALUES
	(3, 'HKJdrpSCSChm2vXotrC3gpvVfkvoDx1u', '2025-02-18 12:36:13', 14),
	(4, 'vg-7sNj6hjb8oygQ4X87NyavRWG2PxAl', '2025-02-23 12:57:53', 13),
	(5, 'sOUMVwiuxhfqZUfXY0_u1QBt3hE6Gwmk', '2025-02-23 13:12:18', 15),
	(8, 'zQ-Z4auwpn1EsQ_7u8YB2W8rVRItlY1F', '2025-02-24 10:06:11', 1),
	(12, 'S41XsyloyzCj7fi2Q-GubLd75W6qzVw2', '2025-02-23 13:14:28', 18),
	(13, 'X60v1XNCs-OZHuS2t8fYVxiWUI2-fYOw', '2025-02-24 11:27:40', 2),
	(19, 'hUGVwdXdJHGWvXKBbFhIFeghNmppn1qw', '2025-02-24 09:10:26', 19),
	(22, 'WXhdWAM7JCP09bYJVpKMNXzOZKZQYz5N', '2025-02-24 10:19:24', 5),
	(23, 'k5sG7fc8P-ZqgFVA_DIZl1CpbUACtYuD', '2025-02-24 11:44:32', 20);

-- Listage de la structure de table watt_else_db. obj_tarification_borne
DROP TABLE IF EXISTS `obj_tarification_borne`;
CREATE TABLE IF NOT EXISTS `obj_tarification_borne` (
  `id_tarification` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_type_tarification` bigint(20) NOT NULL,
  `prix` decimal(19,4) NOT NULL,
  `date_de_demarrage_tarif` datetime NOT NULL,
  `date_de_fin_tarif` datetime DEFAULT NULL,
  PRIMARY KEY (`id_tarification`),
  KEY `FK_tarification_type_tarification` (`id_type_tarification`),
  CONSTRAINT `FK_tarification_type_tarification` FOREIGN KEY (`id_type_tarification`) REFERENCES `gamme_type_de_tarification` (`id_type_tarification`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.obj_tarification_borne : ~5 rows (environ)
REPLACE INTO `obj_tarification_borne` (`id_tarification`, `id_type_tarification`, `prix`, `date_de_demarrage_tarif`, `date_de_fin_tarif`) VALUES
	(1, 0, 2.3500, '2025-02-12 16:47:27', NULL),
	(2, 0, 5.0000, '2025-02-18 19:19:04', NULL),
	(3, 1, 7.0000, '2025-02-18 19:19:19', NULL),
	(4, 1, 5.5000, '2025-02-18 19:19:37', NULL),
	(5, 0, 3.9900, '2025-02-18 19:19:47', NULL),
	(6, 0, 12.0000, '2025-02-23 00:00:00', NULL),
	(7, 1, 10.9900, '2025-02-23 00:00:00', NULL),
	(8, 1, 8.7500, '2025-02-23 00:00:00', NULL),
	(9, 0, 4.0000, '2025-02-23 00:00:00', NULL),
	(10, 1, 6.5000, '2025-02-23 00:00:00', NULL),
	(11, 0, 5.0000, '2025-02-24 00:00:00', NULL),
	(12, 1, 2.0000, '2025-02-24 00:00:00', NULL),
	(13, 0, 1.0000, '2025-02-24 00:00:00', NULL),
	(14, 0, 1.0000, '2025-02-24 00:00:00', NULL),
	(15, 0, 1.0000, '2025-02-24 00:00:00', NULL),
	(16, 0, 2.0000, '2025-02-24 00:00:00', NULL),
	(17, 1, 2.1500, '2025-02-24 00:00:00', NULL),
	(18, 1, 4.0000, '2025-02-24 00:00:00', NULL),
	(19, 0, 4.0300, '2025-02-24 00:00:00', NULL),
	(20, 0, 4.0300, '2025-02-24 00:00:00', NULL),
	(21, 0, 4.0300, '2025-02-24 00:00:00', NULL),
	(22, 0, 4.0300, '2025-02-24 00:00:00', NULL),
	(23, 0, 2.0000, '2025-02-24 00:00:00', NULL),
	(24, 1, 10.0000, '2025-02-24 00:00:00', NULL),
	(25, 0, 4.0000, '2025-02-24 00:00:00', NULL),
	(26, 0, 4.0000, '2025-02-24 00:00:00', NULL),
	(27, 0, 4.0000, '2025-02-24 00:00:00', NULL),
	(28, 1, 2.0000, '2025-02-24 00:00:00', NULL);

-- Listage de la structure de table watt_else_db. obj_utilisateur
DROP TABLE IF EXISTS `obj_utilisateur`;
CREATE TABLE IF NOT EXISTS `obj_utilisateur` (
  `Id_Utilisateur` bigint(20) NOT NULL AUTO_INCREMENT,
  `Email` varchar(254) NOT NULL,
  `Mot_de_passe` varchar(254) NOT NULL,
  `Prenom` varchar(254) NOT NULL,
  `Nom` varchar(254) NOT NULL,
  `role` varchar(50) NOT NULL DEFAULT 'USER',
  `Telephone` varchar(254) DEFAULT NULL,
  `Naissance` datetime DEFAULT NULL,
  `Ouverture_compte` datetime NOT NULL,
  `Fermeture_compte` datetime DEFAULT NULL,
  `Id_Motif_fermeture` bigint(20) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `address_display` varchar(254) DEFAULT NULL,
  `code_postal` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id_Utilisateur`),
  UNIQUE KEY `Email` (`Email`),
  KEY `FK_utilisateur_motif_fermeture` (`Id_Motif_fermeture`),
  CONSTRAINT `FK_utilisateur_motif_fermeture` FOREIGN KEY (`Id_Motif_fermeture`) REFERENCES `gamme_motif_fermeture_compte` (`Id_Motif_fermeture`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.obj_utilisateur : ~5 rows (environ)
REPLACE INTO `obj_utilisateur` (`Id_Utilisateur`, `Email`, `Mot_de_passe`, `Prenom`, `Nom`, `role`, `Telephone`, `Naissance`, `Ouverture_compte`, `Fermeture_compte`, `Id_Motif_fermeture`, `latitude`, `longitude`, `address_display`, `code_postal`) VALUES
	(1, 'elise.bourgoin@example.com', 'elisepass2025', 'Elise', 'Bourgoin', 'USER', '0698765432', '1989-04-17 11:00:00', '2025-02-18 14:30:00', NULL, NULL, 48.8488, 2.3113, '18 Rue de la Paix, Paris, Île-de-France, France', '75002'),
	(2, 'lucas.dupont@example.com', 'password123', 'Lucas', 'Dupont', 'USER', '0682345678', '1985-05-15 14:30:00', '2025-01-10 08:45:00', NULL, NULL, 48.8566, 2.3522, '10 Rue de Paris, Paris, Île-de-France, France', '75000'),
	(3, 'claire.martin@example.com', 'securePassword!', 'Claire', 'Martin', 'USER', '0612345678', '1993-08-25 09:00:00', '2025-02-05 10:20:30', NULL, NULL, 48.7753, 2.3985, '12 Rue de la République, Montreuil, Île-de-France, France', '93100'),
	(4, 'remi.laurent@example.com', 'remi2025', 'Rémi', 'Laurent', 'USER', '0676543210', '1990-10-30 17:45:00', '2025-02-08 11:00:00', NULL, NULL, 48.8594, 2.3195, '15 Avenue des Champs-Élysées, Paris, Île-de-France, France', '75008'),
	(5, 'sophie.benoit@example.com', 'SophiePass22', 'Sophie', 'Benoit', 'USER', '0656789012', '1988-03-19 10:15:00', '2025-02-15 14:45:00', NULL, NULL, 48.8291, 2.2952, '22 Rue de la Liberté, Levallois-Perret, Hauts-de-Seine, France', '92300'),
	(6, 'julien.baudoin@example.com', 'Julien2025!', 'Julien', 'Baudoin', 'USER', '0698765432', '1995-11-02 16:00:00', '2025-02-20 09:30:00', NULL, NULL, 48.8568, 2.3512, '50 Boulevard Saint-Germain, Paris, Île-de-France, France', '75005'),
	(7, 'maxime.roux@example.com', 'maxime123', 'Maxime', 'Roux', 'USER', '0656789345', '1987-07-01 12:00:00', '2025-02-10 12:00:00', NULL, NULL, 48.8588, 2.2773, '7 Rue du Faubourg Saint-Antoine, Paris, Île-de-France, France', '75011'),
	(8, 'anne.perrin@example.com', 'anne2025', 'Anne', 'Perrin', 'USER', '0666666666', '1992-12-12 08:30:00', '2025-02-11 14:10:00', NULL, NULL, 48.8507, 2.3499, '34 Rue de la Montagne Sainte-Geneviève, Paris, Île-de-France, France', '75005'),
	(9, 'lucas.besnard@example.com', 'lucas2025!', 'Lucas', 'Besnard', 'USER', '0623456789', '1994-03-22 17:45:00', '2025-02-14 15:00:00', NULL, NULL, 48.8787, 2.3605, '19 Rue de la Gare, Clichy, Île-de-France, France', '92110'),
	(10, 'nathalie.lucas@example.com', 'nathaliepassword', 'Nathalie', 'Lucas', 'USER', '0676541234', '1983-02-27 10:10:00', '2025-02-16 13:30:00', NULL, NULL, 48.86, 2.32, '6 Boulevard Montmartre, Paris, Île-de-France, France', '75009'),
	(19, 'thomas.duron@gmail.com', 'mdp01234', 'Thomas', 'Duron', 'USER', '0123456789', '1993-09-19 00:00:00', '2025-02-24 00:00:00', NULL, NULL, 48.7759694, 2.2611372, '28, Avenue Pierre Brossolette, Cité Haute, Le Plessis-Robinson, Antony, Hauts-de-Seine, France métropolitaine, 92350, France', '92350'),
	(20, 'a@b.c', 'mdp', 'b', 'a', 'USER', '012345', '1111-11-11 00:00:00', '2025-02-24 00:00:00', NULL, NULL, 48.9552448, 2.5317986, '5, Boulevard de la Pépinière, Clos-Montceleux, Villepinte, Le Raincy, Seine-Saint-Denis, France métropolitaine, 93420, France', '93420');

-- Listage de la structure de table watt_else_db. obj_vehicule
DROP TABLE IF EXISTS `obj_vehicule`;
CREATE TABLE IF NOT EXISTS `obj_vehicule` (
  `Id_Vehicule` bigint(20) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(254) NOT NULL,
  `Plaque_immatriculation` varchar(254) NOT NULL,
  `Id_Type_de_prise` bigint(20) NOT NULL,
  `Id_Utilisateur` bigint(20) NOT NULL,
  `Date_ajout` datetime NOT NULL,
  `Date_retrait` datetime DEFAULT NULL,
  PRIMARY KEY (`Id_Vehicule`),
  KEY `FK_vehicule_type_de_prise` (`Id_Type_de_prise`),
  KEY `FK_vehicule_utilisateur` (`Id_Utilisateur`),
  CONSTRAINT `FK_vehicule_type_de_prise` FOREIGN KEY (`Id_Type_de_prise`) REFERENCES `gamme_type_de_prise` (`Id_Type_de_prise`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_vehicule_utilisateur` FOREIGN KEY (`Id_Utilisateur`) REFERENCES `obj_utilisateur` (`Id_Utilisateur`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- Listage des données de la table watt_else_db.obj_vehicule : ~12 rows (environ)
REPLACE INTO `obj_vehicule` (`Id_Vehicule`, `Nom`, `Plaque_immatriculation`, `Id_Type_de_prise`, `Id_Utilisateur`, `Date_ajout`, `Date_retrait`) VALUES
	(1, 'Mégane', 'AZE567YUU', 0, 2, '2025-02-13 16:45:27', NULL),
	(2, 'Peugeot 207', 'AZERTY', 1, 2, '2025-02-18 14:16:00', NULL),
	(3, 'CLIO', 'GE-600-XP', 1, 1, '2025-02-13 00:00:00', NULL),
	(4, 'ZOE', 'GE-500-XP', 1, 1, '2025-02-13 00:00:00', NULL),
	(5, '6L', 'GE-800-XP', 1, 1, '2025-02-13 00:00:00', NULL),
	(6, 'DELOREAN', 'GE-900-XP', 0, 1, '2025-02-14 00:00:00', NULL),
	(7, 'LAGUNA', 'GE-950-XP', 0, 1, '2025-02-14 00:00:00', NULL),
	(8, '206', 'GE-970-XP', 0, 1, '2025-02-14 00:00:00', NULL),
	(9, '309', 'GE-980-XP', 2, 1, '2025-02-14 00:00:00', NULL),
	(10, 'TEST', 'TEST', 2, 1, '2025-02-14 00:00:00', NULL),
	(11, '3008', 'TEST', 1, 1, '2025-02-14 00:00:00', NULL),
	(12, 'DS5', 'GS-450-XP', 1, 1, '2025-02-12 00:00:00', NULL),
	(13, 'oui', '89', 2, 18, '2025-02-23 00:00:00', NULL),
	(14, 'ma voiture', '6906H445', 0, 2, '2025-02-24 00:00:00', NULL),
	(15, 'ma voiture ', '04356RV5', 1, 19, '2025-02-24 00:00:00', '2025-02-24 00:00:00'),
	(16, 'ma voiture 2', '04356RV5', 0, 19, '2025-02-24 00:00:00', NULL),
	(17, 'Titine', '7896543845', 0, 5, '2025-02-24 00:00:00', NULL),
	(18, 'ygfh²', '1234567', 0, 20, '2025-02-24 00:00:00', NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
