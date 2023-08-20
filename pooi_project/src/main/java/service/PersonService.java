package service;

import entity.GenderType;
import entity.Person;
import repository.PersonRepository;

import java.util.Date;

public class PersonService {

    PersonRepository personRepo;

    public static Date createDate(int year, int month, int day) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(year, month-1, day);
        return calendar.getTime();
    }

    public PersonService(PersonRepository personRepository){
        this.personRepo = personRepository;
    }

    public Person RegisterNewPerson(String name, String birthDate, GenderType gender ){
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

    public void showAllRegister(){
        this.personRepo.showAllRecords();
    }


}
