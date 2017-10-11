package by.epam.service.impl;

import by.epam.dao.exception.DAOException;
import by.epam.dao.repository.ArticleRepository;
import by.epam.entity.Article;
import by.epam.exception.ServiceException;
import by.epam.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreServiceImpl.class);
    private static final String ARTICLE_SERVICE_EXCEPTION_MESSAGE = "Service internal exception";

    @Autowired
    private ArticleRepository repository;

    @Override
    @Transactional
    public void addArticle(Article article) throws ServiceException {
        try {
            repository.add(article);
        } catch (DAOException e) {
            LOGGER.error("Can't add article: " + article, e);
            throw new ServiceException(ARTICLE_SERVICE_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    @Transactional
    public void addArticles(List<Article> articles) throws ServiceException {
        try {
            repository.addAll(articles);
        } catch (DAOException e) {
            LOGGER.error("Can't add articles: " + articles, e);
            throw new ServiceException(ARTICLE_SERVICE_EXCEPTION_MESSAGE, e);
        }
    }
}
