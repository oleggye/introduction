package by.epam.dao.repository;

import by.epam.dao.exception.DAOException;

import java.util.List;

public interface GenericRepository<E, P> {

    public void add(E object) throws DAOException;

    public void addAll(List<E> listObject) throws DAOException;

    public void update(E object) throws DAOException;

    public void updateAll(List<E> listObject) throws DAOException;

    public void delete(E object) throws DAOException;

    public E getById(P id) throws DAOException;

    public List<E> getAll() throws DAOException;

    void deleteAll(List<E> listObject) throws DAOException;
}
