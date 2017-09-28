package by.epam.dao.impl;

import by.epam.dao.IParser;
import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;
import org.junit.Test;

import java.util.List;

public class XmlParserTest {

    private static final String RESOURCE_DIRECTORY_PATH = "src/main/resources";

    private IParser parser = new XmlParser();

    @Test
    public void testGetArticles() throws DAOException {
        List<Article> list = parser.getArticles(RESOURCE_DIRECTORY_PATH);
        System.out.println("---Articles----");
        list.stream().forEach(System.out::println);

        System.out.println("---Authors----");
        list.stream().map(Article::getAuthor).peek(System.out::println).forEach(e->System.out.println(e.getArticles()));
    }
}
