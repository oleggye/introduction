package by.epam.dao.parse.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

public class XmlParserTest {

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("testData");

    private static final Article firstFileArticle = new ArticleBuilder()
            .setTitle(BUNDLE.getString("article.title.xml.first"))
            .setAuthor(
                    new Author(BUNDLE.getString("article.authorName.xml.first"))
            )
            .setContents(BUNDLE.getString("article.contents.xml.first"))
            .build();

    private static final Article secondFileArticle = new ArticleBuilder()
            .setTitle(BUNDLE.getString("article.title.xml.second"))
            .setAuthor(
                    new Author(BUNDLE.getString("article.authorName.xml.second"))
            )
            .setContents(BUNDLE.getString("article.contents.xml.second"))
            .build();

    private static final Article thirdFileArticle = new ArticleBuilder()
            .setTitle(BUNDLE.getString("article.title.xml.third"))
            .setAuthor(
                    new Author(BUNDLE.getString("article.authorName.xml.third"))
            )
            .setContents(BUNDLE.getString("article.contents.xml.third"))
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
