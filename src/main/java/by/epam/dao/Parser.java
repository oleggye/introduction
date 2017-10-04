package by.epam.dao;

import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;

import java.io.File;
import java.util.List;

public interface Parser {

    Article loadArticle(File file) throws DAOException;

    List<Article> loadArticles(String directory) throws DAOException;
}
