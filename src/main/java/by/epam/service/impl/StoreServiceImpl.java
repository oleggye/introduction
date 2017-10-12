package by.epam.service.impl;

import by.epam.dao.exception.DAOException;
import by.epam.dao.repository.ArticleRepository;
import by.epam.dao.repository.AuthorRepository;
import by.epam.entity.Article;
import by.epam.entity.Author;
import by.epam.service.StoreService;
import by.epam.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreServiceImpl.class);
    private static final String ARTICLE_SERVICE_EXCEPTION_MESSAGE = "Service internal exception";

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void addArticle(Article article) throws ServiceException {
        try {
            addAuthor(article.getAuthor());
            articleRepository.save(article);
        } catch (DAOException e) {
            LOGGER.error("Can't save article: " + article, e);
            throw new ServiceException(ARTICLE_SERVICE_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public void addArticles(List<Article> articles) throws ServiceException {
        try {
            List<Author> authors = getAuthorsFromArticles(articles);
            authorRepository.saveAll(authors);

            articleRepository.saveAll(articles);
        } catch (DAOException e) {
            LOGGER.error("Can't save articles: " + articles, e);
            throw new ServiceException(ARTICLE_SERVICE_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public void addAuthor(Author author) throws ServiceException {
        try {
            authorRepository.save(author);
        } catch (DAOException e) {
            LOGGER.error("Can't save author: " + author, e);
            throw new ServiceException(ARTICLE_SERVICE_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public void addAuthors(List<Author> authors) throws ServiceException {
        try {
            authorRepository.saveAll(authors);
        } catch (DAOException e) {
            LOGGER.error("Can't save authors: " + authors, e);
            throw new ServiceException(ARTICLE_SERVICE_EXCEPTION_MESSAGE, e);
        }
    }

    private List<Author> getAuthorsFromArticles(List<Article> articles){
        return articles.stream()
            .map(Article::getAuthor)
            .distinct()
            .collect(Collectors.toList());
    }
}
