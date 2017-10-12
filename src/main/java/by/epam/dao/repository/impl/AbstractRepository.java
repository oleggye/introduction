package by.epam.dao.repository.impl;

import by.epam.dao.exception.DAOException;
import by.epam.dao.repository.GenericRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
public abstract class AbstractRepository<E, K extends Serializable> implements GenericRepository<E, K> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRepository.class);

    private static final String SELECT_ALL_FORMAT_PATTERN = "select E from %s E";

    private final Class<E> clazz;

    @PersistenceContext
    protected EntityManager manager;

    protected AbstractRepository(Class<E> clazz) {
        this.clazz = clazz;
    }

    @Override
    public E findById(K id) throws DAOException {
        LOGGER.debug(clazz.getSimpleName() + " findById: " + id);
        return manager.find(clazz, id);
    }

    @Override
    public List<E> findAll() throws DAOException {
        LOGGER.debug(clazz.getSimpleName() + " findAll: ");
        String queryString = String.format(SELECT_ALL_FORMAT_PATTERN, clazz.getSimpleName());
        return manager.createQuery(queryString, clazz)
            .getResultList();
    }

    @Override
    public void save(E entity) throws DAOException {
        LOGGER.debug(clazz.getSimpleName() + " save: " + entity);
        if (manager.contains(entity)) {
            manager.merge(entity);
        } else
            manager.persist(entity);
    }

    @Override
    public void saveAll(List<E> listObject) throws DAOException {
        LOGGER.debug(clazz.getSimpleName() + " saveAll:");
        for (E object : listObject) {
            save(object);
        }
    }

    @Override
    public void update(E entity) throws DAOException {
        LOGGER.debug(clazz.getSimpleName() + " update: " + entity);
        manager.merge(entity);
    }

    @Override
    public void updateAll(List<E> listObject) throws DAOException {
        LOGGER.debug(clazz.getSimpleName() + " updateAll:");
        for (E object : listObject) {
            update(object);
        }
    }

    @Override
    public void delete(E entity) throws DAOException {
        LOGGER.debug(clazz.getSimpleName() + " delete: " + entity);
        manager.remove(manager.contains(entity) ? entity : manager.merge(entity));

    }

    @Override
    public void deleteAll(List<E> listObject) throws DAOException {
        LOGGER.debug(clazz.getSimpleName() + " deleteAll:");
        for (E object : listObject) {
            delete(object);
        }
    }
}
