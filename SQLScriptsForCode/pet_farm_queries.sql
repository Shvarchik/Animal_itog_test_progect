-- GetAll
SELECT Id, GenusId, PetName, Birthday 
FROM pet_list
ORDER BY Id; 

-- получить список команд, которые умеет выполнять конкретный питомец
SELECT Command_name
FROM pet_command pc
JOIN commands c ON pc.CommandId = c.Id
WHERE pc.PetId = 8;

-- Create Pet
INSERT INTO pet_list (PetName, Birthday, GenusId)
SELECT 'Полкан', '2018-04-05', (SELECT Id FROM pet_types WHERE Genus_name = "Dog");

-- Train

INSERT INTO pet_command (PetId, CommandId)
SELECT 2, (SELECT Id FROM commands WHERE Command_name = 'кс-кс');

-- get possible commanhds by petId

SELECT Command_name
FROM commands c
JOIN Genus_command gc ON c.Id = gc.CommandId
WHERE gc.GenusId = (SELECT GenusId FROM pet_list WHERE Id = 1);

-- delete

DELETE FROM pet_list WHERE Id = 5;

-- update

-- UPDATE pet_list SET PetName = ?, Birthday = ? WHERE Id = ? 




