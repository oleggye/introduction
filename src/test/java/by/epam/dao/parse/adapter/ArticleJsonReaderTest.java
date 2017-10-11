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
        .setTitle(BUNDLE.getString("article.json.title.first"))
        .setAuthor(
            new Author(BUNDLE.getString("article.json.authorName.first"))
        )
        .setContents(BUNDLE.getString("article.json.contents.first"))
        .build();

    private static final Article secondFileArticle = new ArticleBuilder()
        .setTitle(BUNDLE.getString("article.json.title.second"))
        .setAuthor(
            new Author(BUNDLE.getString("article.json.authorName.second"))
        )
        .setContents(BUNDLE.getString("article.json.contents.second"))
        .build();

    private static final Article thirdFileArticle = new ArticleBuilder()
        .setTitle(BUNDLE.getString("article.json.title.third"))
        .setAuthor(
            new Author(BUNDLE.getString("article.json.authorName.third"))
        )
        .setContents(BUNDLE.getString("article.json.contents.third"))
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