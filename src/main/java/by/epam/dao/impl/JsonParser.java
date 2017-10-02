package by.epam.dao.impl;

import by.epam.dao.adapter.ArticleJsonDeserializer;
import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;
import by.epam.entity.Author;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JsonParser extends AbstractParser {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String EXTENSION = "json";
    private static final String DAO_EXCEPTION_MESSAGE = "Exception while parsing";

    public JsonParser() {
        super(EXTENSION);
    }

    protected Article parse(String fileName) throws DAOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(Article.class, new ArticleJsonDeserializer());
            mapper.registerModule(module);


            Article article = mapper.readValue(new File(fileName), Article.class);

            if (article.getTitle() == null || article.getContents() == null) {
                String errorMessage = "File " + fileName + " cannot be parsed:  title/contents is missing";
                LOGGER.error(errorMessage);
                throw new DAOException(errorMessage);
            }

            Author author = registerAuthorInLocalRepo(article.getAuthor());
            article.setAuthor(author);
            author.getArticles().add(article);

            return article;
        } catch (IOException e) {
            LOGGER.error(e);
            throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
        }
    }
}
