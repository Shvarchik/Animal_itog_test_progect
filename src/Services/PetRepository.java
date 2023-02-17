package Services;

import java.util.ArrayList;
import java.util.List;

import Model.Pet;

public class PetRepository implements IRepository <Pet> {

    private List <Pet> farm;

           
    public PetRepository () {
        farm = new ArrayList<Pet>();
    };
        

    @Override
    public List <Pet> GetAll() {
        return farm;
    }

    @Override
    public Pet GetById(int id) {
        return farm.get(id-1);
    }

    @Override
    public void Create (Pet pet) {
        farm.add(pet);
    }

    @Override
    public void Update(Pet pet) {
    }

    @Override
    public void Remove (int id) {
        farm.remove(id-1);
    }
}
