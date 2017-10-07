package by.epam.dao.repository;

import java.util.List;

public interface GenericDAO<E, P> {

    public void add(E object);

    public void addAll(List<E> listObject);

    public void update(E object);

    public void updateAll(List<E> listObject);

    public void delete(E object);

    public E getById(P id);

    public List<E> getAll();

    void deleteAll(List<E> listObject);
}
