package by.epam.service;

import by.epam.entity.Article;
import by.epam.entity.Author;
import by.epam.exception.ServiceException;

import java.util.List;

public interface FileService {

    List<Article> readArticles() throws ServiceException;

    List<Author> readAuthors() throws ServiceException;
}
