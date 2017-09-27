package by.epam.dao.impl;

import by.epam.dao.IParser;
import by.epam.dao.exception.DAOException;
import org.junit.Test;

import java.util.List;

public class XmlParserTest {

    private static final String RESOUCE_DIRECTORY_PATH = "src/main/resources";

    private IParser parser = new XmlParser();

    @Test
    public void testGetArticles() throws DAOException {
        List<Article> list = parser.getArticles(RESOUCE_DIRECTORY_PATH);
        System.out.println("OUTPUT");
        System.out.println(list);
    }
}
