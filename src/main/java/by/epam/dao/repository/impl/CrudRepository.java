package by.epam.dao.repository.impl;

import by.epam.dao.repository.GenericDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public abstract class CrudRepository<E, PK extends Serializable> implements GenericDAO<E, PK> {

    private static final String FORMAT_PATTERN = "select E from %s E";

    protected EntityManager manager;

    private final Class<E> clazz;

    protected CrudRepository(Class<E> clazz, EntityManager manager) {
        this.clazz = clazz;
        this.manager = manager;
    }

    public E getById(PK id) {
        return manager.find(clazz, id);
    }

    public List<E> getAll() {
        String queryString = String.format(FORMAT_PATTERN, clazz.getSimpleName());
        TypedQuery<E> query =
                manager.createQuery(queryString, clazz);
        return query.getResultList();
    }

    public void add(E entity) {
        if (manager.contains(entity)) {
            manager.merge(entity);
        } else
            manager.persist(entity);
        manager.flush();
    }

    public void addAll(List<E> listObject) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        for (E object : listObject) {
            manager.persist(object);
        }
        transaction.commit();
    }

    @Override
    public void update(E object) {
        manager.merge(object);
        manager.flush();
    }

    @Override
    public void updateAll(List<E> listObject) {
        for (E object : listObject) {
            manager.merge(object);
        }
    }

    @Override
    public void delete(E object) {
        manager.remove(manager.contains(object) ? object : manager.merge(object));
        manager.flush();
    }

    @Override
    public void deleteAll(List<E> listObject) {
        for (E object : listObject) {
            manager.remove(manager.contains(object) ? object : manager.merge(object));
        }
        manager.flush();
    }
}
