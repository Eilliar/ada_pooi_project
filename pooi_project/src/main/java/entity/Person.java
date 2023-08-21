package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Person {

    private String name;
    private Date dateBirth;
    private SextType sextType;
    private List<CareerType> careers;

    public Person(String name, Date dateBirth,  SextType sextType) {
        this.name = name;
        this.dateBirth = dateBirth;
        this.sextType = sextType;
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

    public SextType getGenderType() {
        return sextType;
    }

    public void setGenderType(SextType sextType) {
        this.sextType = sextType;
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
        return Objects.equals(name, person.name) && Objects.equals(dateBirth, person.dateBirth) && sextType == person.sextType && Objects.equals(careers, person.careers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateBirth, sextType, careers);
    }

    @Override
    public String toString() {
        List<String> careerDisplayNames = careers.stream()
                .map(CareerType::getDisplayName)
                .collect(Collectors.toList());

        return "Person{" +
                "name = '" + name + '\'' +
                ", date of birth = " + dateBirth +
                ", Sex = " + sextType.getDisplayName() +
                ", careers = " + careerDisplayNames +
                '}';
    }
}