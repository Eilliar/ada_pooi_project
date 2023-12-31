package service;

import entity.CareerType;
import entity.SextType;
import entity.Person;
import repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

import static service.Utils.stringToDate;

public class DirectorService {

    private PersonRepository personRepository;

    public DirectorService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void addDirector(String name, String birthDate, SextType gender) {
        Person director = new Person(name, stringToDate(birthDate), gender);
        addCareer(director, CareerType.DIRECTOR);
        personRepository.add(director);
    }

    public List<Person> getDirector(String name) {
        List<Person> directors = new ArrayList<>();
        List<String[]> results =  personRepository.get(name, CareerType.DIRECTOR);
        for(String[] r: results){
            Person director = new Person(r[1], stringToDate(r[2]), SextType.valueOf(r[3]));
            addCareer(director, CareerType.valueOf(r[4]));
            directors.add(director);
        }
        return directors;
    }

    public List<Person> findAllDirectors() {
        List<Person> directors = new ArrayList<>();
        List<String[]> results = this.personRepository.findAll(CareerType.DIRECTOR);

        for(String[] r: results){
            Person director = new Person(r[1], stringToDate(r[2]), SextType.valueOf(r[3]));
            addCareer(director, CareerType.valueOf(r[4]));
            directors.add(director);
        }
        return directors;
    }

    public void addCareer(Person person, CareerType career) {
        person.getCareers().add(career);
    }
}
