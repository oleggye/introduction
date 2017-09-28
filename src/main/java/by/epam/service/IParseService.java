package by.epam.service;

import by.epam.entity.Author;
import by.epam.exception.ServiceException;
import by.epam.entity.Article;

import java.util.List;

public interface IParseService {

    List<Article> getArticles() throws ServiceException;

    List<Author> getAuthors() throws ServiceException;

}
