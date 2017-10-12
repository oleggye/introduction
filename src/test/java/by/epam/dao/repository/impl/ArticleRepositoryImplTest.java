package by.epam.dao.repository.impl;

import by.epam.dao.exception.DAOException;
import by.epam.dao.repository.ArticleRepository;
import by.epam.entity.Article;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
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
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED,
        value = "/database/data/expected/add/addFirstArticle.xml")
    @DatabaseTearDown(value = "/database/data/tearDown.xml")
    public void shouldAddFirstArticle() throws DAOException {
        repository.save(firstArticle);
        System.out.println();
    }

    @Test
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED,
        value = "/database/data/expected/add/addSecondArticle.xml")
    @DatabaseTearDown(value = "/database/data/tearDown.xml")
    public void shouldAddSecondArticle() throws DAOException {
        repository.save(secondArticle);
    }

    @Test
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED,
        value = "/database/data/expected/add/addThirdArticle.xml")
    @DatabaseTearDown(value = "/database/data/tearDown.xml")
    public void shouldAddThirdArticle() throws DAOException {
        repository.save(thirdArticle);
    }

    @Test
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED,
        value = "/database/data/expected/add/addArticles.xml")
    @DatabaseTearDown(value = "/database/data/tearDown.xml")
    public void shouldAddThreeArticles() throws DAOException {
        final List<Article> expectedArticles = Arrays.asList(firstArticle, secondArticle, thirdArticle);
        repository.saveAll(expectedArticles);
    }
}