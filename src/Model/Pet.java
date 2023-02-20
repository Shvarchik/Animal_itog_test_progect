package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class Pet {
    
    protected int petId;
    private String name;
    private LocalDate birthday;
    
    public Pet() {
    }

    protected Pet (int id, String name, LocalDate birthday){
        this.petId = id;
        this.name = name;
        this.birthday = birthday;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getPetId() {
        return petId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBirthday(LocalDate date) {
        this.birthday = date;
    }

    public void setBirthday(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.birthday = LocalDate.parse(date, formatter);
    }

    public LocalDate getBirthdayDate(){
        return birthday;
    }

    public String getBirthday() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return formatter.format(birthday);
    }

    public abstract List <String> getPossibleCommands ();

    @Override
    public String toString() {
        return String.format("%d. %s: имя: %s, дата рождения: %s ", getPetId(), getClass().getSimpleName(), name, getBirthday());
    }
}
