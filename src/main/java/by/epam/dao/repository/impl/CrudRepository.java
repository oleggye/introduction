package by.epam.dao.repository.impl;

import by.epam.dao.exception.DAOException;
import by.epam.dao.repository.GenericDAO;
import by.epam.dao.util.TransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import static by.epam.dao.util.TransactionManager.getInstance;

public abstract class CrudRepository<E, PK extends Serializable> implements GenericDAO<E, PK> {

    private static final String FORMAT_PATTERN = "select E from %s E";

    private final Class<E> clazz;
    private final EntityManager manager;

    protected CrudRepository(Class<E> clazz) {
        this.clazz = clazz;
        this.manager = getInstance().getEntityManager();
    }

    public E getById(PK id) throws DAOException {
        getInstance().beginTransaction();

        E entity = manager.find(clazz, id);

        getInstance().endTransaction();

        return entity;
    }

    public List<E> getAll() throws DAOException {
        getInstance().beginTransaction();

        String queryString = String.format(FORMAT_PATTERN, clazz.getSimpleName());
        TypedQuery<E> query =
                manager.createQuery(queryString, clazz);

        getInstance().endTransaction();

        return query.getResultList();
    }

    public void add(E entity) throws DAOException {
        getInstance().beginTransaction();

        if (manager.contains(entity)) {
            manager.merge(entity);
        } else
            manager.persist(entity);
        //manager.flush();
        getInstance().endTransaction();
    }

    public void addAll(List<E> listObject) throws DAOException {
        getInstance().beginTransaction();

        for (E object : listObject) {
            add(object);
            //manager.persist(object);
        }
        getInstance().endTransaction();
    }

    @Override
    public void update(E object) throws DAOException {
        getInstance().beginTransaction();

        manager.merge(object);
        // manager.flush();

        getInstance().endTransaction();
    }

    @Override
    public void updateAll(List<E> listObject) throws DAOException {
        getInstance().beginTransaction();

        for (E object : listObject) {
            update(object);
            //manager.merge(object);
        }
        getInstance().endTransaction();
    }

    @Override
    public void delete(E object) throws DAOException {
        getInstance().beginTransaction();

        manager.remove(manager.contains(object) ? object : manager.merge(object));
        //manager.flush();

        getInstance().endTransaction();
    }

    @Override
    public void deleteAll(List<E> listObject) throws DAOException {
        getInstance().beginTransaction();

        for (E object : listObject) {
            //manager.remove(manager.contains(object) ? object : manager.merge(object));
            delete(object);
        }
        //manager.flush();
        getInstance().endTransaction();
    }
}
