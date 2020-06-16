DROP TABLE IF EXISTS `backend_db`.`transaction`;
CREATE TABLE `backend_db`.`transaction` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `amount` DOUBLE NULL,
    `description` VARCHAR(45) NULL,
    `date` DATETIME NULL,
    `user_id` INT NULL,
PRIMARY KEY (`id`));
