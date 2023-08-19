package service;

import entity.Movie;
import repository.MovieRepository;
import src.entity.Person;

import java.util.Date;
import java.util.List;

public class MovieService{

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public void registerMovie(Movie movie){
        movieRepository.addMovie(movie);
    }

    public List<Movie> listMovies(){
        return movieRepository.listMovies();
    }

    public List<Person> listActors(String nameMovie){
        return movieRepository.listActors(nameMovie);
    }

    public Person showDirector(String nameMovie){
        return movieRepository.searchMovieByName(nameMovie).getDirector();
    }

}