package by.epam.dao.impl;

import by.epam.dao.adapter.ArticleJsonDeserializer;
import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class JsonParser extends AbstractParser {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(JsonParser.class);

    private static final String EXTENSION = "json";
    private static final String DAO_EXCEPTION_MESSAGE = "Exception while parsing";

    public JsonParser() {
        super(EXTENSION);
    }

    public Article loadArticle(File file) throws DAOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(Article.class, new ArticleJsonDeserializer());
            mapper.registerModule(module);

            Article article = mapper.readValue(file, Article.class);

            if (article.getTitle() == null || article.getContents() == null) {
                String errorMessage = "File " + file.getName() + " cannot be parsed:  title/contents is missing";
                LOGGER.error(errorMessage);
                throw new DAOException(errorMessage);
            }

            return article;
        } catch (IOException e) {
            LOGGER.error("Can't parse file: " + file.getName(), e);
            throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
        }
    }
}
