package Controller;

import Model.Pet;

public abstract class Creator {

    private Validator validator;

    {
        validator = new Validator();
    }

    protected abstract Pet createNewPet(PetType type);
    
    public Pet createPet (PetType type, String name, String date){

        Pet pet = createNewPet(type);
        String [] data = new String []{name,date};
        if (validator.validate(data)){
            pet.setName(name);
            pet.setBirthday(date);
        }
        return pet;
    }
}
