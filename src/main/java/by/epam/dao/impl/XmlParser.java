package by.epam.dao.impl;

import by.epam.dao.adapter.ArticleXmlDeserializer;
import by.epam.dao.adapter.exception.ParseException;
import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;
import by.epam.entity.Author;
import org.slf4j.LoggerFactory;

public class XmlParser extends AbstractParser {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(XmlParser.class);

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
            LOGGER.error("Can't parse file: " + fileName, e);
            throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
        }
    }
}
