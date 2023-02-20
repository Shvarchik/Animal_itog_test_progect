-- GetAll
SELECT Id, GenusId, PetName, Birthday 
FROM pet_list
ORDER BY Id; 

-- получить список команд, которые умеет выполнять конкретный питомец
SELECT Command_name
FROM pet_command pc
JOIN commands c ON pc.CommandId = c.Id
WHERE pc.PetId = 5;

-- Create Pet
INSERT INTO pet_list (PetName, Birthday, GenusId)
SELECT 'Полкан', '2018-04-05', (SELECT Id FROM pet_types WHERE Genus_name = "Dog");

-- Train
INSERT INTO pet_command (PetId, CommandId)
SELECT 2, (SELECT Id FROM commands WHERE Command_name = "лежать");