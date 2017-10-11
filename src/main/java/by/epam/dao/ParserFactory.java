package by.epam.dao;

import by.epam.dao.parse.Parser;
import by.epam.dao.parse.ParserType;
import by.epam.dao.parse.impl.JsonParser;
import by.epam.dao.parse.impl.TxtParser;
import by.epam.dao.parse.impl.XmlParser;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class ParserFactory {
    private static final ParserFactory INSTANCE = new ParserFactory();

    private Map<ParserType, Parser> parserMap;

    private ParserFactory() {
        parserMap = new EnumMap<>(ParserType.class);
        parserMap.put(ParserType.XML, new XmlParser());
        parserMap.put(ParserType.JSON, new JsonParser());
        parserMap.put(ParserType.TXT, new TxtParser());
    }

    public static ParserFactory getInstance() {
        return INSTANCE;
    }

    public Parser getParser(ParserType parserType) {
        Parser parser = parserMap.get(parserType);
        if (parser == null) {
            throw new IllegalArgumentException("No bind such parser:" + parserType);
        }
        return parser;
    }
}
