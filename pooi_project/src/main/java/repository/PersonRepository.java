package repository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import entity.CareerType;
import entity.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonRepository extends Repository{

    private static final String INSERT_PERSON_SQL = "INSERT INTO persons" +
            "  (name, birth_date, gender) VALUES " +
            " (?, ?, ?);";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM persons";

    public PersonRepository() {
        super();
        this.lista = new ArrayList<>();
    }

    @Override
    public void add(Object obj) {

        try (Connection connection = H2Utils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PERSON_SQL)) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(((Person) obj).getDateBirth());

            preparedStatement.setString(1, ((Person) obj).getName());
            preparedStatement.setString(2, formattedDate);
            preparedStatement.setString(3, ((Person) obj).getGenderType().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public Person get(String nome, CareerType carrerToCheck) {
        for (Object obj : lista) {
            if (obj instanceof Person person) {
                if (person.getName().equals(nome) && person.getCareers().contains(carrerToCheck)) {
                    return person;
                }
            }
        }
        return null;
    }

    @Override
    public List<Object> findAll() {
        return Collections.unmodifiableList(this.lista);
    }

    public void showAllRecords(){
        try (Connection connection = H2Utils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);) {
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String birthDate = rs.getString("birth_date");
                    String gender = rs.getString("gender");
                    System.out.println(id + "," + name + "," + birthDate + "," + gender );
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
    }

    public void addCareer(Person person, CareerType career) {
        person.getCareers().add(career);
    }
}
