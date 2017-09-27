package by.epam.service;

import by.epam.dao.DAOFactory;
import by.epam.dao.IParser;
import by.epam.dao.ParserType;
import by.epam.dao.exception.DAOException;
import by.epam.exception.ServiceException;
import by.epam.dao.impl.Article;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ParseService implements IParseService {

    private static final String RESOURCE_DIRECTORY_PATH = "src/main/resources";
    private static final String PARSER_SERVICE_EXCEPTION_MESSAGE = "Service internal exception";

    public List<Article> getArticles() throws ServiceException {
        List<Article> articles = new LinkedList<>();

        DAOFactory factory = DAOFactory.getIntance();

        for (ParserType parserType :
                ParserType.values()) {
            IParser parser = factory.getParser(parserType);

            List<Article> articleList = getArticlesByParser(parser);
            Collections.copy(articles, articleList);
        }
        return articles;
    }

    private List<Article> getArticlesByParser(IParser parser) throws ServiceException {
        try {
            return parser.getArticles(RESOURCE_DIRECTORY_PATH);
        } catch (DAOException e) {
            throw new ServiceException(PARSER_SERVICE_EXCEPTION_MESSAGE, e);
        }
    }

    public List<String> getAthors(List<Article> list) {
        return list.stream().map(Article::getAuthor).distinct().collect(Collectors.toList());
    }
}
