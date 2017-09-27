package by.epam.dao.impl;

import by.epam.dao.exception.DAOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class XmlParser extends AbstractParser {
    private static final String EXTENSION = "xml";
    private static final String DAO_EXCEPTION_MESSAGE = "Exception while parsing";

    public XmlParser() {
        super(EXTENSION);
    }

    @Override
    protected List<Article> parse(String fileName) throws DAOException {
        List<Article> articles = new LinkedList<>();
        try {
            JAXBContext context = JAXBContext.newInstance(Article.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Object o = unmarshaller.unmarshal(new File(fileName));
            articles.add((Article) o);
        } catch (JAXBException e) {
            throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
        }
        return articles;
    }
}
