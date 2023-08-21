import repository.H2Utils;
import repository.MovieRepository;
import repository.PersonRepository;
import service.ActorService;
import service.DirectorService;
import service.Menu;
import service.MovieService;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        H2Utils db = new H2Utils();
        try {
            db.initializeDB();
        } catch (SQLException e) {
            System.out.println("[H2 Database] Unable 3to create tables. Closing App...");
            return;
        }

        PersonRepository personRepository = new PersonRepository();
        MovieRepository movieRepository = new MovieRepository();
        ActorService actorService = new ActorService(personRepository);
        DirectorService directorService = new DirectorService(personRepository);
        MovieService movieService = new MovieService(movieRepository);
        Menu menu = new Menu();
        menu.runUserInterface(actorService, directorService, movieService);

    }
}
