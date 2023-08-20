package repository;

import entity.CareerType;

import java.util.List;

public abstract class Repository {

    List<Object> lista;

    public abstract void add(Object obj);

    public abstract List<String[]> get(String nome, CareerType careerToCheck);

    public abstract List<String[]> findAll(CareerType career);

}