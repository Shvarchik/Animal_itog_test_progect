package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hamster extends Pet{

    private static List<String> possibleCommands = new ArrayList <String> (Arrays.asList("кушать","кувырок", "колесо", "дверка"));

    public Hamster(int id, String name, LocalDate birthday) {
        super(id, name, birthday);
    }

    public Hamster() {
    }

    @Override
    public List<String> getPossibleCommands() {
        return possibleCommands;
    }
    
}
