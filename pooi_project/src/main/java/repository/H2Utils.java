package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Utils {

    private static String jdbcURL = "jdbc:h2:file:./TestDataBase;DB_CLOSE_DELAY=-1";
    private static String jdbcUsername = "ada";
    private static String jdbcPassword = "santander";

    static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    void createTable(String createTableSQL) throws SQLException {
        try (Connection connection = this.getConnection();
             Statement statement = connection.createStatement();) {

            statement.execute(createTableSQL);

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void initializeDB() throws SQLException {
        String createPersonSQL = """
                CREATE TABLE IF NOT EXISTS persons (\s
                   id INT auto_increment PRIMARY KEY,\s
                   name VARCHAR(50) NOT NULL,\s
                   birth_date DATE NOT NULL,\s
                   gender VARCHAR(50) NOT NULL
                );""";


        String createMoviesSQL = """
                CREATE TABLE IF NOT EXISTS movies (\s
                   id INT auto_increment PRIMARY KEY,\s
                   title VARCHAR(50) NOT NULL,\s
                   launch_date DATE NOT NULL,\s
                   budget NUMERIC NOT NULL,\s
                   description VARCHAR(255),\s
                   director_id INT,
                   foreign key (director_id) references persons(id)
                );""";

        System.out.println("[H2 Database] Creating persons table...");
        this.createTable(createPersonSQL);
        System.out.println("[H2 Database] Successfully created persons table!");
        System.out.println("[H2 Database] Creating movies table...");
        this.createTable(createMoviesSQL);
        System.out.println("[H2 Database] Successfully created movies table!");
    }

}
