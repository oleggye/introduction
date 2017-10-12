package by.epam.dao.repository;

import by.epam.dao.exception.DAOException;
import by.epam.entity.Author;

import java.util.List;

public interface AuthorRepository{

    void save(Author author) throws DAOException;

    void saveAll(List<Author> authors) throws DAOException;
}
