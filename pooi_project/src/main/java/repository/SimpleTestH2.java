package repository;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleTestH2 {

    public static void TestH2ConnectionMemory() {

        var url = "jdbc:h2:mem:";

        try (var con = DriverManager.getConnection(url);
             var stm = con.createStatement();
             var rs = stm.executeQuery("SELECT 1+1")) {

            if (rs.next()) {

                System.out.printf("[H2 Database] Query result: %d\n", rs.getInt(1));
                System.out.println("[H2 Database] Connection successful!");
            }

        } catch (SQLException ex) {

            var lgr = Logger.getLogger(SimpleTestH2.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}