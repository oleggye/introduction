package by.epam.service;

import by.epam.entity.Article;
import by.epam.entity.Author;
import by.epam.service.exception.ServiceException;

import java.util.List;

public interface StoreService {

    void addArticle(Article article) throws ServiceException;

    void addArticles(List<Article> articles) throws ServiceException;

    void addAuthor(Author author) throws ServiceException;

    void addAuthors(List<Author> authors) throws ServiceException;

}
