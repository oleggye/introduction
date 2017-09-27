package by.epam.dao;

import by.epam.dao.exception.DAOException;
import by.epam.dao.impl.Article;

import java.util.List;

public interface IParser {
    List<Article> getArticles(String directory) throws DAOException;
}
