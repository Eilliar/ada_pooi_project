package repository;

import entity.CareerType;

import java.util.List;

public abstract class Repository {

    List<Object> lista;

    public abstract void add(Object obj);

    public abstract Object get(String nome, CareerType carrerToCheck);

    public abstract List<Object> findAll();

}