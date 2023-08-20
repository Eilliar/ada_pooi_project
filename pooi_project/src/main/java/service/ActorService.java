package service;

import entity.CareerType;
import entity.GenderType;
import entity.Person;
import repository.PersonRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ActorService {

    private PersonRepository personRepository;

    public ActorService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void addActor(String name, Date dateBirth, GenderType gender) {
        Person actor = new Person(name, dateBirth , gender);
        personRepository.addCareer(actor, CareerType.ACTOR);
        personRepository.add(actor);
    }

    public Person getActor(String name) {
        return personRepository.get(name, CareerType.ACTOR);
    }

    public List<Object> findAllActors() {
        return this.personRepository.findAll()
                .stream()
                .filter(person -> person instanceof Person)
                .map(person -> (Person) person)
                .filter(person -> person.getCareers().contains(CareerType.ACTOR))
                .collect(Collectors.toList());
    }

    public void addCareer(Person person, CareerType career) {
        person.getCareers().add(career);
    }
}
