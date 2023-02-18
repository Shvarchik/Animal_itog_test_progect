package Controller;

import java.util.List;

import Exceptions.UncorrectDataException;
import Model.*;
import Services.IRepository;
import UI.*;

public class PetController {
    private IRepository <Pet> petRepository;
    private Creator petCreator;
    private final View <Pet> view;

    public PetController (IRepository <Pet> petRepository){
        this.petRepository = petRepository;
        this.petCreator = new PetCreator();
        this.view = new ConsoleView();
    }

    public void createPet (PetType type){
        petRepository.Create(petCreator.createPet(type, view.getName(), view.getBirthday()));    
    }

    public List <Pet> getAllPet (){
        return petRepository.GetAll();
    }

    public boolean trainPet (int id, String command){
        
        Pet pet = petRepository.GetById(id);
        if (pet.getCommands().contains(command))
            return false;
        if (! pet.getPossibleCommands().contains(command))
            throw new UncorrectDataException("невыполнимая команда");
        else {
            pet.train(command);
            return true;
        }
    }

    public  void getCommands (int id){
        view.print(petRepository.GetById(id).commandsToString());
    }

    public void showFarm (){
        view.printAll(getAllPet());
    }
}
