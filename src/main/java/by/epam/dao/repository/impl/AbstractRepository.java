package by.epam.dao.repository.impl;

import by.epam.dao.exception.DAOException;
import by.epam.dao.repository.GenericRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
public abstract class AbstractRepository<E, K extends Serializable> implements GenericRepository<E, K> {

    private static final String SELECT_ALL_FORMAT_PATTERN = "select E from %s E";

    private final Class<E> clazz;

    @PersistenceContext
    protected EntityManager manager;

    protected AbstractRepository(Class<E> clazz) {
        this.clazz = clazz;
    }

    @Override
    public E findById(K id) throws DAOException {
        return manager.find(clazz, id);
    }

    @Override
    public List<E> findAll() throws DAOException {
        String queryString = String.format(SELECT_ALL_FORMAT_PATTERN, clazz.getSimpleName());
        return manager.createQuery(queryString, clazz)
            .getResultList();
    }

    @Override
    public void save(E entity) throws DAOException {
        if (manager.contains(entity)) {
            manager.merge(entity);
        } else
            manager.persist(entity);
    }

    @Override
    public void saveAll(List<E> listObject) throws DAOException {
        for (E object : listObject) {
            save(object);
        }
    }

    @Override
    public void update(E object) throws DAOException {
        manager.merge(object);
    }

    @Override
    public void updateAll(List<E> listObject) throws DAOException {
        for (E object : listObject) {
            update(object);
        }
    }

    @Override
    public void delete(E object) throws DAOException {
        manager.remove(manager.contains(object) ? object : manager.merge(object));

    }

    @Override
    public void deleteAll(List<E> listObject) throws DAOException {
        for (E object : listObject) {
            delete(object);
        }
    }
}
