package service;

import entity.Person;
import entity.SextType;

import java.util.List;
import java.util.Scanner;

public class Menu {

    public void runUserInterface(ActorService actorService, DirectorService directorService){
        Scanner scanner = new Scanner(System.in);
        int chosenOption;
        do {
            System.out.println("\nMenu:");
            System.out.println("0. Create Actor");
            System.out.println("1. Create Director");
            System.out.println("2. Create Movie");
            System.out.println("3. Search a Movie (by name)");
            System.out.println("4. Show all Actors");
            System.out.println("5. Show all Directors");
            System.out.println("6. Quit");
            System.out.print("Choose an option: ");
            chosenOption = scanner.nextInt();
            scanner.nextLine(); //Clear buffer
            switch (chosenOption) {
                case 0:
                    System.out.printf("\nCreating Actor Steps...\n");
                    System.out.println("Give me a name: ");
                    String a_name = scanner.nextLine();
                    System.out.println("Give me a birth date (YYYY-MM-DD): ");
                    String a_birthDate = scanner.nextLine();
                    System.out.println("Give me a Sex (MALE or FEMALE): ");
                    SextType a_sex = SextType.valueOf(scanner.nextLine());
                    actorService.addActor(a_name, a_birthDate, a_sex);
                    System.out.println("Successfully registered new Actor!");
                    break;
                case 1:
                    System.out.printf("Creating Director Steps...\n");
                    System.out.println("Give me a name: ");
                    String d_name = scanner.nextLine();
                    System.out.println("Give me a birth date (YYYY-MM-DD): ");
                    String d_birthDate = scanner.nextLine();
                    System.out.println("Give me a Sex (MALE or FEMALE): ");
                    SextType d_sex = SextType.valueOf(scanner.nextLine());
                    directorService.addDirector(d_name, d_birthDate, d_sex);
                    System.out.println("Successfully registered new Director!");
                    break;
                case 2:
                    System.out.printf("Creating Movie Steps...\n");
                    break;
                case 3:
                    System.out.println("Searching Movie Steps...\n");
                    break;
                case 4:
                    List<Person> actors = actorService.findAllActors();
                    for(Person a: actors){
                        System.out.println(a);
                    }
                    break;
                case 5:
                    List<Person> directors = directorService.findAllDirectors();
                    for(Person d: directors){
                        System.out.println(d);
                    }
                    break;
                case 6:
                    System.out.println("Ok, bye!");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        } while(chosenOption != 6);
    }
}
