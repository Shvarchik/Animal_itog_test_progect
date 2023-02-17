package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cat extends Pet{

    private static List<String> possibleCommands = new ArrayList <String >(Arrays.asList("кc-кc","сидеть","ко мне","дай лапку","принеси"));

    public Cat(String name, LocalDate birthday) {
        super(name, birthday);
    }

    public Cat() {
    }

    @Override
    protected List<String> getPossibleCommands() {
        return possibleCommands;
    }
}
