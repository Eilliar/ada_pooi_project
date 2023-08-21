package service;

import entity.CareerType;
import entity.Movie;
import entity.SextType;
import repository.MovieRepository;
import entity.Person;

import java.io.CharArrayReader;
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
            Person director = new Person(s[6], stringToDate(s[7]), SextType.valueOf(s[8]));
            director.getCareers().add(CareerType.DIRECTOR);
            Movie movie = new Movie(s[1], stringToDate(s[2]), Double.valueOf(s[3]), s[4], director);
            movies.add(movie);
        }
        return movies;
    }

    public List<Person> listActors(String nameMovie){
        List<Person> actors = new ArrayList<>();
        List<String[]> results = movieRepository.listActors(nameMovie);
        for (String[] s: results){
            Person actor = new Person(s[0], stringToDate(s[1]), SextType.valueOf(s[2]));
            actor.getCareers().add(CareerType.ACTOR);
            actors.add(actor);
        }
        return actors;
    }

    public Person showDirector(String nameMovie){
        return movieRepository.searchMovieByName(nameMovie).getDirector();
    }

}