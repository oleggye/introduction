package by.epam.dao.repository.impl;

import by.epam.dao.exception.DAOException;
import by.epam.dao.repository.AuthorRepository;
import by.epam.entity.Author;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
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
@ContextConfiguration(locations = "classpath:springTestContext.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
                         DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class,
                         DbUnitTestExecutionListener.class})
public class AuthorRepositoryImplTest {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    @Qualifier("firstAuthor")
    private Author firstAuthor;

    @Autowired
    @Qualifier("secondAuthor")
    private Author secondAuthor;

    @Autowired
    @Qualifier("thirdAuthor")
    private Author thirdAuthor;

    @Test
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED,
        value = "/database/data/expected/author/add/addFirstAuthors.xml")
    @DatabaseTearDown(value = "/database/data/tearDown.xml")
    public void shouldAddFirstAuthor() throws DAOException {
        repository.save(firstAuthor);
    }

    @Test
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED,
        value = "/database/data/expected/author/add/addSecondAuthor.xml")
    @DatabaseTearDown(value = "/database/data/tearDown.xml")
    public void shouldAddSecondAuthor() throws DAOException {
        repository.save(secondAuthor);
    }

    @Test
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED,
        value = "/database/data/expected/author/add/addThirdAuthor.xml")
    @DatabaseTearDown(value = "/database/data/tearDown.xml")
    public void shouldAddThirdAuthor() throws DAOException {
        repository.save(thirdAuthor);
    }

    @Test
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED,
        value = "/database/data/expected/author/add/addAuthors.xml")
    @DatabaseTearDown(value = "/database/data/tearDown.xml")
    public void shouldAddThreeAuthors() throws DAOException {
        final List<Author> expectedAuthors = Arrays.asList(firstAuthor, secondAuthor, thirdAuthor);
        repository.saveAll(expectedAuthors);
    }
}