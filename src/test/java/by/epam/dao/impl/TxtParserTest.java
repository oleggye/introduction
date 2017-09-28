package by.epam.dao.impl;

import by.epam.dao.IParser;
import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;
import org.junit.Test;

import java.util.List;

public class TxtParserTest {

    private static final String RESOURCE_DIRECTORY_PATH = "src/main/resources";

    private IParser parser = new TxtParser();

    @Test
    public void testGetArticles() throws DAOException {
        List<Article> list = parser.getArticles(RESOURCE_DIRECTORY_PATH);
        list.stream().forEach(System.out::println);
    }
}
