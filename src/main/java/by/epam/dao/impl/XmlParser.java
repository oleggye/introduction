package by.epam.dao.impl;

import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;
import by.epam.entity.Author;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlParser extends AbstractParser {
    private static final String EXTENSION = "xml";
    private static final String DAO_EXCEPTION_MESSAGE = "Exception while parsing";

    public XmlParser() {
        super(EXTENSION);
    }

    @Override
    protected Article parse(String fileName) throws DAOException {
        try {
            JAXBContext context = JAXBContext.newInstance(Article.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Article article = (Article) unmarshaller.unmarshal(new File(fileName));

            Author author = registerAuthorInLocalRepo(article.getAuthor());
            article.setAuthor(author);
            author.getArticles().remove(article);
            author.getArticles().add(article);


           return article;
        } catch (JAXBException e) {
            throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
        }
    }
}
