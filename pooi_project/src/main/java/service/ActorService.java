package service;

import entity.CareerType;
import entity.SextType;
import entity.Person;
import repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static service.Utils.stringToDate;

public class ActorService {

    private PersonRepository personRepository;


    public ActorService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void addActor(String name, String birthDate, SextType gender) {

        Person actor = new Person(name, stringToDate(birthDate), gender);
        addCareer(actor, CareerType.ACTOR);
        personRepository.add(actor);
    }

    public List<Person> getActors(String name) {
        List<Person> actors = new ArrayList<>();
        List<String[]> results =  personRepository.get(name, CareerType.ACTOR);
        for(String[] r: results){
            Person actor = new Person(r[1], stringToDate(r[2]), SextType.valueOf(r[3]));
            addCareer(actor, CareerType.valueOf(r[4]));
            actors.add(actor);
        }
        return actors;
    }

    public List<Person> findAllActors() {
        List<Person> actors = new ArrayList<>();
        List<String[]> results = this.personRepository.findAll(CareerType.ACTOR);

        for(String[] r: results){
            Person actor = new Person(r[1], stringToDate(r[2]), SextType.valueOf(r[3]));
            addCareer(actor, CareerType.valueOf(r[4]));
            actors.add(actor);
        }
        return actors;
    }

    public void addCareer(Person person, CareerType career) {
        person.getCareers().add(career);
    }
}
