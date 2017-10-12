package by.epam.dao.parse.adapter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import by.epam.dao.parse.adapter.exception.ParseException;
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
        .setTitle(null)
        .setAuthor(
            new Author(BUNDLE.getString("article.authorName.xml.third"))
        )
        .setContents(BUNDLE.getString("article.contents.xml.third"))
        .build();

    private ArticleReader reader = new ArticleXmlReader();

    @Test
    public void shouldParseFirstFile() throws ParseException {
        final String fileName = PropertyLoader
            .getInstance().getString("file.xml.url.first");
        final Article expectedArticle = firstFileArticle;

        Article actualArticle = reader.read(new File(fileName));

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

        Article actualArticle = reader.read(new File(fileName));

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

        Article actualArticle = reader.read(new File(fileName));

        assertNotNull(actualArticle);
        assertEquals(expectedArticle, actualArticle);
        assertEquals(expectedArticle.getTitle(), actualArticle.getTitle());
        assertEquals(expectedArticle.getAuthor(), actualArticle.getAuthor());
        assertEquals(expectedArticle.getContents(), actualArticle.getContents());
    }
}