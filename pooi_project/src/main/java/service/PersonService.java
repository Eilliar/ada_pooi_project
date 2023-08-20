package service;

import entity.SextType;
import entity.Person;
import repository.PersonRepository;

import static service.Utils.createDate;

public class PersonService {

    PersonRepository personRepo;

    public PersonService(PersonRepository personRepository){
        this.personRepo = personRepository;
    }

    public Person RegisterNewPerson(String name, String birthDate, SextType gender ){
        String[] splitArray = birthDate.split("-");
        int[] birthArray = new int[splitArray.length];
        for(int i=0; i < splitArray.length; i++){
            birthArray[i] = Integer.valueOf(splitArray[i]);
        }
        Person p1 = new Person(name,
                createDate(birthArray[0], birthArray[1], birthArray[2]), gender);
        this.personRepo.add(p1);

        return p1;
    }

}
