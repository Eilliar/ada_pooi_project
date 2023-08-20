package service;

import entity.CareerType;
import entity.GenderType;
import entity.Person;
import repository.PersonRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DirectorService {

    private PersonRepository personRepository;

    public DirectorService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void addDirector(String name, Date dateBirth, GenderType gender) {
        Person director = new Person(name, dateBirth , gender);
        personRepository.addCareer(director, CareerType.DIRECTOR);
        personRepository.add(director);
    }

    public Person getDirector(String name) {
        return personRepository.get(name, CareerType.DIRECTOR);
    }

    public List<Object> findAllDirectors() {
        return this.personRepository.findAll()
                .stream()
                .filter(person -> person instanceof Person)
                .map(person -> (Person) person)
                .filter(person -> person.getCareers().contains(CareerType.DIRECTOR))
                .collect(Collectors.toList());
    }

    public void addCareer(Person person, CareerType career) {
        person.getCareers().add(career);
    }
}
