package by.epam.dao.repository.impl;

import by.epam.dao.exception.DAOException;
import by.epam.dao.repository.ArticleRepository;
import by.epam.entity.Article;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springTestContext.xml"/*, classes = {SpringTestConfig.class}*/)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class ArticleRepositoryImplTest {

    @Autowired
    private ArticleRepository repository;

    @Autowired
    @Qualifier("firstArticle")
    private Article firstArticle;

    @Autowired
    @Qualifier("secondArticle")
    private Article secondArticle;

    @Autowired
    @Qualifier("thirdArticle")
    private Article thirdArticle;

    @Test
/*    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED,
            value = "/data/expected/cancellation/addCancellation.xml")*/
    @DatabaseTearDown(type = DatabaseOperation.CLEAN_INSERT)
    public void shouldAddFirstArticle() throws DAOException {
        shouldAddArticle(firstArticle);
    }

    @Test
    @DatabaseTearDown(type = DatabaseOperation.CLEAN_INSERT)
    public void shouldAddSecondArticle() throws DAOException {
        shouldAddArticle(secondArticle);
    }

    @Test
    @DatabaseTearDown(type = DatabaseOperation.CLEAN_INSERT)
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
   /* @ExpectedDatabase()*/
    @DatabaseTearDown(type = DatabaseOperation.CLEAN_INSERT)
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