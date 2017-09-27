package by.epam.dao.impl;

import by.epam.dao.exception.DAOException;
import by.epam.entity.Article;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AbstractParserTest {
    private static final String RESOURCE_DIRECTORY_PATH = "src/main/resources";

    private AbstractParser parser = new AbstractParser() {
        @Override
        protected List<Article> parse(String name) throws DAOException {
            return Collections.EMPTY_LIST;
        }
    };

    @Test
    public void getAllFileNamesFromDirectoryTest() throws DAOException {
        final String EXTENSION = "json";
        String[] names = parser.getAllFileNamesFromDirectory(RESOURCE_DIRECTORY_PATH, EXTENSION);

        System.out.println(Arrays.deepToString(names));
    }
}
