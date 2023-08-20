package repository;

import java.sql.*;
import java.text.SimpleDateFormat;

import entity.CareerType;
import entity.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonRepository extends Repository {

    private static final String INSERT_PERSON_SQL = "INSERT INTO persons" +
            "  (name, birth_date, gender) VALUES " +
            " (?, ?, ?);";
    private static final String INSERT_PERSON_CAREER_SQL = "INSERT INTO person_careers" +
            "  (person_id, career) VALUES " +
            " (?, ?);";
    private static final String SELECT_ALL_QUERY = "SELECT p.*, pc.career FROM persons p" +
            " LEFT JOIN person_careers pc" +
            " ON p.id = pc.person_id" +
            " ORDER BY p.id ASC";

    private static final String SELECT_QUERY = "SELECT p.*, pc.career FROM persons p" +
            " LEFT JOIN person_careers pc" +
            " ON p.id = pc.person_id" +
            " WHERE pc.career = ?" +
            " ORDER BY p.id ASC";
    private static final String FIND_QUERY = "SELECT p.*, pc.career FROM persons p" +
            " LEFT JOIN person_careers pc" +
            " ON p.id = pc.person_id" +
            " WHERE pc.career = ? AND p.name = ?";

    public PersonRepository() {
        super();
        this.lista = new ArrayList<>();
    }

    @Override
    public void add(Object obj) {

        try (Connection connection = H2Utils.getConnection();
             PreparedStatement insertPersonStatement = connection.prepareStatement(INSERT_PERSON_SQL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement insertCareerStatement = connection.prepareStatement(INSERT_PERSON_CAREER_SQL);) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(((Person) obj).getDateBirth());

            insertPersonStatement.setString(1, ((Person) obj).getName());
            insertPersonStatement.setString(2, formattedDate);
            insertPersonStatement.setString(3, ((Person) obj).getGenderType().toString());
            insertPersonStatement.executeUpdate();
            ResultSet generatedKeys = insertPersonStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                long person_id = generatedKeys.getLong(1);
                for (CareerType cType: ((Person) obj).getCareers()){
                    insertCareerStatement.setString(1, String.valueOf(person_id));
                    insertCareerStatement.setString(2, cType.toString());
                    insertCareerStatement.executeUpdate();
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public List<String[]> get(String queryName, CareerType careerType) {
        List<String[]> results = new ArrayList<>();

        try (Connection connection = H2Utils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_QUERY)) {
            preparedStatement.setString(1, careerType.toString());
            preparedStatement.setString(2, queryName);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String birthDate = rs.getString("birth_date");
                String gender = rs.getString("gender");
                String career = rs.getString("career");
                results.add(new String[] {id, name, birthDate, gender, career});
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return results;
    }

    @Override
    public List<String[]> findAll(CareerType careerType) {
        List<String[]> results = new ArrayList<>();

        try (Connection connection = H2Utils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setString(1, careerType.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String birthDate = rs.getString("birth_date");
                String gender = rs.getString("gender");
                String career = rs.getString("career");
                results.add(new String[] {id, name, birthDate, gender, career});
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return results;
    }


}
