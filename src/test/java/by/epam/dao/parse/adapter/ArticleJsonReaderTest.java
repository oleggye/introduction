package by.epam.dao.parse.adapter;

import by.epam.dao.parse.adapter.exception.ParseException;
import by.epam.dao.util.PropertyLoader;
import by.epam.entity.Article;
import by.epam.entity.ArticleBuilder;
import by.epam.entity.Author;
import org.junit.Test;

import java.io.File;
import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ArticleJsonReaderTest {

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("testData");

    private static final Article firstFileArticle = new ArticleBuilder()
        .setTitle(BUNDLE.getString("article.json.first.title"))
        .setAuthor(
            new Author(BUNDLE.getString("article.json.first.authorName"))
        )
        .setContents(BUNDLE.getString("article.json.first.contents"))
        .build();

    private static final Article secondFileArticle = new ArticleBuilder()
        .setTitle(BUNDLE.getString("article.json.second.title"))
        .setAuthor(
            new Author(BUNDLE.getString("article.json.second.authorName"))
        )
        .setContents(BUNDLE.getString("article.json.second.contents"))
        .build();

    private static final Article thirdFileArticle = new ArticleBuilder()
        .setTitle(BUNDLE.getString("article.json.third.title"))
        .setAuthor(
            new Author(BUNDLE.getString("article.json.third.authorName"))
        )
        .setContents(BUNDLE.getString("article.json.third.contents"))
        .build();

    private ArticleReader reader = new ArticleJsonReader();

    @Test
    public void shouldLoadFirstFileArticle() throws ParseException {
        final String fileName = PropertyLoader.getInstance()
            .getString("file.json.url.first");
        final Article expectedArticle = firstFileArticle;

        Article actualArticle = reader.load(new File(fileName));

        assertNotNull(actualArticle);
        assertEquals(expectedArticle, actualArticle);
        assertEquals(expectedArticle.getTitle(), actualArticle.getTitle());
        assertEquals(expectedArticle.getAuthor(), actualArticle.getAuthor());
        assertEquals(expectedArticle.getContents(), actualArticle.getContents());
    }

    @Test
    public void shouldLoadSecondFileArticle() throws ParseException {
        final String fileName = PropertyLoader.getInstance()
            .getString("file.json.url.second");
        final Article expectedArticle = secondFileArticle;

        Article actualArticle = reader.load(new File(fileName));

        assertNotNull(actualArticle);
        assertEquals(expectedArticle, actualArticle);
        assertEquals(expectedArticle.getTitle(), actualArticle.getTitle());
        assertEquals(expectedArticle.getAuthor(), actualArticle.getAuthor());
        assertEquals(expectedArticle.getContents(), actualArticle.getContents());
    }

    @Test
    public void shouldLoadThirdFileArticle() throws ParseException {
        final String fileName = PropertyLoader.getInstance()
            .getString("file.json.url.third");
        final Article expectedArticle = thirdFileArticle;

        Article actualArticle = reader.load(new File(fileName));

        assertNotNull(actualArticle);
        assertEquals(expectedArticle, actualArticle);
        assertEquals(expectedArticle.getTitle(), actualArticle.getTitle());
        assertEquals(expectedArticle.getAuthor(), actualArticle.getAuthor());
        assertEquals(expectedArticle.getContents(), actualArticle.getContents());
    }
}