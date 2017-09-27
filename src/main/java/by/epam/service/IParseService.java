package by.epam.service;

import by.epam.exception.ServiceException;
import by.epam.dao.impl.Article;

import java.util.List;

public interface IParseService {

    public List<Article> getArticles() throws ServiceException;

    public List<String> getAthors(List<Article> list);

}
