package by.epam.dao.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import by.epam.entity.Author;
import org.junit.Before;
import org.junit.Test;

public class AuthorCacheTest {

    private static final Author DEFAULT_AUTHOR = new Author("Unknown");
    private AuthorCache authorCache;

    @Before
    public void setUp() throws Exception {
        authorCache = new AuthorCache();
    }

    @Test
    public void shouldRegisterOneAuthorInLocalRepo(){
        final Author expectedAuthor = DEFAULT_AUTHOR;
        final Author testAuthor = new Author("Unknown");

        Author actualAuthor1 = authorCache.cacheAuthor(expectedAuthor);
        Author actualAuthor2 = authorCache.cacheAuthor(testAuthor);

        assertNotNull(actualAuthor1);
        assertNotNull(actualAuthor2);
        assertTrue(actualAuthor1 == expectedAuthor);
        assertTrue(actualAuthor1 != testAuthor);
        assertTrue(actualAuthor2 != testAuthor);
        assertTrue(actualAuthor1 == actualAuthor2);
    }
}