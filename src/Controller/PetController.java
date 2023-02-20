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
            petRepository.Create(petCreator.createPet(type, data[0], birthday));    
        }
    }

    public List <Pet> getAllPet (){
        return petRepository.GetAll();
    }

    public boolean trainPet (int id, String command){
        
        Pet pet = petRepository.GetById(id);
        if (((PetRepository)petRepository).getCommandsById(id).contains(command))
            return false;
        if (! pet.getPossibleCommands().contains(command))
            throw new UncorrectDataException("невыполнимая команда");
        else {
            ((PetRepository)petRepository).train (id,command);
            return true;
        }
    }

    public Pet getById (int id){
        return petRepository.GetById(id);
    }

    public  void getCommands (int id){
        view.print(((PetRepository)petRepository).getCommandsById(id));
    }

    public void showFarm (){
        view.printAll(getAllPet());
    }
}
