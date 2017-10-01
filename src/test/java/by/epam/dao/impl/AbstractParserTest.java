package by.epam.dao.impl;

import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;
import org.junit.Test;

import java.util.Arrays;

public class AbstractParserTest {
    private static final String EXTENSION = "xml";
    private static final String RESOURCE_DIRECTORY_PATH = "src/main/resources";

    private AbstractParser parser = new AbstractParser(EXTENSION) {
        @Override
        protected Article parse(String name) throws DAOException {
            return new Article();
        }
    };

    @Test
    public void getAllFileNamesFromDirectoryTest() throws DAOException {
        final String EXTENSION = "json";
        String[] names = AbstractParser.FileResolver
                                        .getFileNamesWithExtensionFromDirectory(
                                                RESOURCE_DIRECTORY_PATH, EXTENSION);

        System.out.println(Arrays.deepToString(names));
    }
}
