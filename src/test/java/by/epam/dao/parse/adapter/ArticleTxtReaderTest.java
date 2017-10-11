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

public class ArticleTxtReaderTest {

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("testData");

    private static final Article firstFileArticle = new ArticleBuilder()
        .setTitle(BUNDLE.getString("article.txt.title.first"))
        .setAuthor(
            new Author(BUNDLE.getString("article.txt.authorName.first"))
        )
        .setContents(BUNDLE.getString("article.txt.contents.first"))
        .build();

    private static final Article secondFileArticle = new ArticleBuilder()
        .setTitle(BUNDLE.getString("article.txt.title.second"))
        .setAuthor(
            new Author(BUNDLE.getString("article.txt.authorName.second"))
        )
        .setContents(BUNDLE.getString("article.txt.contents.second"))
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