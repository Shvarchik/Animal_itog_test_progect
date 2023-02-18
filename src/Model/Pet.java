package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class Pet {
    
    private String name;
    private LocalDate birthday;
    private ArrayList<String> commands;

    {
        commands = new ArrayList<String>();
    }

    public Pet() {
    }

    protected Pet (String name, LocalDate birthday){
        this.name = name;
        this.birthday = birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.birthday = LocalDate.parse(date, formatter);
    }

    public String getBirthday() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return formatter.format(birthday);
    }

    public ArrayList<String> getCommands() {
        return commands;
    }

    public void train (String command){
        // if (! getPossibleCommands().contains(command))
        //     throw new UncorrectDataException("невыполнимая команда");
        // else 
            commands.add(command);
    }

    public abstract List <String> getPossibleCommands ();

    public String commandsToString (){
        StringBuilder sb = new StringBuilder();
        if (commands.size() != 0){
            sb.append ("команды:\n");
            for (String command : commands) {
                sb.append(command);
                sb.append ("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("имя: %s, дата рождения: %s", name, getBirthday()));
        if (commands.size() != 0){
            sb.append (", команды: ");
            for (String command : commands) {
                sb.append(String.format(" %s,",command));
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
