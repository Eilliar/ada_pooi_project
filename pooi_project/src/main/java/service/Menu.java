package service;

import entity.GenderType;
import repository.PersonRepository;

import java.util.Scanner;

public class Menu {

    public void runUserInterface(ActorService actorService){
        Scanner scanner = new Scanner(System.in);
        int chosenOption;
        do {
            System.out.println("\nMenu:");
            System.out.println("0. Create Actor");
            System.out.println("1. Create Director");
            System.out.println("2. Create Movie");
            System.out.println("3. Search a Movie (by name)");
            System.out.println("4. Show all Actors");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            chosenOption = scanner.nextInt();
            scanner.nextLine(); //Clear buffer
            switch (chosenOption) {
                case 0:
                    System.out.printf("Creating Actor Steps...\n");
                    System.out.println("Give me a name: ");
                    String name = scanner.nextLine();
                    System.out.println("Give me a birth date (YYYY-MM-DD): ");
                    String birthDate = scanner.nextLine();
                    System.out.println("Give me a Gender: ");
                    GenderType gender = GenderType.valueOf(scanner.nextLine());
                    actorService.addActor(name, birthDate, gender);
                    System.out.println("Successfully registered new Actor!");
                    break;
                case 1:
                    System.out.printf("Creating Director Steps...\n");
                    break;
                case 2:
                    System.out.printf("Creating Movie Steps...\n");
                    break;
                case 3:
                    System.out.println("Searching Movie Steps...\n");
                    break;
                case 4:
                    actorService.showAllRecords();
                    break;
                case 5:
                    System.out.println("Ok, bye!");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        } while(chosenOption != 5);
    }
}
