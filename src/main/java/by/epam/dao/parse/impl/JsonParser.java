package by.epam.dao.parse.impl;

import by.epam.dao.exception.DAOException;
import by.epam.dao.parse.adapter.ArticleJsonReader;
import by.epam.dao.parse.adapter.ArticleReader;
import by.epam.dao.parse.adapter.exception.ParseException;
import by.epam.entity.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class JsonParser extends AbstractParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonParser.class);

    private static final String EXTENSION = "json";
    private static final String DAO_EXCEPTION_MESSAGE = "Exception while parsing";

    public JsonParser() {
        super(EXTENSION);
    }

    public Article loadArticle(File file) throws DAOException {
        ArticleReader reader = new ArticleJsonReader();
        Article article;
        try {
            article = reader.read(file);
        } catch (ParseException e) {
            LOGGER.error("Can't parse file: " + file.getName(), e);
            throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
        }

        if (article.getTitle() == null || article.getContents() == null) {
            String errorMessage = "File " + file.getName() + " cannot be parsed:  title/contents is missing";
            LOGGER.error(errorMessage);
            throw new DAOException(errorMessage);
        }
        return article;
    }
}
