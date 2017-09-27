package by.epam.dao;

import by.epam.dao.impl.JsonParser;
import by.epam.dao.impl.TxtParser;
import by.epam.dao.impl.XmlParser;

public final class DAOFactory {
    private static final DAOFactory INSTANCE = new DAOFactory();

    private IParser jsonParser = new JsonParser();
    private IParser xmlParser = new XmlParser();
    private IParser txtParser = new TxtParser();

    public static DAOFactory getIntance() {
        return INSTANCE;
    }

    public IParser getParser(ParserType parserType) {
        switch (parserType) {
            case XML:
            return xmlParser;
            case JSON:
                return jsonParser;
            case TXT:
                return txtParser;
            default:
                throw new IllegalArgumentException("Wrong param:" + parserType);
        }
    }
}
