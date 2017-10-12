package by.epam.service;

import by.epam.entity.Article;
import by.epam.service.exception.ServiceException;

import java.util.List;

public interface StoreService {

    void addArticle(Article article) throws ServiceException;

    void addArticles(List<Article> articles) throws ServiceException;

}
