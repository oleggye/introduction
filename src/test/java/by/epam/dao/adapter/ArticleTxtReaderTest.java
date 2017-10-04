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

public class ArticleTxtReaderTest {

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("testData");

    private static final Article firstFileArticle = new ArticleBuilder()
        .setTitle(BUNDLE.getString("article.txt.first.title"))
        .setAuthor(
            new Author(BUNDLE.getString("article.txt.first.authorName"))
        )
        .setContents(BUNDLE.getString("article.txt.first.contents"))
        .build();

    private static final Article secondFileArticle = new ArticleBuilder()
        .setTitle(BUNDLE.getString("article.txt.second.title"))
        .setAuthor(
            new Author(BUNDLE.getString("article.txt.second.authorName"))
        )
        .setContents(BUNDLE.getString("article.txt.second.contents"))
        .build();

    private ArticleReader reader = new ArticleTxtReader();

    @Test
    public void shouldLoadFirstArticleFile() throws ParseException {
        final String fileName = PropertyLoader.getInstance()
            .getString("file.txt.url.first");
        final Article expectedArticle = firstFileArticle;

        Article actualArticle = reader.load(new File(fileName));

        assertNotNull(actualArticle);
        assertEquals(expectedArticle, actualArticle);
        assertEquals(expectedArticle.getTitle(), actualArticle.getTitle());
        assertEquals(expectedArticle.getAuthor(), actualArticle.getAuthor());
        assertEquals(expectedArticle.getContents(), actualArticle.getContents());
    }

    @Test
    public void shouldLoadSecondArticleFile() throws ParseException {
        final String fileName = PropertyLoader.getInstance()
            .getString("file.txt.url.second");
        final Article expectedArticle = secondFileArticle;

        Article actualArticle = reader.load(new File(fileName));

        assertNotNull(actualArticle);
        assertEquals(expectedArticle, actualArticle);
        assertEquals(expectedArticle.getTitle(), actualArticle.getTitle());
        assertEquals(expectedArticle.getAuthor(), actualArticle.getAuthor());
        assertEquals(expectedArticle.getContents(), actualArticle.getContents());
    }
}