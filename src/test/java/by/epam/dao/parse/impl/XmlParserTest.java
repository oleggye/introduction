package by.epam.dao.parse.impl;

import by.epam.dao.exception.DAOException;
import by.epam.dao.parse.Parser;
import by.epam.dao.util.PropertyLoader;
import by.epam.entity.Article;
import by.epam.entity.ArticleBuilder;
import by.epam.entity.Author;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class XmlParserTest {

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("testData");

    private static final Article firstFileArticle = new ArticleBuilder()
            .setTitle(BUNDLE.getString("article.xml.first.title"))
            .setAuthor(
                    new Author(BUNDLE.getString("article.xml.first.authorName"))
            )
            .setContents(BUNDLE.getString("article.xml.first.contents"))
            .build();

    private static final Article secondFileArticle = new ArticleBuilder()
            .setTitle(BUNDLE.getString("article.xml.second.title"))
            .setAuthor(
                    new Author(BUNDLE.getString("article.xml.second.authorName"))
            )
            .setContents(BUNDLE.getString("article.xml.second.contents"))
            .build();

    private static final Article thirdFileArticle = new ArticleBuilder()
            .setTitle(BUNDLE.getString("article.xml.third.title"))
            .setAuthor(
                    new Author(BUNDLE.getString("article.xml.third.authorName"))
            )
            .setContents(BUNDLE.getString("article.xml.third.contents"))
            .build();

    private static final String resourceDirectoryPath = PropertyLoader
            .getInstance()
            .getString("resourceDirectoryPath");

    private Parser parser = new XmlParser();

    @Test
    public void shouldParseFirstFile() throws DAOException {
        final String fileName = PropertyLoader
                .getInstance().getString("file.xml.url.first");
        final Article expectedArticle = firstFileArticle;

        Article actualArticle = parser.loadArticle(new File(fileName));

        checkArticle(expectedArticle, actualArticle);
    }

    @Test
    public void shouldParseSecondFile() throws DAOException {
        final String fileName = PropertyLoader
                .getInstance().getString("file.xml.url.second");
        final Article expectedArticle = secondFileArticle;

        Article actualArticle = parser.loadArticle(new File(fileName));

        checkArticle(expectedArticle, actualArticle);
    }

    @Test(expected = DAOException.class)
    public void shouldThrowDAOExceptionWhenParseThirdFile() throws DAOException {
        final String fileName = PropertyLoader
                .getInstance().getString("file.xml.url.third");
        final Article expectedArticle = thirdFileArticle;

        Article actualArticle = parser.loadArticle(new File(fileName));

        checkArticle(expectedArticle, actualArticle);
    }

    private void checkArticle(Article expectedArticle, Article actualArticle) {
        assertNotNull(actualArticle);
        assertEquals(expectedArticle, actualArticle);
        assertEquals(expectedArticle.getTitle(), actualArticle.getTitle());
        assertEquals(expectedArticle.getAuthor(), actualArticle.getAuthor());
        assertEquals(expectedArticle.getContents(), actualArticle.getContents());
    }

    @Test(expected = DAOException.class)
    public void shouldThrowDAOExceptionWhenLoadThreeArticleFiles() throws DAOException {
        final int expectedSize = 3;
        final List<Article> expectedArticleList = Arrays.asList(firstFileArticle, secondFileArticle, thirdFileArticle);

        List<Article> actualArticleList = parser.loadArticles(resourceDirectoryPath);

        assertNotNull(actualArticleList);
        assertEquals(expectedSize, actualArticleList.size());
        assertEquals(expectedArticleList, actualArticleList);
    }
}
