package by.epam.dao.impl;

import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class JsonParser extends AbstractParser {
    private static final String EXTENSION = "json";
    private static final String DAO_EXCEPTION_MESSAGE = "Exception while parsing";

    public JsonParser() {
        super(EXTENSION);
    }

    protected List<Article> parse(String fileName) throws DAOException {

        try {
            JAXBContext context = JAXBContext.newInstance(Article.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Object o = unmarshaller.unmarshal(new File(fileName));
            System.out.println(o);


        } catch (JAXBException e) {
            throw new DAOException(DAO_EXCEPTION_MESSAGE, e);
        }
        return null;
    }
}
