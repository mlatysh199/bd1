-- Proyecto 2 Bases de Datos 1
-- Maximilian Latysh. Carnet 2022091544

-- mysqldump -u root -p blockbuster > temp.sql

DROP DATABASE IF EXISTS blockbuster;
CREATE DATABASE IF NOT EXISTS blockbuster;
USE blockbuster;

CREATE TABLE client (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    email VARCHAR(45) NOT NULL UNIQUE,
    phone_number VARCHAR(20),
    INDEX lastname_idx (lastname)
);

CREATE TABLE category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    description VARCHAR(45)
);

CREATE TABLE movie (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL UNIQUE,
    release_date DATETIME NOT NULL,
    category_id INT NOT NULL,
    units_available INT NOT NULL,
    CONSTRAINT movie_category_fk FOREIGN KEY (category_id) REFERENCES category(id),
    INDEX movie_title_idx (title)
);

CREATE TABLE rentals (
    id INT PRIMARY KEY AUTO_INCREMENT,
    rental_date DATETIME NOT NULL,
    client_id INT NOT NULL,
    movie_id INT NOT NULL,
    CONSTRAINT rental_client_fk FOREIGN KEY (client_id) REFERENCES client(id),
    CONSTRAINT rental_movie_fk FOREIGN KEY (movie_id) REFERENCES movie(id)
);

CREATE TABLE blockbuster_log (
    id INT PRIMARY KEY AUTO_INCREMENT,
    table_name VARCHAR(45) NOT NULL,
    created_on DATETIME NOT NULL,
    entry_text VARCHAR(45) NOT NULL
);

CREATE TABLE review (
    id INT PRIMARY KEY AUTO_INCREMENT,
    rating DECIMAL(2) NOT NULL,
    review_text VARCHAR(100),
    created_on DATETIME NOT NULL,
    client_id INT NOT NULL,
    movie_id INT NOT NULL,
    CONSTRAINT review_client_fk FOREIGN KEY (client_id) REFERENCES client(id),
    CONSTRAINT review_movie_fk FOREIGN KEY (movie_id) REFERENCES movie(id)
);

CREATE TRIGGER rentals_AFTER_INSERT AFTER INSERT ON rentals FOR EACH ROW INSERT INTO blockbuster_log(table_name, created_on, entry_text) VALUES ('rentals', NOW(), 'New rental for client ' + NEW.client_id + ' and movie ' + NEW.movie_id);

CREATE TRIGGER rentals_AFTER_UPDATE AFTER UPDATE ON rentals FOR EACH ROW INSERT INTO blockbuster_log(table_name, created_on, entry_text) VALUES ('rentals', NOW(), 'Update on rental ' + NEW.id);

CREATE TRIGGER rentals_AFTER_DELETE AFTER DELETE ON rentals FOR EACH ROW INSERT INTO blockbuster_log(table_name, created_on, entry_text) VALUES ('rentals', NOW(), 'Rental ' + OLD.id + ' was deleted');

CREATE TRIGGER review_AFTER_INSERT AFTER INSERT ON review FOR EACH ROW INSERT INTO blockbuster_log(table_name, created_on, entry_text) VALUES ('reviews', NOW(), 'New review for movie ' + NEW.movie_id + ' by client ' + NEW.client_id);

CREATE TRIGGER review_AFTER_UPDATE AFTER UPDATE ON review FOR EACH ROW INSERT INTO blockbuster_log(table_name, created_on, entry_text) VALUES ('reviews', NOW(), 'Review ' + NEW.id + ' updated');

CREATE TRIGGER review_AFTER_DELETE AFTER DELETE ON review FOR EACH ROW INSERT INTO blockbuster_log(table_name, created_on, entry_text) VALUES ('reviews', NOW(), 'Review ' + OLD.id + ' was deleted');

DROP USER IF EXISTS 'blockbusterappuser'@'localhost';
CREATE USER IF NOT EXISTS 'blockbusterappuser'@'localhost' IDENTIFIED BY 'blockbusterapppass';
GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE ON blockbuster.* TO 'blockbusterappuser'@'localhost';