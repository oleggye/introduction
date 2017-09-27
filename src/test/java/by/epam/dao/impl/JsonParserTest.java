package by.epam.dao.impl;

import by.epam.dao.IParser;
import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;
import org.junit.Test;

import java.util.List;

public class JsonParserTest {

    private static final String RESOUCE_DIRECTORY_PATH = "src/main/resources";

    private IParser parser = new JsonParser();

    @Test
    public void testGetArticles() throws DAOException {
        List<Article> list = parser.getArticles(RESOUCE_DIRECTORY_PATH);
        System.out.println(list);
    }
}
