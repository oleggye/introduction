package by.epam.dao.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import by.epam.dao.exception.DAOException;
import org.junit.Before;
import org.junit.Test;

public class FileResolverTest {

    private static final String XML_EXTENSION = "xml";
    private static final String JSON_EXTENSION = "json";
    private static final String TXT_EXTENSION = "txt";
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

    private FileResolver fileResolver;

    @Before
    public void setUp() throws Exception {
        fileResolver = new FileResolver();
    }

    @Test
    public void shouldGetAllJsonFileNamesFromDirectory() throws DAOException {
        String[] actualNames = fileResolver.getFileNamesWithExtensionFromDirectory(
            RESOURCE_DIRECTORY_PATH, JSON_EXTENSION);

        assertNotNull(actualNames);
        assertEquals(JSON_FILE_NAMES_FROM_RESOURCE_DIRECTORY.length, actualNames.length);
        assertEquals(actualNames, JSON_FILE_NAMES_FROM_RESOURCE_DIRECTORY);
    }

    @Test
    public void shouldGetAllXmlFileNamesFromDirectory() throws DAOException {
        String[] actualNames = fileResolver.getFileNamesWithExtensionFromDirectory(
            RESOURCE_DIRECTORY_PATH, XML_EXTENSION);

        assertNotNull(actualNames);
        assertEquals(XML_FILE_NAMES_FROM_RESOURCE_DIRECTORY.length, actualNames.length);
        assertEquals(actualNames, XML_FILE_NAMES_FROM_RESOURCE_DIRECTORY);
    }

    @Test
    public void shouldGetAllTxtFileNamesFromDirectory() throws DAOException {
        String[] actualNames = fileResolver.getFileNamesWithExtensionFromDirectory(
            RESOURCE_DIRECTORY_PATH, TXT_EXTENSION);

        assertNotNull(actualNames);
        assertEquals(TXT_FILE_NAMES_FROM_RESOURCE_DIRECTORY.length, actualNames.length);
        assertEquals(actualNames, TXT_FILE_NAMES_FROM_RESOURCE_DIRECTORY);
    }
}
