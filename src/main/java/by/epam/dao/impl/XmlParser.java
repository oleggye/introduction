package by.epam.dao.impl;

import by.epam.dao.adapter.ArticleXmlDeserializer;
import by.epam.dao.adapter.exception.ParseException;
import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;
import by.epam.entity.Author;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class XmlParser extends AbstractParser {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String EXTENSION = "xml";
    private static final String DAO_EXCEPTION_MESSAGE = "Exception while parsing";

    public XmlParser() {
        super(EXTENSION);
    }

    @Override
    protected Article parse(String fileName) throws DAOException {
        try {
            ArticleXmlDeserializer deserializer = new ArticleXmlDeserializer();
            Article article = deserializer.deserialize(fileName);

            if (article.getTitle() == null || article.getContents() == null) {
                String errorMessage = "File " + fileName + " cannot be parsed:  title/contents is missing";
                LOGGER.error(errorMessage);
                throw new DAOException(errorMessage);
            }

            Author author = registerAuthorInLocalRepo(article.getAuthor());
            article.setAuthor(author);
            author.getArticles().add(article);

            return article;
        } catch (ParseException e) {
            LOGGER.error(e);
            throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
        }
    }
}
