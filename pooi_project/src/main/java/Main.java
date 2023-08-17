import repository.H2Utils;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        H2Utils db = new H2Utils();
        try {
            db.initializeDB();
        } catch (SQLException e) {
            System.out.println("[H2 Database] Unable to create tables. Closing App...");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int chosenOption;
        do {
            System.out.println("\nMenu:");
            System.out.println("0. Create Actor");
            System.out.println("1. Create Director");
            System.out.println("2. Create Movie");
            System.out.println("3. Search a Movie (by name)");
            System.out.println("4. Quit");
            System.out.print("Choose an option: ");
            chosenOption = scanner.nextInt();
            scanner.nextLine(); //Clear buffer
            switch (chosenOption) {
                case 0:
                    System.out.printf("Creating Actor Steps...\n");
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
                    System.out.println("Ok, bye!");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        } while(chosenOption != 4);
    }
}
