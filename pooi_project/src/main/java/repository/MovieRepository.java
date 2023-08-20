package repository;

import entity.Movie;

import java.util.ArrayList;
import java.util.List;
import entity.Person;

public class MovieRepository {

    private List<Movie> movies;

    public MovieRepository(){
        movies = new ArrayList<>();
    }

    public void addMovie(Movie movie){
        this.movies.add(movie);
    }

    public List<Person> listActors(String nameMovie){
        if (searchMovieByName(nameMovie) != null){
            return searchMovieByName(nameMovie).getActors();
        }
        return null;
    }

    public Movie searchMovieByName(String nameMovie){
        for(Movie movie : movies){
            if(movie.getName().equalsIgnoreCase(nameMovie)){
                return movie;
            }
        }
        return null;
    }

    public List<Movie> listMovies(){
        return new ArrayList<>(movies);
    }


}
