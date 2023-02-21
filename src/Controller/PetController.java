package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import Exceptions.UncorrectDataException;
import Model.*;
import Services.IRepository;
import Services.PetRepository;
import UI.*;

public class PetController {
    private IRepository <Pet> petRepository;
    private Creator petCreator;
    private final View <Pet> view;
    private Validator validator;

    public PetController (IRepository <Pet> petRepository){
        this.petRepository = petRepository;
        this.petCreator = new PetCreator();
        this.view = new ConsoleView();
        this.validator = new Validator();
    }

    public void createPet (PetType type){

        String [] data = new String []{view.getName(), view.getBirthday()};
        if (validator.validate(data)){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate birthday = LocalDate.parse(data[1], formatter);
            petRepository.create(petCreator.createPet(type, data[0], birthday));    
        }
    }

    public void updatePet (int id){

        Pet pet = getById(id);
        String [] data = new String []{view.getName(), view.getBirthday()};

        if (validator.validate(data)){

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate birthday = LocalDate.parse(data[1], formatter);
            pet.setName(data[0]);
            pet.setBirthday(birthday);
            petRepository.update(pet);
        }    
    }

    public List <Pet> getAllPet (){
        return petRepository.getAll();
    }

    public boolean trainPet (int id, String command){
        
        if (((PetRepository)petRepository).getCommandsById(id,1).contains(command))
            return false;
        if (! ((PetRepository)petRepository).getCommandsById(id,2).contains(command))
            throw new UncorrectDataException("невыполнимая команда");
        else {
            ((PetRepository)petRepository).train (id,command);
            return true;
        }
    }

    public Pet getById (int id){
        return petRepository.getById(id);
    }

    public void delete (int id){
        petRepository.delete(id);
    }

    public  void getCommands (int id){
        view.print(((PetRepository)petRepository).getCommandsById(id,1));
    }

    public void showFarm (){
        view.printAll(getAllPet());
    }
}
