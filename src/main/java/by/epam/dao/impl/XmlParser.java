package by.epam.dao.impl;

import by.epam.dao.adapter.ArticleXmlDeserializer;
import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;
import by.epam.entity.Author;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XmlParser extends AbstractParser {
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

            //TODO:check according business logic
            Author author = registerAuthorInLocalRepo(article.getAuthor());
            article.setAuthor(author);
            author.getArticles().add(article);

            return article;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
        }
    }
}
