package by.epam.dao.repository;

import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;

import java.util.List;

public interface ArticleRepository {

    void save(Article article) throws DAOException;

    void saveAll(List<Article> articles) throws DAOException;
}
