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
import Model.*;


public class PetRepository implements IRepository<Pet> {

    private Creator petCreator;
    private Statement sqlSt;
    private ResultSet resultSet;
    
    public PetRepository() {
        this.petCreator = new PetCreator();
    };

    @Override
    public List<Pet> GetAll() {
        List<Pet> farm = new ArrayList<Pet>();
        Pet pet;
        PetType type = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {
                sqlSt = dbConnection.createStatement();
                resultSet = sqlSt.executeQuery("SELECT GenusId, Id, PetName, Birthday FROM pet_list ORDER BY Id");
                while (resultSet.next()) {

                    int petType = resultSet.getInt(1);
                    int id = resultSet.getInt(2);
                    String name = resultSet.getString(3);
                    LocalDate birthday = resultSet.getDate(4).toLocalDate();
                    switch (petType){
                        case 1:
                            type = PetType.Cat;
                            break;
                        case 2:
                            type = PetType.Dog;
                            break;
                        case 3:
                            type = PetType.Hamster;
                            break;
                    }
                    pet = petCreator.createPet(type, name, birthday);
                    pet.setPetId(id);
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
    public Pet GetById(int petId) {
        Pet pet = null;
        PetType type = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {

                String SQLstr = "SELECT GenusId, Id, PetName, Birthday FROM pet_list WHERE Id = ?";
                PreparedStatement prepSt = dbConnection.prepareStatement(SQLstr);
                prepSt.setInt(1, petId);
                resultSet = prepSt.executeQuery();

                if (resultSet.next()) {

                    int typeNum = resultSet.getInt(1);
                    int id = resultSet.getInt(2);
                    String name = resultSet.getString(3);
                    LocalDate birthday = resultSet.getDate(4).toLocalDate();
                    switch (typeNum){
                        case 1:
                            type = PetType.Cat;
                            break;
                        case 2:
                            type = PetType.Dog;
                            break;
                        case 3:
                            type = PetType.Hamster;
                            break;
                    }
                    pet = petCreator.createPet(type, name, birthday);
                    pet.setPetId(id);
                } 
            } 
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(PetRepository.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return pet; 
    }

    @Override
    public void Create(Pet pet) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {
                String SQLstr = "INSERT INTO pet_list (PetName, Birthday, GenusId) SELECT ?, ?, (SELECT Id FROM pet_types WHERE Genus_name = ?)";
                PreparedStatement prepSt = dbConnection.prepareStatement(SQLstr);
                prepSt.setString(1, pet.getName());
                prepSt.setDate(2, Date.valueOf(pet.getBirthdayDate())); 
                prepSt.setString(3, pet.getClass().getSimpleName());

                int rows = prepSt.executeUpdate();
                System.out.printf("%d запись добавлена \n", rows);
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(PetRepository.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        } 
    }

    public void train (int id, String command){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {
                String SQLstr = "INSERT INTO pet_command (PetId, CommandId) SELECT ?, (SELECT Id FROM commands WHERE Command_name = ?)";
                PreparedStatement prepSt = dbConnection.prepareStatement(SQLstr);
                prepSt.setInt(1, id);
                prepSt.setString(2, command);

                int rows = prepSt.executeUpdate();
                System.out.printf("%d команда разучена\n", rows);
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(PetRepository.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        } 
    }

    public List<String> getCommandsById (int petId){

        List <String> commands = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {
                String SQLstr = "SELECT Command_name FROM pet_command pc JOIN commands c ON pc.CommandId = c.Id WHERE pc.PetId = ?";
                PreparedStatement prepSt = dbConnection.prepareStatement(SQLstr);
                prepSt.setInt(1, petId);
                resultSet = prepSt.executeQuery();
                while (resultSet.next()) {
                    commands.add(resultSet.getString(1));
                }
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(PetRepository.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        } 
        return commands;
    }

    @Override
    public void Update(Pet pet) {
    }

    @Override
    public void Remove(int id) {
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
}
