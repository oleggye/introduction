package by.epam.dao.util;

import by.epam.entity.Author;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AuthorCashTest {

    private static final Author DEFAULT_AUTHOR = new Author("Unknown");
    private AuthorCash authorCash;

    @Before
    public void setUp() throws Exception {
        authorCash = new AuthorCash();
    }

    @Test
    public void shouldRegisterOneAuthorInLocalRepo(){
        final Author expectedAuthor = DEFAULT_AUTHOR;
        final Author testAuthor = new Author("Unknown");

        Author actualAuthor1 = authorCash.addAuthorToLocalRepository(expectedAuthor);
        Author actualAuthor2 = authorCash.addAuthorToLocalRepository(testAuthor);

        assertNotNull(actualAuthor1);
        assertNotNull(actualAuthor2);
        assertTrue(actualAuthor1 == expectedAuthor);
        assertTrue(actualAuthor1 != testAuthor);
        assertTrue(actualAuthor2 != testAuthor);
        assertTrue(actualAuthor1 == actualAuthor2);
    }
}