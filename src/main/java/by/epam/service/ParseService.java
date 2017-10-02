package by.epam.service;

import by.epam.dao.DAOFactory;
import by.epam.dao.IParser;
import by.epam.dao.ParserType;
import by.epam.dao.exception.DAOException;
import by.epam.entity.Author;
import by.epam.exception.ServiceException;
import by.epam.entity.Article;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class ParseService implements IParseService {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String RESOURCE_DIRECTORY_PATH = "src/main/resources/files";
    private static final String PARSER_SERVICE_EXCEPTION_MESSAGE = "Service internal exception";

    public List<Article> getArticles() throws ServiceException {
        List<Article> articles = new LinkedList<>();

        DAOFactory factory = DAOFactory.getInstance();

        for (ParserType parserType :
                ParserType.values()) {
            IParser parser = factory.getParser(parserType);

            List<Article> articleList = getArticlesByParser(parser);
            articles.addAll(articleList);
        }
        return articles;
    }

    private List<Article> getArticlesByParser(IParser parser) throws ServiceException {
        try {
            return parser.getArticles(RESOURCE_DIRECTORY_PATH);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(PARSER_SERVICE_EXCEPTION_MESSAGE, e);
        }
    }

    public List<Author> getAuthors() throws ServiceException {
        List<Article> articles = getArticles();
        return articles.stream()
                .map(Article::getAuthor)
                .distinct()
                .collect(Collectors.toList());
    }
}
