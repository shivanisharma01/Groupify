DELIMITER $$

DROP procedure IF EXISTS `spLoadPasswordPolicy`;

DELIMITER $$
USE `CSCI5308_3_DEVINT`$$
CREATE PROCEDURE spLoadPasswordPolicy ()
BEGIN
SELECT * FROM PasswordPolicy where IsValid=1;
END$$

DELIMITER ;