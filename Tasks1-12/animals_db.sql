USE animals;

CREATE TABLE animal_classes
(
	Id INT AUTO_INCREMENT PRIMARY KEY, 
	Class_name VARCHAR(20)
);

INSERT INTO animal_classes (Class_name)
VALUES ('вьючные'),
('домашние');  


CREATE TABLE packed_animals
(
	Id INT AUTO_INCREMENT PRIMARY KEY,
    Genus_name VARCHAR (20),
    Class_id INT,
    FOREIGN KEY (Class_id) REFERENCES animal_classes (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO packed_animals (Genus_name, Class_id)
VALUES ('Лошади', 1),
('Ослы', 1),  
('Верблюды', 1); 
    
CREATE TABLE home_animals
(
	Id INT AUTO_INCREMENT PRIMARY KEY,
    Genus_name VARCHAR (20),
    Class_id INT,
    FOREIGN KEY (Class_id) REFERENCES animal_classes (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO home_animals (Genus_name, Class_id)
VALUES ('Кошки', 2),
('Собаки', 2),  
('Хомяки', 2); 

CREATE TABLE cats 
(       
	Id INT AUTO_INCREMENT PRIMARY KEY, 
	Name VARCHAR(20), 
	Birthday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES home_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO cats (Name, Birthday, Commands, Genus_id)
VALUES ('Барсик', '2015-01-01', 'ко мне, лежать', 1),
('Васька', '2016-01-01', "", 1),  
('Мурзик', '2017-01-01', "", 1); 

CREATE TABLE dogs 
(       
	Id INT AUTO_INCREMENT PRIMARY KEY, 
	Name VARCHAR(20), 
	Birthday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES home_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO dogs (Name, Birthday, Commands, Genus_id)
VALUES ('Полкан', '2020-01-01', 'ко мне, лежать, лапу, голос', 2),
('Жучка', '2021-06-12', "сидеть, лежать, лапу", 2),  
('Мухтар', '2018-05-01', "сидеть, лежать, лапу, след, фас", 2), 
('Стрелка', '2021-05-10', "сидеть, лежать, фу, место", 2);

CREATE TABLE hamsters 
(       
	Id INT AUTO_INCREMENT PRIMARY KEY, 
	Name VARCHAR(20), 
	Birthday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES home_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO hamsters (Name, Birthday, Commands, Genus_id)
VALUES ('Рыжик', '2022-10-12', 'стоять', 3),
('Умка', '2022-03-12', "стоять, сальто", 3),  
('Черныш', '2021-07-11', NULL, 3), 
('Снежинка', '2021-05-10', NULL, 3);

CREATE TABLE horses 
(       
	Id INT AUTO_INCREMENT PRIMARY KEY, 
	Name VARCHAR(20), 
	Birthday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES packed_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO horses (Name, Birthday, Commands, Genus_id)
VALUES ('Гранат', '2020-01-12', 'рысь, шагом', 1),
('Закат', '2017-03-12', "рысь, шагом, хоп, стоять, тише", 1),  
('Байкал', '2016-07-12', "рысь, шагом, хоп, стоять, тише", 1), 
('Зумба', '2020-11-10', "рысь, шагом, стоять", 1);

CREATE TABLE donkeys 
(       
	Id INT AUTO_INCREMENT PRIMARY KEY, 
	Name VARCHAR(20), 
	Birthday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES packed_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO donkeys (Name, Birthday, Commands, Genus_id)
VALUES ('Яша', '2021-04-10', NULL, 2),
('Иа', '2015-03-12', "вперед, стоп", 2),  
('Диззи', '2019-07-12', "вперед, стоп", 2), 
('Харви', '2020-12-10', NULL, 2);

CREATE TABLE camels 
(       
	Id INT AUTO_INCREMENT PRIMARY KEY, 
	Name VARCHAR(20), 
	Birthday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES packed_animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO camels (Name, Birthday, Commands, Genus_id)
VALUES ('Терро', '2020-04-10', 'кс-кс, цок-цок', 3),
('Шилло', '2017-03-12', "кс-кс, цок-цок, гари, шшшш", 3),  
('Атилла', '2013-07-12', "кс-кс, цок-цок, шшш", 3), 
('Монстр', '2022-12-10', NULL, 3);

