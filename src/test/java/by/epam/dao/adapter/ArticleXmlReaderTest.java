package by.epam.dao.adapter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import by.epam.dao.adapter.exception.ParseException;
import by.epam.dao.util.PropertyLoader;
import by.epam.entity.Article;
import by.epam.entity.ArticleBuilder;
import by.epam.entity.Author;
import org.junit.Test;

import java.io.File;
import java.util.ResourceBundle;

public class ArticleXmlReaderTest {

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
        .setTitle(null)
        .setAuthor(
            new Author(BUNDLE.getString("article.xml.third.authorName"))
        )
        .setContents(BUNDLE.getString("article.xml.third.contents"))
        .build();

    private ArticleReader reader = new ArticleXmlReader();

    @Test
    public void shouldParseFirstFile() throws ParseException {
        final String fileName = PropertyLoader
            .getInstance().getString("file.xml.url.first");
        final Article expectedArticle = firstFileArticle;

        Article actualArticle = reader.load(new File(fileName));

        assertNotNull(actualArticle);
        assertEquals(expectedArticle, actualArticle);
        assertEquals(expectedArticle.getTitle(), actualArticle.getTitle());
        assertEquals(expectedArticle.getAuthor(), actualArticle.getAuthor());
        assertEquals(expectedArticle.getContents(), actualArticle.getContents());
    }

    @Test
    public void shouldParseSecondFile() throws ParseException {
        final String fileName = PropertyLoader
            .getInstance().getString("file.xml.url.second");
        final Article expectedArticle = secondFileArticle;

        Article actualArticle = reader.load(new File(fileName));

        assertNotNull(actualArticle);
        assertEquals(expectedArticle, actualArticle);
        assertEquals(expectedArticle.getTitle(), actualArticle.getTitle());
        assertEquals(expectedArticle.getAuthor(), actualArticle.getAuthor());
        assertEquals(expectedArticle.getContents(), actualArticle.getContents());
    }

    @Test
    public void shouldParseThirdFile() throws ParseException {
        final String fileName = PropertyLoader
            .getInstance().getString("file.xml.url.third");
        final Article expectedArticle = thirdFileArticle;

        Article actualArticle = reader.load(new File(fileName));

        assertNotNull(actualArticle);
        assertEquals(expectedArticle, actualArticle);
        assertEquals(expectedArticle.getTitle(), actualArticle.getTitle());
        assertEquals(expectedArticle.getAuthor(), actualArticle.getAuthor());
        assertEquals(expectedArticle.getContents(), actualArticle.getContents());
    }
}