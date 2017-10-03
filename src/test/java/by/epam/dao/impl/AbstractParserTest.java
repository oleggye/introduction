package by.epam.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;
import by.epam.entity.Author;
import org.junit.Test;

public class AbstractParserTest {
    private static final String EXTENSION = "xml";
    private static final String RESOURCE_DIRECTORY_PATH = "src/main/resources/files";

    private static final String[] JSON_FILE_NAMES_FROM_RESOURCE_DIRECTORY =
            {"src\\main\\resources\\files\\Article1.json",
                    "src\\main\\resources\\files\\Article4.json",
                    "src\\main\\resources\\files\\Article6.json"};
    private static final String[] XML_FILE_NAMES_FROM_RESOURCE_DIRECTORY =
            {"src\\main\\resources\\files\\Article2.xml",
                    "src\\main\\resources\\files\\Article3.xml",
                    "src\\main\\resources\\files\\Article5.xml"};
    private static final String[] TXT_FILE_NAMES_FROM_RESOURCE_DIRECTORY =
            {"src\\main\\resources\\files\\Article7.txt",
                    "src\\main\\resources\\files\\Article8.txt"};

    private static final Author DEFAULT_AUTHOR = new Author("Unknown");

    private AbstractParser parser = new AbstractParser(EXTENSION) {
        @Override
        protected Article parse(String name) throws DAOException {
            return new Article();
        }
    };

    @Test
    public void shouldGetAllJsonFileNamesFromDirectory() throws DAOException {
        final String EXTENSION = "json";
        String[] actualNames = AbstractParser.FileResolver
                .getFileNamesWithExtensionFromDirectory(
                        RESOURCE_DIRECTORY_PATH, EXTENSION);

        assertNotNull(actualNames);
        assertEquals(JSON_FILE_NAMES_FROM_RESOURCE_DIRECTORY.length,actualNames.length);
        assertEquals(actualNames, JSON_FILE_NAMES_FROM_RESOURCE_DIRECTORY);
    }

    @Test
    public void shouldGetAllXmlFileNamesFromDirectory() throws DAOException {
        final String EXTENSION = "xml";
        String[] actualNames = AbstractParser.FileResolver
                .getFileNamesWithExtensionFromDirectory(
                        RESOURCE_DIRECTORY_PATH, EXTENSION);

        assertNotNull(actualNames);
        assertEquals(XML_FILE_NAMES_FROM_RESOURCE_DIRECTORY.length,actualNames.length);
        assertEquals(actualNames, XML_FILE_NAMES_FROM_RESOURCE_DIRECTORY);
    }

    @Test
    public void shouldGetAllTxtFileNamesFromDirectory() throws DAOException {
        final String EXTENSION = "txt";
        String[] actualNames = AbstractParser.FileResolver
                .getFileNamesWithExtensionFromDirectory(
                        RESOURCE_DIRECTORY_PATH, EXTENSION);

        assertNotNull(actualNames);
        assertEquals(TXT_FILE_NAMES_FROM_RESOURCE_DIRECTORY.length,actualNames.length);
        assertEquals(actualNames, TXT_FILE_NAMES_FROM_RESOURCE_DIRECTORY);
    }

    @Test
    public void shouldRegisterOneAuthorInLocalRepo(){
        final Author expectedAuthor = DEFAULT_AUTHOR;
        final Author testAuthor = new Author("Unknown");

        Author actualAuthor1 = parser.registerAuthorInLocalRepo(expectedAuthor);
        Author actualAuthor2 = parser.registerAuthorInLocalRepo(testAuthor);

        assertNotNull(actualAuthor1);
        assertNotNull(actualAuthor2);
        assertTrue(actualAuthor1 == expectedAuthor);
        assertTrue(actualAuthor1 != testAuthor);
        assertTrue(actualAuthor2 != testAuthor);
        assertTrue(actualAuthor1 == actualAuthor2);
    }
}
