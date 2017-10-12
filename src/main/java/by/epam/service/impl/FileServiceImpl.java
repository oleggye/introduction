package by.epam.service.impl;

import by.epam.dao.ParserFactory;
import by.epam.dao.exception.DAOException;
import by.epam.dao.parse.Parser;
import by.epam.dao.parse.ParserType;
import by.epam.dao.util.PropertyLoader;
import by.epam.entity.Article;
import by.epam.entity.Author;
import by.epam.service.exception.ServiceException;
import by.epam.service.FileService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);

    private final String resourceDirectoryPath;
    private static final String ARTICLE_SERVICE_EXCEPTION_MESSAGE = "Service internal exception";

    @Autowired
    private ParserFactory factory;

    public FileServiceImpl() {
        resourceDirectoryPath = PropertyLoader.getInstance().getString("resourceDirectoryPath");
    }

    public List<Article> readArticles() throws ServiceException {
        List<Article> articles = new LinkedList<>();

        for (ParserType parserType : ParserType.values()) {
            Parser parser = factory.getParser(parserType);

            List<Article> articleList = getArticlesByParser(parser);
            articles.addAll(articleList);
        }
        return articles;
    }

    private List<Article> getArticlesByParser(Parser parser) throws ServiceException {
        try {
            return parser.loadArticles(resourceDirectoryPath);
        } catch (DAOException e) {
            LOGGER.error("Can't get articles", e);
            throw new ServiceException(ARTICLE_SERVICE_EXCEPTION_MESSAGE, e);
        }
    }

    public List<Author> readAuthors() throws ServiceException {
        List<Article> articles = readArticles();
        return articles.stream()
                .map(Article::getAuthor)
                .distinct()
                .collect(Collectors.toList());
    }
}
