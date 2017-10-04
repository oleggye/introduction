package by.epam.service;

import by.epam.entity.Article;
import by.epam.entity.Author;
import by.epam.exception.ServiceException;

import java.util.List;

public interface ParseService {

    List<Article> getArticles() throws ServiceException;

    List<Author> getAuthors() throws ServiceException;
}
