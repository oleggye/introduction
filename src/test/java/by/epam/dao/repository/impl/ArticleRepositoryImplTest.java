package by.epam.dao.repository.impl;

import by.epam.dao.exception.DAOException;
import by.epam.dao.repository.ArticleRepository;
import by.epam.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springTestContext.xml"/*, classes = {SpringTestConfig.class}*/)
public class ArticleRepositoryImplTest {

    @Autowired
    private ArticleRepository repository;

    @Autowired
    private Article firstArticle;

    @Autowired
    private Article secondArticle;

    @Autowired
    private Article thirdArticle;

    @Test
    public void shouldAddFirstArticle() throws DAOException {
        shouldAddArticle(firstArticle);
    }

    @Test
    public void shouldAddSecondArticle() throws DAOException {
        shouldAddArticle(secondArticle);
    }

    @Test
    public void shouldAddThirdArticle() throws DAOException {
        shouldAddArticle(thirdArticle);
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
    //FIXME: better do it separately (drop database state before this test)
    public void shouldAddThreeArticles() throws DAOException {
        final List<Article> expectedArticles = Arrays.asList(firstArticle, secondArticle, thirdArticle);

        repository.addAll(expectedArticles);

        Article firstActualArticle = repository.getById(firstArticle.getTitle());
        Article secondActualArticle = repository.getById(secondArticle.getTitle());
        Article thirdActualArticle = repository.getById(thirdArticle.getTitle());

        checkArticle(firstArticle, firstActualArticle);
        checkArticle(secondArticle, secondActualArticle);
        checkArticle(thirdActualArticle, thirdActualArticle);
    }
}