package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cat extends Pet{

    private static List<String> possibleCommands = new ArrayList <String >(Arrays.asList("кc-кc","сидеть","ко мне","дай лапу","принеси"));

    public Cat(int id, String name, LocalDate birthday) {
        super(id, name, birthday);
    }

    public Cat() {
    }

    @Override
    public List<String> getPossibleCommands() {
        return possibleCommands;
    }
}
