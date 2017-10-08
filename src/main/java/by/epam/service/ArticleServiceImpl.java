package by.epam.service;

import by.epam.dao.DAOFactory;
import by.epam.dao.exception.DAOException;
import by.epam.dao.parse.Parser;
import by.epam.dao.parse.ParserType;
import by.epam.dao.repository.ArticleRepository;
import by.epam.dao.util.PropertyLoader;
import by.epam.entity.Article;
import by.epam.entity.Author;
import by.epam.exception.ServiceException;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleServiceImpl implements ArticleService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);

    private final String resourceDirectoryPath;
    private static final String ARTICLE_SERVICE_EXCEPTION_MESSAGE = "Service internal exception";

    public ArticleServiceImpl() {
        resourceDirectoryPath = PropertyLoader.getInstance().getString("resourceDirectoryPath");
    }

    @Override
    public void addArticle(Article article) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ArticleRepository repository = factory.getArticleRepository();

        try {
            repository.add(article);
        } catch (DAOException e) {
            LOGGER.error("Can't add article: " + article, e);
            throw new ServiceException(ARTICLE_SERVICE_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public void addArticles(List<Article> articles) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ArticleRepository repository = factory.getArticleRepository();

        try {
            repository.addAll(articles);
        } catch (DAOException e) {
            LOGGER.error("Can't add articles: " + articles, e);
            throw new ServiceException(ARTICLE_SERVICE_EXCEPTION_MESSAGE, e);
        }
    }

    public List<Article> getArticles() throws ServiceException {
        List<Article> articles = new LinkedList<>();

        DAOFactory factory = DAOFactory.getInstance();

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

    public List<Author> getAuthors() throws ServiceException {
        List<Article> articles = getArticles();
        return articles.stream()
                .map(Article::getAuthor)
                .distinct()
                .collect(Collectors.toList());
    }
}
