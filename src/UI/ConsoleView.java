package UI;

import java.util.List;
import java.util.Scanner;
import Model.*;

public class ConsoleView implements View <Pet> {

    Scanner in;

    public ConsoleView() {
        in = new Scanner(System.in, "ibm866");
    }

    @Override
    public String getName() {
        System.out.printf("Имя: ");
        return in.nextLine();
    }

    @Override
    public String getBirthday() {
        System.out.printf("Введите дату рождения в формате 'dd.mm.yyyy': ");
        return in.nextLine();
    }

    @Override
    public void printAll (List<Pet> petList){
        System.out.print("\033[H\033[J");
        System.out.println("\n          Наши питомцы:");
        for (Pet pet : petList) {
            System.out.println(String.format("%s", pet.toString()));
        }
    }

    @Override
    public void print (List<String> strings) {
        if (strings.isEmpty())
            System.out.println("список пуст");
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
