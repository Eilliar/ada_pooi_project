package service;

import entity.CareerType;
import entity.Movie;
import repository.MovieRepository;
import entity.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static service.Utils.stringToDate;

public class MovieService{

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public void registerMovie(String title, String releaseDate, double budget, String description, Person director, List<Person> actors ){
        Movie movie = new Movie(title, stringToDate(releaseDate), budget, description, director);
        movie.setActors(actors);
        movieRepository.addMovie(movie);
    }

    public List<Movie> listMovies(){
        List<Movie> movies = new ArrayList<>();
        List<String[]> results = this.movieRepository.listMovies();
        for (String[] s: results){
            System.out.println(Arrays.stream(s).toList());
        }

        return movies;
    }

    public List<Person> listActors(String nameMovie){
        return movieRepository.listActors(nameMovie);
    }

    public Person showDirector(String nameMovie){
        return movieRepository.searchMovieByName(nameMovie).getDirector();
    }

}