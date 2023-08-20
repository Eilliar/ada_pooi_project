package service;

import entity.CareerType;
import entity.GenderType;
import entity.Person;
import repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;

import static service.Utils.createDate;

public class ActorService {

    private PersonRepository personRepository;


    public ActorService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void addActor(String name, String birthDate, GenderType gender) {

        String[] splitArray = birthDate.split("-");
        int[] birthArray = new int[splitArray.length];
        for(int i=0; i < splitArray.length; i++){
            birthArray[i] = Integer.valueOf(splitArray[i]);
        }

        Person actor = new Person(name, createDate(birthArray[0], birthArray[1], birthArray[2]) , gender);
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

    public void showAllRecords(){
        this.personRepository.showAllRecords();
    }
    public void addCareer(Person person, CareerType career) {
        person.getCareers().add(career);
    }
}
