package repository;

import entity.CareerType;
import entity.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonRepository extends Repository{

    public PersonRepository() {
        super();
        this.lista = new ArrayList<>();
    }

    @Override
    public void add(Object obj) {
        this.lista.add(obj);
    }

    @Override
    public Person get(String nome, CareerType carrerToCheck) {
        for (Object obj : lista) {
            if (obj instanceof Person person) {
                if (person.getName().equals(nome) && person.getCareers().contains(carrerToCheck)) {
                    return person;
                }
            }
        }
        return null;
    }

    @Override
    public List<Object> findAll() {
        return Collections.unmodifiableList(this.lista);
    }

    public void addCareer(Person person, CareerType career) {
        person.getCareers().add(career);
    }
}
