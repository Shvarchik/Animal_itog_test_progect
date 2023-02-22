CREATE DATABASE pet_farm;
USE pet_farm;
CREATE TABLE pet_types
(
	Id INT AUTO_INCREMENT PRIMARY KEY, 
	Genus_name VARCHAR(20)
);

INSERT INTO pet_types (Genus_name)
VALUES ('Cat'),
('Dog'),  
('Hamster'); 

CREATE TABLE commands
(
	Id INT AUTO_INCREMENT PRIMARY KEY, 
	Command_name VARCHAR(20)
);

INSERT INTO commands (Command_name)
VALUES ('кс-кс'), ('дай лапу'), ('сидеть'), ('лежать'),
('принеси'), ('фу'), ('фас'), ('служи'), ('голос'),
('кушать'), ('кувырок'), ('колесо'), ('дверка'); 

CREATE TABLE Genus_command
(
	GenusId INT , 
	CommandId INT,
    Foreign KEY (Genusid) REFERENCES pet_types (Id),
    Foreign KEY (CommandId) REFERENCES commands (Id)
);

INSERT INTO Genus_command (GenusId, CommandId)
VALUES (1, 1),
(1, 2), (1, 3), (1, 4), (1, 5),
(2, 2), (2, 3), (2, 4), (2, 5), (2, 6), (2, 7), (2, 8), (2, 9),
(3, 10), (3, 11), (3, 12), (3, 13);

CREATE TABLE pet_list 
(       
	Id INT AUTO_INCREMENT PRIMARY KEY, 
	PetName VARCHAR(20), 
	Birthday DATE,
    GenusId int,
    Foreign KEY (GenusId) REFERENCES pet_types (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO pet_list (PetName, Birthday, GenusId)
VALUES 
('Барсик', '2015-01-01', 1),
('Васька', '2016-01-01', 1),  
('Мурзик', '2017-01-01', 1), 
('Полкан', '2020-01-01', 2),
('Жучка', '2021-06-12',  2),  
('Мухтар', '2018-05-01', 2), 
('Стрелка', '2021-05-10', 2),
('Рыжик', '2022-10-12', 3),
('Умка', '2022-03-12', 3),  
('Черныш', '2021-07-11', 3), 
('Пушинка', '2021-05-10', 3);

CREATE TABLE pet_command
(
	PetId INT , 
	CommandId INT,
    Foreign KEY (PetId) REFERENCES pet_list (Id) ON DELETE CASCADE ON UPDATE CASCADE,
    Foreign KEY (CommandId) REFERENCES commands (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO pet_command (PetId, CommandId)
VALUES (1, 1),
(2, 1), (2, 2), (3, 4), (3, 5),
(4, 2), (4, 9), (5, 2), (5, 6), (6, 2), (6, 3), (7, 2), (7, 8),
(8, 10), (9, 10), (10, 10), (10, 13);

SHOW VARIABLES LIKE "sql_safe_updates";
SET SQL_SAFE_UPDATES = 1;

