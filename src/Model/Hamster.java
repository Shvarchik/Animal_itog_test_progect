package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hamster extends Pet{

    private static List<String> possibleCommands = new ArrayList <String> (Arrays.asList("кушать","кувырок", "колесо", "дверка"));

    public Hamster(String name, LocalDate birthday) {
        super(name, birthday);
    }

    public Hamster() {
    }

    @Override
    protected List<String> getPossibleCommands() {
        return possibleCommands;
    }
    
}
