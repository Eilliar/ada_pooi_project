package service;

import entity.Movie;
import entity.Person;
import entity.SextType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public void runUserInterface(ActorService actorService, DirectorService directorService, MovieService movieService){
        Scanner scanner = new Scanner(System.in);
        int chosenOption;
        do {
            System.out.println("\nMenu:");
            System.out.println("0. Create Actor");
            System.out.println("1. Create Director");
            System.out.println("2. Create Movie");
            System.out.println("3. Show all Movies");
            System.out.println("4. Show all Actors");
            System.out.println("5. Show all Directors");
            System.out.println("6. Search Actor (by name)");
            System.out.println("7. Search Director (by name)");
            System.out.println("8. Search Movie (by name)");
            System.out.println("9. Quit");
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
                    addMovieFlow(scanner, directorService, actorService, movieService);
                    break;
                case 3:
                    System.out.printf("Listing all movies...\n");
                    List<Movie> movies = movieService.listMovies();
                    for(Movie m: movies){
                        System.out.println(m);
                        System.out.println("Actors:");
                        List<Person> actors = movieService.listActors(m.getTitle());
                        for(Person a: actors){
                            System.out.println(a);
                        }
                    }
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
                    System.out.println("Give me a actor name to search for: ");
                    String sa_name = scanner.nextLine();
                    List<Person> actorSearch = actorService.getActors(sa_name);
                    for(Person a: actorSearch){
                        System.out.println(a);
                    }
                    break;
                case 7:
                    System.out.println("Give me a director name to search for: ");
                    String sd_name = scanner.nextLine();
                    List<Person> directorSearch = directorService.getDirector(sd_name);
                    for(Person a: directorSearch){
                        System.out.println(a);
                    }
                    break;
                case 8:
                    System.out.println("Give me a movie title to search for: ");
                    String title = scanner.nextLine();
                    List<Movie> moviesFound = movieService.searchMovie(title);
                    for(Movie m: moviesFound){
                        System.out.println(m);
                        System.out.println("Actors:");
                        List<Person> actorsFound = movieService.listActors(m.getTitle());
                        for(Person a: actorsFound){
                            System.out.println(a);
                        }
                    }
                    break;
                case 9:
                    System.out.println("Ok, Bye!");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        } while(chosenOption != 9);
    }

    void addMovieFlow(Scanner scanner, DirectorService directorService, ActorService actorService, MovieService movieService){

        // Movie Attributes
        System.out.printf("Creating Movie Steps...\n");
        System.out.println("Give me a Title: ");
        String title = scanner.nextLine();
        System.out.println("Give me a release date (YYYY-MM-DD): ");
        String releaseDate = scanner.nextLine();
        System.out.println("Give me a budget: ");
        double budget = Double.parseDouble(scanner.nextLine());
        System.out.println("Give me a description: ");
        String description = scanner.nextLine();

        // Director
        System.out.println("Give me a director name to search for: ");
        String directorName = scanner.nextLine();
        List<Person> directorsFound = directorService.getDirector(directorName);
        Person directorToAdd = null;
        for(Person d: directorsFound){
            System.out.println("Found this director:");
            System.out.println(d);
            String chosenOption;
            do {
                System.out.println("Add it as a director (y/n)? ");
                chosenOption = scanner.nextLine();
                switch (chosenOption.toLowerCase()) {
                    case "y":
                        directorToAdd = d;
                        break;
                    case "n":
                        System.out.println("OK, searching for next director with the same name...");
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } while(chosenOption == "y" || chosenOption == "n");
            if (directorToAdd != null) {
                break;
            }
        }
        if (directorToAdd == null) {
            System.out.println("Unable to add a director, you need to have one! Try again.");
            return;
        }

        // Actors
        List<Person> actorsToAdd = new ArrayList<>();
        boolean continueAdding = false;
        do {
            System.out.println("Give me an actor name to search for: ");
            String actorName = scanner.nextLine();
            List<Person> actorsFound = actorService.getActors(actorName);
            String chosenOption;
            for (Person a : actorsFound) {
                System.out.println("Found this actor:");
                System.out.println(a);

                do {
                    System.out.println("Add it as an actor (y/n)? ");
                    chosenOption = scanner.nextLine();
                    switch (chosenOption.toLowerCase()) {
                        case "y":
                            actorsToAdd.add(a);
                            break;
                        case "n":
                            System.out.println("OK, searching for next actor with the same name...");
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                } while (chosenOption == "y" || chosenOption == "n");
            }

            do{
                System.out.println("Do you want to add more actors (y/n)? ");
                chosenOption = scanner.nextLine();
                switch (chosenOption){
                    case "y":
                        continueAdding = true;
                        break;
                    case "n":
                        continueAdding = false;
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            }while(chosenOption == "y" || chosenOption == "n");

        } while(continueAdding);

        if (actorsToAdd.isEmpty()) {
            System.out.println("Unable to add an actor, you need at least one! Try again");
            return;
        }

        movieService.registerMovie(title, releaseDate, budget, description, directorToAdd, actorsToAdd);
        System.out.println("Successfully registered new Movie!");
    }
}
