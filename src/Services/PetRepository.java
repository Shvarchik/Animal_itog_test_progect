package Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Model.*;


public class PetRepository implements IRepository<Pet> {

    private Statement sqlSt;
    private ResultSet resultSet;
    private List<Pet> farm;

    public PetRepository() {
    };

    @Override
    public List<Pet> GetAll() {
        farm = new ArrayList<Pet>();
        Pet pet;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {
                sqlSt = dbConnection.createStatement();
                resultSet = sqlSt.executeQuery("SELECT GenusId, PetName, Birthday FROM pet_list ORDER BY Id");
                while (resultSet.next()) {

                    int type = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    LocalDate birthday = resultSet.getDate(3).toLocalDate();
                    switch (type){
                        case 1:
                            pet = new Cat(name,birthday);
                            break;
                        case 2:
                            pet = new Dog(name, birthday);
                            break;
                        case 3:
                            pet = new Hamster(name,birthday);
                            break;
                        default:
                            pet = null;
                    }
                    farm.add(pet);
                }
            } 
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(PetRepository.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        } 
        return farm;
    }

    @Override
    public Pet GetById(int id) {
        return farm.get(id - 1);
    }

    @Override
    public void Create(Pet pet) {
        farm.add(pet);
    }

    @Override
    public void Update(Pet pet) {
    }

    @Override
    public void Remove(int id) {
        farm.remove(id - 1);
    }

    public static Connection getConnection() throws SQLException, IOException {

        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("src/Resources/database.properties")) {

            props.load(fis);
            String url = props.getProperty("url");
            String username = props.getProperty("username");
            String password = props.getProperty("password");

            return DriverManager.getConnection(url, username, password);
        }

    }

    void example() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {
                sqlSt = dbConnection.createStatement();
                resultSet = sqlSt.executeQuery("SELECT Name, Birthday FROM animals.cats");
                while (resultSet.next()) {

                    String name = resultSet.getString(1);
                    LocalDate birthday = resultSet.getDate(2).toLocalDate();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    System.out.printf("%s  %s\n", name, formatter.format(birthday));
                }
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(PetRepository.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        } 
    }
}
