package by.epam.dao;

import by.epam.dao.parse.Parser;
import by.epam.dao.parse.ParserType;
import by.epam.dao.parse.impl.JsonParser;
import by.epam.dao.parse.impl.TxtParser;
import by.epam.dao.parse.impl.XmlParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DAOFactoryTest {
    private static final DAOFactory factory = DAOFactory.getInstance();

    @Test
    public void shouldGetJsonParser() throws Exception {
        final Class<?> expectedParserClass = JsonParser.class;
        checkReturnParserType(ParserType.JSON, expectedParserClass);
    }

    @Test
    public void shouldGetXmlParser() throws Exception {
        final Class<?> expectedParserClass = XmlParser.class;
        checkReturnParserType(ParserType.XML, expectedParserClass);
    }

    @Test
    public void shouldGetTxtParser() throws Exception {
        final Class<?> expectedParserClass = TxtParser.class;
        checkReturnParserType(ParserType.TXT, expectedParserClass);
    }

    private void checkReturnParserType(ParserType parserType, Class<?> expectedParserClass) {
        Parser parser = factory.getParser(parserType);

        assertNotNull(parser);
        final Class<?> actualParserClass = parser.getClass();
        assertEquals(expectedParserClass, actualParserClass);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenParserTypeInNull() {
        ParserType parserType = null;
        Class<?> stubClass = Object.class;

        checkReturnParserType(parserType, stubClass);
    }
}