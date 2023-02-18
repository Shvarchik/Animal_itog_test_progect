package UI;

import java.util.Scanner;
import Controller.*;
import Exceptions.UncorrectDataException;

public class ConsoleMenu {

    PetController petController;

    public ConsoleMenu(PetController controller) {
        this.petController = controller;
    }

    public void start() {

        try (Scanner in = new Scanner(System.in, "ibm866"); Counter count = new Counter()) {

            boolean flag = true;
            int id;
            while (flag) {

                // System.out.print("\033[H\033[J");

                System.out.println(
                        "\n1 - Список всех животных\n2 - Завести новое животное\n3 - Что умеет животное\n4 - Дрессировка\n5 - Выход");
                String key = in.next();
                switch (key) {
                    case "1":
                        System.out.println("Ферма:");
                        petController.showFarm();
                        break;
                    case "2":
                        PetType type = menuChoice(in);
                        if (type != null) {
                            try {
                                petController.createPet(type);
                                count.add();
                                System.out.println("ОК");
                            } catch (UncorrectDataException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    case "3":
                        petController.showFarm();
                        id = menuChoicePet(in);
                        if (id != 0)
                            petController.getCommands(id);
                        break;
                    case "4":
                        petController.showFarm();
                        id = menuChoicePet(in);
                        if (id != 0)
                            menuTrainPet(id, in);
                        break;
                    case "5":
                        flag = false;
                        break;
                    default:
                        System.out.println("такого варианта нет");
                        break;
                }
            }
        }
    }

    private PetType menuChoice(Scanner in) {
        System.out.println("Какое животное добавить:\n1 - Кошка\n2 - Собака\n3 - Хомяк\n4 - Возврат о основное меню");

        while (true) {
            String key = in.next();
            switch (key) {
                case "1":
                    return PetType.Cat;
                case "2":
                    return PetType.Dog;
                case "3":
                    return PetType.Hamster;
                case "4":
                    return null;
                default:
                    System.out.println("Такого варианта нет, введите число от 1 до 4");
                    break;
            }
        }
    }

    private int menuChoicePet(Scanner in) {
        System.out.println("\nВведите номер животного, 0 для возврата в основное меню: ");
        while (true) {
            int id = in.nextInt();
            in.nextLine();
            if (id < 0 || id > petController.getAllPet().size()) {
                System.out.println("Животного с таким номером нет, попробуйте еще раз:");
            } else
                return id;

        }
    }

    private void menuTrainPet(int petId, Scanner in) {
        Scanner sc = in;
        while (true) {
            System.out.println("Введите новую команду, 0 для возврата в основное меню: ");
            String command = sc.nextLine();
            if (command.length() == 1 && command.equals("0"))
                return;
            try {
                if (petController.trainPet(petId, command))
                    System.out.println("получилось!");
                else
                    System.out.println("Это мы уже умеем!");
            } catch (UncorrectDataException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
