package by.epam.dao;

import by.epam.dao.impl.JsonParser;
import by.epam.dao.impl.TxtParser;
import by.epam.dao.impl.XmlParser;

import java.util.EnumMap;
import java.util.Map;

public final class DAOFactory {
    private static final DAOFactory INSTANCE = new DAOFactory();

    private Map<ParserType, IParser> parserMap;

    private DAOFactory() {
        parserMap = new EnumMap<>(ParserType.class);
        parserMap.put(ParserType.XML, new XmlParser());
        parserMap.put(ParserType.JSON, new JsonParser());
        parserMap.put(ParserType.TXT, new TxtParser());
    }

    public static DAOFactory getInstance() {
        return INSTANCE;
    }

    public IParser getParser(ParserType parserType) {
        IParser parser = parserMap.get(parserType);
        if (parser == null) {
            throw new IllegalArgumentException("No bind such parser:" + parserType);
        }
        return parser;
    }
}
