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

public class JsonParserTest {

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("testData");

    private static final Article firstFileArticle = new ArticleBuilder()
            .setTitle(BUNDLE.getString("article.title.json.first"))
            .setAuthor(
                    new Author(BUNDLE.getString("article.authorName.json.first"))
            )
            .setContents(BUNDLE.getString("article.contents.json.first"))
            .build();

    private static final Article secondFileArticle = new ArticleBuilder()
            .setTitle(BUNDLE.getString("article.title.json.second"))
            .setAuthor(
                    new Author(BUNDLE.getString("article.authorName.json.second"))
            )
            .setContents(BUNDLE.getString("article.contents.json.second"))
            .build();

    private static final Article thirdFileArticle = new ArticleBuilder()
            .setTitle(BUNDLE.getString("article.title.json.third"))
            .setAuthor(
                    new Author(BUNDLE.getString("article.authorName.json.third"))
            )
            .setContents(BUNDLE.getString("article.contents.json.third"))
            .build();


    private static final String resourceDirectoryPath = PropertyLoader
            .getInstance()
            .getString("resourceDirectoryPath");

    private Parser parser = new JsonParser();

    @Test
    public void shouldLoadFirstFileArticle() throws DAOException {
        final String fileName = PropertyLoader.getInstance().getString("file.json.url.first");
        final Article expectedArticle = firstFileArticle;

        Article actualArticle = parser.loadArticle(new File(fileName));

        checkArticle(expectedArticle, actualArticle);
    }

    @Test
    public void shouldLoadSecondFileArticle() throws DAOException {
        final String fileName = PropertyLoader.getInstance().getString("file.json.url.second");
        final Article expectedArticle = secondFileArticle;

        Article actualArticle = parser.loadArticle(new File(fileName));

        checkArticle(expectedArticle, actualArticle);
    }

    @Test
    public void shouldLoadThirdFileArticle() throws DAOException {
        final String fileName = PropertyLoader.getInstance().getString("file.json.url.third");
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

    @Test
    public void shouldLoadThreeArticleFiles() throws DAOException {
        final int expectedSize = 3;
        final List<Article> expectedArticleList = Arrays.asList(firstFileArticle, secondFileArticle, thirdFileArticle);

        List<Article> actualArticleList = parser.loadArticles(resourceDirectoryPath);

        assertNotNull(actualArticleList);
        assertEquals(expectedSize, actualArticleList.size());
        assertEquals(expectedArticleList, actualArticleList);
    }
}
