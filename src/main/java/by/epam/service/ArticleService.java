package by.epam.service;

import by.epam.entity.Article;
import by.epam.entity.Author;
import by.epam.exception.ServiceException;

import java.util.List;

public interface ArticleService {

    void addArticle(Article article) throws ServiceException;

    void addArticles(List<Article> articles) throws ServiceException;

    List<Article> getArticles() throws ServiceException;

    List<Author> getAuthors() throws ServiceException;
}
