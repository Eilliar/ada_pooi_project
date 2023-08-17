package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Utils {

    private static String jdbcURL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static String jdbcUsername = "sa";
    private static String jdbcPassword = "";

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
        String createActorSQL = """
                CREATE TABLE IF NOT EXISTS actors (\s
                   id INT auto_increment PRIMARY KEY,\s
                   name VARCHAR(50) NOT NULL,\s
                   birth_date DATE NOT NULL\s
                );""";

        String createDirectorSQL = """
                CREATE TABLE IF NOT EXISTS directors (\s
                   id INT auto_increment PRIMARY KEY,\s
                   name VARCHAR(50) NOT NULL,\s
                   birth_date DATE NOT NULL\s
                );""";

        String createMoviesSQL = """
                CREATE TABLE IF NOT EXISTS movies (\s
                   id INT auto_increment PRIMARY KEY,\s
                   title VARCHAR(50) NOT NULL,\s
                   launch_date DATE NOT NULL,\s
                   budget NUMERIC NOT NULL,\s
                   description VARCHAR(255),\s
                   director_id INT,
                   foreign key (director_id) references directors(id)
                );""";

        System.out.println("[H2 Database] Creating actors table...");
        this.createTable(createActorSQL);
        System.out.println("[H2 Database] Successfully created actors table!");
        System.out.println("[H2 Database] Creating directors table...");
        this.createTable(createDirectorSQL);
        System.out.println("[H2 Database] Successfully created directors table!");
        System.out.println("[H2 Database] Creating movies table...");
        this.createTable(createMoviesSQL);
        System.out.println("[H2 Database] Successfully created movies table!");
    }
}
