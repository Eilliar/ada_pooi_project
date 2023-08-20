import entity.GenderType;
import entity.Person;
import repository.H2Utils;
import repository.PersonRepository;
import service.ActorService;
import service.Menu;
import service.PersonService;

import java.time.LocalDate;
import java.util.Date;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        H2Utils db = new H2Utils();
        try {
            db.initializeDB();
        } catch (SQLException e) {
            System.out.println("[H2 Database] Unable to create tables. Closing App...");
            return;
        }

        PersonRepository personRepository = new PersonRepository();
        ActorService actorService = new ActorService(personRepository);
        Menu menu = new Menu();
        menu.runUserInterface(actorService);


    }
}
