package by.epam.dao.repository;

import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;

import java.util.List;

public interface ArticleRepository {

    void add(Article article) throws DAOException;

    void addAll(List<Article> articles) throws DAOException;

    Article getById(String title) throws DAOException;

    List<Article> getAll() throws DAOException;
}
