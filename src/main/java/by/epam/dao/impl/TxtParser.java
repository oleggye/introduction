package by.epam.dao.impl;

import by.epam.dao.adapter.ArticleTxtDeserializer;
import by.epam.dao.adapter.exception.ParseException;
import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;
import by.epam.entity.Author;
import org.slf4j.LoggerFactory;

public class TxtParser extends AbstractParser {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TxtParser.class);

    private static final String EXTENSION = "txt";
    private static final String DAO_EXCEPTION_MESSAGE = "Exception while parsing";

    public TxtParser() {
        super(EXTENSION);
    }

    @Override
    protected Article parse(String fileName) throws DAOException {

        ArticleTxtDeserializer deserializer = new ArticleTxtDeserializer();
        Article article;
        try {
            article = deserializer.deserialize(fileName);
        } catch (ParseException e) {
            LOGGER.error("Can't parse file: " + fileName, e);
            throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
        }

        if (article.getTitle() == null || article.getContents() == null) {
            String errorMessage = "File " + fileName + " cannot be parsed:  title/contents is missing";
            LOGGER.error(errorMessage);
            throw new DAOException(errorMessage);
        }

        Author author = registerAuthorInLocalRepo(article.getAuthor());
        author.getArticles().add(article);
        article.setAuthor(author);

        return article;
    }
}
