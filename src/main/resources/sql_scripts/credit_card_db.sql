CREATE DATABASE  IF NOT EXISTS `user_directory`;
USE `user_directory`;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `oib` varchar(45) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `file_name` varchar(45),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
