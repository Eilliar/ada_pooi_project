package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class Movie {

    private String title;
    private Date releaseDate;
    private double budget;
    private String description;
    private Person director;
    private List<Person> actors;

    public Movie(String title, Date releaseDate, double budget, String description, Person director){
        this.title = title;
        this.releaseDate = releaseDate;
        this.budget = budget;
        this.description = description;
        this.director = director;
        this.actors = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getDirector() {
        return director;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    public List<Person> getActors() {
        return actors;
    }

    public void setActors(List<Person> actors) {
        this.actors = actors;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, releaseDate, budget, description, director);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Movie other = (Movie) obj;
        return Objects.equals(title, other.title) && Objects.equals(releaseDate, other.releaseDate);
    }

    @Override
    public String toString() {
        return "Filme: " + title + ", Data de lançamento: " + releaseDate + ", Orçamento: " + budget + ", Diretor: " + director +
                "Atores: "+ actors + "Descrição: " + description;
    }
}
