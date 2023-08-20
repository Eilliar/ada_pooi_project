package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Person {

    private String name;
    private Date dateBirth;
    private GenderType genderType;
    private List<CareerType> careers;

    public Person(String name, Date dateBirth,  GenderType genderType) {
        this.name = name;
        this.dateBirth = dateBirth;
        this.genderType = genderType;
        this.careers = new ArrayList<CareerType>();
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

    public GenderType getGenderType() {
        return genderType;
    }

    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }

    public List<CareerType> getCareers() {
        return careers;
    }

    public void setCareers(List<CareerType> careers) {
        this.careers = careers;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Person person = (Person) object;
        return Objects.equals(name, person.name) && Objects.equals(dateBirth, person.dateBirth) && genderType == person.genderType && Objects.equals(careers, person.careers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateBirth, genderType, careers);
    }

    @Override
    public String toString() {
        List<String> careerDisplayNames = careers.stream()
                .map(CareerType::getDisplayName)
                .collect(Collectors.toList());

        return "Person{" +
                "name='" + name + '\'' +
                ", dateBirth=" + dateBirth +
                ", genderType=" + genderType.getDisplayName() +
                ", careers=" + careerDisplayNames +
                '}';
    }
}