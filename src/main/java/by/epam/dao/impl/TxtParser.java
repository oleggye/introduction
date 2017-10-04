package by.epam.dao.impl;

import by.epam.dao.adapter.ArticleReader;
import by.epam.dao.adapter.ArticleTxtReader;
import by.epam.dao.adapter.exception.ParseException;
import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;
import org.slf4j.LoggerFactory;

import java.io.File;

public class TxtParser extends AbstractParser {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TxtParser.class);

    private static final String EXTENSION = "txt";
    private static final String DAO_EXCEPTION_MESSAGE = "Exception while parsing";

    public TxtParser() {
        super(EXTENSION);
    }

    @Override
    public Article loadArticle(File file) throws DAOException {

        ArticleReader reader = new ArticleTxtReader();
        Article article;
        try {
            article = reader.load(file);
        } catch (ParseException e) {
            LOGGER.error("Can't parse file: " + file, e);
            throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
        }

        if (article.getTitle() == null || article.getContents() == null) {
            String errorMessage = "File " + file + " cannot be parsed:  title/contents is missing";
            LOGGER.error(errorMessage);
            throw new DAOException(errorMessage);
        }
        return article;
    }
}
