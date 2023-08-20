package repository;

import entity.CareerType;
import entity.Movie;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import entity.Person;

public class MovieRepository {
    PersonRepository personRepository = new PersonRepository();

    private static final String INSERT_MOVIE_SQL = "INSERT INTO movies" +
            " (title, launch_date, budget, description, director_id) VALUES " +
            " (?, ?, ?, ?, ?);";
    private static final String INSERT_ACTOR_MOVIES_SQL = "INSERT INTO actor_movies" +
            " (actor_id, movie_id) VALUES " +
            " (?, ?);";

    private static final String SEARCH_MOVIE_SQL = "SELECT m.* FROM movies m" +
            " WHERE m.title = ? ;" ;

    private static final String SELECT_ALL_SQL = "SELECT * FROM movies ORDER BY 1 ASC;";
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

    public List<Person> listActors(String nameMovie){
        if (searchMovieByName(nameMovie) != null){
            return searchMovieByName(nameMovie).getActors();
        }
        return null;
    }

    public Movie searchMovieByName(String nameMovie){
        for(Movie movie : movies){
            if(movie.getTitle().equalsIgnoreCase(nameMovie)){
                return movie;
            }
        }
        return null;
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

                results.add(new String[] {id, title, launch_date, budget, description, director_id});
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return results;
    }


}
