package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dog extends Pet {

    private static List<String> possibleCommands = new ArrayList <String >(Arrays.asList("лежать","сидеть","ко мне","дай лапу","фу","фас", "служи", "голос"));

    public Dog(int id, String name, LocalDate birthday) {
        super(id, name, birthday);
    }

    public Dog() {
    }

    @Override
    public List<String> getPossibleCommands() {
        return possibleCommands;
    }
    
}
