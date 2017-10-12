package by.epam.dao.repository;

import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;

import java.util.List;

public interface ArticleRepository {

    void save(Article article) throws DAOException;

    void saveAll(List<Article> articles) throws DAOException;

    Article findById(String title) throws DAOException;

    List<Article> findAll() throws DAOException;
}
