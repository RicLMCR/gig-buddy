-- Create the database if it doesn't exist and use it
CREATE DATABASE IF NOT EXISTS `gb_user_profiles_directory`;
USE `gb_user_profiles_directory`;

-- Drop existing tables and foreign key constraint
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `members`;

-- Create `members` table
CREATE TABLE `members` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Insert sample data into `members` table
INSERT INTO `members` VALUES
    (1, 'Leslie', 'Andrews', 'amazingLez', 'leslie@luv2code.com'),
    (2, 'Emma', 'Baumgarten', 'Emzy', 'emma@luv2code.com'),
    (3, 'Avani', 'Gupta', 'XX Avani XX', 'avani@luv2code.com'),
    (4, 'Yuri', 'Petrov', 'BadBadger', 'yuri@luv2code.com'),
    (5, 'Juan', 'Vega', 'Orange Cactus', 'juan@luv2code.com'),
    (6, 'john', 'smith', 'john', 'john@luv2code.com'),
    (7, 'mary', 'smith', 'mary', 'mary@luv2code.com'),
    (8, 'susan', 'smith', 'susan', 'susan@luv2code.com');

-- Create `users` table
CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Start transaction to insert data into `users` table
START TRANSACTION;

-- maybe add constraint to the above table instead of this in order to establish proper relationship?
INSERT INTO `users` (`username`, `enabled`)
SELECT `user_name`, 1
FROM `members`;

COMMIT;

-- Disable safe update mode
SET SQL_SAFE_UPDATES = 0;

-- Update the passwords in `users` table
UPDATE `users`
SET `password` = '{noop}test123'
WHERE `username` IN ('amazingLez', 'Emzy', 'XX Avani XX', 'BadBadger', 'Orange Cactus', 'john', 'mary', 'susan');

-- Re-enable safe update mode
SET SQL_SAFE_UPDATES = 1;

-- Check the data in `members` table
SELECT `id`, `user_name` FROM `members`;

-- Create `authorities` table
CREATE TABLE `authorities` (
  `username` varchar(45) NOT NULL,
  `authority` varchar(45) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`, `authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Insert data into `authorities` table
INSERT INTO `authorities` (username, authority) VALUES
    ('amazingLez', 'ROLE_USER'),
    ('Emzy', 'ROLE_USER'),
    ('XX Avani XX', 'ROLE_USER'),
    ('BadBadger', 'ROLE_USER'),
    ('Orange Cactus', 'ROLE_USER'),
    ('john', 'ROLE_USER'),
    ('mary', 'ROLE_USER'),
    ('mary', 'ROLE_MODERATOR'),
    ('susan', 'ROLE_USER'),
    ('susan', 'ROLE_MODERATOR'),
    ('susan', 'ROLE_ADMIN');
