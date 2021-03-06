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

public class TxtParserTest {

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("testData");

    private static final Article firstFileArticle = new ArticleBuilder()
            .setTitle(BUNDLE.getString("article.title.txt.first"))
            .setAuthor(
                    new Author(BUNDLE.getString("article.authorName.txt.first"))
            )
            .setContents(BUNDLE.getString("article.contents.txt.first"))
            .build();

    private static final Article secondFileArticle = new ArticleBuilder()
            .setTitle(BUNDLE.getString("article.title.txt.second"))
            .setAuthor(
                    new Author(BUNDLE.getString("article.authorName.txt.second"))
            )
            .setContents(BUNDLE.getString("article.contents.txt.second"))
            .build();

    private static final String resourceDirectoryPath = PropertyLoader
            .getInstance()
            .getString("resourceDirectoryPath");

    private Parser parser = new TxtParser();

    @Test
    public void shouldLoadFirstFileArticle() throws DAOException {
        final String fileName = PropertyLoader.getInstance().getString("file.txt.url.first");
        final Article expectedArticle = firstFileArticle;

        Article actualArticle = parser.loadArticle(new File(fileName));

        checkArticle(expectedArticle, actualArticle);
    }

    @Test
    public void shouldLoadSecondFileArticle() throws DAOException {
        final String fileName = PropertyLoader.getInstance().getString("file.txt.url.second");
        final Article expectedArticle = secondFileArticle;

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
    public void shouldLoadTwoArticleFiles() throws DAOException {
        final int expectedSize = 2;
        final List<Article> expectedArticleList = Arrays.asList(firstFileArticle, secondFileArticle);

        List<Article> actualArticleList = parser.loadArticles(resourceDirectoryPath);

        assertNotNull(actualArticleList);
        assertEquals(expectedSize, actualArticleList.size());
        assertEquals(expectedArticleList, actualArticleList);
    }
}
