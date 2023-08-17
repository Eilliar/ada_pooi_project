package src.entity;

import java.util.Date;
import java.util.Objects;

public abstract class Person {

    private String name;
    private Date dateBirth;

    public Person(String name, Date dateBirth) {
        this.name = name;
        this.dateBirth = dateBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Person person = (Person) object;
        return Objects.equals(name, person.name) && Objects.equals(dateBirth, person.dateBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateBirth);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", dateBirth=" + dateBirth +
                '}';
    }
}