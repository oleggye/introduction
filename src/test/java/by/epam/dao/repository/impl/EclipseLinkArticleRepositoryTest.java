package by.epam.dao.repository.impl;

import by.epam.dao.exception.DAOException;
import by.epam.dao.repository.ArticleRepository;
import by.epam.dao.util.TransactionManager;
import by.epam.entity.Article;
import by.epam.entity.ArticleBuilder;
import by.epam.entity.Author;
import org.junit.*;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

public class EclipseLinkArticleRepositoryTest {

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


    private ArticleRepository repository = new EclipseLinkArticleRepository();

    @Test
    public void shouldAddFirstArticle() throws DAOException {
        shouldAddArticle(firstFileArticle);
    }

    @Test
    public void shouldAddSecondArticle() throws DAOException {
        shouldAddArticle(secondFileArticle);
    }

    @Test
    public void shouldAddThirdArticle() throws DAOException {
        shouldAddArticle(thirdFileArticle);
    }

    private void shouldAddArticle(Article expectedArticle) throws DAOException {
        final String expectedTitle = expectedArticle.getTitle();

        repository.add(expectedArticle);

        Article actualArticle = repository.getById(expectedTitle);
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
    //FIXME: better do it separately (drop db state before this test)
    public void shouldAddThreeArticles() throws DAOException {
        final List<Article> expectedArticles = Arrays.asList(firstFileArticle, secondFileArticle, thirdFileArticle);

        repository.addAll(expectedArticles);

        Article firstActualArticle = repository.getById(firstFileArticle.getTitle());
        Article secondActualArticle = repository.getById(secondFileArticle.getTitle());
        Article thirdActualArticle = repository.getById(thirdFileArticle.getTitle());

        checkArticle(firstFileArticle, firstActualArticle);
        checkArticle(secondFileArticle, secondActualArticle);
        checkArticle(thirdActualArticle, thirdActualArticle);
    }
}