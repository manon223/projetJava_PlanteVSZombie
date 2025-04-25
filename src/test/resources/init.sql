USE pvz;

-- Supprimer les tables si elles existent déjà (dans l'ordre pour respecter les contraintes)
DROP TABLE IF EXISTS Zombie;
DROP TABLE IF EXISTS Plante;
DROP TABLE IF EXISTS Map;

-- Créer la table "map"
CREATE TABLE Map (
                     id_map INT AUTO_INCREMENT PRIMARY KEY,
                     ligne INT UNSIGNED NOT NULL,
                     colonne INT UNSIGNED NOT NULL,
                     chemin_image VARCHAR(255) DEFAULT NULL
);

-- Créer la table "plante"
CREATE TABLE Plante (
                        id_plante INT AUTO_INCREMENT PRIMARY KEY,
                        nom VARCHAR(255) NOT NULL,
                        point_de_vie INT UNSIGNED NOT NULL,
                        attaque_par_seconde DECIMAL(5, 2) DEFAULT 0.00,
                        degat_attaque INT UNSIGNED DEFAULT 0,
                        cout INT UNSIGNED NOT NULL,
                        soleil_par_seconde DECIMAL(5, 2) DEFAULT 0.00,
                        effet ENUM('normal', 'slow low', 'slow medium', 'slow stop') DEFAULT 'normal',
                        chemin_image VARCHAR(255) DEFAULT NULL
);

-- Créer la table "zombie"
CREATE TABLE Zombie (
                        id_zombie INT AUTO_INCREMENT PRIMARY KEY,
                        nom VARCHAR(255) NOT NULL,
                        point_de_vie INT UNSIGNED NOT NULL,
                        attaque_par_seconde DECIMAL(5, 2) DEFAULT 0.00,
                        degat_attaque INT UNSIGNED NOT NULL,
                        vitesse_de_deplacement DECIMAL(5, 2) DEFAULT 0.00,
                        chemin_image VARCHAR(255) DEFAULT NULL,
                        id_map INT,
                        CONSTRAINT fk_zombie_map FOREIGN KEY (id_map) REFERENCES Map(id_map)
);

-- Peuplement de la table "map"
INSERT INTO Map (ligne, colonne, chemin_image) VALUES
                                                   (5, 9, 'images/map/gazon.png'),
                                                   (6, 9, 'images/map/gazon.png'),
                                                   (4, 8, 'images/map/gazon.png');

-- Peuplement de la table "plante"
INSERT INTO Plante (nom, point_de_vie, attaque_par_seconde, degat_attaque, cout, soleil_par_seconde, effet, chemin_image) VALUES
                                                                                                                              ('Tournesol', 100, 0.00, 0, 50, 25.00, 'normal', 'images/plante/tournesol.png'),
                                                                                                                              ('Pois Tireur', 150, 1.50, 20, 100, 0.00, 'normal', 'images/plante/poistireur.png'),
                                                                                                                              ('Double Pisto P', 150, 1.50, 40, 200, 0.00, 'normal', 'images/plante/doublepois.png'),
                                                                                                                              ('Glace Pois', 120, 1.00, 10, 175, 0.00, 'slow low', 'images/plante/glacepois.png'),
                                                                                                                              ('Noix', 300, 0.00, 0, 50, 0.00, 'normal', 'images/plante/noix.png');

-- Peuplement de la table "zombie"
INSERT INTO Zombie (nom, point_de_vie, attaque_par_seconde, degat_attaque, vitesse_de_deplacement, chemin_image, id_map) VALUES
                                                                                                                             ('Zombie de base', 100, 0.80, 10, 0.50, 'images/zombie/zombie.png', 1),
                                                                                                                             ('Zombie Cone', 200, 0.80, 10, 0.45, 'images/zombie/conehead.png', 1),
                                                                                                                             ('Zombie Seau', 300, 0.70, 10, 0.40, 'images/zombie/buckethead.png', 1),
                                                                                                                             ('Runner Zombie', 80, 1.00, 8, 0.70, 'images/zombie/runner.png', 2),
                                                                                                                             ('Football Zombie', 250, 0.90, 12, 0.60, 'images/zombie/football.png', 3);

-- Création de l'utilisateur s'il n'existe pas déjà
CREATE USER IF NOT EXISTS 'userEPF'@'%' IDENTIFIED BY 'EPF';

-- Attribution de tous les privilèges à l'utilisateur sur la base de données pvz
GRANT ALL PRIVILEGES ON pvz.* TO 'userEPF'@'%';

-- Application des changements de privilèges
FLUSH PRIVILEGES;