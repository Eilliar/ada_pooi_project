package repository;

import entity.CareerType;
import entity.Movie;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Person;

public class MovieRepository {
    PersonRepository personRepository = new PersonRepository();

    private static final String INSERT_MOVIE_SQL = "INSERT INTO movies" +
            " (title, launch_date, budget, description, director_id) VALUES " +
            " (?, ?, ?, ?, ?);";
    private static final String INSERT_ACTOR_MOVIES_SQL = "INSERT INTO actor_movies" +
            " (actor_id, movie_id) VALUES " +
            " (?, ?);";

    private static final String SEARCH_MOVIE_SQL = "SELECT m.*, p.name, p.birth_date, p.gender  " +
            " FROM movies m LEFT JOIN persons p" +
            " ON m.director_id = p.id" +
            " WHERE LOWER(m.title) = LOWER(?) ;" ;

    private static final String SELECT_ALL_SQL = "SELECT m.*, p.name, p.birth_date, p.gender " +
            " FROM movies m LEFT JOIN persons p " +
            " ON m.director_id = p.id" +
            " ORDER BY 1 ASC;";

    private static final String SELECT_LIST_ACTORS_SQL = "SELECT  p.name, p.birth_date, p.gender" +
            " FROM movies m LEFT JOIN actor_movies am" +
            " ON m.id = am.movie_id" +
            " LEFT JOIN persons p " +
            " ON am.actor_id = p.id " +
            " WHERE m.title = ?" +
            " ;";
    private List<Movie> movies;

    public MovieRepository(){
        movies = new ArrayList<>();
    }

    public void addMovie(Movie movie){
        try (Connection connection = H2Utils.getConnection();
             PreparedStatement insertMovieStatement = connection.prepareStatement(INSERT_MOVIE_SQL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement insertActorMoviesStatement = connection.prepareStatement(INSERT_ACTOR_MOVIES_SQL)) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            List<String[]> director = personRepository.get(movie.getDirector().getName(), CareerType.DIRECTOR);

            insertMovieStatement.setString(1, movie.getTitle());
            insertMovieStatement.setString(2, dateFormat.format(movie.getReleaseDate()));
            insertMovieStatement.setDouble(3, movie.getBudget());
            insertMovieStatement.setString(4, movie.getDescription());
            insertMovieStatement.setString(5, director.get(0)[0]);
            insertMovieStatement.executeUpdate();
            ResultSet generatedKeys = insertMovieStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                long movie_id = generatedKeys.getLong(1);
                for (Person actors: movie.getActors()){
                    List<String[]> actorsToAdd = personRepository.get(actors.getName(), CareerType.ACTOR);
                    for(String[] a: actorsToAdd){
                        insertActorMoviesStatement.setString(1, a[0]);
                        insertActorMoviesStatement.setLong(2, movie_id);
                    }
                    insertActorMoviesStatement.executeUpdate();
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<String[]> listActors(String nameMovie){
        List<String[]> results = new ArrayList<>();

        try (Connection connection = H2Utils.getConnection();
             PreparedStatement selectActorsStatement = connection.prepareStatement(SELECT_LIST_ACTORS_SQL)) {

            selectActorsStatement.setString(1, nameMovie);

            ResultSet rs = selectActorsStatement.executeQuery();

            while (rs.next()) {
                String actor_name = rs.getString("name");
                String birth_date = rs.getString("birth_date");
                String sex = rs.getString("gender");
                results.add(new String[] {actor_name, birth_date, sex});
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return results;
    }

    public List<String[]> searchMovieByName(String movieName){

        List<String[]> results = new ArrayList<>();

        try (Connection connection = H2Utils.getConnection();
             PreparedStatement searchMovieStatement = connection.prepareStatement(SEARCH_MOVIE_SQL)) {
            searchMovieStatement.setString(1, movieName);
            ResultSet rs = searchMovieStatement.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String title = rs.getString("title");
                String launch_date = rs.getString("launch_date");
                String budget = rs.getString("budget");
                String description = rs.getString("description");
                String director_id = rs.getString("director_id");
                String director_name = rs.getString("name");
                String director_birth = rs.getString("birth_date");
                String director_gender = rs.getString("gender");

                results.add(new String[] {id, title, launch_date, budget, description, director_id, director_name, director_birth, director_gender});
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return results;
    }

    public List<String[]> listMovies(){
        List<String[]> results = new ArrayList<>();

        try (Connection connection = H2Utils.getConnection();
             PreparedStatement selectMoviesStatement = connection.prepareStatement(SELECT_ALL_SQL)) {
            ResultSet rs = selectMoviesStatement.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String title = rs.getString("title");
                String launch_date = rs.getString("launch_date");
                String budget = rs.getString("budget");
                String description = rs.getString("description");
                String director_id = rs.getString("director_id");
                String director_name = rs.getString("name");
                String director_birth = rs.getString("birth_date");
                String director_gender = rs.getString("gender");

                results.add(new String[] {id, title, launch_date, budget, description, director_id, director_name, director_birth, director_gender});
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return results;
    }


}
