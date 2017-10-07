package by.epam.dao;

import by.epam.dao.parse.Parser;
import by.epam.dao.parse.ParserType;
import by.epam.dao.parse.impl.JsonParser;
import by.epam.dao.parse.impl.TxtParser;
import by.epam.dao.parse.impl.XmlParser;
import by.epam.dao.repository.ArticleRepository;
import by.epam.dao.repository.impl.EclipseLinkArticleRepository;

import java.util.EnumMap;
import java.util.Map;

public class DAOFactory {
    private static final DAOFactory INSTANCE = new DAOFactory();

    private Map<ParserType, Parser> parserMap;

    private DAOFactory() {
        parserMap = new EnumMap<>(ParserType.class);
        parserMap.put(ParserType.XML, new XmlParser());
        parserMap.put(ParserType.JSON, new JsonParser());
        parserMap.put(ParserType.TXT, new TxtParser());
    }

    public static DAOFactory getInstance() {
        return INSTANCE;
    }

    public Parser getParser(ParserType parserType) {
        Parser parser = parserMap.get(parserType);
        if (parser == null) {
            throw new IllegalArgumentException("No bind such parser:" + parserType);
        }
        return parser;
    }

    public ArticleRepository getArticleRepository() {
        return ArticleRepositoryHolder.getInstance();
    }

    private static class ArticleRepositoryHolder {
        private static final ArticleRepository INSTANCE = new EclipseLinkArticleRepository();

        private ArticleRepositoryHolder() {
        }

        public static ArticleRepository getInstance() {
            return ArticleRepositoryHolder.INSTANCE;
        }
    }
}
